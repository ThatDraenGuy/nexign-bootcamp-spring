package generators;

import com.draen.service.calltype.generator.CallTypeGenerator;
import com.draen.service.calltype.generator.CallTypeGeneratorImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@DisplayName("CDR1")
public class CallTypeGeneratorTest {

    public CallTypeGenerator callTypeGenerator = new CallTypeGeneratorImpl();
    String[] callTypes = {"01", "02"};

    @Test
    @DisplayName("Проверяем корректность генерации типа звонка. Ожидаем получить 01 или 02")
    public void callTypeTest() {
        assertTrue("Не 01 или 02", Arrays.asList(callTypes).contains(callTypeGenerator.generateCallType()));
    }
}
