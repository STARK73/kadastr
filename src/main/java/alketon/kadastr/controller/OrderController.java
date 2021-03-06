package alketon.kadastr.controller;

import alketon.kadastr.models.Client;
import alketon.kadastr.models.Contract;
import alketon.kadastr.repos.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private ClientRepo clientRepo;

    @GetMapping
    public ModelAndView test() {
        Map<String, String> model = new HashMap<>();
        model.put("id", "1");
        model.put("text", "test");

         List<Map<String, String>> order = new ArrayList<Map<String, String>>() {{
            add(new HashMap<String, String>() {{ put("id", "1"); put("text", "Межевание земельных участков"); }});
            add(new HashMap<String, String>() {{ put("id", "2"); put("text", "Подготовка техннических планов"); }});
            add(new HashMap<String, String>() {{ put("id", "3"); put("text", "Вынос границ земельного участка"); }});
            add(new HashMap<String, String>() {{ put("id", "4"); put("text", "Топографическая съемка"); }});
        }};
        return new ModelAndView("order", model);
    }

    @GetMapping("{id}")
    public ModelAndView getOne(@PathVariable String id) {
        Map<String, String> model = new HashMap<>();
        model.put("id", "1");
        model.put("text", "test");

        List<Map<String, String>> order = new ArrayList<Map<String, String>>() {{
            add(new HashMap<String, String>() {{ put("id", "1"); put("text", "Межевание земельных участков"); }});
            add(new HashMap<String, String>() {{ put("id", "2"); put("text", "Подготовка техннических планов"); }});
            add(new HashMap<String, String>() {{ put("id", "3"); put("text", "Вынос границ земельного участка"); }});
            add(new HashMap<String, String>() {{ put("id", "4"); put("text", "Топографическая съемка"); }});
        }};
        return new ModelAndView("order", model);
    }

    @PostMapping()
    public Map<String, String> create(@RequestBody Map<String, String> order) {

        Client client = new Client();
        Contract contact = new Contract();
        contact.setAddressObject(order.get("addressObject"));
        contact.setTypeProperty(order.get("typeProperty"));
        contact.setTargetPlacing(order.get("targetPlacing"));
        contact.setEntitlingType(order.get("entitlingType"));
        contact.setEntitlingNum(order.get("entitlingNum"));
        contact.setDateOrder(new Date());
        contact.setStatus(1);
        contact.setClient(client);


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


        client.addContract(contact);

        Map<String, String> request = new HashMap<>();
        try {
            clientRepo.save(client);
            request.put("req", "ok");
        }catch (Exception e) {
            request.put("req", "error!");
        }
        return request;
    }
}
