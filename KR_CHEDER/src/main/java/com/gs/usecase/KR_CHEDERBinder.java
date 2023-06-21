package com.gs.usecase;

import com.google.gson.Gson;
import tau.ods.gs.util.Const;
import com.gs.infra.service.RequestBinder;
import com.gs.infra.service.ServiceRequest;

/**
 * Binds a general request to the specific service.
 */
public class KR_CHEDERBinder implements RequestBinder {

    private final Gson gson = new Gson();

    @Override
    public ServiceRequest bind(spark.Request request) {

        KR_CHEDERRequest requestFromHeaders = new KR_CHEDERRequest();
        requestFromHeaders.setK_BINYAN(request.queryParams("K_BINYAN"));
        requestFromHeaders.setK_MIS_CHEDER(request.queryParams("K_MIS_CHEDER"));

       return requestFromHeaders;
    }
}
