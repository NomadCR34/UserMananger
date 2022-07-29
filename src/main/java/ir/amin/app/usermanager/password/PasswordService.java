package ir.amin.app.usermanager.password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordService {

    private PasswordRepository passwordRepository;

    @Autowired
    public PasswordService(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    private Password save(Password password){
        return passwordRepository.save(password);
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

}
