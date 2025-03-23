package site.easy.to.build.crm.my.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.my.model.Budget;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget,Integer> {
    @Query("SELECT b FROM Budget b WHERE b.customer = :customer AND b.dateBudget <= :dateTime")
    List<Budget> budgetCustomerDate(@Param("customer") Customer customer,@Param("dateTime") LocalDateTime dateTime);

    @Query("SELECT b FROM Budget b WHERE b.dateBudget <= :dateTime")
    List<Budget> budgetDate(@Param("dateTime") LocalDateTime dateTime);
}
