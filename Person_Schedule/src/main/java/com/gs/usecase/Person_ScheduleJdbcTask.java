package com.gs.usecase;


import com.gs.infra.service.GeneralTask;
import org.openspaces.core.executor.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.*;


public class Person_ScheduleJdbcTask extends GeneralTask<Person_ScheduleRequest, Person_ScheduleResponse> implements Task<ArrayList<Person_ScheduleResponse>> {
    private static final Logger logger = LoggerFactory.getLogger(Person_ScheduleResponse.class);

    static private final String USECASE_QUERY ="SELECT\n" +
    "tlkr.K_PNIMI,\n" +
    "concat(glb.V_YEAR,glb.SEMESTER) SEM_KVUTZA,\n" +
    "kr.K_KURS,\n" +
    "kr.TEUR_K,\n" +
    "kr.TEUR_ENG_K,\n" +
    "kr.TEUR_KURS,\n" +
    "kr.TEUR_ENG,\n" +
    "tlkr.KVUTZA,\n" +
    "kr.OFEN_HORAA1,\n" +
    "t002.TEUR_K1,\n" +
    "t002.TEUR_ENG_K1,\n" +
    "glb.V_DATE,\n" +
    "glb.V_START,\n" +
    "glb.V_END,\n" +
    "t962.TEUR_K,\n" +
    "t962.TEUR_ENG_K,\n" +
    "pr.SHEM_PRATI,\n" +
    "pr.SHEM_EMTZAI,\n" +
    "pr.SHEM_MISHP,\n" +
    "pr.SHEM_PRATI_ENG,\n" +
    "pr.SHEM_EMTZAI_ENG,\n" +
    "pr.SHEM_MISHP_ENG,\n" +
    "t911.TEUR_K,\n" +
    "t911.TEUR_ENG_K,\n" +
    "t911.NZ_ORECH,\n" +
    "t911.NZ_ROHAV,\n" +
    "glb.room,\n" +
    "glb.room_extra_code\n" +
    "FROM STUD.KR_KURS kr\n" + // kr - 122k
    "JOIN STUD.TB_002_OFEN_HORAA t002\n" + // t002 - 20
    "on kr.OFEN_HORAA1 = t002.K_CODE\n" +
    "JOIN dbo.Portal_Calendary_View glb on kr.K_KURS = glb.course_ID\n" + // glb - 270k
    "JOIN STUD.TA_PERSON pr ON pr.IDNO = glb.lecturer\n" + // person - 560k
    "JOIN STUD.TB_962_TOAR_MORE t962 on pr.TOAR_SHEM = t962.K_CODE\n" + // t962 - 13
    "JOIN STUD.TB_911_BINYAN t911 on glb.building = t911.K_CODE\n" + // t911 - 250
    "JOIN (SELECT K_PNIMI,KVUTZA,K_SEM,K_KURS,SEM_KVUTZA FROM STUD.TL_KURS WHERE K_PNIMI = %s AND MEVUTAL = '0') tlkr\n" + // tlkr - total 12M, We bring 160K for 5 years
    "on kr.K_KURS = tlkr.K_KURS\n" +
    "AND (tlkr.K_SEM >= kr.K_ME_SEM AND tlkr.K_SEM <= kr.AD_SEM)\n" +
    "AND tlkr.KVUTZA = glb.V_group\n" +
    "AND concat(glb.V_year,glb.semester)=tlkr.SEM_KVUTZA\n" +
    "AND glb.V_date >= %s AND glb.V_date <= %s\n" +
    // "AND glb.year >= (SELECT SUBSTR(SHANA_SCL_0-10,1,4) FROM STUD.TA_PIKUAH WHERE K_CODE='05')\n" +
    "ORDER BY glb.V_date\n" +
    "LIMIT %s";


    @Override
    public Integer routing() {
        return null;
    }

    public ArrayList<Person_ScheduleResponse> execute() {

        String query = String.format(USECASE_QUERY, request.getTL_KURS_K_PNIMI(), request.getPortal_Calendary_View_FROM_T_DATE(), request.getPortal_Calendary_View_TO_T_DATE(), request.getLimit());

        // A list to store the results
        ArrayList<Person_ScheduleResponse> responseList = new ArrayList<>();

        Properties props = new Properties();
        props.put("com.gs.embeddedQP.enabled", "false");

        try (Connection con = DriverManager.getConnection(properties.getProperty("driverurl"),props)) {

             Statement stmt = con.createStatement();
            ResultSet resultSet = null;
            logger.info("query = " + query);

            try {
                resultSet = stmt.executeQuery(query);
                logger.info("##### Got the resultSet ##### " + resultSet);
            }catch(Throwable e){
                logger.error("##### Got Exception while on stmt.executeQuery(query) ##### " + e);
            }

            if (resultSet == null) {
                logger.info("resultSet is null");
                return responseList;
            }

            while (resultSet.next()) {
                Person_ScheduleResponse response = new Person_ScheduleResponse(resultSet);
                responseList.add(response);
            }

        } catch (SQLException sqlException) {
            logger.error("Cannot query database ", sqlException);
            return null;
        }
        return responseList;
    }
}
