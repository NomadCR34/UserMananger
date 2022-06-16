package ir.amin.app.usermanager.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
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
}
