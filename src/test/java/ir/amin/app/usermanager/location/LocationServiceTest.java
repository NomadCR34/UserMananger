package ir.amin.app.usermanager.location;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class LocationServiceTest {

    private LocationService locationService;
    @Mock private CountryRepository countryRepository;
    @Mock private ProvinceRepository provinceRepository;
    @Mock private CityRepository cityRepository;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        locationService = new LocationService(
                countryRepository,
                provinceRepository,
                cityRepository
        );
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getCountryList() {
        Country country = new Country("TEST","TE");
        List<Country> countries = new ArrayList<>();
        countries.add(country);
        given(countryRepository.findAll()).willReturn(countries);
        List<Country> givenCountry = locationService.getCountryList();
        assertThat(country).isEqualTo(givenCountry.get(0));
    }

    @Test
    void getCountryByID() {
        Country country = new Country("TEST","TE");
        given(countryRepository.findById(1)).willReturn(Optional.of(country));
        Country givenCountry = locationService.getCountryByID(1);
        assertThat(country).isEqualTo(givenCountry);

    }

    @Test
    void getProvinceByCountryID() {
        Province province = new Province("Test",1);
        List<Province> provinces = new ArrayList<>();
        provinces.add(province);
        given(provinceRepository.findProvincesByCountryID(1)).willReturn(provinces);
        List<Province> givenProvince = locationService.getProvinceByCountryID(1);
        assertThat(province).isEqualTo(givenProvince.get(0));
    }

    @Test
    void getProvinceByID() {
        Province province = new Province("Test",1);
        given(provinceRepository.findById(1L)).willReturn(Optional.of(province));
        Province givenProvince = locationService.getProvinceByID(1L);
        assertThat(province).isEqualTo(givenProvince);
    }

    @Test
    void getCityByProvinceID() {
        City city = new City("Test",1L,null,null);
        List<City> cities = new ArrayList<>();
        cities.add(city);
        given(cityRepository.findCitiesByProvinceID(1L)).willReturn(cities);
        List<City> givenCities = locationService.getCityByProvinceID(1L);
        assertThat(city).isEqualTo(givenCities.get(0));
    }

    @Test
    void getCityByID() {
        City city = new City("Test",1L,null,null);
        given(cityRepository.findById(1L)).willReturn(Optional.of(city));
        City givenCity = locationService.getCityByID(1L);
        assertThat(city).isEqualTo(givenCity);
    }

    @Test
    void saveCountry() {
        Country country = new Country("Test","TE");
        given(countryRepository.save(country)).willReturn(country);
        Country savedCountry = locationService.saveCountry(country);
        assertThat(country).isEqualTo(savedCountry);
    }

    @Test
    void throwExceptionWhenCountryIsNull(){
        assertThatThrownBy(() -> locationService.saveCountry(null))
                .hasMessageContaining("PLease insert valid country info");
    }

    @Test
    void saveProvince() {
        Province province = new Province("Test",1);
        given(countryRepository.existsById(1)).willReturn(true);
        given(provinceRepository.save(province)).willReturn(province);
        Province savedProvince = locationService.saveProvince(province);
        assertThat(province).isEqualTo(savedProvince);
    }

    @Test
    void throwExceptionWhenProvinceIsNull(){
        assertThatThrownBy(() -> locationService.saveProvince(null))
                .hasMessageContaining("PLease insert valid province info");

    }

    @Test
    void saveCity() {
        City city = new City("Test",1L,10.0,20.0);
        given(provinceRepository.existsById(1L)).willReturn(true);
        given(cityRepository.save(city)).willReturn(city);
        City savedCity = locationService.saveCity(city);
        assertThat(savedCity).isEqualTo(city);
    }

    @Test
    void throwExceptionSaveCity() {
        City city = new City("Test",1L,10.0,20.0);
        given(locationService.isProvinceExist(1L)).willReturn(false);
        assertThatThrownBy(()->locationService.saveCity(city))
                .hasMessageContaining("Could not find province with id = 1");
    }

    @Test
    void throwExceptionSaveNullCity() {
        assertThatThrownBy(()->locationService.saveCity(null))
                .hasMessageContaining("PLease insert valid city info");
    }

    @Test
    void updateCountry() {
        Country country = new Country("Test","98");
        country.setId(1);
        given(countryRepository.existsById(1)).willReturn(true);
        locationService.updateCountry(1,country);
        ArgumentCaptor<Country> countryArgumentCaptor = ArgumentCaptor.forClass(Country.class);
        verify(countryRepository).save(countryArgumentCaptor.capture());
        Country capturedCountry = countryArgumentCaptor.getValue();
        assertThat(capturedCountry).isEqualTo(country);
    }

    @Test
    void throwExceptionUpdateCountryIsNull(){
        assertThatThrownBy(()-> locationService.updateCountry(1,null))
                .hasMessageContaining("PLease insert valid country info");
    }

    @Test
    void updateProvince() {
        Province province = new Province("Test",1);
        province.setId(1L);
        given(countryRepository.existsById(1)).willReturn(true);
        given(provinceRepository.existsById(1L)).willReturn(true);
        locationService.updateProvince(1L,province);
        ArgumentCaptor<Province> provinceArgumentCaptor = ArgumentCaptor.forClass(Province.class);
        verify(provinceRepository).save(provinceArgumentCaptor.capture());
        Province capturedProvince = provinceArgumentCaptor.getValue();
        assertThat(capturedProvince).isEqualTo(province);
    }

    @Test
    void updateCity() {
    }

    @Test
    void deleteCountry() {
    }

    @Test
    void deleteProvince() {
    }

    @Test
    void deleteCity() {
    }

    @Test
    void isCountryExist() {
        given(countryRepository.existsById(1)).willReturn(true);
        assertThat(locationService.isCountryExist(1)).isTrue();
    }

    @Test
    void isCountryNotExist() {
        given(countryRepository.existsById(1)).willReturn(false);
        assertThat(locationService.isCountryExist(1)).isFalse();
    }

    @Test
    void isProvinceExist() {
        given(provinceRepository.existsById(1L)).willReturn(true);
        assertThat(locationService.isProvinceExist(1L)).isTrue();
    }

    @Test
    void isProvinceNotExist() {
        given(provinceRepository.existsById(1L)).willReturn(false);
        assertThat(locationService.isProvinceExist(1L)).isFalse();
    }

    @Test
    void isCityExist() {
        given(cityRepository.existsById(1L)).willReturn(true);
        assertThat(locationService.isCityExist(1L)).isTrue();
    }

    @Test
    void isCityNotExist() {
        given(cityRepository.existsById(1L)).willReturn(false);
        assertThat(locationService.isCityExist(1L)).isFalse();
    }
}