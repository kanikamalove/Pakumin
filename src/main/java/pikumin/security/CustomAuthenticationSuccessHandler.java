//src/main/java/pikumin/security/CustomAuthenticationSuccessHandler.java

package pikumin.security;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import pikumin.model.User;
import pikumin.repository.UserRepository;
import pikumin.service.LoginBonusService;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginBonusService loginBonusService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
        System.out.println("=== Login Success Handler Invoked ===");
        
        String username = authentication.getName();
        System.out.println("Authenticated username: " + username);
        
        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null) {
            System.out.println("User found in DB: " + user.getUsername());
            loginBonusService.handleLoginBonus(user);
            user.setLastLoginDate(LocalDate.now());
            userRepository.save(user);
        } else {
            System.out.println("User NOT found in DB");
        }

        System.out.println("Redirecting to /home");
        response.sendRedirect("/home");
    }
}
