//src/main/java/pikumin/service/GrowthService.java
//花の成長

package pikumin.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pikumin.model.GrowthState;
import pikumin.model.SeedLog;
import pikumin.repository.GrowthStateRepository;
import pikumin.repository.SeedLogRepository;

@Service
public class GrowthService {

    @Autowired
    private SeedLogRepository seedLogRepository;

    @Autowired
    private GrowthStateRepository growthStateRepository;

    /**
     * 勤怠打刻に応じて種を成長させる。
     * 成長段階は一定時間（例：30分）ごとに進行。
     */
    public void progressGrowth(Long seedLogId) {
        Optional<SeedLog> optionalSeedLog = seedLogRepository.findById(seedLogId);
        if (optionalSeedLog.isEmpty()) return;

        SeedLog seedLog = optionalSeedLog.get();
        GrowthState state = seedLog.getGrowthState();

        LocalDateTime lastUpdated = seedLog.getLastUpdated();
        Duration elapsed = Duration.between(lastUpdated, LocalDateTime.now());

        // 30分ごとに1段階成長（最大10段階）
        long growthSteps = elapsed.toMinutes() / 30;
        int newLevel = Math.min(state.getLevel() + (int) growthSteps, 10);

        if (newLevel > state.getLevel()) {
            state.setLevel(newLevel);
            growthStateRepository.save(state);
            seedLog.setLastUpdated(LocalDateTime.now());
            seedLogRepository.save(seedLog);
        }
    }
}
