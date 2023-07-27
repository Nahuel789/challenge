package org.proyect.application;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.proyect.application.dto.CountryInfoDTO;
import org.proyect.application.dto.CurrencyDTO;
import org.proyect.domain.CountryInfo;
import org.proyect.domain.Language;
import org.proyect.domain.QuoteResponse;
import org.proyect.infraestructure.input.port.GeoDataInputPort;
import org.proyect.infraestructure.output.adapter.impl.CurrencyAdapter;
import org.proyect.infraestructure.output.adapter.impl.IpCountryAdapter;
import org.proyect.infraestructure.output.adapter.impl.QuoteAdapter;
import org.proyect.infraestructure.output.adapter.impl.TimeZoneAdapter;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class GeolocationService implements GeoDataInputPort {

    @ConfigProperty(name = "quote-access-key")
    private String quoteAccessKey;

    private static final double EARTH_RADIUS_KM = 6371.0;

    @Inject
    private IpCountryAdapter ipCountryAdapter;

    @Inject
    private TimeZoneAdapter timeZoneAdapter;

    @Inject
    private CurrencyAdapter currencyAdapter;

    @Inject
    private QuoteAdapter quoteAdapter;

    public GeolocationService(IpCountryAdapter ipCountryAdapter, TimeZoneAdapter timeZoneAdapter, CurrencyAdapter currencyAdapter, QuoteAdapter quoteAdapter) {
        this.ipCountryAdapter = ipCountryAdapter;
        this.timeZoneAdapter = timeZoneAdapter;
        this.currencyAdapter = currencyAdapter;
        this.quoteAdapter = quoteAdapter;
    }

    @Override
    public CountryInfoDTO getCountryInfo(String ip) {
        Uni<CountryInfo> countryInfoUni = Uni.createFrom().item(ipCountryAdapter.getCountryInfo(ip));
        Uni<String> timeZoneUni = Uni.createFrom().item(timeZoneAdapter.getWorldTime(ip));
        Uni<String> currencyCodeUni = Uni.createFrom().item(currencyAdapter.getCurrencyCode(ip));

        Uni<QuoteResponse> quoteResponseUni = currencyCodeUni
                .onItem().transformToUni(currencyCode -> Uni.createFrom().item(quoteAdapter.getExchangeRate(quoteAccessKey, currencyCode, "USD")));

        return combineResponseData(ip, countryInfoUni, timeZoneUni, currencyCodeUni, quoteResponseUni);
    }

    private static CountryInfoDTO combineResponseData(String ip, Uni<CountryInfo> countryInfoUni, Uni<String> timeZoneUni, Uni<String> currencyCodeUni, Uni<QuoteResponse> quoteResponseUni) {
        return Uni.combine().all().unis(countryInfoUni, timeZoneUni, currencyCodeUni, quoteResponseUni)
                .asTuple()
                .onItem().transformToUni(tuple -> {
                    CountryInfoDTO countryInfoDTO = new CountryInfoDTO();
                    countryInfoDTO.setIp(ip);
                    countryInfoDTO.setCountryName(tuple.getItem1().getCountryName() + " (" + tuple.getItem1().getCountryCode() + ")");
                    countryInfoDTO.setCurrency(new CurrencyDTO(tuple.getItem3(), (tuple.getItem4() == null || tuple.getItem4().getRates() == null) ? "not found" : String.valueOf(tuple.getItem4().getRates().get("USD"))));
                    countryInfoDTO.setTime(List.of(tuple.getItem2()));
                    countryInfoDTO.setLanguages((tuple.getItem1() ==null || tuple.getItem1().getLocation() == null) ? null : tuple.getItem1().getLocation().getLanguages().stream().map(Language::getName).collect(Collectors.toList()));
                    countryInfoDTO.setEstimatedDistanceFromBuenosAiresToCountry(calculateDistanceFromBuenosAires(tuple.getItem1().getLatitude(), tuple.getItem1().getLongitude()));

                    return Uni.createFrom().item(countryInfoDTO);
                }).await().indefinitely();
    }

    public static String calculateDistanceFromBuenosAires(double lat2, double lon2) {
        double lat1 = -34.6037;
        double lon1 = -58.3816;

        double dLat = degreesToRadians(lat2 - lat1);
        double dLon = degreesToRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(degreesToRadians(lat1)) * Math.cos(degreesToRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (Math.round(EARTH_RADIUS_KM * c)) +((Math.round(EARTH_RADIUS_KM * c)) == 1 ? " km" : " kms");
    }

    public static double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180.0;
    }
}
