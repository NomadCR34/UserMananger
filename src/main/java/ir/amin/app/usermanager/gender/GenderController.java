package ir.amin.app.usermanager.gender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gender")
public class GenderController {

    private GenderService genderService;

    @Autowired
    public GenderController(GenderService genderService) {
        this.genderService = genderService;
    }

    @GetMapping
    public List<Gender> getGender(){
        return genderService.getGenders();
    }

    @GetMapping("/{gender_id}")
    public Gender getGenderById(@PathVariable("gender_id") Short genderID){
        return genderService.getGenderByID(genderID);
    }

}
