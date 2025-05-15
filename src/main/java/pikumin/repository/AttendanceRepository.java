//src/main/java/pikumin/repository/AttendanceRepository.java
//勤怠情報を管理


package pikumin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pikumin.model.Attendance;

//@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // 特定ユーザーの勤怠履歴を取得
    List<Attendance> findByUserId(Long userId);

    // 日付範囲で検索（必要に応じて）
    List<Attendance> findByUserIdAndStartTimeBetween(Long userId, java.time.LocalDateTime start, java.time.LocalDateTime end);
}
