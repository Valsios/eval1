package site.easy.to.build.crm.my.apiController;


import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import site.easy.to.build.crm.my.model.SeuilBudget;
import site.easy.to.build.crm.my.modelAPI.BudgetModel;
import site.easy.to.build.crm.my.service.BudgetService;
import site.easy.to.build.crm.my.service.DepenseService;
import site.easy.to.build.crm.my.service.SeuilBudgetService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BudgetApi {

    @Autowired
    DepenseService depenseService;

    @Autowired
    BudgetService budgetService;

    @Autowired
    SeuilBudgetService seuilBudgetService;
    @GetMapping("/getAllBudget")
    public ResponseEntity<List<BudgetModel>> getAllBudgetModels(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        List<BudgetModel> budgets = budgetService.getListBudgetModel(date);
        return ResponseEntity.ok(budgets);
    }

    @PostMapping("/updateSeuil")
    @Transactional
    public ResponseEntity<String> updateTicket(@RequestParam("newSeuil") String newSeuil) {
        try {
            SeuilBudget seuilBudget = seuilBudgetService.getSeuilBudget();
            seuilBudget.setValue(Double.parseDouble(newSeuil));
            seuilBudget.setDateSet(LocalDateTime.now());
            seuilBudgetService.save(seuilBudget);
            return ResponseEntity.ok("Seuil update avec succès.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur : " + e.getMessage());
        }
    }
}
