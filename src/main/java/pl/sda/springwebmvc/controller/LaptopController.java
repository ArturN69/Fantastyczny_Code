package pl.sda.springwebmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.springwebmvc.model.Laptop;

@Controller
@RequestMapping("/laptop")
public class LaptopController {

    @GetMapping("/add-form")
    public String showAddForm(Model model){
        model.addAttribute("laptop", Laptop.builder().build());
        return "/laptop/add-form";
    }

    @PostMapping("/add")
    public String add(Laptop laptop){
        return "redirect:/";
    }
}
