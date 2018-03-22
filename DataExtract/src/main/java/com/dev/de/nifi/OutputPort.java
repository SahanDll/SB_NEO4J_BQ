package com.dev.de.nifi;

import com.dev.de.util.IConstants;
import org.apache.nifi.remote.Transaction;
import org.apache.nifi.remote.TransferDirection;
import org.apache.nifi.remote.client.KeystoreType;
import org.apache.nifi.remote.client.SiteToSiteClient;
import org.apache.nifi.remote.client.SiteToSiteClientConfig;
import org.apache.nifi.remote.protocol.DataPacket;
import org.apache.nifi.remote.protocol.SiteToSiteTransportProtocol;
import org.apache.nifi.storm.NiFiDataPacket;
import org.apache.nifi.stream.io.StreamUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
@Service
public class OutputPort {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OutputPort.class);
    private static SiteToSiteClientConfig clientConfig;
    private static LinkedBlockingQueue queue;

    //@Scheduled(fixedDelayString = "${const.bq.delay}", initialDelay = 1000L)
    public void load() {
        LinkedBlockingQueue queue = fetchData("testPrice");
        if (null != queue) {
            while (queue.size() > 0){
                NiFiDataPacket data = (NiFiDataPacket) queue.poll();
                JSONObject jsonObjMatch;
                JSONObject jsonObjData;
                try {
                    jsonObjMatch = (JSONObject) new JSONParser().parse(data.getAttributes().get("match"));
                    jsonObjData = (JSONObject) new JSONParser().parse(jsonObjMatch.get("data").toString());
                } catch (Exception e) {
                    logger.error("Error ", e);
                    continue;
                }
                logger.info(jsonObjData.toString());
            }
            try {
                String anim= "|/-\\";
                String text = "\t Refreshing data grid.... ";
                String end = "\r ";
                System.out.write(text.getBytes());
                for (int x =0 ; x < 100 ; x++) {
                    String d = "\r" + anim.charAt(x % anim.length()) + " " + x;
                    System.out.write(d.getBytes());
                    Thread.sleep(100);
                }
                System.out.write(end.getBytes());
            } catch (IOException e) {
                logger.error("Error ", e);
            } catch (InterruptedException e) {
                logger.error("Error ", e);
            }
        }
    }


    public static LinkedBlockingQueue fetchData(String portName) {
        try {
            queue = new LinkedBlockingQueue<>(100000);
            clientConfig = new SiteToSiteClient.Builder()
                    .url(IConstants.NIFI_URL)
                    .portName(portName)
                    .transportProtocol(SiteToSiteTransportProtocol.HTTP)
                    .keystoreFilename(IConstants.KEYSTORE)
                    .keystorePass(IConstants.KEYSTORE_PASS)
                    .keystoreType(KeystoreType.valueOf(IConstants.KEYSTORE_TYPE))
                    .truststoreFilename(IConstants.TRUSTSTORE)
                    .truststorePass(IConstants.TRUSTSTORE_PASS)
                    .truststoreType(KeystoreType.valueOf(IConstants.TRUSTSTORE_TYPE))
                    .buildConfig();

            fetch();
        } catch (Exception e) {
            logger.error("Error ", e);
        }
        return queue;
    }


    private static void fetch() {
        try {
            SiteToSiteClient client = new SiteToSiteClient.Builder().fromConfig(clientConfig).build();
            //HttpClient client = new HttpClient(clientConfig);
            try {
                Transaction transaction = null;
                try {
                    transaction = client.createTransaction(TransferDirection.RECEIVE);
                    if (transaction == null) {
                        return;
                    }
                } catch (Exception e) {
                    logger.error("Error ", e);
                    return;
                }
                DataPacket dataPacket = null;
                try {
                    dataPacket = transaction.receive();
                } catch (IOException e) {
                    logger.error("Error ", e);
                }
                if (dataPacket == null) {
                    transaction.confirm();
                    transaction.complete();
                    return;
                }

                final List<NiFiDataPacket> dataPackets = new ArrayList<>();
                do {
                    final InputStream inStream = dataPacket.getData();
                    final byte[] data = new byte[(int) dataPacket.getSize()];
                    StreamUtils.fillBuffer(inStream, data);

                    final Map attributes = dataPacket.getAttributes();
                    final NiFiDataPacket niFiDataPacket = new NiFiDataPacket() {
                        @Override
                        public byte[] getContent() {
                            return data;
                        }

                        @Override
                        public Map getAttributes() {
                            return attributes;
                        }
                    };
                    dataPacket = transaction.receive();
                    dataPackets.add(niFiDataPacket);

                } while (dataPacket != null);
                transaction.confirm();

                for (NiFiDataPacket dp : dataPackets) {
                    queue.offer(dp);
                }
                transaction.complete();
            } finally {
                try {
                    client.close();
                } catch (final IOException ioe) {
                    logger.error("Error ", ioe);
                }
            }
        } catch (final IOException ioe) {
            logger.error("Error ", ioe);
        }
    }

}
