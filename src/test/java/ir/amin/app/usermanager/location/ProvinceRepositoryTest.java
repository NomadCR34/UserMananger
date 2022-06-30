package ir.amin.app.usermanager.location;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProvinceRepositoryTest {

    @Autowired
    private ProvinceRepository provinceRepository;

    @AfterEach
    void tearDown(){
        provinceRepository.deleteAll();
    }


    @Test
    void findProvincesByCountryID() {
        Province province = new Province("TestProvince",1);
        provinceRepository.save(province);
        List<Province> provinces = provinceRepository.findProvincesByCountryID(1);
        assertThat(province).isEqualTo(provinces.get(0));
    }
}