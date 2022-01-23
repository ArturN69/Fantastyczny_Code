package pl.sda.springwebmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.springwebmvc.entity.LaptopEntity;
import pl.sda.springwebmvc.model.Laptop;
import pl.sda.springwebmvc.service.LaptopService;

import java.util.List;

@Controller
@RequestMapping("/laptop")
public class LaptopController {
    private final LaptopService laptopService;

    public LaptopController(LaptopService laptopService) {
        this.laptopService = laptopService;
    }

    @ModelAttribute("laptops")
    public List<LaptopEntity> laptopEntities(){
        return laptopService.findAll();
    }

    @GetMapping("/add-form")
    public String showAddForm(Model model){
        model.addAttribute("laptop", Laptop.builder().build());
        return "/laptop/add-form";
    }

    @PostMapping("/add")
    public String add(Laptop laptop){
        laptopService.save(laptop);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String list(){
        return "/laptop/list";
    }
}
