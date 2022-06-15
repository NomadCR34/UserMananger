package ir.amin.app.usermanager.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LocationService {
    private final CountryRepository countryRepository;
    private final ProvinceRepository provinceRepository;
    private final CityRepository cityRepository;

    @Autowired
    public LocationService(
            CountryRepository countryRepository,
            ProvinceRepository provinceRepository,
            CityRepository cityRepository
    ) {
        this.countryRepository = countryRepository;
        this.provinceRepository = provinceRepository;
        this.cityRepository = cityRepository;
    }

    public List<Country> getCountryList() {
        return StreamSupport
                .stream(countryRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Country getCountryByID(Integer countryID) {
        return countryRepository.findById(countryID)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Country with id = " + countryID + " not found.")
                );

    }

    public List<Province> getProvinceByCountryID(Integer countryID) {
        return provinceRepository.findProvincesByCountryID(countryID);
    }

    public Province getProvinceByID(Long provinceID) {
        return provinceRepository.findById(provinceID)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Province with id = " + provinceID + " not found.")
                );
    }

    public List<City> getCityByProvinceID(Long provinceID) {
        return cityRepository.findCitiesByProvinceID(provinceID);
    }

    public City getCityByID(Long cityID) {
        return cityRepository.findById(cityID)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "City with id = " + cityID + " not found.")
                );
    }

    public Country saveCountry(Country country) {
        validateCountryInfo(country);
        try {
            return countryRepository.save(country);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not save country.");
        }
    }

    public Province saveProvince(Province province) {
        validateProvinceInfo(province);
        try {
            return provinceRepository.save(province);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not save province.");
        }
    }

    public City saveCity(City city) {
        validateCityInfo(city);
        try {
            return cityRepository.save(city);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not save city.");
        }
    }

    public Country updateCountry(Integer countryID, Country country) {
        if (!isCountryExist(countryID)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find country with id = " + countryID);
        }
        validateCountryInfo(country);
        try {
            country.setId(countryID);
            return countryRepository.save(country);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not save country.");
        }
    }

    public Province updateProvince(Long provinceID, Province province) {
        if (!isProvinceExist(provinceID)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find province with id = " + provinceID);
        }
        validateProvinceInfo(province);
        try {
            province.setId(provinceID);
            return provinceRepository.save(province);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not save province.");
        }
    }

    public City updateCity(Long cityID, City city) {
        if (!isCityExist(cityID)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find city with id = " + cityID);
        }
        validateCityInfo(city);
        try {
            city.setId(cityID);
            return cityRepository.save(city);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not save city.");
        }
    }

    public ResponseEntity<?> deleteCountry(Integer countryID) {
        if (!isCountryExist(countryID)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find country with id = " + countryID);
        }
        try {
            countryRepository.deleteById(countryID);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not delete country.");
        }
    }

    public ResponseEntity<?> deleteProvince(Long cityID) {
        if (!isProvinceExist(cityID)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find province with id = " + cityID);
        }
        try {
            provinceRepository.deleteById(cityID);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not delete province.");
        }
    }

    public ResponseEntity<?> deleteCity(Long cityID) {
        if (!isCityExist(cityID)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find city with id = " + cityID);
        }
        try {
            cityRepository.deleteById(cityID);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not delete city.");
        }
    }

    public boolean isCountryExist(Integer countryID){
        return countryRepository.existsById(countryID);
    }

    public boolean isProvinceExist(Long provinceID){
        return provinceRepository.existsById(provinceID);
    }

    public boolean isCityExist(Long cityID){
        return cityRepository.existsById(cityID);
    }

    private void validateCountryInfo(Country country) {
        if (country.getName() == null || country.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name of country can not be empty");
        }
        if (country.getCountryCode() == null || country.getCountryCode().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Country Code can not be empty");
        }
    }

    private void validateProvinceInfo(Province province) {
        if (province.getName() == null || province.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name of province can not be empty");
        }
        if (province.getCountryID() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Country ID can not be empty");
        }
    }

    private void validateCityInfo(City city) {
        if (city.getName() == null || city.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name of city can not be empty");
        }
        if (city.getProvinceID() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Province ID can not be empty");
        }
    }

}
