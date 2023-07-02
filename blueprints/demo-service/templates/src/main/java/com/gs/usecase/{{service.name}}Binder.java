package com.gs.usecase;

import com.google.gson.Gson;
import com.gs.infra.service.RequestBinder;
import com.gs.infra.service.ServiceRequest;

/**
 * Binds a general request to the specific service.
 */
public class {{service.name}}Binder implements RequestBinder {

    private final Gson gson = new Gson();

    @Override
    public ServiceRequest bind(spark.Request request) {
        {{service.name}}Request requestFromHeaders = new {{service.name}}Request();
        requestFromHeaders.set{{request.param1}}(request.queryParams("{{request.param1}}"));
        requestFromHeaders.set{{request.param2}}(request.queryParams("{{request.param2}}"));

        return requestFromHeaders;
    }
}
