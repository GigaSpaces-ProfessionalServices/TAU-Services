package com.gs.usecase;


import java.util.ArrayList;
import com.gs.infra.service.GeneralTask;
import org.openspaces.core.executor.Task;


/*
This class is not in use. We are using JDBC
 */
public class Person_TuitionApiTask extends GeneralTask<Person_TuitionRequest, Person_TuitionResponse> implements Task<ArrayList<Person_TuitionResponse>> {


    @Override
    public Integer routing() {
        return null;
    }

    @Override
    public ArrayList<Person_TuitionResponse> execute() {
        return null;
    }
}