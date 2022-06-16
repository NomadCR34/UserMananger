package ir.amin.app.usermanager.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/user")
public class UserAdminController {

    private final UserService userService;

    @Autowired
    public UserAdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{user_id}")
    public User getUser(@PathVariable("user_id")Long userID){
        return userService.getUserByID(userID);
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping("/{user_id}")
    public User updateUser(@PathVariable("user_id")Long userID,@RequestBody User user){
        return userService.updateUser(userID,user);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<?> updateUser(@PathVariable("user_id")Long userID){
        return userService.deleteUser(userID);
    }

}
