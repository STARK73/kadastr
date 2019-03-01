package alketon.kadastr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderListController {

    @GetMapping("/orders")
    public String getOrders() {
        return "orders";
    }

    @GetMapping("/orders/{id}")
    public String getOrderItem() {
        return "orderitem";
    }
}
