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
//    private static final Logger logger_service = tau.ods.gs.model.logging.LoggerFactory.getLogger(Person_Tziun_KursApiTask.class);
    static private final String type = "STUD.Person_Tziun_Kurs";


    public ArrayList execute() {

        String USECASE_QUERY = null;
        String query = null;
        String idno = request.getPERSON_IDNO();
        String semKvutza = request.getTL_KURS_SEM_KVUTZA();

        ArrayList responseList = new ArrayList<>();

       if (idno == null && semKvutza != null) {
            responseList.add(new ErrorServiceResponse("Invalid keys", "400", "keys: idno - NaN, sem_kvutza - " + semKvutza));

            logger_service.error(LogBuilder.get()
                    .instantiateHttpRequest()
                    .nullHttpResponse()
                    .nullError()
                    .setRequestMessage("Query: idno = " + idno + " sem_kvutz = " + semKvutza)
                    .setLevel(LogMessage.Level.ERROR)
                    .setTimestamp(new Date(System.currentTimeMillis()))
                    .createLogMessage());

            logger_service.error(LogBuilder.get()
                    .instantiateHttpResponse()
                    .nullHttpRequest()
                    .instantiateError()
                    .setErrorCode(400)
                    .setLevel(LogMessage.Level.ERROR)
                    .setTimestamp(new Date(System.currentTimeMillis()))
                    .setErrorMessage("Invalid keys: keys: idno - NaN, sem_kvutza - " + semKvutza)
                    .createLogMessage());

            return responseList;

        }else if (idno == null && semKvutza == null) {
            responseList.add(new ErrorServiceResponse("Invalid keys", "400", "keys: idno - NaN, sem_kvutza - NaN"));

            logger_service.error(LogBuilder.get()
                    .instantiateHttpRequest()
                    .nullHttpResponse()
                    .nullError()
                    .setRequestMessage("Query: idno = " + idno + " sem_kvutza = " + semKvutza)
                    .setLevel(LogMessage.Level.ERROR)
                    .setTimestamp(new Date(System.currentTimeMillis()))
                    .createLogMessage());

            logger_service.error(LogBuilder.get()
                    .instantiateHttpResponse()
                    .nullHttpRequest()
                    .nullError()
                    .setErrorCode(400)
                    .setLevel(LogMessage.Level.ERROR)
                    .setTimestamp(new Date(System.currentTimeMillis()))
                    .setErrorMessage("Invalid keys: idno - NaN, sem_kvutza - NaN")
                    .createLogMessage());

            return responseList;

        }else if (idno != null && semKvutza.length() != 5){
            responseList.add(new ErrorServiceResponse("Invalid value for: sem_kvutza, format: ^[0-9]{5}$", "400", "idno: " + idno + " sem_kvutza: " + semKvutza));

            logger_service.error(LogBuilder.get()
                    .instantiateHttpRequest()
                    .nullHttpResponse()
                    .nullError()
                    .setRequestMessage("Query: idno = " + idno + " sem_kvutza = " + semKvutza)
                    .setLevel(LogMessage.Level.ERROR)
                    .setTimestamp(new Date(System.currentTimeMillis()))
                    .createLogMessage());

            logger_service.error(LogBuilder.get()
                    .instantiateHttpResponse()
                    .nullHttpRequest()
                    .instantiateError()
                    .setErrorCode(400)
                    .setLevel(LogMessage.Level.ERROR)
                    .setTimestamp(new Date(System.currentTimeMillis()))
                    .setErrorMessage("Invalid value for: sem_kvutza, format: ^[0-9]{5}$ idno: " + idno + " sem_kvutza: " + semKvutza)
                    .createLogMessage());

            return responseList;
        }else if (idno != null && idno.length() == 9 && semKvutza == null){
            responseList.add(new ErrorServiceResponse("missing reqested feilds", "400", "sem_kvutza undefined dosent exist"));

            logger_service.error(LogBuilder.get()
                    .instantiateHttpRequest()
                    .nullHttpResponse()
                    .nullError()
                    .setRequestMessage("Query: idno = " + idno + " sem_kvutza = " + semKvutza)
                    .setLevel(LogMessage.Level.ERROR)
                    .setTimestamp(new Date(System.currentTimeMillis()))
                    .createLogMessage());

            logger_service.error(LogBuilder.get()
                    .instantiateHttpResponse()
                    .nullHttpRequest()
                    .instantiateError()
                    .setErrorCode(400)
                    .setLevel(LogMessage.Level.ERROR)
                    .setTimestamp(new Date(System.currentTimeMillis()))
                    .setErrorMessage("missing reqested feilds: ^[0-9]{5}$: sem_kvutza undefined dosent exist")
                    .createLogMessage());

            return responseList;
        }else if (idno != null && idno.length() == 9 && !semKvutza.equals("null") && semKvutza.length() != 5) {
            responseList.add(new ErrorServiceResponse("Invalid value for: sem_kvutza, format: ^[0-9]{5}$", "400", "sem_kvutza: " + semKvutza.length()));

            logger_service.error(LogBuilder.get()
                    .instantiateHttpRequest()
                    .nullHttpResponse()
                    .instantiateError()
                    .setRequestMessage("Query: idno = " + idno + " sem_kvutza = " + semKvutza)
                    .setLevel(LogMessage.Level.ERROR)
                    .setTimestamp(new Date(System.currentTimeMillis()))
                    .createLogMessage());

            logger_service.error(LogBuilder.get()
                    .instantiateHttpResponse()
                    .nullHttpRequest()
                    .instantiateError()
                    .setErrorCode(400)
                    .setLevel(LogMessage.Level.ERROR)
                    .setTimestamp(new Date(System.currentTimeMillis()))
                    .setErrorMessage("Invalid value for: sem_kvutza, format: ^[0-9]{5}$: sem_kvutza: " + semKvutza.length())
                    .createLogMessage());

            return responseList;
        }



        if (idno.equals("null")){

            USECASE_QUERY = "idno" + " = %s ";

            query = format(USECASE_QUERY,
                    request.getPERSON_IDNO());

        }else {

            USECASE_QUERY = "idno" + " = %s AND sem_kvutza = %s";

            query = format(USECASE_QUERY,
                    request.getPERSON_IDNO(), request.getPERSON_IDNO());
        }

        SQLQuery<SpaceDocument> sqlQuery = new SQLQuery<>(type, query);
        SpaceDocument[] results = gigaSpace.readMultiple(sqlQuery, 100);

        for (SpaceDocument entry : results) {
            responseList.add(new Person_Tziun_KursResponse(entry));

        }

        logger_service.info(LogBuilder.get()
                .instantiateHttpRequest()
                .nullHttpResponse()
                .nullError()
                .setRequestMessage("Query: " + sqlQuery.getQuery())
                .setLevel(LogMessage.Level.INFO)
                .setTimestamp(new Date(System.currentTimeMillis()))
                .createLogMessage());

        logger_service.info(LogBuilder.get()
                .instantiateHttpResponse()
                .nullHttpRequest()
                .nullError()
                .setStatusCode(200)
                .setLevel(LogMessage.Level.INFO)
                .setTimestamp(new Date(System.currentTimeMillis()))
                .setMessage("Query: " + sqlQuery.getQuery())
                .createLogMessage());

        return responseList;
    }

    @Override
    public Integer routing() {
        return null;
    }
}