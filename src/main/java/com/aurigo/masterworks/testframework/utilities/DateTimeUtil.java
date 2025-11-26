package com.aurigo.masterworks.testframework.utilities;

import com.aurigo.masterworks.testframework.utilities.models.environment.Build;
import com.aurigo.masterworks.testframework.webUI.constants.enums.AuditLogQuarter;
import com.aurigo.masterworks.testframework.webUI.constants.enums.TimeZoneList;
import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class DateTimeUtil {


    /**
     * Get Current DateTime.
     *
     * @return Current DateTime.
     */
    public static String getCurrentDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }

    /**
     * Get Current Date.
     *
     * @param dateFormat Date Format
     * @return Current Date.
     */
    public static String getCurrentDate(String dateFormat) {
        return new SimpleDateFormat(dateFormat).format(new Date());
    }

    /**
     * Get Date string
     *
     * @param dateFormat date format to be used
     * @param date       Date to be formatted
     * @return Date string
     */
    public static String getDateString(String dateFormat, Date date) {
        return new SimpleDateFormat(dateFormat).format(date);
    }


    /**
     * Get time based on Zone
     *
     * @param offset zone off set from UTC
     * @return Time in String format
     */
    public static String getTimeBasedOnTimeZone(TimeZoneList offset) {
        Instant instant = Instant.now();
        ZoneOffset zoneOffset = null;
        if (offset.getValue() == "+0:00") {
            zoneOffset = OffsetDateTime.now().getOffset();
        } else {
            String timeZoneOperator =  offset.getValue().contains("+")?"+":"-";
            Integer hours = Integer.parseInt(StringUtils.substringBetween(offset.getValue(), timeZoneOperator, ":"));
            Integer minutes = Integer.parseInt(StringUtils.substringAfter(offset.getValue(), ":"));
            zoneOffset = ZoneOffset.ofHoursMinutes(hours, minutes);
        }
        OffsetDateTime odt = OffsetDateTime.ofInstant(instant, zoneOffset);
        String zoneDateTime = odt.toString();
        DateFormat srcDf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = new Date();
        try {
            date = srcDf.parse(zoneDateTime);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
        DateFormat destDf = new SimpleDateFormat("HH:mm:ss");
        String time = destDf.format(date);
        return time;
    }

    /**
     * Get time based on Zone
     *
     * @param offset zone off set from UTC
     * @return Time in String format
     */
    public static String getDateBasedOnTimeZone(TimeZoneList offset, String dateFormat) {
        Instant instant = Instant.now();
        ZoneOffset zoneOffset = null;
        if (offset.getValue() == "+0:00") {
            zoneOffset = OffsetDateTime.now().getOffset();
        } else {
            zoneOffset = ZoneOffset.of(offset.getValue());
        }
        OffsetDateTime odt = OffsetDateTime.ofInstant(instant, zoneOffset);
        String zoneDateTime = odt.toString();
        DateFormat srcDf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = new Date();
        try {
            date = srcDf.parse(zoneDateTime);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
        DateFormat destDf = new SimpleDateFormat(dateFormat);
        String zoneDate = destDf.format(date);
        return zoneDate;
    }

    /**
     * Method to Convert the time format
     * @param value Time to get converted to required format
     * @param dateFormatValue Date Format obtained
     * @param dateFormat datetime fetched form DB
     * @return Time in String format
     */
    public static String convertDateTimeFormat(String value, String dateFormatValue,String dateFormat) {
        try {
            SimpleDateFormat actualFormat = new SimpleDateFormat(dateFormatValue);
            SimpleDateFormat expectedFormat = new SimpleDateFormat(dateFormat);
            Date actualDate = actualFormat.parse(value);
            value = expectedFormat.format(actualDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return value;
    }


    /**
     * Generate Date from today's date.
     *
     * @param addDays    Days to add from current date
     * @param addMonths  Months  to add from current date
     * @param addYears   Years to add from current date
     * @param dateFormat Expected date format.
     * @return String of the date
     */
    public static String dateGenerationFromToday(Integer addDays, Integer addMonths, Integer addYears, String dateFormat) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, addDays);
        cal.add(Calendar.MONTH, addMonths);
        cal.add(Calendar.YEAR, addYears);
        Date date = cal.getTime();
        String dateGenerated = new SimpleDateFormat(dateFormat).format(date);
        return dateGenerated;
    }

    /**
     * Generate Date from a particular day
     *
     * @param day        given day in String format
     * @param addDays    Days to add from given date
     * @param addMonths  Months  to add from given date
     * @param addYears   Years to add from given date
     * @param dateFormat Expected date format.
     * @return String of the date
     */
    public static String dateGenerationFromParticularDay(String day, Integer addDays, Integer addMonths, Integer addYears, String dateFormat) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            Calendar cal = Calendar.getInstance();
            cal.setTime(simpleDateFormat.parse(day));
            cal.add(Calendar.DAY_OF_MONTH, addDays);
            cal.add(Calendar.MONTH, addMonths);
            cal.add(Calendar.YEAR, addYears);
            Date date = cal.getTime();
            String dateGenerated = new SimpleDateFormat(dateFormat).format(date);
            return dateGenerated;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Validating date format
     *
     * @param strDate    Date to validate
     * @param dateFormat Date to format
     * @return if date is of the same format
     */
    public static boolean validateDateFormat(String strDate, String dateFormat) {

        if (!Strings.isNullOrEmpty(strDate)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            simpleDateFormat.setLenient(false);
            try {
                simpleDateFormat.parse(strDate);
            } catch (ParseException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Convert to date format
     *
     * @param dateFormat        date format
     * @param dateToBeConverted Date string to be converted
     * @return Converted Date String
     */
    public static String convertToDateFormat(String dateFormat, String dateToBeConverted) {
        Date date = convertToDate(dateToBeConverted, dateFormat);
        DateFormat destDf = new SimpleDateFormat(dateFormat);
        String convertedDate = destDf.format(date);
        return convertedDate;

    }

    /**
     * Convert to date format
     *
     * @param dateToBeConverted Date string to be converted
     * @param inputFormat input date format
     * @param outputFormat output date format
     * @return Converted Date String
     */
    public static String convertToDateFormat(String dateToBeConverted,String inputFormat,String outputFormat) {
        Date date = convertToDate(dateToBeConverted, inputFormat);
        DateFormat destDf = new SimpleDateFormat(outputFormat);
        return destDf.format(date);
    }

    /**
     * To get the earliest dates
     *
     * @param dates  list of dates to sort
     * @param forMin early or farther
     * @return if true return earliest date , otherwise max date
     */
    public static Date getDate(List<Date> dates, boolean forMin) {
        if (dates == null || dates.isEmpty())
            return null;
        dates.removeIf(Objects::isNull);

        if (forMin) {
            Date date = dates.isEmpty() ? null : Collections.min(dates);
            return date;
        } else {
            Date date = dates.isEmpty() ? null : Collections.max(dates);
            return date;
        }
    }


    /**
     * conversion into Date type
     *
     * @param date       date to be converted into date type
     * @param dateFormat date format
     * @return Date in date type
     */
    public static Date convertToDate(String date, String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        simpleDateFormat.setLenient(false);

        try {
            Date javaDate = simpleDateFormat.parse(date);
            return javaDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * conversion into Date type
     *
     * @param date       date to be converted into date type
     * @param dateFormat date format
     * @return Date in date type
     */
    public static DateTime convertToDateTime(String date, String dateFormat) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(dateFormat);
        return formatter.parseDateTime(date);
    }

    /**
     * Method to get random time in HHMM format
     *
     * @return String value of time in HHMM format
     */
    public static String getRandomTime() {
        String time =
                String.valueOf(TestDataUtil.getRandomNumber(10, 20)) +
                        TestDataUtil.getRandomNumber(10, 59);
        return time;
    }



    /**
     * Calculate time difference
     * @param start - start date.
     * @param end - end date.
     * @param unit - Unit in which difference is required
     * @param dateFormat - Format in whic the date is passed.
     * @return time difference
     */
    public static Long calculateTimeDifference(String start, String end, ChronoUnit unit, String dateFormat) {
        java.time.format.DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern(dateFormat);
        LocalDate startDate = LocalDate.parse(start,dtf);
        LocalDate endDate = LocalDate.parse(end,dtf);
        return calculateTimeDifference(startDate, endDate,unit);
    }



    /**
     * Calculate time difference
     * @param start - start date.
     * @param end - end date.
     * @param unit - Unit in which difference is required
     * @return time difference
     */
    public static Long calculateTimeDifference(LocalDate start,LocalDate end,ChronoUnit unit) {
        return unit.between(start, end);
    }

    /**
     * Get month year from a particular Month
     * @param numberOfMonths Number of month
     * @param startingMonth Month number from which to start
     * @return the month duration details
     */
    public static List<String> getMonthYearForADuration(Integer numberOfMonths,Integer startingMonth)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM YYYY");
        Calendar cal = Calendar.getInstance();
        List<String> months = new ArrayList<>();
        cal.set(Calendar.MONTH, startingMonth-1);
        for(int i=0;i<numberOfMonths;i++)
        {
            Date date = cal.getTime();
            var newDate = simpleDateFormat.format(date);
            cal.add(Calendar.MONTH, 1);
            months.add(newDate);
        }
        return months;

    }

    /**
     * Get Current Quarter
     * @param month the month
     * @return The Quarter
     */
    public static String getCurrentQuarter(Integer month)
    {
        Calendar c =Calendar.getInstance();
        return (month >= Calendar.JANUARY && month <= Calendar.MARCH)     ? AuditLogQuarter.QuarterOne.getValue() :
                (month >= Calendar.APRIL && month <= Calendar.JUNE)        ?AuditLogQuarter.QuarterTwo.getValue():
                        (month >= Calendar.JULY && month <= Calendar.SEPTEMBER)        ?AuditLogQuarter.QuarterThree.getValue():
                                AuditLogQuarter.QuarterFour.getValue();
    }

    /**
     * Method to check if the year is a leap year
     *
     * @param year year
     * @return true if the year is a leap year
     */
    public static boolean checkLeapYear(int year){
        return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
    }
}
