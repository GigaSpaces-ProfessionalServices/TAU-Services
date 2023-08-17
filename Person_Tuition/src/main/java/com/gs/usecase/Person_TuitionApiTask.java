package com.gs.usecase;


import java.util.ArrayList;
import com.gs.infra.service.GeneralTask;
import com.gs.infra.service.ServiceResponse;
import org.openspaces.core.executor.Task;


/*
This class is not in use. We are using JDBC
 */
public class Person_TuitionApiTask extends GeneralTask<Person_TuitionRequest, ServiceResponse> implements Task<ArrayList<ServiceResponse>> {


    @Override
    public Integer routing() {
        return null;
    }

    @Override
    public ArrayList<ServiceResponse> execute() {
        return null;
    }
}