package com.gs.usecase;

import tau.ods.gs.model.logging.LogBuilder;
import tau.ods.gs.model.logging.LogMessage;
import com.gs.infra.service.ErrorServiceResponse;
import org.slf4j.Logger;
import java.sql.Date;
import java.util.ArrayList;
import com.gs.infra.service.GeneralTask;
import org.openspaces.core.executor.Task;
import com.gigaspaces.document.SpaceDocument;
import com.j_spaces.core.client.SQLQuery;
import static java.lang.String.format;


/*
This class is not in use. We are using JDBC
 */
public class Person_Tziun_KursApiTask extends GeneralTask<Person_Tziun_KursRequest, Person_Tziun_KursResponse> implements Task<ArrayList<Person_Tziun_KursResponse>> {

    private static final Logger logger_service = org.slf4j.LoggerFactory.getLogger(Person_Tziun_KursApiTask.class);


    public ArrayList execute() {

        ArrayList responseList = new ArrayList<>();

        return responseList;
    }

    @Override
    public Integer routing() {
        return null;
    }
}