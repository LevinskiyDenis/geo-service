import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceImplTest {

    @ParameterizedTest
    @ValueSource(strings = {"172.40.38.16", GeoServiceImpl.MOSCOW_IP, "172.0.9.26"})
    public void byIpRussia(String ip) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Country expected = Country.RUSSIA;
        Country result = geoService.byIp(ip).getCountry();
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"96.23.183.149", GeoServiceImpl.NEW_YORK_IP, "96.894.113.376"})
    public void byIpUsa(String ip) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Country expected = Country.USA;
        Country result = geoService.byIp(ip).getCountry();
        Assertions.assertEquals(expected, result);
    }
}
