package com.tvd12.example.time;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Java8TimeExample {

    public static void main(String[] args) throws Exception {
        firstExample();
        getCurrentTime();
        representingSpecificTime();
        extractingSpecificFields();
        addingAndSubtractingTime();
        alteringSpecificFields();
        truncating();
        timeZoneConversion();
        gettingTimeSpanBetweenTwoPointsInTime();
        timeFormattingAndParsing();
        interactingWithLegacyCode();
        dateTimeToMillis();
        millisToDateTime();
        dateToDateTime();
        dateTimeToInt();

        System.out.println("dayOfWeek: " + LocalDate.now().getDayOfWeek());
    }

    public static void firstExample() {
        ZonedDateTime nextFriday = LocalDateTime.now()
            .plusHours(1)
            .with(TemporalAdjusters.next(DayOfWeek.FRIDAY))
            .atZone(ZoneId.of("GMT"));
        System.out.println(nextFriday);
    }

    public static void getCurrentTime() {
        // Old
        Date now = new Date();
        // New
        ZonedDateTime now8 = ZonedDateTime.now();
        System.out.println("now: " + now + ", now8: " + now8);
    }

    public static void representingSpecificTime() {
        // Old
        Date birthDay = new GregorianCalendar(1990, Calendar.DECEMBER, 15).getTime();
        // New
        LocalDate birthDay8 = LocalDate.of(1990, Month.DECEMBER, 15);
        System.out.println("birthDay: " + birthDay + ", birthDay8: " + birthDay8);
    }

    public static void extractingSpecificFields() {
        // Old
        int month = new GregorianCalendar().get(Calendar.MONTH);
        // New
        Month month8 = LocalDateTime.now().getMonth();
        System.out.println("month: " + month + ", month8: " + month8);
    }

    public static void addingAndSubtractingTime() {
        // Old
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.HOUR_OF_DAY, -5);
        Date fiveHoursBefore = calendar.getTime();
        // New
        LocalDateTime fiveHoursBefore8 = LocalDateTime.now().minusHours(5);
        System.out.println("fiveHoursBefore: " + fiveHoursBefore + ", fiveHoursBefore8: " + fiveHoursBefore8);
    }

    public static void alteringSpecificFields() {
        // Old
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.MONTH, Calendar.JUNE);
        Date inJune = calendar.getTime();
        // New
        LocalDateTime inJune8 = LocalDateTime.now().withMonth(Month.JUNE.getValue());
        System.out.println("inJune: " + inJune + ", inJune8: " + inJune8);
    }

    public static void truncating() {
        // Old
        Calendar now = Calendar.getInstance();
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        Date truncated = now.getTime();
        // New
        LocalTime truncated8 = LocalTime.now().truncatedTo(ChronoUnit.HOURS);
        System.out.println("truncated: " + truncated + ", truncated8: " + truncated8);
    }

    public static void timeZoneConversion() {
        // Old
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone("CET"));
        Date centralEastern = calendar.getTime();
        // New
        ZonedDateTime centralEastern8 = LocalDateTime.now().atZone(ZoneId.of("CET"));
        System.out.println("centralEastern: " + centralEastern + ", centralEastern8: " + centralEastern8);
    }

    public static void gettingTimeSpanBetweenTwoPointsInTime() {
        // Old
        GregorianCalendar calendar = new GregorianCalendar();
        Date now = new Date();
        calendar.add(Calendar.HOUR, 1);
        Date hourLater = calendar.getTime();
        long elapsed = hourLater.getTime() - now.getTime();
        // New
        LocalDateTime now8 = LocalDateTime.now();
        LocalDateTime hourLater8 = LocalDateTime.now().plusHours(1);
        Duration span = Duration.between(now8, hourLater8);
        System.out.println("elapsed: " + elapsed + ", span: " + span);
    }

    public static void timeFormattingAndParsing() throws Exception {
        // Old
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String formattedDate = dateFormat.format(now);
        Date parsedDate = dateFormat.parse(formattedDate);

        // New
        LocalDate now8 = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate8 = now8.format(formatter);
        LocalDate parsedDate8 = LocalDate.parse(formattedDate8, formatter);
        System.out.println("parsedDate: " + parsedDate + ", parsedDate8: " + parsedDate8);
    }

    @SuppressWarnings("unused")
    public static void interactingWithLegacyCode() {
        Instant instantFromCalendar = GregorianCalendar.getInstance().toInstant();
        ZonedDateTime zonedDateTimeFromCalendar = new GregorianCalendar().toZonedDateTime();
        Date dateFromInstant = Date.from(Instant.now());
        GregorianCalendar calendarFromZonedDateTime = GregorianCalendar.from(ZonedDateTime.now());
        Instant instantFromDate = new Date().toInstant();
        ZoneId zoneIdFromTimeZone = TimeZone.getTimeZone("PST").toZoneId();
    }

    public static void dateTimeToMillis() {
        ZonedDateTime nextFriday = LocalDateTime.now()
            .atZone(ZoneId.systemDefault());
        long millis = nextFriday.toInstant().toEpochMilli();
        System.out.println("milis: " + millis);
    }

    public static void millisToDateTime() {
        long millis = System.currentTimeMillis();
        Instant instant = Instant.ofEpochMilli(millis);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println("millis to date time: " + dateTime);
    }

    public static void dateToDateTime() {
        Date date = new Date();
        long millis = date.getTime();
        Instant instant = Instant.ofEpochMilli(millis);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println("millis to date time: " + dateTime);
    }

    public static void dateTimeToInt() {
        LocalDateTime now = LocalDateTime.now();
        int answer = now.getYear() * 10000 + now.getMonthValue() * 100 + now.getDayOfMonth();
        System.out.println("dateTimeToInt: " + answer);
    }
}
