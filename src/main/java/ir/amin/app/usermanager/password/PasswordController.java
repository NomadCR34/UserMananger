package ir.amin.app.usermanager.password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/password")
public class PasswordController {

    private final PasswordService passwordService;

    @Autowired
    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping
    public ResponseEntity<?> setPassword(@RequestBody Password password){
        return passwordService.savePassword(password);
    }


}
