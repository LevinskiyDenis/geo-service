import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImplTest {

    @Test
    public void localeRussia() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String expected = "Добро пожаловать";
        String result = localizationService.locale(Country.RUSSIA);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void localeOther() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String expected = "Welcome";
        String result = localizationService.locale(Country.BRAZIL);
        Assertions.assertEquals(expected, result);
    }

//    public String locale(Country country) {
//        switch (country) {
//            case RUSSIA:
//                return "Добро пожаловать";
//            default:
//                return "Welcome";
//        }
//    }

}
