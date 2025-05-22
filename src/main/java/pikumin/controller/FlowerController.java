// src/main/java/pikumin/controller/FlowerController.java
//育成スロットや図鑑など「花」の管理


package pikumin.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pikumin.model.Flower;
import pikumin.model.Seed;
import pikumin.service.FlowerService;

@Controller
@RequestMapping("/flowers")
public class FlowerController {

    @Autowired
    private FlowerService flowerService;

    // 育成中の種（スロット）一覧を表示
    @GetMapping("/grow")
    public String growingSlots(Model model, Principal principal) {
        String username = principal.getName();
        List<Seed> growingSeeds = flowerService.getGrowingSeeds(username);
        model.addAttribute("growingSeeds", growingSeeds);
        return "flower/grow";
    }

    // 種を育成スロットにセット
    @PostMapping("/set")
    public String setSeedToSlot(@RequestParam Long seedId, Principal principal) {
        String username = principal.getName();
        flowerService.setSeedToGrow(username, seedId);
        return "redirect:/flowers/grow";
    }

    // 咲いた花（図鑑）一覧を表示
    @GetMapping("/zukan")
    public String flowerZukan(Model model, Principal principal) {
        String username = principal.getName();
        List<Flower> flowers = flowerService.getBlossomedFlowers(username);
        model.addAttribute("flowers", flowers);
        return "flower/zukan";
    }
}
