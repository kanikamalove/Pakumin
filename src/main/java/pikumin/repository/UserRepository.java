//src/main/java/pikumin/repository/UserRepository.java

package pikumin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pikumin.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // ユーザー名で検索（ログイン用）
    Optional<User> findByUsername(String username);

    // ユーザー名が存在するかチェック
    boolean existsByUsername(String username);
}



//findByUsername() は Spring Security 認証でも使われる重要メソッド。
//
//existsByUsername() は新規登録時のバリデーション用。