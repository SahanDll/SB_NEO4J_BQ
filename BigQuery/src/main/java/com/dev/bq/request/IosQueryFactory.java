package com.dev.bq.request;

import com.dev.bq.util.IConstants;

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
                        " FROM ["+ IConstants.IosTableName+date+"]" +
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
                        " FROM ["+ IConstants.IosTableName+date+"]" +
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
                        " FROM ["+ IConstants.IosTableName+date+"]" +
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
                        " FROM ["+ IConstants.IosTableName+date+"]" +
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
                        " FROM ["+ IConstants.IosTableName+date+"]" +
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
                        " FROM ["+ IConstants.IosTableName+date+"]" +
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
                        " FROM ["+ IConstants.IosTableName+date+"]" +
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
                        " FROM ["+ IConstants.IosTableName+date+"]" +
                        " WHERE user_dim.user_id <> '-1'" +
                        " AND user_dim.user_id <> 'null'" +
                        " AND event_dim.name= 'screen_view'" +
                        " AND (event_dim.params.key = 'firebase_previous_screen' OR event_dim.params.key = 'firebase_screen')" +
                        " ORDER BY user_dim.user_id desc, event_dim.timestamp_micros asc";
                break;
            case 9:
                query = "SELECT" +
                        " user_dim.user_id," +//0
                        " event_dim.name," +//1
                        " event_dim.params.key," +//2
                        " event_dim.params.value.int_value," +//3
                        " event_dim.timestamp_micros," +//4
                        " event_dim.params.value.string_value" +//5
                        " FROM ["+ IConstants.IosTableName+date+"]" +
                        " WHERE user_dim.user_id <> '-1'" +
                        " AND user_dim.user_id <> 'null'" +
                        " AND event_dim.name= 'user_engagement'" +
                        " AND (event_dim.params.key = 'engagement_time_msec' OR event_dim.params.key = 'firebase_screen')" +
                        " ORDER BY user_dim.user_id desc, event_dim.timestamp_micros asc, event_dim.params.key desc" +
                        " LIMIT "+records;
                break;
            case 10:
                query = "SELECT" +
                        " user_dim.user_id," +//0
                        " event_dim.name," +//1
                        " event_dim.params.key," +//2
                        " event_dim.params.value.int_value," +//3
                        " event_dim.timestamp_micros," +//4
                        " event_dim.params.value.string_value" +//5
                        " FROM ["+ IConstants.IosTableName+date+"]" +
                        " WHERE user_dim.user_id <> '-1'" +
                        " AND user_dim.user_id <> 'null'" +
                        " AND event_dim.name= 'user_engagement'" +
                        " AND (event_dim.params.key = 'engagement_time_msec' OR event_dim.params.key = 'firebase_screen')" +
                        " ORDER BY user_dim.user_id desc, event_dim.timestamp_micros asc, event_dim.params.key desc";
                break;
            case 11:
                query = "SELECT" +
                        " user_dim.user_id," +//0
                        " event_dim.name," +//1
                        " event_dim.params.key," +//2
                        " event_dim.params.value.string_value," +//3
                        " event_dim.params.value.int_value," +//4
                        " event_dim.params.value.float_value," +//5
                        " event_dim.params.value.double_value," +//6
                        " event_dim.timestamp_micros" +//7
                        " FROM ["+ IConstants.IosTableName+date+"]" +
                        " WHERE user_dim.user_id <> '-1'" +
                        " AND user_dim.user_id <> 'null'" +
                        " AND event_dim.name = 'Trade'" +
                        " ORDER BY user_dim.user_id desc, event_dim.timestamp_micros asc, event_dim.params.key desc" +
                        " LIMIT "+records;
                break;
            case 12:
                query = "SELECT" +
                        " user_dim.user_id," +//0
                        " event_dim.name," +//1
                        " event_dim.params.key," +//2
                        " event_dim.params.value.string_value," +//3
                        " event_dim.params.value.int_value," +//4
                        " event_dim.params.value.float_value," +//5
                        " event_dim.params.value.double_value," +//6
                        " event_dim.timestamp_micros" +//7
                        " FROM ["+ IConstants.IosTableName+date+"]" +
                        " WHERE user_dim.user_id <> '-1'" +
                        " AND user_dim.user_id <> 'null'" +
                        " AND event_dim.name = 'Trade'" +
                        " ORDER BY user_dim.user_id desc, event_dim.timestamp_micros asc, event_dim.params.key desc";
                break;
            case 13:
                query = "SELECT" +
                        " user_dim.user_id," +//0
                        " event_dim.name," +//1
                        " event_dim.params.key," +//2
                        " event_dim.params.value.string_value," +//3
                        " event_dim.timestamp_micros  " +//4
                        " FROM ["+ IConstants.IosTableName+date+"]" +
                        " WHERE user_dim.user_id <> '-1'" +
                        " AND user_dim.user_id <> 'null'" +
                        " AND event_dim.name = 'NotificationClicked'" +
                        " AND event_dim.params.key = 'Notification_Type'" +
                        " ORDER BY user_dim.user_id desc, event_dim.timestamp_micros asc" +
                        " LIMIT "+records;
                break;
            case 14:
                query = "SELECT" +
                        " user_dim.user_id," +//0
                        " event_dim.name," +//1
                        " event_dim.params.key," +//2
                        " event_dim.params.value.string_value," +//3
                        " event_dim.timestamp_micros  " +//4
                        " FROM ["+ IConstants.IosTableName+date+"]" +
                        " WHERE user_dim.user_id <> '-1'" +
                        " AND user_dim.user_id <> 'null'" +
                        " AND event_dim.name = 'NotificationClicked'" +
                        " AND event_dim.params.key = 'Notification_Type'" +
                        " ORDER BY user_dim.user_id desc, event_dim.timestamp_micros asc";
                break;
            case 15:
                query = "SELECT" +
                        " user_dim.user_id," +//0
                        " event_dim.name," +//1
                        " event_dim.params.key," +//2
                        " event_dim.params.value.string_value," +//3
                        " event_dim.timestamp_micros  " +//4
                        " FROM ["+ IConstants.IosTableName+date+"]" +
                        " WHERE user_dim.user_id <> '-1'" +
                        " AND user_dim.user_id <> 'null'" +
                        " AND event_dim.name = 'NotificationOpenedScreen'" +
                        " AND event_dim.params.key = 'Notification_Type'" +
                        " ORDER BY user_dim.user_id desc, event_dim.timestamp_micros asc" +
                        " LIMIT "+records;
                break;
            case 16:
                query = "SELECT" +
                        " user_dim.user_id," +//0
                        " event_dim.name," +//1
                        " event_dim.params.key," +//2
                        " event_dim.params.value.string_value," +//3
                        " event_dim.timestamp_micros  " +//4
                        " FROM ["+ IConstants.IosTableName+date+"]" +
                        " WHERE user_dim.user_id <> '-1'" +
                        " AND user_dim.user_id <> 'null'" +
                        " AND event_dim.name = 'NotificationOpenedScreen'" +
                        " AND event_dim.params.key = 'Notification_Type'" +
                        " ORDER BY user_dim.user_id desc, event_dim.timestamp_micros asc";
                break;
            default:
                query = null;

        }
        return query;
    }
}
