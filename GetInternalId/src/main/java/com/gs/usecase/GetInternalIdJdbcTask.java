package com.gs.usecase;

import java.sql.*;
import java.util.*;
import com.gs.infra.service.GeneralTask;
import org.openspaces.core.executor.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GetInternalIdJdbcTask extends GeneralTask<GetInternalIdRequest, GetInternalIdResponse> implements Task<ArrayList<GetInternalIdResponse>> {
    private static final Logger logger = LoggerFactory.getLogger(GetInternalIdResponse.class);

    //todo change here the number of query params and request params acording to what you have in the values.yaml file
    // this query will be used when setting the 2 parameters
    static private final String USECASE_QUERY1 ="SELECT K_PNIMI " +
            "FROM STUD.TA_PERSON " +
            "WHERE IDNO = '%s' ";

    @Override
    public Integer routing() {
        return null;
    }

    public ArrayList<GetInternalIdResponse> execute() {

        //todo change here the number of query params and request params acording to what you have in the values.yaml file.
        String query = String.format(USECASE_QUERY1,request.getIDNO());

        // A list to store the results
        ArrayList<GetInternalIdResponse> responseList = new ArrayList<>();

        Properties props = new Properties();
        props.put("com.gs.embeddedQP.enabled", "false");

        try (Connection con = DriverManager.getConnection(properties.getProperty("driverurl"),props)) {

            Statement stmt = con.createStatement();
            ResultSet resultSet = null;
            logger.info("query = " + query);

            try {
                 resultSet = stmt.executeQuery(query);
                logger.info("Got resultSet: " + resultSet);
            }catch(Exception e){
                logger.info("Got Exception: " + e);
            }

            if (resultSet == null) {
                logger.info("resultSet is null");
                return responseList;
            }

            while (resultSet.next()) {
                GetInternalIdResponse response = new GetInternalIdResponse(resultSet);
                responseList.add(response);
            }

        } catch (SQLException sqlException) {
            logger.error("Cannot query database ", sqlException);
            return null;
        }
        return responseList;
    }
}
