// src/main/java/pikumin/model/GrowthState.java
//成長状態を保持


package pikumin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GrowthState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int level = 0; // 成長段階（0〜10など）

    private boolean isBloomed = false; // 開花状態フラグ

    // --- Getter / Setter ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isBloomed() {
        return isBloomed;
    }

    public void setBloomed(boolean bloomed) {
        isBloomed = bloomed;
    }
}
