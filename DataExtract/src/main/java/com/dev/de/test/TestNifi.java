package com.dev.de.test;

import com.dev.de.nifi.OutputPort;
import org.apache.nifi.storm.NiFiDataPacket;

import java.util.concurrent.LinkedBlockingQueue;

public class TestNifi {
    public static void main(String[] args) {
        LinkedBlockingQueue queue = OutputPort.fetchData("test");
        if (null != queue) {
            while (queue.size() > 0){
                NiFiDataPacket data = (NiFiDataPacket) queue.poll();
                System.out.println(data);
            }
        }
    }
}
