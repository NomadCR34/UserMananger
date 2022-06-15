package ir.amin.app.usermanager.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/country")
    public List<Country> getCountryList(){
        return locationService.getCountryList();
    }

    @GetMapping("/country/{country_id}")
    public Country getCountry(@PathVariable("country_id") Integer countryID){
        return locationService.getCountryByID(countryID);
    }

    @GetMapping("/province/{country_id}")
    public List<Province> getProvinceList(@PathVariable("country_id") Integer countryID){
        return locationService.getProvinceByCountryID(countryID);
    }

    @GetMapping("/province/{province_id}")
    public Province getProvince(@PathVariable("province_id") Long provinceID){
        return locationService.getProvinceByID(provinceID);
    }

    @GetMapping("/city/{province_id}")
    public List<City> getCityList(@PathVariable("province_id") Long provinceID){
        return locationService.getCityByProvinceID(provinceID);
    }

    @GetMapping("/city/{city_id}")
    public City getCity(@PathVariable("city_id") Long cityID){
        return locationService.getCityByID(cityID);
    }

}
