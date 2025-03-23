package site.easy.to.build.crm.my.apiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import site.easy.to.build.crm.my.modelAPI.LeadModel;
import site.easy.to.build.crm.my.modelAPI.TicketModel;
import site.easy.to.build.crm.my.service.DepenseService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketApi {
    @Autowired
    DepenseService depenseService;
    @GetMapping("/getAllTicket")
    public ResponseEntity<List<TicketModel>> getAllLeadModels(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        List<TicketModel> ticketModels = depenseService.getListTicketModel(date);
        return ResponseEntity.ok(ticketModels);
    }

    @PostMapping("/deleteTicket")
    @Transactional
    public ResponseEntity<String> deleteLead(@RequestParam("idTicket") String idTicket) {
        try {
            depenseService.deleteTicket(idTicket);
            return ResponseEntity.ok("Ticket supprimé avec succès.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur : " + e.getMessage());
        }
    }

    @PostMapping("/updateTicket")
    @Transactional
    public ResponseEntity<String> updateTicket(@RequestParam("idTicket") String idTicket,@RequestParam("newPrice") String newPrice) {
        try {
            depenseService.updateValeurTicket(idTicket,newPrice);
            return ResponseEntity.ok("Ticket update avec succès.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur : " + e.getMessage());
        }
    }
}
