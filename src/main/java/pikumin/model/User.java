//src/main/java/pikumin/model/User.java


package pikumin.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;  // ログインID

    @Column(nullable = false)
    private String password;  // パスワード（Spring Security連携前提でハッシュ保存）

    @Column(nullable = false)
    private String displayName;  // 表示名（ニックネーム）

    @Column(nullable = false)
    private boolean isAdmin = false;  // 管理者フラグ

    @Column(nullable = false)
    private int loginStreak = 0;  // 継続ログイン日数

    @Column
    private LocalDate lastLoginDate;  // 最終ログイン日（継続判定に使用）

    // === コンストラクタ ===
    public User() {
    }

    public User(String username, String password, String displayName) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.isAdmin = false;
        this.loginStreak = 0;
        this.lastLoginDate = null;
    }

    // === Getter / Setter ===

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public int getLoginStreak() {
        return loginStreak;
    }

    public void setLoginStreak(int loginStreak) {
        this.loginStreak = loginStreak;
    }

    public LocalDate getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDate lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
}





//🔍 補足ポイント
//username と password は Spring Security 用に使用。
//
//loginStreak（継続ログイン日数）と lastLoginDate（最後のログイン）で「継続ボーナス」の処理が可能。
//
//isAdmin は管理者画面用の判定に使えます。
//
//パスワードは将来的に BCrypt などでハッシュ化して保存してください。
//Spring Boot + JPA 環境で使う User エンティティの基本形を以下に示します。
//ログイン機能 + 継続ログイン日数（ボーナス用）+ 管理者判定 などを含めた仕様です。

