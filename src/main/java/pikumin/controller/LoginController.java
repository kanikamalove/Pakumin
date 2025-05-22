// src/main/java/pikumin/controller/LoginController.java

package pikumin.controller;
import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String loginPage(Principal principal) {
	    if (principal != null) {
	        return "redirect:/home";
	    }
	    return "login";
	}
}