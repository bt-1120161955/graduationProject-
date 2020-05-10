package com.plj.back.Controller;

import com.plj.back.Entities.User;
import com.plj.back.Service.UserService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@Controller
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("/register")
    @ResponseBody
    public String Register(@RequestBody User user) {
        String username = user.getUsername();
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);

        String password = user.getPassword();
        boolean isExist = userService.isExist(username);
        if(isExist) {
            return "用户已经存在";
        }
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times =2;
        String algorithm = "md5";
        String pwdAlgorithm = new SimpleHash(algorithm,password, salt, times).toString();
        user.setSalt(salt);
        user.setPassword(pwdAlgorithm);
        userService.addUser(user);
        return "成功";
    }

    @PostMapping("/login")
    @ResponseBody
    public Integer Login(@RequestBody User user) {
        String username = user.getUsername();
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        String password = user.getPassword();
        String algorithm = "md5";
        int times = 2;

        boolean isExist = userService.isExist(username);
        if(isExist) {
            User rightUser = userService.getUserByUsername(username);
            String pwdAlgorithm = new SimpleHash(algorithm,password, rightUser.getSalt(), times).toString();

            boolean login = pwdAlgorithm.equals(rightUser.getPassword());
            if(login)
                return 1;
            else
                return 0;
        } else {
            return 0;
        }
    }
}
