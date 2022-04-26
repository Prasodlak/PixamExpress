package com.greenfox.pixamexpress.controllers;

import com.greenfox.pixamexpress.models.Customer;
import com.greenfox.pixamexpress.repositories.CustomerRepo;
import com.greenfox.pixamexpress.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.List;

@Controller
public class WebController {

    private Long actuallOrder;
    private final CustomerRepo customerRepo;
    private final CustomerService customerService;

    @Autowired
    public WebController(CustomerRepo customerRepo, CustomerService customerService) {
        this.customerRepo = customerRepo;
        this.customerService = customerService;
        this.actuallOrder = null;
    }

    @GetMapping(value = {"/"})
    public String getIndex(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("pizzaSalami", "Salami");
        model.addAttribute("pizzaDiavola", "Diavola");
        model.addAttribute("pizzaProsc", "Prosciutto Cotto");
        model.addAttribute("pizzaFormaggi", "4 Formaggi");
        model.addAttribute("pizzaHawai", "Hawai");

        return "index";
    }

    @PostMapping(value = {"/order"})
    public ResponseEntity<Customer> postOrder(Customer customer, Model model) {
        customerRepo.save(customer);

        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping(value = {"/order/{id}"})
    public String getOrder(@PathVariable long id, Model model) {
        model.addAttribute("id", customerRepo.getById(id).getId());
        model.addAttribute("name", customerRepo.getById(id).getName());
        model.addAttribute("address", customerRepo.getById(id).getAddress());
        model.addAttribute("pizza", customerRepo.getById(id).getPizza());

        return "order";
    }

    @GetMapping(value = {"/api/orders/{id}"})
    public ResponseEntity<Customer> getApiOrder(@PathVariable long id, Customer customer, Model model) {
        model.addAttribute("id", customerRepo.getById(id).getId());
        String name = customerRepo.getById(id).getName();
        String address = customerRepo.getById(id).getAddress();
        String pizza = customerRepo.getById(id).getPizza();

        return new ResponseEntity<>(customerService.getCustomer(id, name, address, pizza), HttpStatus.FOUND);
    }

    @GetMapping(value = {"/statistics"})
    public String getStatistics() {

        return "statistics";
    }

    @GetMapping(value = {"/error"})
    public String getError() {
        return "error";
    }
}
