package com.dev.bigquery;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.bigquery.Bigquery;
import com.google.api.services.bigquery.BigqueryScopes;
import com.google.api.services.bigquery.model.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by N5608296 on 05/01/2018 005.
 */
public class TestBigQuery {

    public static void main(String[] args) throws IOException {

        String projectId = "mketrade-cefcb";

        // Create a new Bigquery client authorized via Application Default Credentials.
        Bigquery bigquery = createAuthorizedClient();

        List<TableRow> rows =
/*                executeQuery(
                        "SELECT title "
                                + "FROM [publicdata:samples.wikipedia] LIMIT 10",
                        bigquery,
                        projectId);*/
                executeQuery(
                        "SELECT" +
                                " user_dim.user_id," +
                                " user_dim.device_info.device_model," +
                                " user_dim.geo_info.region," +
                                " user_dim.app_info.app_version," +
                                " event_dim.name" +
                                " FROM [mketrade-cefcb:com_mbb_mketrade_uat_ANDROID.app_events_20180212] LIMIT 3",
                        bigquery,
                        projectId);

        displayResults(rows);
    }

    private static void displayResults(List<TableRow> rows) {
        System.out.print("\nResults:\n------------\n");
        for (TableRow row : rows) {
            for (TableCell field : row.getF()) {
                System.out.printf("%-50s", field.getV());
            }
            System.out.println();
        }
    }

    private static List<TableRow> executeQuery(String querySql, Bigquery bigquery, String projectId)
            throws IOException {
        QueryResponse query =
                bigquery.jobs().query(projectId, new QueryRequest().setQuery(querySql)).execute();

        // Execute it
        GetQueryResultsResponse queryResult =
                bigquery
                        .jobs()
                        .getQueryResults(
                                query.getJobReference().getProjectId(), query.getJobReference().getJobId())
                        .execute();
        System.out.println(queryResult);
        System.out.println(queryResult.getRows());
        return queryResult.getRows();
    }

    public static Bigquery createAuthorizedClient() throws IOException {
        // Create the credential
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


}
