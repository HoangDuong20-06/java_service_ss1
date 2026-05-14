package com.example.session02_exercise05.controller;

import com.example.session02_exercise05.exception.InvalidWishException;
import com.example.session02_exercise05.model.Wish;
import com.example.session02_exercise05.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/genie")
@RequiredArgsConstructor
public class GenieController {

    private final WishService wishService;

    // Điều ước 1: Tạo một item mới (POST)
    @PostMapping("/wish/create-item")
    public ResponseEntity<Wish> createItem(@RequestBody Map<String, String> request) {
        String itemName = request.get("itemName");
        if (itemName == null || itemName.trim().isEmpty()) {
            throw new InvalidWishException("itemName không được để trống!");
        }
        
        Wish wish = wishService.makeWish("CREATE_ITEM", "Tạo item: " + itemName);
        return ResponseEntity.ok(wish);
    }

    // Điều ước 2: Thay đổi trạng thái của item (PUT)
    @PutMapping("/wish/change-status")
    public ResponseEntity<Wish> changeStatus(@RequestBody Map<String, String> request) {
        String itemId = request.get("itemId");
        String newStatus = request.get("newStatus");
        
        if (itemId == null || itemId.trim().isEmpty()) {
            throw new InvalidWishException("itemId không được để trống!");
        }
        if (newStatus == null || newStatus.trim().isEmpty()) {
            throw new InvalidWishException("newStatus không được để trống!");
        }
        
        Wish wish = wishService.makeWish("CHANGE_STATUS", "Thay đổi trạng thái item " + itemId + " thành " + newStatus);
        return ResponseEntity.ok(wish);
    }

    // Điều ước 3: Nhận thông tin ngẫu nhiên (GET)
    @GetMapping("/wish/random-info")
    public ResponseEntity<Wish> getRandomInfo() {
        String[] randomInfos = {
            "Bạn sẽ gặp may mắn trong tuần này!",
            "Cơ hội đang chờ đợi bạn ở góc đường.",
            "Hãy kiên nhẫn, thành công sẽ đến.",
            "Một người bạn cũ sẽ liên hệ với bạn.",
            "Đừng bỏ cuộc, bạn đang đi đúng hướng."
        };
        Random random = new Random();
        String info = randomInfos[random.nextInt(randomInfos.length)];
        
        Wish wish = wishService.makeWish("RANDOM_INFO", info);
        return ResponseEntity.ok(wish);
    }

    // API xem lịch sử điều ước
    @GetMapping("/wishes/history")
    public ResponseEntity<List<Wish>> getWishHistory() {
        List<Wish> history = wishService.getWishHistory();
        return ResponseEntity.ok(history);
    }

    // API xem số lượt ước còn lại
    @GetMapping("/wishes/remaining")
    public ResponseEntity<Map<String, Integer>> getRemainingWishes() {
        Map<String, Integer> response = Map.of("remaining", wishService.getRemainingWishes());
        return ResponseEntity.ok(response);
    }

    // API reset (để test)
    @PostMapping("/wishes/reset")
    public ResponseEntity<Map<String, String>> resetWishes() {
        WishService.reset();
        return ResponseEntity.ok(Map.of("message", "Đã reset số lượt ước!"));
    }
}
