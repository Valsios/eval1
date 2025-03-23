package site.easy.to.build.crm.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.my.model.SeuilBudget;
import site.easy.to.build.crm.my.repository.SeuilBudgetRepository;

@Service
public class SeuilBudgetService {
    @Autowired
    SeuilBudgetRepository seuilBudgetRepository;
    public SeuilBudget getSeuilBudget()
    {
        return seuilBudgetRepository.findAll().get(0);
    }
}
