package com.gs.usecase;

import com.google.gson.Gson;
import com.gs.infra.service.RequestBinder;
import com.gs.infra.service.ServiceRequest;

//todo change this bind method, so it will fit to the number of request params in the values.yaml file.
public class GetInternalIdBinder implements RequestBinder {

    private final Gson gson = new Gson();

    @Override
    public ServiceRequest bind(spark.Request request) {
        GetInternalIdRequest requestFromHeaders = new GetInternalIdRequest();
        requestFromHeaders.setIDNO(request.queryParams("IDNO"));

        return requestFromHeaders;
    }
}
