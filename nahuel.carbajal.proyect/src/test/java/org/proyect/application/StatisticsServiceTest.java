package org.proyect.application;

import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.proyect.domain.Statistic;
import org.proyect.infraestructure.input.adapter.CacheAdapter;
import org.proyect.infraestructure.output.adapter.StatisticsRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StatisticsServiceTest {

    @InjectMocks
    private StatisticsService statisticsService;

    @Mock
    private StatisticsRepository statisticsRepository;

    @Mock
    private CacheAdapter cacheAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveStatistics_InvocationNotFound_SaveNewStatistic() {
        String country = "Argentina";
        Statistic statistic = new Statistic();
        statistic.setCountry(country);

        when(statisticsRepository.findByCountry(country)).thenReturn(null);

        Uni<Void> saveUni = statisticsService.saveStatistics(statistic);

        verify(statisticsRepository).save(statistic);

        verify(cacheAdapter).invalidateCacheGreaterDistance();
        verify(cacheAdapter).invalidateCacheAverageDistance();
        verify(cacheAdapter).invalidateCacheShorterDistance();

        assertNotNull(saveUni);
    }

    @Test
    public void testSaveStatistics_InvocationFound_UpdateStatistic() {
        String country = "Argentina";
        Statistic existingStatistic = new Statistic();
        existingStatistic.setCountry(country);
        existingStatistic.setInvocations(3);

        Statistic statistic = new Statistic();
        statistic.setCountry(country);

        when(statisticsRepository.findByCountry(country)).thenReturn(existingStatistic);

        Uni<Void> saveUni = statisticsService.saveStatistics(statistic);

        verify(statisticsRepository).save(existingStatistic);

        verify(cacheAdapter).invalidateCacheGreaterDistance();
        verify(cacheAdapter).invalidateCacheAverageDistance();
        verify(cacheAdapter).invalidateCacheShorterDistance();

        assertNotNull(saveUni);
    }
}