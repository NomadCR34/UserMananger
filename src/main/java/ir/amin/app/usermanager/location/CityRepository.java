package ir.amin.app.usermanager.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {

    @Query("SELECT t FROM City t WHERE t.provinceID = ?1")
    List<City> findCitiesByProvinceID(Long provinceID);

}
