package generators;

import com.draen.service.phonenumber.generator.PhoneNumberGenerator;
import com.draen.service.phonenumber.generator.PhoneNumberGeneratorImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@DisplayName("CDR2")
public class PhoneNumberGeneratorTest {

    PhoneNumberGenerator phoneNumberGenerator = new PhoneNumberGeneratorImpl();

    @Test
    @DisplayName("Проверяем корректность генерации номера. " +
                 "Ожидаем, что в номере 11 цифр, " +
                 "номер начинается на 7, " +
                 "код оператора начинается на 9.")
    public void phoneNumberTest() {
        assertEquals("В номере 11 цифр", 11, phoneNumberGenerator.generateNewNumber().length());
        assertEquals("Первая цифра - 7", "7", String.valueOf(phoneNumberGenerator.generateNewNumber().charAt(0)));
        assertEquals("Вторая цифра - 9", "9", String.valueOf(phoneNumberGenerator.generateNewNumber().charAt(1)));
    }
}