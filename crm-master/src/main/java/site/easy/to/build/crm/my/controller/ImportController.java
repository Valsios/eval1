package site.easy.to.build.crm.my.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.my.service.ImportService;
import site.easy.to.build.crm.service.user.UserService;
import site.easy.to.build.crm.util.AuthenticationUtils;

import java.io.File;
import java.sql.SQLException;

@Controller
public class ImportController {

    @Autowired
    ImportService importService;

    @Autowired
    AuthenticationUtils authenticationUtils;

    @Autowired
    UserService userService;

    @GetMapping("/manager/getFormImport")
    public String getFormImport()
    {
        return "my/import-data";
    }


    @PostMapping("/manager/save-data")
    public String saveData(HttpServletRequest request, Model model, @RequestParam("customer_file") MultipartFile customerFile,
                           @RequestParam("ticket_file") MultipartFile ticketFile,@RequestParam("budget_file") MultipartFile budgetFile, Authentication authentication) throws SQLException {
        int userId = authenticationUtils.getLoggedInUserId(authentication);
        User manager = userService.findById(userId);
        if (customerFile.isEmpty() || ticketFile.isEmpty() || budgetFile.isEmpty())
        {
            model.addAttribute("error","File missing.");
            return "my/import-data";
        }

        String absolutePath = "/Users/randriamalalavalisoa/Documents/DATA-EVAL";
        //import csv

        String customerFilePath = absolutePath + File.separator + customerFile.getOriginalFilename();
        String budgetFilePath = absolutePath + File.separator + budgetFile.getOriginalFilename();
        String ticketFilePath = absolutePath + File.separator + ticketFile.getOriginalFilename();


        //import all
        try {
           importService.importAll(budgetFilePath,customerFilePath,ticketFilePath,manager);
           importService.clearTemp();
        }
        catch (Exception e)
        {
            model.addAttribute("error",e.getMessage());
            return "my/import-data";
        }

        return "redirect:/manager/getFormImport";
    }
}
