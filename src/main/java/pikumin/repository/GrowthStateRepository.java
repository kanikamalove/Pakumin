// src/main/java/pikumin/repository/GrowthStateRepository.java

package pikumin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pikumin.model.GrowthState;

@Repository
public interface GrowthStateRepository extends JpaRepository<GrowthState, Long> {
    // 必要に応じてカスタムクエリを追加
}
