package alketon.kadastr.controller;

import alketon.kadastr.exceptions.NotFoundException;
import alketon.kadastr.models.Client;
import alketon.kadastr.repos.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    private int count = 4;

    private List<Map<String, String>> order = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("text", "Межевание земельных участков");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("text", "Подготовка техннических планов");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("text", "Вынос границ земельного участка");
        }});
        add(new HashMap<String, String>() {{
            put("id", "4");
            put("text", "Топографическая съемка");
        }});
    }};

    @Autowired
    private ClientRepo clientRepo;

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
    public Map<String, String> create(@RequestBody Map<String, String> order) {
        order.put("id", String.valueOf(count++));

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MMM.yyyy");

        Client client = new Client();
        client.setFamilyName(order.get("familyName"));
        client.setFirstName(order.get("firstName"));
        client.setPatronymicName(order.get("patronymicName"));
        client.setEmail(order.get("email"));
        client.setPhoneMobile(order.get("phoneMobile"));
        client.setPhoneHome(order.get("phoneHome"));
        client.setPhoneJob(order.get("phoneJob"));
        client.setPasSerialNumber(order.get("pasSerialNumber"));
        client.setPasIssued(order.get("pasIssued"));
        client.setSnils(order.get("snils"));
        client.setAddressResidence(order.get("addressResidence"));
        client.setAddressRegistration(order.get("addressRegistration"));
        client.setDateBirth(order.get("dateBirth"));
        client.setPasDate(order.get("pasDate"));
        clientRepo.save(client);

        Map<String, String> request = new HashMap<>();
        request.put("status", "ok");
        return request;
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
