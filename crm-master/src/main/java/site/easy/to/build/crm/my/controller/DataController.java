package site.easy.to.build.crm.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import site.easy.to.build.crm.my.service.DataService;

@Controller
public class DataController {
    @Autowired
    DataService dataService;

    @GetMapping("/manager/resetData")
    public String resetData()
    {
        dataService.resetData();
        return "redirect:/";
    }

}
