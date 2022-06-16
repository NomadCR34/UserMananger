package ir.amin.app.usermanager.gender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GenderService {

    private final GenderRepository genderRepository;

    @Autowired
    public GenderService(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public List<Gender> getGenders() {
        return StreamSupport
                .stream(genderRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Gender getGenderByID(Short id) {
        return genderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gender with id = " + id + " not found."));
    }

    public Gender save(Gender gender){
        if(gender.getName() == null || gender.getName().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name can not be empty");
        }
        try {
            return genderRepository.save(gender);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not save gender");
        }
    }

    public Gender update(Short id,Gender gender){
        if(!genderRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gender with id = " + id + " not found.");
        }
        if(gender.getName() == null || gender.getName().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name can not be empty");
        }
        try {
            return genderRepository.save(gender);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not update gender");
        }
    }

    public ResponseEntity<?> delete(Short id){
        if(!genderRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gender with id = " + id + " not found.");
        }
        try {
            genderRepository.deleteById(id);
            return new ResponseEntity<>( HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not delete gender");
        }
    }

    public boolean isGenderExist(Short id){
        return genderRepository.existsById(id);
    }

}
