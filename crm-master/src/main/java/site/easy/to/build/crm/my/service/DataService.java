package site.easy.to.build.crm.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class DataService {
    @Autowired
    DataSource dataSource;

    public void resetData() {
        String[] sqlStatements = {
                "set foreign_key_checks = 0",
                "TRUNCATE TABLE contract_settings",
                "TRUNCATE TABLE google_drive_file",
                "TRUNCATE TABLE lead_action",
                "TRUNCATE TABLE lead_settings",
                "TRUNCATE TABLE ticket_settings",
                "TRUNCATE TABLE trigger_contract",
                "TRUNCATE TABLE trigger_lead",
                "TRUNCATE TABLE trigger_ticket",
                "TRUNCATE TABLE email_template",
                "TRUNCATE TABLE file",
                "TRUNCATE TABLE budget",
                "TRUNCATE TABLE depense",
                "set foreign_key_checks = 1"
        };

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            for (String sql : sqlStatements) {
                statement.execute(sql);
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to obtain database connection: " + e.getMessage(), e);
        }
    }
}
