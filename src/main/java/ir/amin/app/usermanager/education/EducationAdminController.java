package ir.amin.app.usermanager.education;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/education")
public class EducationAdminController {

    private final EducationService educationService;

    @Autowired
    public EducationAdminController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping
    public List<Education> getEducationList() {
        return educationService.getEducationList();
    }

    @GetMapping("/{education_id}")
    public Education getEducationList(@PathVariable("education_id") Integer educationID) {
        return educationService.getEducationByID(educationID);
    }

    @PostMapping
    public Education updateEducation(Education education) {
        return educationService.save(education);
    }

    @PutMapping("/{education_id}")
    public Education updateEducation(@PathVariable("education_id") Integer educationID,Education education) {
        return educationService.update(educationID,education);
    }

    @DeleteMapping("/{education_id}")
    public ResponseEntity<?> deleteEducation(@PathVariable("education_id") Integer educationID) {
        return educationService.delete(educationID);
    }
}
