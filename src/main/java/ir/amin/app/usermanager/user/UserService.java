package ir.amin.app.usermanager.user;

import ir.amin.app.usermanager.education.EducationService;
import ir.amin.app.usermanager.gender.GenderService;
import ir.amin.app.usermanager.language.LanguageService;
import ir.amin.app.usermanager.location.LocationService;
import ir.amin.app.usermanager.util.invalidator.EmailValidator;
import ir.amin.app.usermanager.util.invalidator.PhoneValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final LocationService locationService;
    private final GenderService genderService;
    private final EducationService educationService;
    private final LanguageService languageService;
    private final EmailValidator emailValidator;
    private final PhoneValidator phoneValidator;

    @Autowired
    public UserService(
            UserRepository userRepository,
            LocationService locationService,
            GenderService genderService,
            LanguageService languageService,
            EducationService educationService,
            EmailValidator emailValidator,
            PhoneValidator phoneValidator
    ) {
        this.userRepository = userRepository;
        this.locationService = locationService;
        this.genderService = genderService;
        this.languageService = languageService;
        this.educationService = educationService;
        this.emailValidator = emailValidator;
        this.phoneValidator = phoneValidator;
    }

    public User getUserByID(Long userID) {
        return userRepository.findById(userID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with id = " + userID + " not found."));
    }

    public User createUser(User user) {
        invalidateUser(user);
        try {
            user.setRegisterDate(System.currentTimeMillis());
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not save user");
        }
    }

    public User updateUser(Long userID, User user) {
        if (!userRepository.existsById(userID)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with id = " + userID + " not found.");
        }
        invalidateUser(user);
        try {
            user.setId(userID);
            user.setModifiedDate(System.currentTimeMillis());
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not update user");
        }
    }

    public ResponseEntity<?> deleteUser(Long userID) {
        if (!userRepository.existsById(userID)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with id = " + userID + " not found.");
        }
        try {
            userRepository.deleteById(userID);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not delete user");
        }
    }

    private void invalidateUser(User user) {
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Firstname can not be empty.");
        }
        if (user.getNationalID() == null || user.getNationalID().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "NationalID can not be empty.");
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lastname can not be empty.");
        }
        if (user.getAddress() == null || user.getAddress().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address can not be empty.");
        }
        if (!locationService.isCountryExist(user.getCountryID())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Country with id = " + user.getCountryID() + " not found.");
        }
        if (!locationService.isProvinceExist(user.getProvinceID())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Province with id = " + user.getProvinceID() + " not found.");
        }
        if (!locationService.isCityExist(user.getCityID())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "City with id = " + user.getCityID() + " not found.");
        }
        if (!locationService.isCityExist(user.getBirthPlaceCity())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "City of birth with id = " + user.getBirthPlaceCity() + " not found.");
        }
        if (!emailValidator.isValid(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is invalid.");
        }
        if (!phoneValidator.isValid(user.getPhone())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone is invalid.");
        }
        if (user.getBirthDate() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Birthdate is invalid");
        }
        if (!genderService.isGenderExist(user.getGenderID())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gender is invalid");
        }
        if (!educationService.isEducationExist(user.getDegree())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Degree is invalid");
        }
        if (!languageService.isLanguageExist(user.getLanguage())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Language is invalid");
        }
    }

}
