package Model;


import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CustomDate  {
    public static LocalDate DateToLocalDate(Date date){
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date LocalDateToDate(LocalDate localDate){
        return java.util.Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static int GetDayDiff(Date start, Date end){
        int diff = 0;
        if(end.compareTo(start) == 1 )
        {
            long diffInMillis =  end.getTime() - start.getTime() ;
            diff = (int) TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        }
        return diff;
    }

}
