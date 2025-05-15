//src/main/java/pikumin/repository/SeedRepository.java


package pikumin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pikumin.model.Seed;
import pikumin.model.User;

public interface SeedRepository extends JpaRepository<Seed, Long> {
    
    // 所有者による検索
    List<Seed> findByOwner(User user);

    // 育成中の種だけを取得（"owner" と growing=true）
    List<Seed> findByOwnerAndGrowingTrue(User user);

    // 指定IDかつ所有者確認
    Seed findByIdAndOwner(Long id, User user); 
}
