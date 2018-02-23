package com.dev.bigquery;

import java.io.IOException;

/**
 * Created by N5608296 on 26/01/2018 026.
 */
public class IosQueryFactory {
    private static IosQueryFactory self;

    private IosQueryFactory(){

    }

    public static IosQueryFactory getInstance() throws IOException {
        if (self == null) {
            self = new IosQueryFactory();
        }

        return self;
    }

    public String generateQuery(int code, String date, int records){
        String query;
        switch (code) {
            case 1:
                query = "SELECT" +
                        " user_dim.user_id," +//0
                        " user_dim.device_info.device_category," +//1
                        " user_dim.device_info.mobile_brand_name," +//2
                        " user_dim.device_info.mobile_model_name," +//3
                        " user_dim.device_info.mobile_marketing_name," +//4
                        " user_dim.device_info.device_model," +//5
                        " user_dim.device_info.platform_version," +//6
                        " user_dim.device_info.device_id," +//7
                        " user_dim.device_info.resettable_device_id," +//8
                        " user_dim.device_info.user_default_language," +//9
                        " user_dim.device_info.device_time_zone_offset_seconds," +//10
                        " user_dim.device_info.limited_ad_tracking" +//11
                        " FROM [com_mayBank_MKETradeUAT_IOS.app_events_"+date+"]" +
                        " WHERE (user_dim.user_id <> '-1'" +
                        " AND user_dim.user_id <> 'null')" +
                        " LIMIT "+records;
                break;
            case 2:
                query = "SELECT" +
                        " user_dim.user_id," +//0
                        " user_dim.device_info.device_category," +//1
                        " user_dim.device_info.mobile_brand_name," +//2
                        " user_dim.device_info.mobile_model_name," +//3
                        " user_dim.device_info.mobile_marketing_name," +//4
                        " user_dim.device_info.device_model," +//5
                        " user_dim.device_info.platform_version," +//6
                        " user_dim.device_info.device_id," +//7
                        " user_dim.device_info.resettable_device_id," +//8
                        " user_dim.device_info.user_default_language," +//9
                        " user_dim.device_info.device_time_zone_offset_seconds," +//10
                        " user_dim.device_info.limited_ad_tracking" +//11
                        " FROM [com_mayBank_MKETradeUAT_IOS.app_events_"+date+"]" +
                        " WHERE (user_dim.user_id <> '-1'" +
                        " AND user_dim.user_id <> 'null')" ;
                break;
            case 3:
                query = "SELECT" +
                        " user_dim.user_id," +//0
                        " user_dim.geo_info.continent," +//1
                        " user_dim.geo_info.country," +//2
                        " user_dim.geo_info.region," +//3
                        " user_dim.geo_info.city" +//4
                        " FROM [com_mayBank_MKETradeUAT_IOS.app_events_"+date+"]" +
                        " WHERE (user_dim.user_id <> '-1'" +
                        " AND user_dim.user_id <> 'null')" +
                        " LIMIT "+records;
                break;
            case 4:
                query = "SELECT" +
                        " user_dim.user_id," +//0
                        " user_dim.geo_info.continent," +//1
                        " user_dim.geo_info.country," +//2
                        " user_dim.geo_info.region," +//3
                        " user_dim.geo_info.city" +//4
                        " FROM [com_mayBank_MKETradeUAT_IOS.app_events_"+date+"]"+
                        " WHERE (user_dim.user_id <> '-1'" +
                        " AND user_dim.user_id <> 'null')";
                break;
            case 5:
                query = "SELECT" +
                        " user_dim.user_id," +//0
                        " user_dim.app_info.app_version," +//1
                        " user_dim.app_info.app_instance_id," +//2
                        " user_dim.app_info.app_store," +//3
                        " user_dim.app_info.app_platform," +//4
                        " user_dim.app_info.app_id" +//5
                        " FROM [com_mayBank_MKETradeUAT_IOS.app_events_"+date+"]" +
                        " WHERE (user_dim.user_id <> '-1'" +
                        " AND user_dim.user_id <> 'null')" +
                        " LIMIT "+records;
                break;
            case 6:
                query = "SELECT" +
                        " user_dim.user_id," +//0
                        " user_dim.app_info.app_version," +//1
                        " user_dim.app_info.app_instance_id," +//2
                        " user_dim.app_info.app_store," +//3
                        " user_dim.app_info.app_platform," +//4
                        " user_dim.app_info.app_id" +//5
                        " FROM [com_mayBank_MKETradeUAT_IOS.app_events_"+date+"]" +
                        " WHERE (user_dim.user_id <> '-1'" +
                        " AND user_dim.user_id <> 'null')";
                break;
            case 7:
                query = "SELECT" +
                        " user_dim.user_id," +
                        " event_dim.name," +
                        " event_dim.params.key," +
                        " event_dim.params.value.string_value," +
                        " event_dim.timestamp_micros" +
                        " FROM [com_mayBank_MKETradeUAT_IOS.app_events_"+date+"]" +
                        " WHERE user_dim.user_id <> '-1'" +
                        " AND user_dim.user_id <> 'null'" +
                        " AND event_dim.name= 'screen_view'" +
                        " AND (event_dim.params.key = 'firebase_previous_screen' OR event_dim.params.key = 'firebase_screen')" +
                        " ORDER BY user_dim.user_id desc, event_dim.timestamp_micros asc" +
                        " LIMIT "+records;
                break;
            case 8:
                query = "SELECT" +
                        " user_dim.user_id," +
                        " event_dim.name," +
                        " event_dim.params.key," +
                        " event_dim.params.value.string_value," +
                        " event_dim.timestamp_micros" +
                        " FROM [com_mayBank_MKETradeUAT_IOS.app_events_"+date+"]" +
                        " WHERE user_dim.user_id <> '-1'" +
                        " AND user_dim.user_id <> 'null'" +
                        " AND event_dim.name= 'screen_view'" +
                        " AND (event_dim.params.key = 'firebase_previous_screen' OR event_dim.params.key = 'firebase_screen')" +
                        " ORDER BY user_dim.user_id desc, event_dim.timestamp_micros asc";
                break;
            default:
                query = null;

        }
        return query;
    }
}
