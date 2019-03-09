package alketon.kadastr.controller;

import alketon.kadastr.service.OrderService;
import alketon.kadastr.service.GenerationDoc;
import alketon.kadastr.service.GenerationXML;
import alketon.kadastr.models.Client;
import alketon.kadastr.models.Contract;
import alketon.kadastr.models.Views;
import alketon.kadastr.repos.ClientRepo;
import alketon.kadastr.repos.ContractRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("/api/order")
public class OrderApiController {

    @Autowired
    private ContractRepo contractRepo;
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private OrderService orderService;
    

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
    @JsonView(Views.ShowList.class)
    public List<Contract> search(@RequestBody Map<String, String> find) {
        return contractRepo.findByAddressObjectContainingAndFamilyNameContains(find.get("addressObject"), find.get("familyName"));
    }

    @PostMapping()
    public Map<String, String> create(@RequestBody Contract contract) {

        Client client = new Client();
        BeanUtils.copyProperties(contract, client, "id");
        contract.setDateOrder(new Date());
        contract.setStatus(1);
        contract.setClient(client);
        client.addContract(contract);

        orderService.addContract(contract);

        Map<String, String> request = new HashMap<>();
        try {
            clientRepo.save(client);
            request.put("req", "ok");
        } catch (Exception e) {
            request.put("req", "error!");
        }
        return request;
    }


    @PutMapping("{id}")
    public Contract update(@PathVariable("id") Contract contactFromDB, @RequestBody Contract contact) {
        BeanUtils.copyProperties(contact, contactFromDB, "id", "client", "dateOrder");

        //Отпавка сообщения
        if (!contractRepo.findById(contactFromDB.getId()).get().getStatus().equals(contactFromDB.getStatus())) {
            orderService.editStatusContract(contact);
        }

        return contractRepo.save(contactFromDB);
    }

    @RequestMapping(value = "/files/{id}/{file_name:.+}", method = RequestMethod.GET)
    public void getFile(@PathVariable("id") String id,
                        @PathVariable("file_name") String fileName,
                        HttpServletResponse response) {
        // Прежде всего стоит проверить, если необходимо, авторизован ли пользователь и имеет достаточно прав на скачивание файла. Если нет, то выбрасываем здесь Exception
        //TODO

        //Авторизованные пользователи смогут скачать файл
        
        orderService.getFile(id, fileName, response);

    }
}