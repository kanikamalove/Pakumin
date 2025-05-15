// src/main/java/pikumin/controller/AttendanceController.java
//打刻・勤怠データの記録や確認

package pikumin.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pikumin.service.AttendanceService;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/stamp")
    public String stampAttendance(Principal principal) {
        attendanceService.recordAttendance(principal.getName());
        return "redirect:/home";
    }
}
