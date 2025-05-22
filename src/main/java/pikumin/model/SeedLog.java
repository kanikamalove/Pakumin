// src/main/java/pikumin/model/SeedLog.java


package pikumin.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class SeedLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 成長状態との1対1の関連（必須）
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "growth_state_id", referencedColumnName = "id")
    private GrowthState growthState;

    // 対応する種との多対一（複数のログが1つの種に結びつくことも考えられる）
    @ManyToOne
    @JoinColumn(name = "seed_id")
    private Seed seed;

    // 最終更新日時（成長判定用）
    private LocalDateTime lastUpdated;

    // --- コンストラクタ ---
    public SeedLog() {
        this.lastUpdated = LocalDateTime.now(); // 初期化時に設定
    }

    // --- Getter / Setter ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GrowthState getGrowthState() {
        return growthState;
    }

    public void setGrowthState(GrowthState growthState) {
        this.growthState = growthState;
    }

    public Seed getSeed() {
        return seed;
    }

    public void setSeed(Seed seed) {
        this.seed = seed;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    // --- 更新トリガー（任意）---
    @PrePersist
    @PreUpdate
    public void updateTimestamp() {
        this.lastUpdated = LocalDateTime.now();
    }
}
