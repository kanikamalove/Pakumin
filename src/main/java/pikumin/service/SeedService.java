// src/main/java/pikumin/service/SeedService.java
//	種に関する操作（配布、所持種の一覧、削除など）

package pikumin.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pikumin.model.Seed;
import pikumin.model.User;
import pikumin.repository.SeedRepository;

@Service
public class SeedService {

    @Autowired
    private SeedRepository seedRepository;

    private static final int MAX_SEED_COUNT = 10;

    private Random random = new Random();

    /**
     * ユーザーにランダムな種を付与する。
     * 最大所持数を超えていれば付与しない。
     * 連続ログイン日数に応じてレア種が出やすくなる。
     */
    public void giveRandomSeed(User user) {
        List<Seed> userSeeds = seedRepository.findByOwner(user);

        if (userSeeds.size() >= MAX_SEED_COUNT) {
            System.out.println("所持できる種の上限に達しています。ユーザーID: " + user.getId());
            return;
        }

        int streak = user.getLoginStreak();
        // レア種出現確率 5% + 連続ログイン日数の2%（最大50%）
        double rareChance = Math.min(0.05 + (streak * 0.02), 0.5);

        boolean isRare = random.nextDouble() < rareChance;

        Seed newSeed = new Seed();
        newSeed.setRare(isRare);
        newSeed.setOwner(user);
        newSeed.setName(generateSeedName(isRare));

        seedRepository.save(newSeed);

        System.out.println("新しい種を付与しました: " + newSeed.getName() + " (レア:" + isRare + ")");
    }

    private String generateSeedName(boolean isRare) {
        if (isRare) {
            return "レアフラワー" + random.nextInt(100);
        } else {
            return "ノーマルフラワー" + random.nextInt(100);
        }
    }
}
