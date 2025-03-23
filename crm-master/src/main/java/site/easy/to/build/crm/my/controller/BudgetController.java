package site.easy.to.build.crm.my.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.my.model.Budget;
import site.easy.to.build.crm.my.service.BudgetService;
import site.easy.to.build.crm.my.util.DateUtil;
import site.easy.to.build.crm.service.customer.CustomerService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Controller
public class BudgetController {

    @Autowired
    CustomerService customerService;

    @Autowired
    BudgetService budgetService;
    @GetMapping("/manager/form-budget")
    public String getFormAjouterBudget(Model model)
    {
        model.addAttribute("customers",customerService.findAll());
        model.addAttribute("budget", new Budget());
        return "my/add-budget";
    }

    @PostMapping("/manager/add-budget")
    public String addBudget(@ModelAttribute("budget") @Validated Budget budget,
                            BindingResult bindingResult,
                            Model model,
                            HttpServletRequest request)
    {


        if (bindingResult.hasErrors()) {
            model.addAttribute("budget", budget);
            model.addAttribute("customers", customerService.findAll());
            return "my/add-budget";
        }

        String idCustomer = request.getParameter("customerId");

        Customer customer = customerService.findByCustomerId(Integer.parseInt(idCustomer));
        budget.setCustomer(customer);

        budgetService.save(budget);
        return "redirect:/manager/form-budget";
    }

}
