package com.example.session02_exercise05.service;

import com.example.session02_exercise05.exception.OutOfWishesException;
import com.example.session02_exercise05.model.Wish;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class WishService {
    private static final int MAX_WISHES = 3;
    private static int wishCount = 0;
    private static final List<Wish> wishHistory = new ArrayList<>();
    private static final AtomicLong idGenerator = new AtomicLong(1);

    public Wish makeWish(String type, String description) {
        if (wishCount >= MAX_WISHES) {
            Wish rejectedWish = new Wish();
            rejectedWish.setId(idGenerator.getAndIncrement());
            rejectedWish.setType(type);
            rejectedWish.setDescription(description);
            rejectedWish.setStatus("REJECTED");
            rejectedWish.setTimestamp(LocalDateTime.now());
            wishHistory.add(rejectedWish);
            throw new OutOfWishesException("Thần đèn đã hết lượt ước!");
        }

        Wish wish = new Wish();
        wish.setId(idGenerator.getAndIncrement());
        wish.setType(type);
        wish.setDescription(description);
        wish.setStatus("GRANTED");
        wish.setTimestamp(LocalDateTime.now());
        
        wishHistory.add(wish);
        wishCount++;
        
        return wish;
    }

    public List<Wish> getWishHistory() {
        return new ArrayList<>(wishHistory);
    }

    public int getRemainingWishes() {
        return MAX_WISHES - wishCount;
    }

    public static void reset() {
        wishCount = 0;
        wishHistory.clear();
    }
}
