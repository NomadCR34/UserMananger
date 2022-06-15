package ir.amin.app.usermanager.gender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/gender")
public class GenderAdminController {

    private final GenderService genderService;

    @Autowired
    public GenderAdminController(GenderService genderService) {
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

    @PostMapping
    public Gender save(@RequestBody Gender gender){
        return genderService.save(gender);
    }

    @PutMapping("/{gender_id}")
    public Gender update(@PathVariable("gender_id")Short id,@RequestBody Gender gender){
        return genderService.update(id,gender);
    }

    @DeleteMapping("/{gender_id}")
    public ResponseEntity<?> delete(@PathVariable("gender_id")Short id){
        return genderService.delete(id);
    }

}
