package ir.amin.app.usermanager.gender;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends CrudRepository<Gender,Short> {

}
