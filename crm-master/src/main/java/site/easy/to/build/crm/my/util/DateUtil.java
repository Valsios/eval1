package site.easy.to.build.crm.my.util;


import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static LocalDateTime stringToLocalDate(String timestampString)
    {
        if (timestampString ==null || timestampString.isEmpty())
        {
            return null;
        }
        timestampString = timestampString.replace("T", " ");
        LocalDateTime localDateTime = LocalDateTime.parse(timestampString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm[:ss][.SSS]"));
        return localDateTime;
    }
}
