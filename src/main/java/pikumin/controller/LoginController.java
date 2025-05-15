// src/main/java/pikumin/controller/LoginController.java

package pikumin.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import pikumin.model.User;
import pikumin.repository.UserRepository;
import pikumin.service.LoginBonusService;

@Controller
public class LoginController {

    @Autowired
    private LoginBonusService loginBonusService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")  // Spring Securityに統合している場合は別設計も可
    public String login(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null) {
            loginBonusService.handleLoginBonus(user);
        }

        return "redirect:/home";
    }
}
