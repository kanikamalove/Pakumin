//src/main/java/pikumin/repository/FlowerRepository.java

package pikumin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pikumin.model.Flower;
import pikumin.model.User;

public interface FlowerRepository extends JpaRepository<Flower, Long> {
	
    // 特定ユーザーが咲かせた花の一覧を取得
    List<Flower> findByUserId(Long userId);
    // レアリティ順でソート（例：図鑑画面用）
    List<Flower> findByUserIdOrderByRarityDesc(Long userId);
    // 花の名前で検索（任意）
    List<Flower> findByNameContainingIgnoreCase(String name);
    
    List<Flower> findByUser(User user);
}
