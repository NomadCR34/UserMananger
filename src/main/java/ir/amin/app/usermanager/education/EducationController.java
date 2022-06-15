package ir.amin.app.usermanager.education;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/education")
public class EducationController {

    private final EducationService educationService;

    @Autowired
    public EducationController(EducationService educationService) {
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


}
