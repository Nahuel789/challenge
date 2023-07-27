package org.proyect.infraestructure.input.adapter;

import io.quarkus.cache.CacheInvalidateAll;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CacheAdapter {
    @CacheInvalidateAll(cacheName = "average-distance")
    public void invalidateCacheAverageDistance() {
    }

    @CacheInvalidateAll(cacheName = "greater-distance")
    public void invalidateCacheGreaterDistance() {
    }

    @CacheInvalidateAll(cacheName = "shorter-distance")
    public void invalidateCacheShorterDistance() {
    }
}
