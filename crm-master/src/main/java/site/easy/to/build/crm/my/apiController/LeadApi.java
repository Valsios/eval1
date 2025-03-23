package site.easy.to.build.crm.my.apiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import site.easy.to.build.crm.my.modelAPI.BudgetModel;
import site.easy.to.build.crm.my.modelAPI.LeadModel;
import site.easy.to.build.crm.my.service.BudgetService;
import site.easy.to.build.crm.my.service.DepenseService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LeadApi {
    @Autowired
    DepenseService depenseService;


    @GetMapping("/getAllLead")
    public ResponseEntity<List<LeadModel>> getAllLeadModels(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        List<LeadModel> leadModels = depenseService.getListLeadModel(date);
        return ResponseEntity.ok(leadModels);
    }

    @PostMapping("/deleteLead")
    @Transactional
    public ResponseEntity<String> deleteLead(@RequestParam("idLead") String idLead) {
        try {
            depenseService.deleteLead(idLead);
            return ResponseEntity.ok("Lead supprimé avec succès.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur : " + e.getMessage());
        }
    }

    @PostMapping("/updateLead")
    @Transactional
    public ResponseEntity<String> updateTicket(@RequestParam("idLead") String idLead,@RequestParam("newPrice") String newPrice) {
        try {
            depenseService.updateValeurLead(idLead,newPrice);
            return ResponseEntity.ok("Lead update avec succès.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur : " + e.getMessage());
        }
    }
}
