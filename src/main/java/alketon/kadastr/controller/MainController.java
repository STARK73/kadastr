package alketon.kadastr.controller;

import alketon.kadastr.repos.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
    private final ContractRepo contractRepo;

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    public MainController(ContractRepo messageRepo) {
        this.contractRepo = messageRepo;
    }

    @GetMapping
    public String main(Model model) {
        HashMap<Object, Object> data = new HashMap<>();

        data.put("orders", contractRepo.findAll());

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));

        return "index";
    }
}