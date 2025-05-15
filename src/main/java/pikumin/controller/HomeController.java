// src/main/java/pikumin/controller/HomeController.java
//ログイン後のトップ画面（ホーム）やログインボーナス処理


package pikumin.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pikumin.model.User;
import pikumin.repository.UserRepository;
import pikumin.service.LoginBonusService;

@Controller
public class HomeController {

    @Autowired
    private LoginBonusService loginBonusService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                        .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません: " + principal.getName()));

        loginBonusService.handleLoginBonus(user);
        model.addAttribute("user", user);
        return "home";
    }

}
