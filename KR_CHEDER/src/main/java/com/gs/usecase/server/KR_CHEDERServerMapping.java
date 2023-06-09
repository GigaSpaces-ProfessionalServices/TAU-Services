package com.gs.usecase.server;

import com.gs.infra.health.HealthReporter;
import com.gs.infra.server.*;
import com.gs.usecase.*;
import com.gs.infra.service.ServiceRoute;
import com.gs.infra.metadata.ServiceCatalog;
import com.gs.infra.server.SwaggerGenerator;


public class KR_CHEDERServerMapping extends ServerMapping {

    public KR_CHEDERServerMapping(int minPort, int maxPort, String serviceId, String requiredRole, String consulHost,
                                  int consulPort, HealthReporter healthReporter, String version, RequestHandler requestHandler, ServiceCatalog serviceCatalog) {
        super (minPort, maxPort, serviceId, requiredRole, consulHost, consulPort, healthReporter, version, requestHandler, serviceCatalog);
    }

    @Override
    public String getEndpoint() {
        return "/u1";
    }

    @Override
    public String generateSwagger(){
        return SwaggerGenerator.generate("KR_CHEDER","KR_CHEDER",getEndpoint(),
                                        "KR_CHEDER", port,
        KR_CHEDERRequest.class, KR_CHEDERResponse.class);
        }


    @Override
    public ServiceRoute getServiceRoute() {
        return new ServiceRoute(new KR_CHEDERApiTask(), new KR_CHEDERBinder());
    }
}