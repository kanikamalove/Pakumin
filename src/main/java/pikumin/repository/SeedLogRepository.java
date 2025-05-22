// src/main/java/pikumin/repository/SeedLogRepository.java

package pikumin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pikumin.model.SeedLog;

public interface SeedLogRepository extends JpaRepository<SeedLog, Long> {
}
