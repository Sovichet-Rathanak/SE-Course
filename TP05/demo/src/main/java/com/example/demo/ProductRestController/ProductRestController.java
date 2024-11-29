package com.example.demo.ProductRestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/TP05")
public class ProductRestController {

    static ArrayList<Products> products_list = new ArrayList<>();

    public static void prepopulate(){
        products_list.add(new Products("Flower Boy", "VNL01", "USA", 10.00, 7.99, "tiger-shark.jpg", "Vinyl from Tyler the Creator"));
        products_list.add(new Products("IGOR", "VNL02", "USA", 10.00, 7.99, "AngkorWat.jpg", "Vinyl from Tyler the Creator"));
    }

    @GetMapping("/Task1") 
    public String task1(Model model) {
        model.addAttribute("products_list", products_list);
        return "tasks/Task1";
    }

    @GetMapping("/Add")
    public String showAddForm() {
        return "tasks/Add"; 
    }

    @PostMapping("/Add")
    public String addProduct(
            @RequestParam String name,
            @RequestParam String code,
            @RequestParam String origin,
            @RequestParam double cost,
            @RequestParam double price,
            @RequestParam MultipartFile image,
            @RequestParam String description) throws IOException {
        
        Path pathImage = Paths.get("src/main/resources/static/image/" + image.getOriginalFilename());
        Files.createDirectories(pathImage.getParent());
        Files.write(pathImage, image.getBytes());
        String imageUrl = image.getOriginalFilename();
        System.out.println(imageUrl);
        Products new_product = new Products(name, code, origin, price, cost, imageUrl, description);
        products_list.add(new_product);
        
        //Go back to Task1
        return "redirect:/TP05/Task1";

    }

    @GetMapping("/Edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        String url = "/TP05/Edit/product/" + id ;
        model.addAttribute("url", url);
        return "tasks/Edit"; 
    }

    @PostMapping("/Edit/product/{id}")
    public String editProduct(@PathVariable int id, @RequestParam String name, @RequestParam double price) {
        Products p = products_list.get(id);
        p.setProduct_name(name);
        p.setPrice(price);
        return "redirect:/TP05/Task1";
    }
}