package com.example.TenantApi.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class TenantMetrics {

    private final Counter tenantCreatedCounter;

    public TenantMetrics(MeterRegistry registry) {
        this.tenantCreatedCounter = Counter.builder("tenant_created_count")
                .description("Number of tenants created")
                .register(registry);
    }

    public void increment() {
        tenantCreatedCounter.increment();
    }
}