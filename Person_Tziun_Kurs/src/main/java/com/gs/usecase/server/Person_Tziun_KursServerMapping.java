package com.gs.usecase.server;

import com.gs.infra.health.HealthReporter;
import com.gs.infra.server.*;
import com.gs.usecase.*;
import com.gs.infra.service.ServiceRoute;
import com.gs.infra.metadata.ServiceCatalog;
import com.gs.infra.server.SwaggerGenerator;


public class Person_Tziun_KursServerMapping extends ServerMapping {

    public Person_Tziun_KursServerMapping(int minPort, int maxPort, String serviceId, String requiredRole, String consulHost,
                                  int consulPort, HealthReporter healthReporter, String version, RequestHandler requestHandler, ServiceCatalog serviceCatalog) {
        super (minPort, maxPort, serviceId, requiredRole, consulHost, consulPort, healthReporter, version, requestHandler, serviceCatalog);
    }

    @Override
    public String getEndpoint() {
        return "/u1";
    }

    @Override
    public String generateSwagger(){
        return SwaggerGenerator.generate("Person_Tziun_Kurs","Person_Tziun_Kurs",getEndpoint(),
                                        "Person_Tziun_Kurs", port,
        Person_Tziun_KursRequest.class, Person_Tziun_KursResponse.class);
        }


    @Override
    public ServiceRoute getServiceRoute() {
        // return new ServiceRoute(new Person_Tziun_KursApiTask(), new Person_Tziun_KursBinder());
        return new ServiceRoute(new Person_Tziun_KursJdbcTask(), new Person_Tziun_KursBinder());
    }
}