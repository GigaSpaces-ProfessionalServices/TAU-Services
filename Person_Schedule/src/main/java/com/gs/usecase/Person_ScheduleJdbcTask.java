package com.gs.usecase;


import com.gs.infra.service.GeneralTask;
import org.openspaces.core.executor.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.*;


public class Person_ScheduleJdbcTask extends GeneralTask<Person_ScheduleRequest, Person_ScheduleResponse> implements Task<ArrayList<Person_ScheduleResponse>> {
    private static final Logger logger = LoggerFactory.getLogger(Person_ScheduleJdbcTask.class);

    static PreparedStatement preparedStatement;
    static Connection con;

//  "STUD.TL_KURS" - total 12M, We bring 160K for 5 years
//  "STUD.TA_PERSON" - 560K
//  "dbo.Portal_Calendary_View" - 270K
//  "STUD.KR_KURS" - 122K
//  "STUD.TB_911_BINYAN" - 250
//  "STUD.TB_002_OFEN_HORAA" - 20
//  "STUD.TB_962_TOAR_MORE" - 13

    static private final String USECASE_QUERY = "SELECT\n" +
            "tlkr.K_PNIMI,\n" +
            "concat(glb.V_YEAR,glb.SEMESTER) SEM_KVUTZA,\n" +
            "kr.K_KURS kr_K_KURS,\n" +
            "kr.TEUR_K kr_TEUR_K,\n" +
            "kr.TEUR_ENG_K kr_TEUR_ENG_K,\n" +
            "kr.TEUR_KURS kr_TEUR_KURS,\n" +
            "kr.TEUR_ENG kr_TEUR_ENG,\n" +
            "tlkr.KVUTZA,\n" +
            "kr.OFEN_HORAA1,\n" +
            "t002.TEUR_K1 t002_TEUR_K1,\n" +
            "t002.TEUR_ENG_K1 t002_TEUR_ENG_K1,\n" +
            "glb.V_DATE,\n" +
            "glb.V_START,\n" +
            "glb.V_END,\n" +
            "t962.TEUR_K t962_TEUR_K,\n" +
            "t962.TEUR_ENG_K t962_TEUR_ENG_K,\n" +
            "pr.SHEM_PRATI,\n" +
            "pr.SHEM_EMTZAI,\n" +
            "pr.SHEM_MISHP,\n" +
            "pr.SHEM_PRATI_ENG,\n" +
            "pr.SHEM_EMTZAI_ENG,\n" +
            "pr.SHEM_MISHP_ENG,\n" +
            "t911.TEUR_K t911_TEUR_K,\n" +
            "t911.TEUR_ENG_K t911_TEUR_ENG_K,\n" +
            "t911.NZ_ORECH,\n" +
            "t911.NZ_ROHAV,\n" +
            "glb.room,\n" +
            "glb.room_extra_code\n" +
            "FROM STUD.KR_KURS kr\n" + // kr - 122k
            "JOIN STUD.TB_002_OFEN_HORAA t002\n" + // t002 - 20
            "on kr.OFEN_HORAA1 = t002.K_CODE\n" +
            "JOIN dbo.Portal_Calendary_View glb on kr.K_KURS = glb.course_ID\n" + // glb - 270k
            "JOIN (SELECT IDNO, SHEM_PRATI, SHEM_EMTZAI, SHEM_MISHP, SHEM_PRATI_ENG, SHEM_EMTZAI_ENG, SHEM_MISHP_ENG, MORE, TOAR_SHEM FROM STUD.TA_PERSON WHERE MORE = '01') pr\n" +
            "ON pr.IDNO = glb.lecturer\n" + // person - 560k
            "JOIN STUD.TB_962_TOAR_MORE t962 on pr.TOAR_SHEM = t962.K_CODE\n" + // t962 - 13
            "JOIN STUD.TB_911_BINYAN t911 on glb.building = t911.K_CODE\n" + // t911 - 250
            "JOIN (SELECT K_PNIMI,KVUTZA,K_SEM,K_KURS,SEM_KVUTZA FROM STUD.TL_KURS WHERE K_PNIMI = ? AND MEVUTAL = '0') tlkr\n" + // tlkr - total 12M, We bring 160K for 5 years
            "on kr.K_KURS = tlkr.K_KURS\n" +
            "AND (tlkr.K_SEM >= kr.K_ME_SEM AND tlkr.K_SEM <= kr.AD_SEM)\n" +
            "AND tlkr.KVUTZA = glb.V_group\n" +
            "AND concat(glb.V_year,glb.semester)=tlkr.SEM_KVUTZA\n" +
            "AND glb.V_date >= ? AND glb.V_date <= ?\n" +
            // "AND glb.year >= (SELECT SUBSTR(SHANA_SCL_0-10,1,4) FROM STUD.TA_PIKUAH WHERE K_CODE='05')\n" +
            "ORDER BY glb.V_date\n" +
            "LIMIT ?";


    @Override
    public Integer routing() {
        return null;
    }

    private static Connection getConnection() {
        if (con == null) {
            logger.info("Connection is null. going to initialize");
            Properties props = new Properties();
            props.put("com.gs.embeddedQP.enabled", "true");

            try {
                con = DriverManager.getConnection(properties.getProperty("driverurl"), props);
            } catch (SQLException e) {
                logger.error("Failed to get connection", e);
            }
        } else {
            logger.info("We already have a Connection");
        }

        return con;
    }

    private PreparedStatement getPreparedStatement(String query) {
        if (preparedStatement == null) {
            try {
                preparedStatement = con.prepareStatement(query);
            } catch (SQLException e) {
                logger.error("Failed to create preparedStatement", e);
            }
        } else {
            logger.info("We already have a preparedStatement that we can use");
        }

        return preparedStatement;
    }

    public ArrayList<Person_ScheduleResponse> execute() {

    long startTimeExecute = System.currentTimeMillis();

        String query = USECASE_QUERY;

        // A list to store the results
        ArrayList<Person_ScheduleResponse> responseList = new ArrayList<>();

        try {
            con = getConnection();
            preparedStatement = getPreparedStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(request.getTL_KURS_K_PNIMI()));
            preparedStatement.setString(2, request.getPortal_Calendary_View_FROM_T_DATE());
            preparedStatement.setString(3, request.getPortal_Calendary_View_TO_T_DATE());
            preparedStatement.setInt(4, Integer.parseInt(request.getLimit()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Statement stmt = con.createStatement();
        ResultSet resultSet = null;
        logger.info("query = " + query);

        try {
            long startTimeExecuteQuery = System.currentTimeMillis();
            resultSet = preparedStatement.executeQuery();
            long endTimeExecuteQuery = System.currentTimeMillis();
            logger.info("executeQuery() took " + String.valueOf(endTimeExecuteQuery - startTimeExecuteQuery));

            if (resultSet == null) {
                logger.info("##### preparedStatement.executeQuery() return null #####");
                return responseList;
            }

            // use for debugging
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            for (int k = 1; k <= columnsNumber; k++) {
                if (k > 1) logger.debug(",  ");
                logger.debug(rsmd.getColumnName(k));
                logger.debug("ClassName" + rsmd.getColumnClassName(k));
            }
            // end debugging

            logger.info("##### Got the resultSet ##### " + resultSet);

            while (resultSet.next()) {
                Person_ScheduleResponse response = new Person_ScheduleResponse(resultSet);
                responseList.add(response);
            }

        } catch (Throwable e) {
            logger.error("##### preparedStatement.executeQuery() throw Exception ##### ", e);
        }

        long endTimeExecute = System.currentTimeMillis();
        logger.info("Execute took " + String.valueOf(endTimeExecute - startTimeExecute));

        return responseList;
    }
}
