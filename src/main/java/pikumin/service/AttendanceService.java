//src/main/java/pikumin/service/AttendanceService.java


package pikumin.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pikumin.model.User;
import pikumin.repository.UserRepository;

@Service
public class AttendanceService {

    @Autowired
    private UserRepository userRepository;

    //@Transactional
    public void recordAttendance(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // ここに勤怠時間記録ロジック（例：出勤・退勤打刻）
            // 例えば「最後の打刻日時を記録する」など

            // 例として現在時刻をログに出すだけにしておく
            System.out.println(username + " の勤怠を記録しました: " + LocalDateTime.now());

            // 変更あれば保存
            userRepository.save(user);
        }
    }
}
