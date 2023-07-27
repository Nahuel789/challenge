package org.proyect.infraestructure.input.port;

import io.smallrye.mutiny.Uni;
import org.proyect.domain.Statistic;

public interface StatisticsInputPort {
    Statistic getGreaterDistanceStatistic();
    Statistic getShorterDistanceStatistic();
    Uni<Void> saveStatistics(Statistic statistic);
    String getAverageDistance();

}
