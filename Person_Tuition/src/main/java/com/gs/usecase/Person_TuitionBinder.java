package com.gs.usecase;

import com.google.gson.Gson;
import com.gs.infra.service.RequestBinder;
import com.gs.infra.service.ServiceRequest;

/**
 * Binds a general request to the specific service.
 */
public class Person_TuitionBinder implements RequestBinder {

    private final Gson gson = new Gson();

    @Override
    public ServiceRequest bind(spark.Request request) {

        Person_TuitionRequest requestFromHeaders = new Person_TuitionRequest();
        requestFromHeaders.setK_PNIMI(request.queryParams("idno"));
        requestFromHeaders.setFROM_YEAR(request.queryParams("from_date"));
        requestFromHeaders.setLimit(request.queryParams("limit"));

        return requestFromHeaders;
    }
}
