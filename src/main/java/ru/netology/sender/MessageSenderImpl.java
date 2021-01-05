package ru.netology.sender;

import java.util.Map;

import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

public class MessageSenderImpl implements MessageSender {

    public static final String IP_ADDRESS_HEADER = "x-real-ip";
    private final GeoService geoService;

    private final LocalizationService localizationService;

    public MessageSenderImpl(GeoService geoService, LocalizationService localizationService) {
        this.geoService = geoService;
        this.localizationService = localizationService;
    }

    // Написать тесты для проверки языка отправляемого сообщения (класс MessageSender) с использованием Mockito
    //Поверить, что MessageSenderImpl всегда отправляет только русский текст, если ip относится к российскому сегменту адресов.
    //Поверить, что MessageSenderImpl всегда отправляет только английский текст, если ip относится к американскому сегменту адресов.

    public String send(Map<String, String> headers) {
        String ipAddress = String.valueOf(headers.get(IP_ADDRESS_HEADER));
        if (ipAddress != null && !ipAddress.isEmpty()) {
            Location location = geoService.byIp(ipAddress);
            System.out.printf("Отправлено сообщение: %s", localizationService.locale(location.getCountry()));
            return localizationService.locale(location.getCountry());
        }
        return localizationService.locale(Country.USA);
    }
}
