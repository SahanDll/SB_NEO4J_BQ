package com.dev.db.data.sql.fmt.service.impl;

import com.dev.db.data.sql.fmt.bean.alert.AlertFireLog;
import com.dev.db.data.sql.fmt.repository.AlertFiredLogRepository;
import com.dev.db.data.sql.fmt.service.inte.AlertFiredLogService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by N5608296 on 14/09/17.
 */
@Service
public class AlertFiredLogServiceImpl implements AlertFiredLogService {
    private static final Logger logger = LoggerFactory.getLogger(AlertFiredLogServiceImpl.class);
    @Autowired
    AlertFiredLogRepository repository;

    public JSONObject getAlertFired(String type, Pageable pageable) {
        JSONArray result = new JSONArray();
        JSONObject main = new JSONObject();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        try {

            for (AlertFireLog af : repository.findByAlertTypeAndTriggerTimeBetweenOrderByLogIdDesc(type, cal.getTime(), new Date(), pageable)) {
                JSONObject map = new JSONObject();
                map.put("logId", af.getLogId());
                map.put("title", af.getTitle());
                map.put("shortContent", af.getShortContent());
                map.put("fullContent", af.getFullContent());
                map.put("nric", af.getClntNric());
                map.put("stockName", af.getStkName());
                map.put("alertSource", af.getAlertSource());
                map.put("entity", af.getEntity());
                map.put("stockCode", af.getStkCode());
                map.put("triggerTime", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(af.getTriggerTime()));
                result.add(map);
            }

        } catch (Exception e) {
            logger.error("Service : ", e);
        }

        try {
            if(0 == pageable.getPageNumber()){
                main.put("totalRows", repository.countByAlertTypeAndTriggerTimeBetweenOrderByLogIdDesc(type, cal.getTime(), new Date()));
            }
        } catch (Exception e) {
            logger.error("Service : ", e);
        }
        main.put("result", result);
        return main;
    }

    public JSONObject getAlertFiredCount() {
        JSONArray result = new JSONArray();
        JSONObject main = new JSONObject();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        int total = 0;
        int portfolio = 0;
        int watchlist = 0;
        int news = 0;
        int payment = 0;
        int entitlement = 0;
        int mkeInsight = 0;
        int system = 0;
        int jill = 0;
        try {
            for (AlertFireLog af : repository.findAllByTriggerTimeGreaterThanOrderByAlertTypeDesc(cal.getTime())) {
                try {
                    total++;
                    switch (af.getAlertType()) {
                        case "P":
                            portfolio++;
                            break;
                        case "W":
                            watchlist++;
                            break;
                        case "N":
                            news++;
                            break;
                        case "C":
                            payment++;
                            break;
                        case "E":
                            entitlement++;
                            break;
                        case "I":
                            mkeInsight++;
                            break;
                        case "S":
                            system++;
                            break;
                        case "J":
                            jill++;
                            break;
                    }
                } catch (Exception e) {
                    logger.error("Service : ", e);
                }
            }
            JSONObject map = new JSONObject();
            map.put("total", total);
            map.put("portfolio", portfolio);
            map.put("watchlist", watchlist);
            map.put("news", news);
            map.put("payment", payment);
            map.put("entitlement", entitlement);
            map.put("mkeInsight", mkeInsight);
            map.put("system", system);
            map.put("jill", jill);
            result.add(map);

        } catch (Exception e) {
            logger.error("Service : ", e);
        }

        main.put("result", result);
        return main;
    }

}
