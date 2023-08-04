package com.gs.usecase;

import com.google.gson.Gson;
import com.gs.infra.service.RequestBinder;
import com.gs.infra.service.ServiceRequest;

/**
 * Binds a general request to the specific service.
 */
public class Person_ScheduleBinder implements RequestBinder {

    private final Gson gson = new Gson();

    @Override
    public ServiceRequest bind(spark.Request request) {

        Person_ScheduleRequest requestFromHeaders = new Person_ScheduleRequest();
        requestFromHeaders.setTL_KURS_K_PNIMI(request.queryParams("idno"));
        requestFromHeaders.setPortal_Calendary_View_FROM_T_DATE(request.queryParams("from_date"));
        requestFromHeaders.setPortal_Calendary_View_TO_T_DATE(request.queryParams("to_date"));
        requestFromHeaders.setLimit(request.queryParams("limit"));

        return requestFromHeaders;
    }
}
