package br.com.jigabyte.tom.utils;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Calendata {

    final DateTimeFormatter formatoDeDateTime = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
    final DateTimeFormatter formatoDeData = DateTimeFormat.forPattern("dd/MM/yyyy");
    String data;
    LocalDateTime localDateTime;

    public Calendata() {
        this.localDateTime = new LocalDateTime();
    }

    public Calendata(String data) {
        this.data = data;
        this.localDateTime = LocalDateTime.parse(data, formatoDeData);
    }

    public Calendata(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }


    public boolean ehMenor(String novaData) {
        if (localDateTime.toDateTime().toInstant().isBefore(new Calendata(novaData).getLocalDateTime().toDateTime().toInstant()))
            return true;
        return false;
    }

    public boolean ehMaior(String novaData) {
        if (localDateTime.toDateTime().toInstant().isAfter(new Calendata(novaData).getLocalDateTime().toDateTime().toInstant()))
            return true;
        return false;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String toStringDateTime() {
        return localDateTime.toString(formatoDeData);
    }

    public String toStringDate() {
        return localDateTime.toString(formatoDeData);
    }
}
