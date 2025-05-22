package pikumin.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pikumin.model.Attendance;
import pikumin.model.User;
import pikumin.repository.AttendanceRepository;
import pikumin.repository.UserRepository;

@Service
public class AttendanceService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    public void recordAttendance(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            System.out.println("ユーザーが見つかりません: " + username);
            return;
        }

        User user = optionalUser.get();

        // 直近の勤怠記録を取得（終了時間がnullのもの＝まだ退勤していないレコード）
        List<Attendance> ongoingAttendances = attendanceRepository.findByUserId(user.getId());
        Attendance latestAttendance = ongoingAttendances.stream()
                .filter(a -> a.getEndTime() == null)
                .findFirst()
                .orElse(null);

        LocalDateTime now = LocalDateTime.now();

        if (latestAttendance == null) {
            // 出勤打刻
            Attendance attendance = new Attendance();
            attendance.setUser(user);
            attendance.setStartTime(now);
            attendanceRepository.save(attendance);
            System.out.println(username + " が出勤打刻しました: " + now);
        } else {
            // 退勤打刻
            latestAttendance.setEndTime(now);
            attendanceRepository.save(latestAttendance);
            System.out.println(username + " が退勤打刻しました: " + now);
        }
    }
}
