package util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Timestamp;
public class Util
{
    public static LocalDate convertDate (String dateStr)
	{
		LocalDate date=null;

        try {
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        	date = LocalDate.parse(dateStr, formatter);
        } catch (Exception e) {
        	
        }

        return date;
	}

    public static Timestamp stringToTimestamp(String strDate) {
        if (strDate == null || strDate.isEmpty()) {
            return null;
        }

        // Utiliser le format ISO 8601 pour correspondre au type datetime-local
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            java.util.Date parsedDate = dateFormat.parse(strDate);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            return null;
        }
    }
}