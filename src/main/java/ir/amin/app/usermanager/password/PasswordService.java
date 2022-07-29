package ir.amin.app.usermanager.password;

import ir.amin.app.usermanager.user.UserService;
import ir.amin.app.usermanager.util.hash.SHA;
import ir.amin.app.usermanager.util.invalidator.PasswordValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PasswordService {

    private final PasswordRepository passwordRepository;
    private final UserService userService;
    private final PasswordValidation passwordValidation;
    private final PasswordConfig passwordConfig;
    private final SHA sha;

    @Autowired
    public PasswordService(
            PasswordRepository passwordRepository,
            UserService userService,
            PasswordValidation passwordValidation,
            PasswordConfig passwordConfig,
            SHA sha) {
        this.passwordRepository = passwordRepository;
        this.userService = userService;
        this.passwordValidation = passwordValidation;
        this.passwordConfig = passwordConfig;
        this.sha = sha;
    }

    public Password getUserValidPassword(Long userId){
        return passwordRepository.findPasswordByUserId(userId)
                .stream()
                .filter(Password::isValid)
                .findFirst()
                .orElse(null);
    }

    private Boolean isPasswordExistForUser(Long userID,String password){
        return passwordRepository.isPasswordExistForUser(userID,password);
    }

    private Boolean isPasswordExistAndValidForUser(Long userID,String password){
        return passwordRepository.isPasswordExistAndValidForUser(userID,password);
    }

    public ResponseEntity<?> savePassword(Password password){
        password.setValid(true);
        password.setCreateDate(System.currentTimeMillis());
        if(!userService.isUserExistById(password.getUserId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User with id " + password.getUserId() + " is not exist");
        }
        validatePassword(password);
        password.setPassword(getHashPassword(password.getPassword()));
        if(passwordRepository.isPasswordExistForUser(password.getUserId(),password.getPassword())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Please select new password for user");
        }
        Password currentPassword = getUserValidPassword(password.getUserId());
        if(currentPassword == null) {
            passwordRepository.save(password);
        }else{
            try {
                expiredCurrentPassword(currentPassword);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Can not save password");
            }
            passwordRepository.save(password);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void expiredCurrentPassword(Password password) throws Exception{
        password.setValid(false);
        passwordRepository.save(password);
    }

    private String getHashPassword(String password) {
        return sha.get_SHA_512_SecurePassword(password, passwordConfig.getSalt());
    }

    private void validatePassword(Password password) {
        if(password.getPassword().isEmpty() || password.getPassword() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Password can not be empty");
        }
        if(password.getUserId() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"UserID can not be empty");
        }
        if(password.getPassword().length() < passwordConfig.getMinLength() || password.getPassword().length() > passwordConfig.getMinLength()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Please select a password between 8-64 characters.");
        }
        if(!passwordValidation.isValid(password.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Password should content a-z,A-Z,0-9 and special characters.");
        }
    }

}
