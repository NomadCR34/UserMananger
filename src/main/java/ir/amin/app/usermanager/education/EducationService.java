package ir.amin.app.usermanager.education;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EducationService {

    private final EducationRepository educationRepository;

    @Autowired
    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public List<Education> getEducationList() {
        return new ArrayList<>(educationRepository.findAll());
    }

    public Education getEducationByID(Integer educationID) {
        return educationRepository.findById(educationID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Education with id = " + educationID + " not found"));
    }

    public Education save(Education education) {
        invalidEducationInfo(education);
        try {
            return educationRepository.save(education);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not save education.");
        }
    }

    public Education update(Integer educationID, Education education) {
        if (!isEducationExist(educationID)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Education with id = " + educationID + " not found");
        }
        invalidEducationInfo(education);
        try {
            education.setId(educationID);
            return educationRepository.save(education);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not update education.");
        }
    }

    public ResponseEntity<?> delete(Integer educationID) {
        if (!isEducationExist(educationID)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Education with id = " + educationID + " not found");
        }
        try {
            educationRepository.deleteById(educationID);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not delete education.");
        }
    }

    public boolean isEducationExist(Integer educationID) {
        return educationRepository.existsById(educationID);
    }

    private void invalidEducationInfo(Education education) {
        if (education.getName() == null || education.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name of education can not be empty.");
        }
    }
}
