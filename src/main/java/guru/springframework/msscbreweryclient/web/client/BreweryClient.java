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

import java.net.URI;
import java.util.UUID;

@Slf4j
@ConfigurationProperties(prefix = "sfg.brewery")
@Component
public class BreweryClient {
    private final String BEER_PATH_V1 = "/api/v1/beer/";

    private String apihost;

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

    public URI saveBeerDto(BeerDto beerDto) {
        log.info("BreweryClient: in method saveBeerDto");

        return restTemplate.postForLocation(apihost + BEER_PATH_V1, beerDto);

    }

    public void updateBeer(UUID uuid, BeerDto beerDto) {
        log.info("BreweryClient: in method updateBeer");

         restTemplate.put(apihost+BEER_PATH_V1+uuid, beerDto);
    }

    public void deleteBeer(UUID uuid) {
        restTemplate.delete(apihost + BEER_PATH_V1 + uuid, uuid);
    }



    public void setApihost(String apihost) {
        this.apihost = apihost;
    }
}
