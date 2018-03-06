package com.dev.bq.request;

import com.dev.bq.util.Common;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Data;
import com.google.api.services.bigquery.Bigquery;
import com.google.api.services.bigquery.BigqueryScopes;
import com.google.api.services.bigquery.model.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * Created by N5608296 on 08/01/2018 008.
 */
public class BigQueryRequest {
    private static final Logger LOGGER = Logger.getLogger(BigQueryRequest.class);
    private static BigQueryRequest self;
    private String projectId = "mketrade-cefcb";
    private Bigquery bigquery;

    private BigQueryRequest() throws IOException {
        bigquery = createAuthorizedClient();
    }

    public static BigQueryRequest getInstance() throws IOException {
        if (self == null) {
            self = new BigQueryRequest();
        }

        return self;
    }

    public GetQueryResultsResponse dispatchAndroidQueryRequest(int code, int date, int records) throws IOException {
        GetQueryResultsResponse response = null;

        String query = AndroidQueryFactory.getInstance().generateQuery(code, Common.getDatePortion(date), records);
        if (query != null) {
            try {
                response = executeQuery(query);
            } catch (Exception e) {
                try {
                    LOGGER.error("Error : "+e.getMessage().substring(e.getMessage().indexOf("{")));
                } catch (Exception e1) {
                    LOGGER.error("Error : "+e.getMessage());
                }
            }
        } else {
            LOGGER.info("{\"message\" : \"No matching data found\"}");
        }

        return response;
    }

    public GetQueryResultsResponse dispatchIosQueryRequest(int code, int date, int records) throws IOException {
        GetQueryResultsResponse response = null;

        String query = IosQueryFactory.getInstance().generateQuery(code, Common.getDatePortion(date), records);
        if (query != null) {
            try {
                response = executeQuery(query);
            } catch (Exception e) {
                try {
                    LOGGER.error("Error : "+e.getMessage().substring(e.getMessage().indexOf("{")));
                } catch (Exception e1) {
                    LOGGER.error("Error : "+e.getMessage());
                }
            }
        } else {
            LOGGER.info("{\"message\" : \"No matching data found\"}");
        }

        return response;
    }

    private GetQueryResultsResponse executeQuery(String querySql)
            throws IOException {
        QueryResponse query =
                bigquery.jobs().query(projectId, new QueryRequest().setQuery(querySql)).execute();

        GetQueryResultsResponse queryResult =
                bigquery
                        .jobs()
                        .getQueryResults(
                                query.getJobReference().getProjectId(), query.getJobReference().getJobId())
                        .execute();

        //displayResults(queryResult.getRows());
        return queryResult;
    }

/*    private List<TableRow> executeQuery(String querySql)
            throws IOException {
        QueryResponse query =
                bigquery.jobs().query(projectId, new QueryRequest().setQuery(querySql)).execute();

        GetQueryResultsResponse queryResult =
                bigquery
                        .jobs()
                        .getQueryResults(
                                query.getJobReference().getProjectId(), query.getJobReference().getJobId())
                        .execute();
        System.out.println(queryResult);
        System.out.println(queryResult.getRows());
        return queryResult.getRows();
    }*/

    private Bigquery createAuthorizedClient() throws IOException {
        HttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();
        GoogleCredential credential = GoogleCredential.getApplicationDefault(transport, jsonFactory);

        if (credential.createScopedRequired()) {
            credential = credential.createScoped(BigqueryScopes.all());
        }

        return new Bigquery.Builder(transport, jsonFactory, credential)
                .setApplicationName("Mike")
                .build();
    }

    private static void displayResults(List<TableRow> rows) {
        System.out.print("\nResults:\n------------\n");
        for (TableRow row : rows) {
           for (TableCell cell : row.getF()) {
                System.out.printf("%-50s", Data.isNull(cell.getV()) ? "null" : cell.getV());

            }
            System.out.println();
        }
    }

}
