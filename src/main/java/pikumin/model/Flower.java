// src/main/java/pikumin/model/Flower.java

package pikumin.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Flower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int rarity; // レア度（1〜5など）

    private LocalDateTime bloomDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // --- コンストラクタ ---
    public Flower() {}

    public Flower(String name, int rarity, LocalDateTime bloomDate, User user) {
        this.name = name;
        this.rarity = rarity;
        this.bloomDate = bloomDate;
        this.user = user;
    }

    // --- Getter / Setter ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public LocalDateTime getBloomDate() {
        return bloomDate;
    }

    public void setBloomDate(LocalDateTime bloomDate) {
        this.bloomDate = bloomDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
