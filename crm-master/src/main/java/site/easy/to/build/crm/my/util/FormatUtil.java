package site.easy.to.build.crm.my.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FormatUtil {

    //data of status
   static List<String> ticketStatusList = List.of(
            "open",
            "assigned",
            "on-hold",
            "in-progress",
            "resolved",
            "closed",
            "reopened",
            "pending-customer-response",
            "escalated",
            "archived"
    );
    static List<String> leadStatusList = List.of(
            "meeting-to-schedule",
            "assign-to-sales",
            "archived",
            "success"
    );
    //end data of status

    public static String getFormaNumber(String number)
    {
        number =  number.replace(",",".");
        number =  number.replace(" ","");
        return number;
    }

    public static void check_status(String type,String status)throws Exception
    {
        if (type.compareTo("lead")==0)
        {
            if (!leadStatusList.contains(status))
            {
                status="success";
            }
        }
        else if(type.compareTo("ticket")==0)
        {
            if (!ticketStatusList.contains(status))
            {
                status="open";
            }
        }
    }
}
