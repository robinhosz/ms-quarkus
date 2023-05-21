package io.github.robinhosz.services;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

@ApplicationScoped
public class ElasticService {

    private RestHighLevelClient client;

    //Observa quando o quarkus sobe
    void startup(@Observes StartupEvent startupEvent) {
        client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
    }

    //Metodo que observa quando o quarkus para
    void shutdown(@Observes ShutdownEvent shutdownEvent) {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void index(String index, String json) {
        IndexRequest ir = new IndexRequest(index).source(json, XContentType.JSON);

        try {
            client.index(ir, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
