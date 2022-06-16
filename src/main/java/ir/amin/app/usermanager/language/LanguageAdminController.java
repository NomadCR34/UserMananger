package ir.amin.app.usermanager.language;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/language")
public class LanguageAdminController {

    private final LanguageService languageService;

    @Autowired
    public LanguageAdminController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping
    public List<Language> getLanguageList(){
        return languageService.getLanguageList();
    }

    @GetMapping("/{language_id}")
    public Language getLanguageList(@PathVariable("language_id") Integer languageID){
        return languageService.getLanguageByID(languageID);
    }

    @PostMapping
    public Language saveLanguage(@RequestBody Language language){
        return languageService.saveLanguage(language);
    }

    @PutMapping("/{language_id}")
    public Language updateLanguage(@PathVariable("language_id") Integer languageID,@RequestBody Language language){
        return languageService.updateLanguage(languageID,language);
    }

    @DeleteMapping("/{language_id}")
    public ResponseEntity<?> deleteLanguage(@PathVariable("language_id") Integer languageID){
        return languageService.deleteLanguage(languageID);
    }

}
