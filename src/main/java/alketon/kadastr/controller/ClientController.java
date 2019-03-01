package alketon.kadastr.controller;

import alketon.kadastr.models.Client;
import alketon.kadastr.models.Contract;
import alketon.kadastr.models.Views;
import alketon.kadastr.repos.ClientRepo;
import alketon.kadastr.repos.ContractRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientRepo clientRepo;

    @Autowired
    public ClientController(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    @GetMapping("{id}")
    @JsonView(Views.ClientData.class)
    public Client getOne(@PathVariable("id") String id) {
        return clientRepo.findById(Long.valueOf(id)).get();
    }
}
