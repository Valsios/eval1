package site.easy.to.build.crm.my.apiController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import site.easy.to.build.crm.my.service.LoginService;

@RestController
@RequestMapping("/api")
public class LoginApi {
    @Autowired
    LoginService loginService;
    @GetMapping("/checkSession")
    public boolean deleteLead(@RequestParam("JSessionID") String JSessionID, HttpServletRequest request) {
        HttpSession session = loginService.getSessionById(JSessionID,request);
        return loginService.hasRoleManager(session);
    }
}
