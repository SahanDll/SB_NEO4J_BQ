package com.dev.ds.sync.fetch;

import com.dev.ds.sync.service.UserDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UserDataFetch {
    private static final Logger logger = LoggerFactory.getLogger(UserDataFetch.class);
    @Autowired
    UserDataService userDataService;

    //@Scheduled(fixedRate=10000)
    //@Scheduled(cron = "0 0 2 * * *")
    public void syncFmtData(){
/*        String apiUrl = "http://localhost:8381/fmt-sentinel/api/data-sync/user-data";
        RestTemplate restTemplate = new RestTemplate();
        try {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            Map<String,String> map = new HashMap<String, String>();
            map.put("Content-Type", "application/json");
            map.put("Authorization", Common.authenticate());
            headers.setAll(map);
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<JSONObject> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, JSONObject.class);
            logger.info("Result - status ("+ response.getStatusCode() + ") has body: " + response.hasBody()+" -> "+response.getBody());

        }
        catch (Exception e) {
            logger.error("Exception: "+ e.getMessage());
        }*/

        try {
            logger.info("==========================================================================");
            logger.info("Sync Alert - Setting Master : " + userDataService.syncSettingMasterData());
            logger.info("Sync Alert - Alert Fire Log : " + userDataService.syncAlertData());
            logger.info("Sync Smf - Portfolio : " + userDataService.syncSmfPortfolioData());
            logger.info("Sync Smf - Watchlist : " + userDataService.syncSmfWatchlistData());
            logger.info("Sync Ecos - Portfolio : " + userDataService.syncEcosPortfolioData());
            logger.info("Sync Ecos - Watchlist : " + userDataService.syncEcosWatchlistData());
            logger.info("==========================================================================");
            logger.info("\n");
            //System.out.println(userDataService.getSettingMasterByIdAndNric((long) 3, "N123452"));
        } catch (Exception e) {
            logger.error("Exception: "+ e.getMessage());
        }

        System.gc();
    }
}
