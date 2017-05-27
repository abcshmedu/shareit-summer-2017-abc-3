package common.jaxb;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class ServiceLocalDateTimeAdapter {

    private ServiceLocalDateTimeAdapter() {
    }

    public static LocalDateTime unmarshal(final String xmlGregorianCalendar) {
        try {
            return LocalDateTime.parse(xmlGregorianCalendar, DateTimeFormatter.ISO_DATE_TIME);
        } catch (final DateTimeParseException ex) {
            return null;
        }
    }

    public static String marshal(final LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
