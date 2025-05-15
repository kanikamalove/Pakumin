//src/main/java/pikumin/service/AdminService.java
//管理者用 勤怠記録・ユーザー確認


package pikumin.service;

import java.time.Duration;
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
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    /**
     * 指定ユーザーの全勤怠情報を取得
     */
    public List<Attendance> getUserAttendanceHistory(Long userId) {
        return attendanceRepository.findByUserId(userId);
    }

    /**
     * 管理者が勤怠記録を追加（手動登録用）
     */
    public void addAttendanceRecord(Long userId, LocalDateTime start, LocalDateTime end) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) return;

        Attendance attendance = new Attendance();
        attendance.setUser(optionalUser.get());
        attendance.setStartTime(start);
        attendance.setEndTime(end);
        attendance.setRecordedByAdmin(true);

        attendanceRepository.save(attendance);
    }

    /**
     * 全ユーザーの勤怠合計時間などの集計が必要なら追加
     */
    public long getTotalAttendanceMinutes(Long userId) {
        List<Attendance> records = attendanceRepository.findByUserId(userId);
        return records.stream()
            .mapToLong(a -> Duration.between(a.getStartTime(), a.getEndTime()).toMinutes())
            .sum();
    }
}
