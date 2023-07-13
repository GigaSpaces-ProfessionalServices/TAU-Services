package com.gs.usecase.server;

import com.gs.infra.health.HealthReporter;
import com.gs.infra.server.RequestHandler;
import com.gs.infra.server.ServerMapping;
import com.gs.infra.metadata.ServiceCatalog;
import org.openspaces.config.DefaultServiceConfig;
import org.openspaces.core.config.annotation.SpaceProxyBeansConfig;
import org.openspaces.core.space.SecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DefaultServiceConfig.class, SpaceProxyBeansConfig.class, SecurityConfig.class})
public class {{service.name}}ServerConfig {

    private int minPort;
    private int maxPort;
    private String serviceId;
    private String requiredRole;
    private String consulHost; //default host empty string - doesn't register to consul
    private int consulPort;  //default consul port
    private HealthReporter healthReporter;
    private String version;
    private RequestHandler requestHandler;
    private ServiceCatalog serviceCatalog;

    @Bean
    ServerMapping server() {
        return new {{service.name}}ServerMapping(minPort, maxPort, serviceId, requiredRole, consulHost, consulPort, healthReporter, version, requestHandler, serviceCatalog);
    }


    public String getRequiredRole() {
       return requiredRole;
    }

    public void setRequiredRole(String requiredRole) {
       this.requiredRole = requiredRole;
    }

    public int getMinPort() {
        return minPort;
    }

    public void setMinPort(int minPort) {
        this.minPort = minPort;
    }

    public int getMaxPort() {
        return maxPort;
    }

    public void setMaxPort(int maxPort) {
        this.maxPort = maxPort;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getConsulHost() {
        return consulHost;
    }

    public void setConsulHost(String consulHost) {
        this.consulHost = consulHost;
    }

    public int getConsulPort() {
        return consulPort;
    }

    public void setConsulPort(int consulPort) {
        this.consulPort = consulPort;
    }

    public HealthReporter getHealthReporter() {
        return healthReporter;
    }

    public void setHealthReporter(HealthReporter healthReporter) {
        this.healthReporter = healthReporter;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public RequestHandler getRequestHandler() {
        return requestHandler;
    }

    public void setRequestHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    public ServiceCatalog getServiceCatalog() {
        return serviceCatalog;
    }

    public void setServiceCatalog(ServiceCatalog serviceCatalog) {
        this.serviceCatalog = serviceCatalog;
    }
}