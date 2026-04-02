package com.example.TenantApi.Controller;

import com.example.TenantApi.Repository.Tenant;
import com.example.TenantApi.Service.TenantService;
import com.example.TenantApi.metrics.TenantMetrics;
import org.springframework.web.bind.annotation.*;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Gauge;

import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api")
public class TenantController {

    private final TenantService tenantService;
    private final TenantMetrics metrics;

    public TenantController(TenantService tenantService, MeterRegistry meterRegistry,TenantMetrics metrics) {
        this.tenantService = tenantService;
        this.metrics = metrics;
        Gauge.builder("tenantapi.tenantcount", tenantCount()).
                tag("version","v1").
                description("").
                register(meterRegistry);
    }

    public Supplier<Number> tenantCount() {
        return ()->getTenants().size();
    }

    @GetMapping("/tenants")
    public List<Tenant> getTenants() {
        return tenantService.getTenants();
    }

    @GetMapping("/tenants/{id}")
    public Tenant getTenantById(@PathVariable int id) {
        return tenantService.getTenantById(id);
    }

    @PutMapping("/tenants/{id}")
    public Tenant updateTenant(@PathVariable int id, @RequestBody Tenant tenant) {
        tenant.setId(id);
        return tenantService.updateTenant(tenant);
    }

//    @PostMapping("/tenants")
//    public boolean createTenant(@RequestBody Tenant tenant) {
//        return tenantService.addTenant(tenant);
//    }

    @PostMapping("/tenants") public boolean createTenant(@RequestBody Tenant tenant) {
        boolean created = tenantService.addTenant(tenant); if (created) { metrics.increment(); } return created; }

    @DeleteMapping("/tenants/{id}")
    public boolean deleteTenant(@PathVariable int id) {
        return tenantService.deleteTenant(id);
    }
}
