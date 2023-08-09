package com.gs.usecase.server;

import com.gs.infra.health.HealthReporter;
import com.gs.infra.server.*;
import com.gs.usecase.*;
import com.gs.infra.service.ServiceRoute;
import com.gs.infra.metadata.ServiceCatalog;
import com.gs.infra.server.SwaggerGenerator;


public class Person_TuitionServerMapping extends ServerMapping {

    public Person_TuitionServerMapping(int minPort, int maxPort, String serviceId, String requiredRole, String consulHost,
                                  int consulPort, HealthReporter healthReporter, String version, RequestHandler requestHandler, ServiceCatalog serviceCatalog) {
        super (minPort, maxPort, serviceId, requiredRole, consulHost, consulPort, healthReporter, version, requestHandler, serviceCatalog);
    }

    @Override
    public String getEndpoint() {
        return "/u1";
    }

    @Override
    public String generateSwagger(){
        return SwaggerGenerator.generate("Person_Tuition","Person_Tuition",getEndpoint(),
                                        "Person_Tuition", port,
        Person_TuitionRequest.class, Person_TuitionResponse.class);
        }


    @Override
    public ServiceRoute getServiceRoute() {
        // return new ServiceRoute(new Person_TuitionApiTask(), new Person_TuitionBinder());
        return new ServiceRoute(new Person_TuitionJdbcTask(), new Person_TuitionBinder());
    }
}