package ir.amin.app.usermanager.language;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<Language> getLanguageList() {
        return languageRepository.findAll();
    }

    public Language getLanguageByID(Integer languageID) {
        return languageRepository.findById(languageID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Language with id = " + languageID + " not found"));
    }

    public Language saveLanguage(Language language) {
        invalidateLanguage(language);
        try {
            return languageRepository.save(language);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not save language.");
        }
    }

    public Language updateLanguage(Integer languageID, Language language) {
        if (!isLanguageExist(languageID)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Language with id = " + languageID + " not found");
        }
        invalidateLanguage(language);
        try {
            language.setId(languageID);
            return languageRepository.save(language);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not update language.");
        }
    }

    public ResponseEntity<?> deleteLanguage(Integer languageID) {
        if (!isLanguageExist(languageID)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Language with id = " + languageID + " not found");
        }
        try {
            languageRepository.deleteById(languageID);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not delete language.");
        }
    }

    private void invalidateLanguage(Language language) {
        if (language.getName() == null || language.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name of language can not be empty");
        }
    }

    public boolean isLanguageExist(Integer languageID) {
        return languageRepository.existsById(languageID);
    }
}
