package generators;

import com.draen.data.DateTimePair;
import com.draen.service.datetime.generator.LocalDateTimeGenerator;
import com.draen.service.datetime.generator.LocalDateTimeGeneratorImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@DisplayName("CDR3")
public class DateTimeStartGeneratorTest {

    static private final LocalDateTimeGenerator localDateTimeGenerator = new LocalDateTimeGeneratorImpl();
    static private final DateTimePair dateTimePair = localDateTimeGenerator.generateDateTime();
    static private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    static String currentTime = LocalDateTime.now().format(formatter);
    static String testTime = dateTimePair.getStart().format(formatter);

    static char[] currentYear = new char[4];    static char[] testYear = new char[4];
    static char[] currentMonth = new char[2];   static char[] testMonth = new char[2];
    static char[] currentDay = new char[2]; static char[] testDay = new char[2];
    static char[] currentHours = new char[2];   static char[] testHours = new char[2];
    static char[] currentMinutes = new char[2]; static char[] testMinutes = new char[2];
    static char[] currentSeconds = new char[2]; static char[] testSeconds = new char[2];

    @BeforeAll
    public static void init() {
        currentTime.getChars(0, 4, currentYear, 0);
        testTime.getChars(0, 4, testYear, 0);
        currentTime.getChars(4, 6, currentMonth, 0);
        testTime.getChars(4, 6, testMonth, 0);
        currentTime.getChars(6, 8, currentDay, 0);
        testTime.getChars(6, 8, testDay, 0);
        currentTime.getChars(8, 10, currentHours, 0);
        testTime.getChars(8, 10, testHours, 0);
        currentTime.getChars(10, 12, currentMinutes, 0);
        testTime.getChars(10, 12, testMinutes, 0);
        currentTime.getChars(12, 14, currentSeconds, 0);
        testTime.getChars(12, 14, testSeconds, 0);
    }

    @Test
    @DisplayName("Год начала звонка не превышает текущий год")
    public void dateTimeStartYearTest() {
        assertTrue("Неверный год звонка",
                Integer.parseInt(String.valueOf(currentYear)) >=
                        Integer.parseInt(String.valueOf(testYear)));
    }

    @Test
    @DisplayName("Месяц начала звонка не превышает текущий месяц")
    public void dateTimeStartMonthTest() {
        if (Integer.parseInt(String.valueOf(currentYear)) == Integer.parseInt(String.valueOf(testYear))) {
            assertTrue("Неверный месяц",
                    Integer.parseInt(String.valueOf(currentMonth)) >=
                            Integer.parseInt(String.valueOf(testMonth)));
        }
    }

    @Test
    @DisplayName("День начала звонка не превышает текущий день")
    public void dateTimeStartDayTest() {

        if (Integer.parseInt(String.valueOf(currentMonth)) == Integer.parseInt(String.valueOf(testMonth))) {
            assertTrue("Неверный день",
                    Integer.parseInt(String.valueOf(currentDay)) >=
                            Integer.parseInt(String.valueOf(testDay)));
        }
    }

    @Test
    @DisplayName("Час начала звонка не превышает текущий час")
    public void dateTimeStartHoursTest() {
        if (Integer.parseInt(String.valueOf(currentDay)) == Integer.parseInt(String.valueOf(testDay)) &&
                Integer.parseInt(String.valueOf(currentMonth)) == Integer.parseInt(String.valueOf(testMonth))) {
            assertTrue("Неверный час",
                    Integer.parseInt(String.valueOf(currentHours)) >=
                            Integer.parseInt(String.valueOf(testHours)));
        }
    }

    @Test
    @DisplayName("Минута начала звонка не превышает текущую минуту")
    public void dateTimeStartMinutesTest() {
        if (Integer.parseInt(String.valueOf(currentHours)) == Integer.parseInt(String.valueOf(testHours)) &&
                Integer.parseInt(String.valueOf(currentDay)) == Integer.parseInt(String.valueOf(testDay)) &&
                Integer.parseInt(String.valueOf(currentMonth)) == Integer.parseInt(String.valueOf(testMonth))) {
            assertTrue("Неверная минута",
                    Integer.parseInt(String.valueOf(currentMinutes)) >=
                            Integer.parseInt(String.valueOf(testMinutes)));
        }
    }

    @Test
    @DisplayName("Секунда начала звонка не превышает текущую секунду")
    public void dateTimeStartSecondsTest() {
        if (Integer.parseInt(String.valueOf(currentMinutes)) == Integer.parseInt(String.valueOf(testMinutes)) &&
                Integer.parseInt(String.valueOf(currentHours)) == Integer.parseInt(String.valueOf(testHours)) &&
                Integer.parseInt(String.valueOf(currentDay)) == Integer.parseInt(String.valueOf(testDay)) &&
                Integer.parseInt(String.valueOf(currentMonth)) == Integer.parseInt(String.valueOf(testMonth))) {
            assertTrue("Неверная секунда",
                    Integer.parseInt(String.valueOf(currentSeconds)) >=
                            Integer.parseInt(String.valueOf(testSeconds)));
        }
    }
}
