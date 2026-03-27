package com.example.TenantApi.Service;

import com.example.TenantApi.Repository.Tenant;
import com.example.TenantApi.Repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantService {
    private final TenantRepository tenantRepository;
    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public List<Tenant> getTenants() {
        return tenantRepository.getTenants();
    }

    public Tenant getTenantById(int id) {
        var tenantList = tenantRepository.getTenants();
        for (Tenant tenant : tenantList) {
            if (tenant.getId() == id) {
                return tenant;
            }
        }
        return null;
    }

    public boolean addTenant(Tenant tenant) {
        return tenantRepository.addTenant(tenant);
    }

    public Tenant updateTenant(Tenant tenant) {
        if (tenantRepository.updateTenant(tenant)) return tenant;
        else return null;
    }

    public boolean deleteTenant(int id) {
        return tenantRepository.removeTenant(id);
    }
}
