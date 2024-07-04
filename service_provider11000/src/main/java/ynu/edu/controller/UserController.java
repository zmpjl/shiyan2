package ynu.edu.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;
import ynu.edu.entity.CommonResult;
import ynu.edu.entity.User;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/getUserById/{userId}")
    public CommonResult<User> getUserById(@PathVariable("userId") Integer userId){
        CommonResult<User> commonResult = new CommonResult<>();

        Integer code = 200;
        String message = "success(11000)";
        try {
            User user = new User(userId,"大明","123");
            commonResult.setResult(user);
        }
        catch (Exception e){
            code = 500;
            message = "error";
        }
        commonResult.setMessage(message);
        commonResult.setCode(code);
        return commonResult;
    }
}
