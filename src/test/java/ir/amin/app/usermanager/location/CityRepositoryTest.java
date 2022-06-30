package ir.amin.app.usermanager.location;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    @AfterEach
    void tearDown(){
        cityRepository.deleteAll();
    }

    @Test
    void findCitiesByProvinceIDTest() {
        City city = new City("Test",1L,10.0,20.0);
        cityRepository.save(city);
        List<City> cityList = cityRepository.findCitiesByProvinceID(1L);
        assertThat(city).isEqualTo(cityList.get(0));
    }
}