package com.gs.usecase.server;

import com.gs.infra.health.HealthReporter;
import com.gs.usecase.{{service.name}}JdbcTask;
import com.gs.usecase.{{service.name}}Binder;
import com.gs.usecase.{{service.name}}Request;
import com.gs.usecase.{{service.name}}Response;
import com.gs.infra.server.RequestHandler;
import com.gs.infra.server.ServerMapping;
import com.gs.infra.service.ServiceRoute;
import com.gs.infra.metadata.ServiceCatalog;
import com.gs.infra.server.SwaggerGenerator;


public class {{service.name}}ServerMapping extends ServerMapping {

    public {{service.name}}ServerMapping(int minPort, int maxPort, String serviceId, String requiredRole, String consulHost,
                              int consulPort, HealthReporter healthReporter, String version, RequestHandler requestHandler, ServiceCatalog serviceCatalog) {
        super (minPort, maxPort, serviceId, requiredRole, consulHost, consulPort, healthReporter, version, requestHandler, serviceCatalog);
    }

    @Override
    public String getEndpoint() {
        return "/u1";
    }

    @Override
    public String generateSwagger(){
        return SwaggerGenerator.generate("{{service.description}}","{{service.name}}",getEndpoint(),
                                        "{{service.description}}", port,
        {{service.name}}Request.class, {{service.name}}Response.class);
    }

    @Override
    public ServiceRoute getServiceRoute() {
        return new ServiceRoute(new {{service.name}}JdbcTask(), new {{service.name}}Binder());
    }
}