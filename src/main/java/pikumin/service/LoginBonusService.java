//src/main/java/pikumin/service/LoginBonusService.java


package pikumin.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pikumin.model.User;
import pikumin.repository.UserRepository;

@Service
public class LoginBonusService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SeedService seedService;

    @Transactional
    public void handleLoginBonus(User user) {
        LocalDate today = LocalDate.now();
        LocalDate lastLogin = user.getLastLoginDate();

        if (lastLogin == null || !lastLogin.isEqual(today)) {
            if (lastLogin != null && lastLogin.plusDays(1).isEqual(today)) {
                user.setLoginStreak(user.getLoginStreak() + 1);
            } else {
                user.setLoginStreak(1);
            }
            user.setLastLoginDate(today);
            userRepository.save(user);

            seedService.giveRandomSeed(user);
        }
    }
}


//✅ LoginBonusService.java
//このサービスでは、以下を処理します：
//
//今日初めてログインか判定
//
//継続ログイン数を増やす or リセット
//
//種をランダムに1つ付与（SeedService と連携予定）
//
//
//🔧 SeedService.giveRandomSeed(User user) は後で定義します
//このメソッドで：
//
//継続日数に応じてレアリティ判定
//
//Seed エンティティを生成して保存


