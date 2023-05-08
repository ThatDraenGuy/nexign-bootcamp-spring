package generators;

import com.draen.data.DateTimePair;
import com.draen.service.datetime.generator.LocalDateTimeGenerator;
import com.draen.service.datetime.generator.LocalDateTimeGeneratorImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@DisplayName("CDR4")
public class DateTimeEndGeneratorTest {

    static private final LocalDateTimeGenerator localDateTimeGenerator = new LocalDateTimeGeneratorImpl();
    static private final DateTimePair dateTimePair = localDateTimeGenerator.generateDateTime();
    static private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    static String startTime = dateTimePair.getStart().format(formatter);
    static String endTime = dateTimePair.getEnd().format(formatter);

    static char[] startYear = new char[4];    static char[] endYear = new char[4];
    static char[] startMonth = new char[2];   static char[] endMonth = new char[2];
    static char[] startDay = new char[2]; static char[] endDay = new char[2];
    static char[] startHours = new char[2];   static char[] endHours = new char[2];
    static char[] startMinutes = new char[2]; static char[] endMinutes = new char[2];
    static char[] startSeconds = new char[2]; static char[] endSeconds = new char[2];

    @BeforeAll
    public static void init() {
        startTime.getChars(0, 4, startYear, 0);
        endTime.getChars(0, 4, endYear, 0);
        startTime.getChars(4, 6, startMonth, 0);
        endTime.getChars(4, 6, endMonth, 0);
        startTime.getChars(6, 8, startDay, 0);
        endTime.getChars(6, 8, endDay, 0);
        startTime.getChars(8, 10, startHours, 0);
        endTime.getChars(8, 10, endHours, 0);
        startTime.getChars(10, 12, startMinutes, 0);
        endTime.getChars(10, 12, endMinutes, 0);
        startTime.getChars(12, 14, startSeconds, 0);
        endTime.getChars(12, 14, endSeconds, 0);
    }

    @Test
    @DisplayName("Год окончания звонка совпадает с годом начала звонка или больше на 1")
    public void dateTimeEndYearTest() {
        if (Integer.parseInt(String.valueOf(startMonth)) == 12 &&
                Integer.parseInt(String.valueOf(endMonth)) == 1) {
            assertTrue("Неверный год звонка",
                    Integer.parseInt(String.valueOf(endYear)) -
                            Integer.parseInt(String.valueOf(startYear)) == 1);
        } else {
            assertTrue("Неверный год звонка",
                    Integer.parseInt(String.valueOf(endYear)) ==
                            Integer.parseInt(String.valueOf(startYear)));
        }
    }

    @Test
    @DisplayName("Месяц окончания звонка совпадает с месяцем начала звонка или больше на 1")
    public void dateTimeStartMonthTest() {
        if (Integer.parseInt(String.valueOf(startDay)) > Integer.parseInt(String.valueOf(endDay))) {
            assertTrue("Неверный месяц",
                    Integer.parseInt(String.valueOf(endMonth)) -
                            Integer.parseInt(String.valueOf(startMonth)) == 1 ||
                            Integer.parseInt(String.valueOf(startMonth)) -
                                    Integer.parseInt(String.valueOf(endMonth)) == 11);
        } else {
            assertTrue("Неверный месяц",
                    Integer.parseInt(String.valueOf(endMonth)) ==
                            Integer.parseInt(String.valueOf(startMonth)));
        }
    }

    @Test
    @DisplayName("День окончания звонка совпадает с днём начала звонка или больше на 1")
    public void dateTimeStartDayTest() {
        if (Integer.parseInt(String.valueOf(startHours)) == 23 && Integer.parseInt(String.valueOf(endHours)) == 0) {
            assertTrue("Неверный день",
                    Integer.parseInt(String.valueOf(endDay)) -
                             Integer.parseInt(String.valueOf(startDay)) == 1 ||
                            (Integer.parseInt(String.valueOf(startDay)) -
                                    Integer.parseInt(String.valueOf(endDay)) > 27));
        } else {
            assertTrue("Неверный день",
                    Integer.parseInt(String.valueOf(endMonth)) ==
                            Integer.parseInt(String.valueOf(startMonth)));
        }
    }

    @Test
    @DisplayName("Час окончания звонка совпадает с часом начала звонка или больше на 1")
    public void dateTimeStartHoursTest() {
        if (Integer.parseInt(String.valueOf(startMinutes)) > Integer.parseInt(String.valueOf(endMinutes))) {
            assertTrue("Неверный час",
                    Integer.parseInt(String.valueOf(endHours)) -
                            Integer.parseInt(String.valueOf(startHours)) == 1 ||
                            Integer.parseInt(String.valueOf(startHours)) -
                                    Integer.parseInt(String.valueOf(endHours)) == 23);
        } else {
            assertTrue("Неверный час",
                    Integer.parseInt(String.valueOf(endHours)) ==
                            Integer.parseInt(String.valueOf(startHours)));
        }
    }

    @Test
    @DisplayName("Минута окончания звонка не превышает ту же минуту следующего часа от начала звонка")
    public void dateTimeStartMinutesTest() {
        if (Integer.parseInt(String.valueOf(endHours)) - Integer.parseInt(String.valueOf(startHours)) == 1 ||
                Integer.parseInt(String.valueOf(startHours)) - Integer.parseInt(String.valueOf(endHours)) == 23) {
            assertTrue("Неверная минута",
                       Integer.parseInt(String.valueOf(endMinutes)) <=
                            Integer.parseInt(String.valueOf(startMinutes)));
        } else {
            assertTrue("Неверная минута",
                       Integer.parseInt(String.valueOf(endMinutes)) >=
                            Integer.parseInt(String.valueOf(startMinutes)));
        }
    }

    @Test
    @DisplayName("Секунда окончания звонка не превышает ту же секунду той же минуты следующего часа от начала звонка")
    public void dateTimeStartSecondsTest() {
        if ((Integer.parseInt(String.valueOf(endHours)) - Integer.parseInt(String.valueOf(startHours)) == 1 ||
                Integer.parseInt(String.valueOf(startHours)) - Integer.parseInt(String.valueOf(endHours)) == 23) &&
                Integer.parseInt(String.valueOf(endMinutes)) == Integer.parseInt(String.valueOf(startMinutes))) {
            assertTrue("Неверная секунда",
                    Integer.parseInt(String.valueOf(endSeconds)) <=
                            Integer.parseInt(String.valueOf(startSeconds)));
            }
    }
}
