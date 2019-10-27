package guru.springframework.msscbreweryclient.web.config;

/*
 * Created by arunabhamidipati on 27/10/2019
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//@ConfigurationProperties(prefix = "sfg")
@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

    private final Integer maxtotal;
    private final Integer timeout;
    private final Integer maxperroute;

    public BlockingRestTemplateCustomizer(@Value("${sfg.maxtotal}") Integer maxtotal,
                                          @Value("${sfg.timeout}") Integer timeout,
                                          @Value("${sfg.maxperroute}") Integer maxperroute) {
        this.maxtotal = maxtotal;
        this.timeout = timeout;
        this.maxperroute = maxperroute;
    }

    public ClientHttpRequestFactory clientHttpRequestFactory(){
        PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager();
        clientConnectionManager.setMaxTotal(maxtotal);
        clientConnectionManager.setDefaultMaxPerRoute(maxperroute);

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectionRequestTimeout(timeout).build();

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(clientConnectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig).build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }
    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }

}
