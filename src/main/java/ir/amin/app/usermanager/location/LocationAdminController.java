package ir.amin.app.usermanager.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class LocationAdminController {

    private final LocationService locationService;

    @Autowired
    public LocationAdminController(LocationService locationService) {
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

    @PostMapping("/country")
    public Country saveCountry(@RequestBody Country country){
        return locationService.saveCountry(country);
    }

    @PostMapping("/province")
    public Province saveProvince(@RequestBody Province province){
        return locationService.saveProvince(province);
    }

    @PostMapping("/city")
    public City saveCity(@RequestBody City city){
        return locationService.saveCity(city);
    }

    @PutMapping("/country/{country_id}")
    public Country updateCountry(@PathVariable("country_id") Integer countryID,@RequestBody Country country){
        return locationService.updateCountry(countryID,country);
    }

    @PutMapping("/province/{province_id}")
    public Province updateProvince(@PathVariable("province_id")Long provinceID,@RequestBody Province province){
        return locationService.updateProvince(provinceID,province);
    }

    @PutMapping("/city/{city_id}")
    public City updateCity(@PathVariable("city_id")Long cityID,@RequestBody City city){
        return locationService.updateCity(cityID,city);
    }

    @DeleteMapping("/country/{country_id}")
    public ResponseEntity<?> updateCountry(@PathVariable("country_id") Integer countryID){
        return locationService.deleteCountry(countryID);
    }

    @DeleteMapping("/province/{province_id}")
    public ResponseEntity<?> updateProvince(@PathVariable("province_id")Long provinceID){
        return locationService.deleteProvince(provinceID);
    }

    @DeleteMapping("/city/{city_id}")
    public ResponseEntity<?> updateCity(@PathVariable("city_id")Long cityID){
        return locationService.deleteCity(cityID);
    }


}
