package alketon.kadastr.controller;

import alketon.kadastr.models.Client;
import alketon.kadastr.models.Contract;
import alketon.kadastr.models.Views;
import alketon.kadastr.repos.ClientRepo;
import alketon.kadastr.repos.ContractRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/orderapi")
public class OrderApiController {

    private final ContractRepo contractRepo;
    private final ClientRepo clientRepo;

    @Autowired
    public OrderApiController(ContractRepo contractRepo, ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
        this.contractRepo = contractRepo;
    }

    @GetMapping
    @JsonView(Views.ShowList.class)
    public List<Contract> list() {
       return contractRepo.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullContract.class)
    public Contract getOne(@PathVariable("id") Contract contract) {
        return contract;
    }

    @PostMapping("/search")
    @JsonView(Views.FullContract.class)
    public List<Contract> search(@RequestBody Map<String, String> find) {
        return contractRepo.findByAddressObjectContainingAndFamilyNameContains(find.get("addressObject"),  find.get("familyName"));
    }

    @PostMapping()
    public Map<String, String> create(@RequestBody Map<String, String> order) {

        Client client = new Client();
        //TODO поиск

        Contract contact = new Contract();
        contact.setFamilyName(order.get("familyName"));
        contact.setFirstName(order.get("firstName"));
        contact.setPatronymicName(order.get("patronymicName"));
        contact.setEmail(order.get("email"));
        contact.setPhoneMobile(order.get("phoneMobile"));
        contact.setPhoneHome(order.get("phoneHome"));
        contact.setPhoneJob(order.get("phoneJob"));
        contact.setPasSerialNumber(order.get("pasSerialNumber"));
        contact.setPasIssued(order.get("pasIssued"));
        contact.setSnils(order.get("snils"));
        contact.setAddressResidence(order.get("addressResidence"));
        contact.setAddressRegistration(order.get("addressRegistration"));
        contact.setDateBirth(order.get("dateBirth"));
        contact.setPasDate(order.get("pasDate"));

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