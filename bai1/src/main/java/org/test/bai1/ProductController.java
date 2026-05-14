package org.test.bai1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @GetMapping("/hot")
    public List<Product> getHotProducts() {

        List<Product> products = new ArrayList<>();

        products.add(new Product(
                "HP001",
                "Ao thun 'Code is Life'",
                199000
        ));

        products.add(new Product(
                "HP002",
                "Moc khoa 'Bug Free'",
                99000
        ));

        products.add(new Product(
                "HP003",
                "Sticker Java Spring",
                49000
        ));

        return products;
    }
}
