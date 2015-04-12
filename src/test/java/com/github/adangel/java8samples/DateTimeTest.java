package com.github.adangel.java8samples;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class DateTimeTest {

    @Test
    public void temporalTypes() {
        Instant instant = Instant.now();
        System.out.println("Instant:\t" + instant.toString());

        LocalDate localDate = LocalDate.now();
        System.out.println("LocalDate:\t" + localDate.toString());

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("LocalDateTime:\t" + localDateTime.toString());

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("ZonedDateTime:\t" + zonedDateTime.toString());

        LocalTime localTime = LocalTime.now();
        System.out.println("LocalTime:\t" + localTime.toString());

        MonthDay monthDay = MonthDay.now();
        System.out.println("MonthDay:\t" + monthDay.toString());

        Year year = Year.now();
        System.out.println("Year:\t\t" + year.toString());

        YearMonth yearMonth = YearMonth.now();
        System.out.println("YearMonth:\t" + yearMonth.toString());

        Month month = Month.from(MonthDay.now());
        System.out.println("Month:\t\t" + month.toString());

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println("OffsetDateTime:\t" + offsetDateTime.toString());

        OffsetTime offsetTime = OffsetTime.now();
        System.out.println("OffsetTime:\t" + offsetTime.toString());

        Duration duration = Duration.ofSeconds(3601);
        System.out.println("Duration:\t" + duration.toString());

        Period period = Period.ofDays(365);
        System.out.println("Period:\t\t" + period.toString());
    }

    @Test
    public void localDate() {
        LocalDate date = LocalDate.now();
        LocalDate nextWed = date.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
        System.out.println("Next Wednesday: " + nextWed);
    }

    @Test
    public void yearMonth() {
        for (int i = 2013; i <= 2017; i++) {
            YearMonth date = YearMonth.of(i, Month.FEBRUARY);
            System.out.println(i + ": " + date.lengthOfMonth());
        }
    }

    @Test
    public void digitalClock() {
        LocalTime thisSec;
        for (int i = 0; i < 10; i++) {
            thisSec = LocalTime.now();
            System.out.printf("%d  ::  %d  ::  %d\n", thisSec.getHour(), thisSec.getMinute(), thisSec.getSecond());
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
        }
    }

    @Test
    public void timeZones() {
        Set<String> allZones = ZoneId.getAvailableZoneIds();
        List<String> zoneList = new ArrayList<>(allZones);
        Collections.sort(zoneList);

        ZonedDateTime local = ZonedDateTime.now();

        for (String s : zoneList) {
            ZoneId zone = ZoneId.of(s);
            ZonedDateTime zdt = local.withZoneSameInstant(zone);
            ZoneOffset offset = zdt.getOffset();
            String out = String.format("%35s %10s %20s", zone, offset, zdt.toLocalTime());
            System.out.println(out);
        }
    }

    @Test
    public void parsingFormatting() {
        LocalDate date = LocalDate.parse("2015-04-13", DateTimeFormatter.ISO_DATE);
        System.out.println(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy H:m");
        LocalDateTime time = LocalDateTime.parse("13.04.2015 9:30", formatter);
        System.out.println(time);
        System.out.println(time.format(formatter));
    }

    @Test
    public void temporalAdjusters() {
        LocalDate date = LocalDate.of(2015, Month.APRIL, 1);
        System.out.println(date + " is a " + date.getDayOfWeek());
        System.out.println();
        System.out.println("first Monday: " + date.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));
        System.out.println("first day of next month: " + date.with(TemporalAdjusters.firstDayOfNextMonth()));
        System.out.println("first day of year: " + date.with(TemporalAdjusters.firstDayOfYear()));
    }

    @Test
    public void period() {
        LocalDate today = LocalDate.now();
        LocalDate nextHelloween = LocalDate.of(2015, 10, 31);
        Period period = Period.between(today, nextHelloween);
        System.out.println("Helloween is in " + period);
        long days = ChronoUnit.DAYS.between(today, nextHelloween);
        System.out.println("this is in " + days + " days");
    }

    @Test
    public void find13Fridays() {
        LocalDate date = LocalDate.now().withDayOfMonth(13);
        for (Month m : Month.values()) {
            date = date.withMonth(m.getValue());
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                System.out.println("Friday!: " + date);
            }
        }
    }
}
