// src/main/java/pikumin/controller/AdminController.java
//管理者用の機能（勤務確認・追加)

package pikumin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pikumin.model.User;
import pikumin.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin/dashboard";
    }

    @PostMapping("/addAttendance")
    public String addAttendance(@RequestParam Long userId, @RequestParam int minutes) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            // 勤怠時間を追加する処理を実装（仮想ロジック）
        }
        return "redirect:/admin/dashboard";
    }
}
