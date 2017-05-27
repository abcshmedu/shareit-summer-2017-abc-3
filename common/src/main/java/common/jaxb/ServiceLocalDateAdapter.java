package common.jaxb;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class ServiceLocalDateAdapter {
    private ServiceLocalDateAdapter() {
    }

    public static LocalDate unmarshal(final String xmlGregorianCalendar) {
        try {
            return LocalDate.parse(xmlGregorianCalendar, DateTimeFormatter.ISO_DATE);
        } catch (final DateTimeParseException ex) {
            return null;
        }
    }

    public static String marshal(final LocalDate date) {
        //        final String dateString = date.format(DateTimeFormatter.ISO_DATE);
        return date.toString();
    }
}
