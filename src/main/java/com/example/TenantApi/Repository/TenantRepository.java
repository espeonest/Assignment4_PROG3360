package com.example.TenantApi.Repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TenantRepository {

    private final List<Tenant> tenants;
    public TenantRepository(){
        tenants = new ArrayList<>();
    }

    public List<Tenant> getTenants() {
        return tenants;
    }

    public boolean addTenant(Tenant tenant){
        return tenants.add(tenant);
    }

    public boolean removeTenant(int id){
        for (Tenant t : tenants){
            if(t.getId()==id){
                tenants.remove(t);
                return true;
            }
        }
        return false;
    }

    public boolean updateTenant(Tenant tenant){
        tenants.forEach(t->{
           if(t.getId()==tenant.getId()){
               t.setAddress(tenant.getAddress());
               t.setEmail(tenant.getEmail());
               t.setRent(tenant.getRent());
               t.setAmountOwing(tenant.getAmountOwing());
           }
        });
        return tenants.contains(tenant);
    }
}
