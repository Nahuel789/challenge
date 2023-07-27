package org.proyect.application;

import io.quarkus.cache.CacheResult;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.proyect.domain.Statistic;
import org.proyect.infraestructure.input.adapter.CacheAdapter;
import org.proyect.infraestructure.input.port.StatisticsInputPort;
import org.proyect.infraestructure.output.adapter.StatisticsRepository;

@ApplicationScoped
public class StatisticsService implements StatisticsInputPort {
    @Inject
    private StatisticsRepository statisticsRepository;

    @Inject
    private CacheAdapter cacheAdapter;

    @Override
    public Uni<Void> saveStatistics(Statistic statistic) {

        Statistic invocationFound = statisticsRepository.findByCountry(statistic.getCountry());

        if (invocationFound == null) {
            statistic.setInvocations(1);
            statisticsRepository.save(statistic);
        } else {
            int invocationsAmount = invocationFound.getInvocations();
            invocationFound.setInvocations(invocationsAmount + 1);
            statisticsRepository.save(invocationFound);
        }

        invalidateStatisticsCache();

        return Uni.createFrom().voidItem();
    }

    private void invalidateStatisticsCache() {
        cacheAdapter.invalidateCacheGreaterDistance();
        cacheAdapter.invalidateCacheAverageDistance();
        cacheAdapter.invalidateCacheShorterDistance();
    }

    @Override
    @CacheResult(cacheName = "greater-distance")
    public Statistic getGreaterDistanceStatistic() {
        return statisticsRepository.findTopByOrderByDistanceDesc();
    }

    @Override
    @CacheResult(cacheName = "shorter-distance")
    public Statistic getShorterDistanceStatistic() {
        return statisticsRepository.findTopByOrderByDistanceAsc();
    }

    @Override
    @CacheResult(cacheName = "average-distance")
    public String getAverageDistance() {
        int totalDistance = 0;
        int totalInvocations = 0;

        for (Statistic stat : statisticsRepository.findAll()) {
            int distance = stat.getDistance();
            int invocations = stat.getInvocations();

            totalDistance += distance * invocations;
            totalInvocations += invocations;
        }

        int result = (totalDistance / totalInvocations);
        return "average distance: "+result  +(result == 1 ? " km" :" kms");
    }
}
