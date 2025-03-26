package site.easy.to.build.crm.my.apiController;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.my.service.CloneService;
import site.easy.to.build.crm.my.service.ImportService;
import site.easy.to.build.crm.service.user.UserService;
import site.easy.to.build.crm.util.AuthenticationUtils;

import java.io.File;

@RestController
@RequestMapping("/api")
public class CloneApi {

    @Autowired
    ImportService importService;


    @Autowired
    UserService userService;

    @Autowired
    CloneService cloneService;



    @PostMapping("/importClone")
    public ResponseEntity<String> cloneCustomer(HttpServletRequest request, @RequestParam("file") String file) {

        User manager = userService.findAll().get(0);

        cloneService.createTheThree(file);

        String absolutePath = "/Users/randriamalalavalisoa/Documents/DATA-EVAL";
        //import csv

        String customerFilePath = absolutePath + File.separator + "copiedataCustomer.csv";
        String budgetFilePath = absolutePath + File.separator + "copiedataBudget.csv";
        String ticketFilePath = absolutePath + File.separator + "copiedataExpense.csv";

        try {
            importService.importAll(budgetFilePath,customerFilePath,ticketFilePath,manager);
            importService.clearTemp();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.ok("Error.");
        }

        return ResponseEntity.ok("Clone imported.");
    }
}
