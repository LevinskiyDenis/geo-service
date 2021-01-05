import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {

    @Test
    public void sendRussian() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.anyString())).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Location location = geoService.byIp(Mockito.anyString());

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(location.getCountry())).thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();

        String expected = "Добро пожаловать";
        String result = messageSender.send(headers);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void sendOther() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.anyString())).thenReturn(new Location("Rio", Country.BRAZIL, null, 0));
        Location location = geoService.byIp(Mockito.anyString());

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(location.getCountry())).thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();

        String expected = "Welcome";
        String result = messageSender.send(headers);

        Assertions.assertEquals(expected, result);
    }

//    @Test
//    public void sendRussian() {
//        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
//        Mockito.when(geoService.byIp(Mockito.anyString())).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
//        Location location = geoService.byIp(Mockito.anyString());
//        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
//        String expected = "Добро пожаловать";
//
//        String result = localizationService.locale(location.getCountry());
//        Assertions.assertEquals(expected, result);
//    }
//
//    @Test
//    public void sendEnglish() {
//        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
//        Mockito.when(geoService.byIp(Mockito.anyString())).thenReturn(new Location("New York", Country.USA, null,  0));
//        Location location = geoService.byIp(Mockito.anyString());
//        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
//        String expected = "Welcome";
//
//        String result = localizationService.locale(location.getCountry());
//        Assertions.assertEquals(expected, result);
//    }

}
