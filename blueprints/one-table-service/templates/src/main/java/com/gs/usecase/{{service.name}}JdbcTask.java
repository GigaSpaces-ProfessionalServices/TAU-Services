package com.gs.usecase;

import java.sql.*;
import java.util.*;
import com.gs.infra.service.GeneralTask;
import org.openspaces.core.executor.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class {{service.name}}JdbcTask extends GeneralTask<{{service.name}}Request, {{service.name}}Response> implements Task<ArrayList<{{service.name}}Response>> {
    private static final Logger logger = LoggerFactory.getLogger({{service.name}}Response.class);

    //todo change here the number of query params and request params acording to what you have in the values.yaml file
    // this query will be used when setting the 2 parameters
    static private final String USECASE_QUERY1 ="SELECT {{query.param1}},{{query.param2}},{{query.param3}},{{query.param4}} " +
            "FROM {{service.type}} " +
            "WHERE {{request.param1}} = '%s' AND {{request.param2}} = '%s'";

    //todo change here the number of query params and request params acording to what you have in the values.yaml file.
    // this query will be used when setting just the first parameter
    static private final String USECASE_QUERY2 ="SELECT {{query.param1}},{{query.param2}},{{query.param3}},{{query.param4}} " +
        "FROM {{service.type}} " +
        "WHERE {{request.param1}} = '%s'";

    @Override
    public Integer routing() {
        return null;
    }

    public ArrayList<{{service.name}}Response> execute() {

        String query = null;
        //todo change here the number of query params and request params acording to what you have in the values.yaml file.
        if (request.get{{request.param2}}() != null){
            query=String.format(USECASE_QUERY1,request.get{{request.param1}}(),request.get{{request.param2}}());
        }else{
            query=String.format(USECASE_QUERY2,request.get{{request.param1}}());
        }

        // A list to store the results
        ArrayList<{{service.name}}Response> responseList = new ArrayList<>();

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
                {{service.name}}Response response = new {{service.name}}Response(resultSet);
                responseList.add(response);
            }

        } catch (SQLException sqlException) {
            logger.error("Cannot query database ", sqlException);
            return null;
        }
        return responseList;
    }
}
