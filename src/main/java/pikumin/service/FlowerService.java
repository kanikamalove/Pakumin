//src/main/java/pikumin/service/FlowerService.java
//	花に関する操作（育成スロット管理、図鑑管理など）


package pikumin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pikumin.model.Flower;
import pikumin.model.Seed;
import pikumin.model.User;
import pikumin.repository.FlowerRepository;
import pikumin.repository.SeedRepository;
import pikumin.repository.UserRepository;

@Service
public class FlowerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SeedRepository seedRepository;

    @Autowired
    private FlowerRepository flowerRepository;

    /**
     * 育成中の種リストを取得（最大3つ）
     */
    public List<Seed> getGrowingSeeds(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません: " + username));

        return seedRepository.findByOwnerAndGrowingTrue(user);
    }

    /**
     * 種を育成スロットにセット（最大3スロット制限はフロント側で制御）
     */
    public void setSeedToGrow(String username, Long seedId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません: " + username));

        Seed seed = seedRepository.findByIdAndOwner(seedId, user); // ← 修正済み
        if (seed == null) {
            throw new RuntimeException("この種は存在しないか、あなたのものではありません");
        }

        seed.setGrowing(true);
        seedRepository.save(seed);
    }


    /**
     * ユーザーが咲かせた花を図鑑用に取得
     */
    public List<Flower> getBlossomedFlowers(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません: " + username));

        return flowerRepository.findByUser(user);
    }
}
