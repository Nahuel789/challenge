package org.proyect.infraestructure.output.adapter;

import org.proyect.domain.Statistic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends CrudRepository<Statistic,Long> {
    Statistic findByCountry(String country);

    Statistic findTopByOrderByDistanceDesc();

    Statistic findTopByOrderByDistanceAsc();
}
