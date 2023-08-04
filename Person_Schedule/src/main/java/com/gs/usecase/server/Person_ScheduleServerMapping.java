package com.gs.usecase.server;

import com.gs.infra.health.HealthReporter;
import com.gs.infra.server.*;
import com.gs.usecase.*;
import com.gs.infra.service.ServiceRoute;
import com.gs.infra.metadata.ServiceCatalog;
import com.gs.infra.server.SwaggerGenerator;


public class Person_ScheduleServerMapping extends ServerMapping {

    public Person_ScheduleServerMapping(int minPort, int maxPort, String serviceId, String requiredRole, String consulHost,
                                  int consulPort, HealthReporter healthReporter, String version, RequestHandler requestHandler, ServiceCatalog serviceCatalog) {
        super (minPort, maxPort, serviceId, requiredRole, consulHost, consulPort, healthReporter, version, requestHandler, serviceCatalog);
    }

    @Override
    public String getEndpoint() {
        return "/u1";
    }

    @Override
    public String generateSwagger(){
        return SwaggerGenerator.generate("Person_Schedule","Person_Schedule",getEndpoint(),
                                        "Person_Schedule", port,
        Person_ScheduleRequest.class, Person_ScheduleResponse.class);
        }


    @Override
    public ServiceRoute getServiceRoute() {
        // return new ServiceRoute(new Person_ScheduleApiTask(), new Person_ScheduleBinder());
        return new ServiceRoute(new Person_ScheduleJdbcTask(), new Person_ScheduleBinder());
    }
}