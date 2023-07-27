package org.proyect.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.proyect.application.dto.CountryInfoDTO;
import org.proyect.domain.CountryInfo;
import org.proyect.domain.QuoteResponse;
import org.proyect.infraestructure.output.adapter.impl.CurrencyAdapter;
import org.proyect.infraestructure.output.adapter.impl.IpCountryAdapter;
import org.proyect.infraestructure.output.adapter.impl.QuoteAdapter;
import org.proyect.infraestructure.output.adapter.impl.TimeZoneAdapter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class GeolocationServiceTest {
    @InjectMocks
    GeolocationService geolocationService;

    @Mock
    private IpCountryAdapter ipCountryAdapter;

    @Mock
    private TimeZoneAdapter timeZoneAdapter;

    @Mock
    private CurrencyAdapter currencyAdapter;

    @Mock
    private QuoteAdapter quoteAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCountryInfo_WithValidData_ReturnCountryInfoDTO() {
        String ipAddress = "192.168.1.1";

        CountryInfo countryInfoMock = new CountryInfo();
        countryInfoMock.setCountryName("Argentina");
        countryInfoMock.setCountryCode("AR");
        countryInfoMock.setLatitude(-34.60);
        countryInfoMock.setLongitude(-58.3816);

        String timeZoneMock = "2023-07-27";

        String currencyCodeMock = "ARS";

        QuoteResponse quoteResponseMock = new QuoteResponse();

        when(ipCountryAdapter.getCountryInfo(ipAddress)).thenReturn(countryInfoMock);
        when(timeZoneAdapter.getWorldTime(ipAddress)).thenReturn(timeZoneMock);
        when(currencyAdapter.getCurrencyCode(ipAddress)).thenReturn(currencyCodeMock);
        when(quoteAdapter.getExchangeRate(anyString(), anyString(), anyString())).thenReturn(quoteResponseMock);

        CountryInfoDTO countryInfoResponse = geolocationService.getCountryInfo(ipAddress);

        assertNotNull(countryInfoResponse);
        assertEquals(ipAddress, countryInfoResponse.getIp());
        assertEquals("Argentina (AR)", countryInfoResponse.getCountryName());
    }

    @Test
    public void testCalculateDistanceFromBuenosAires_ValidCoordinates_ReturnsCorrectDistance() {
        //latitud y longitud de new york
        double lat3 = 40.7128;
        double lon3 = -74.0060;

        String distanceFromNewYork = GeolocationService.calculateDistanceFromBuenosAires(lat3, lon3);

        assertEquals("8526 kms", distanceFromNewYork);
    }
}