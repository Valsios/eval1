package site.easy.to.build.crm.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.my.service.CloneService;
import site.easy.to.build.crm.service.customer.CustomerService;

import java.io.IOException;

@Controller
public class CloneController {
    @Autowired
    CloneService cloneService ;

    @Autowired
    CustomerService customerService;

    @PostMapping("/manager/clone-customer/{id}")
    public String cloneCustomer(@PathVariable("id") int id)
    {
        Customer customer = customerService.findByCustomerId(id);
        try {
            cloneService.exportAll(customer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }
}
