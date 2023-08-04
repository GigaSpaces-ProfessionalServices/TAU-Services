package com.gs.usecase;


import java.util.ArrayList;
import com.gs.infra.service.GeneralTask;
import org.openspaces.core.executor.Task;


/*
This class is not in use. We are using JDBC
 */
public class Person_ScheduleApiTask extends GeneralTask<Person_ScheduleRequest, Person_ScheduleResponse> implements Task<ArrayList<Person_ScheduleResponse>> {


    @Override
    public Integer routing() {
        return null;
    }

    @Override
    public ArrayList<Person_ScheduleResponse> execute() {
        return null;
    }
}