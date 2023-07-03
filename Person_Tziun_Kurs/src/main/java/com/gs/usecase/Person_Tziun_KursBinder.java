package com.gs.usecase;

import com.google.gson.Gson;
import tau.ods.gs.util.Const;
import com.gs.infra.service.RequestBinder;
import com.gs.infra.service.ServiceRequest;

/**
 * Binds a general request to the specific service.
 */
public class Person_Tziun_KursBinder implements RequestBinder {

    private final Gson gson = new Gson();

    @Override
    public ServiceRequest bind(spark.Request request) {

        Person_Tziun_KursRequest requestFromHeaders = new Person_Tziun_KursRequest();
        requestFromHeaders.setPERSON_IDNO(request.queryParams("idno"));
        requestFromHeaders.setTL_KURS_SEM_KVUTZA(request.queryParams("sem_kvutza"));
        requestFromHeaders.setLimit(request.queryParams("limit"));

        return requestFromHeaders;
    }
}
