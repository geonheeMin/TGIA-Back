package capstone.market.bugigram;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @PostMapping (value = "create/user")
//    public void createUser(@RequestBody UserDTO userDTO){
//       userService.createUser(userDTO);
//    }

    @PostMapping(value = "create/user")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        try {
             UserDTO user = userService.createUser(userDTO);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user");
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO) {
        try {
            UserDTO loggedInUser = userService.loginUser(userDTO.getEmail(), userDTO.getPassword());
            if (loggedInUser != null) {
                System.out.println("성공");
                return ResponseEntity.ok(loggedInUser);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to log in");
        }
    }


    //모든 유저 조회하기
    @GetMapping("/allusers")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        try {
            List<UserDTO> allUsers = userService.getAllUsers();
            return ResponseEntity.ok(allUsers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }




}
