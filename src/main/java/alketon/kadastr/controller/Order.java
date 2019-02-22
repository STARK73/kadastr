package alketon.kadastr.controller;

import alketon.kadastr.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("order")
public class Order {

    private int count = 4;

    private List<Map<String, String>> order = new ArrayList<Map<String, String>>() {{
       add(new HashMap<String, String>() {{ put("id", "1"); put("text", "Межевание земельных участков"); }});
       add(new HashMap<String, String>() {{ put("id", "2"); put("text", "Подготовка техннических планов"); }});
       add(new HashMap<String, String>() {{ put("id", "3"); put("text", "Вынос границ земельного участка"); }});
       add(new HashMap<String, String>() {{ put("id", "4"); put("text", "Топографическая съемка"); }});
    }};

    @GetMapping
    public List<Map<String, String>> list() {
        return order;
    }

    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return getOrder(id);
    }

    private Map<String, String> getOrder(@PathVariable String id) {
        return order.stream()
                .filter(order -> order.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(count++));
        order.add(message);
        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message) {
        Map<String, String> orderFromDb = getOrder(id);

        orderFromDb.putAll(message);
        orderFromDb.put("id", id);
        return orderFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Map<String, String> message = getOrder(id);
        order.remove(message);
    }
}
