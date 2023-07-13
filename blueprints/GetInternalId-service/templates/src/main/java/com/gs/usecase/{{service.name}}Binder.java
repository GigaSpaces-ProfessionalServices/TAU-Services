package com.gs.usecase;

import com.google.gson.Gson;
import com.gs.infra.service.RequestBinder;
import com.gs.infra.service.ServiceRequest;

//todo change this bind method, so it will fit to the number of request params in the values.yaml file.
public class {{service.name}}Binder implements RequestBinder {

    private final Gson gson = new Gson();

    @Override
    public ServiceRequest bind(spark.Request request) {
        {{service.name}}Request requestFromHeaders = new {{service.name}}Request();
        requestFromHeaders.set{{request.param1}}(request.queryParams("{{request.param1}}"));

        return requestFromHeaders;
    }
}
