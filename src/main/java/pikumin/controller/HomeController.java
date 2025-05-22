// src/main/java/pikumin/controller/HomeController.java
//ログイン後のトップ画面（ホーム）やログインボーナス処理

// src/main/java/pikumin/controller/HomeController.java
// ログイン後のトップ画面（ホーム）やログインボーナス処理

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

    // ルートパスにアクセスされたらログイン状態で振り分け
    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        System.out.println("=== /home accessed ===");
        
        if (principal == null) {
            System.out.println("Principal is null -> redirecting to /login");
            return "redirect:/login";
        }

        String username = principal.getName();
        System.out.println("Logged-in username: " + username);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません: " + username));
        
        System.out.println("User loaded from DB: " + user.getUsername());

        model.addAttribute("user", user);
        return "home";
    }


}