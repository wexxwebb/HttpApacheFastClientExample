package service;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import java.io.IOException;
import java.util.UUID;

@Stateless
public class HttpRequesterServiceImpl implements HttpRequesterService {

    private static final int THREADS = 100;
    private static final int LOOP = 100;

    private PoolingHttpClientConnectionManager poolingConnManager
            = new PoolingHttpClientConnectionManager();
    private final CloseableHttpClient client
            = HttpClients.custom().setConnectionManager(poolingConnManager)
            .build();

    private UUID uuid = UUID.randomUUID();

    @Override
    public UUID exec(int threads, int loop) throws IOException {
        for (int i = 0; i < threads; i++) {
            async(loop);
        }
        return uuid;
    }

    @Asynchronous
    private void async(int loop) throws IOException {
        for (int i = 0; i < loop; i++) {
            HttpGet httpGet = new HttpGet("http://localhost:7998/4096");
            HttpResponse response = client.execute(httpGet);
            EntityUtils.toString(response.getEntity(), "UTF-8");
        }
    }
}
