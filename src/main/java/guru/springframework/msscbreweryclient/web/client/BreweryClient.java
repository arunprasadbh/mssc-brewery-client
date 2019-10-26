package guru.springframework.msscbreweryclient.web.client;

/*
 * Created by arunabhamidipati on 26/10/2019
 */

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Slf4j
@ConfigurationProperties(prefix = "sfg.brewery")
@Component
public class BreweryClient {
    private final String BEER_PATH_V1 = "/api/v1/beer/";

    private String apihost="http://localhost:8080";

    private RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID uuid) {
        log.info("Value of apihost is : {}", apihost);
        log.info("UUID value is {}", uuid.toString());
        log.info("URL is {}", apihost + BEER_PATH_V1 + uuid.toString());
        return restTemplate.getForObject(apihost + BEER_PATH_V1 + uuid.toString(), BeerDto.class);
    }
}
