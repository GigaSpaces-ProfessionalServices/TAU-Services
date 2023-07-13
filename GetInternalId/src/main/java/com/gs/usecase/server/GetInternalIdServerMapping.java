package com.gs.usecase.server;

import com.gs.infra.health.HealthReporter;
import com.gs.usecase.GetInternalIdJdbcTask;
import com.gs.usecase.GetInternalIdBinder;
import com.gs.usecase.GetInternalIdRequest;
import com.gs.usecase.GetInternalIdResponse;
import com.gs.infra.server.RequestHandler;
import com.gs.infra.server.ServerMapping;
import com.gs.infra.service.ServiceRoute;
import com.gs.infra.metadata.ServiceCatalog;
import com.gs.infra.server.SwaggerGenerator;


public class GetInternalIdServerMapping extends ServerMapping {

    public GetInternalIdServerMapping(int minPort, int maxPort, String serviceId, String requiredRole, String consulHost,
                              int consulPort, HealthReporter healthReporter, String version, RequestHandler requestHandler, ServiceCatalog serviceCatalog) {
        super (minPort, maxPort, serviceId, requiredRole, consulHost, consulPort, healthReporter, version, requestHandler, serviceCatalog);
    }

    @Override
    public String getEndpoint() {
        return "/u1";
    }

    @Override
    public String generateSwagger(){
        return SwaggerGenerator.generate("Get K_PNIMI BY IDNO","GetInternalId",getEndpoint(),
                                        "Get K_PNIMI BY IDNO", port,
        GetInternalIdRequest.class, GetInternalIdResponse.class);
    }

    @Override
    public ServiceRoute getServiceRoute() {
        return new ServiceRoute(new GetInternalIdJdbcTask(), new GetInternalIdBinder());
    }
}