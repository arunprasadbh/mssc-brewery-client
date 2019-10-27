package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


/*
 * Created by arunabhamidipati on 26/10/2019
 */
@Slf4j
@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient breweryClient;


    @Test
    void getBeerById() {
        BeerDto beerDto = breweryClient.getBeerById( UUID.randomUUID());
        for (int i = 0; i < 100; i++) {
            beerDto = breweryClient.getBeerById(UUID.randomUUID());
        }

        assertNotNull(beerDto);
    }

    @Test
    void testSaveBeer() {
        BeerDto beerDto = BeerDto.builder().id( UUID.randomUUID())
                .beerName("Heinekin")
                .beerStyle(BeerStyle.LAGER)
                .build();

        URI uri = breweryClient.saveBeerDto(beerDto);
        
        assertNotNull(uri);
        
        log.info("BreweryClientTest: value of uri is {}", uri.toString());

    }

    @Test
    void testUpdateBeer() {
        BeerDto beerDto = BeerDto.builder().id( UUID.randomUUID())
                .beerName("Heinekin")
                .beerStyle(BeerStyle.LAGER)
                .build();
        breweryClient.updateBeer(UUID.randomUUID(), beerDto);
    }

    @Test
    void testDeleteBeer() {
        breweryClient.deleteBeer(UUID.randomUUID());
    }
}