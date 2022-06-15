package ir.amin.app.usermanager.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province,Long> {

   @Query(value = "SELECT t from Province t Where t.countryID = ?1")
    List<Province> findProvincesByCountryID(Integer countryID);
}
