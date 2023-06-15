package com.gs.usecase;


import com.gs.infra.service.GeneralTask;
import org.openspaces.core.executor.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;


public class Person_Tziun_KursJdbcTask extends GeneralTask<Person_Tziun_KursRequest, Person_Tziun_KursResponse> implements Task<ArrayList<Person_Tziun_KursResponse>> {
    private static final Logger logger = LoggerFactory.getLogger(Person_Tziun_KursResponse.class);

    static private final String USECASE_QUERY ="SELECT tlkr.K_PNIMI,pr.IDNO,pr.SHEM_MISHP,pr.SHEM_PRATI SHEM, pr.SHEM_MISHP_ENG,pr.SHEM_PRATI_ENG SHEN_ENG,\n" +
            "                tl.CHUG, tl.TOAR, tl.OFEN_LIMUD,tl.MASLUL,\n" +
            "                tlkr.K_SEM,tlkr.K_KURS,tlkr.KVUTZA,\n" +
            "                tlkr.SEM_KVUTZA,\n" +
            "                tlkr.MISGERET,\n" +
            "                tlkr.TZIUN_SOFI,tlkr.KOD_TZIUN,tlkr.MOED_KOVEA,tlkr.MATZAV_TZIUN,tlkr.PTOR,tlkr.LSHKLL,\n" +
            "                tlkr.HUSHLAM,tlkr.CHOZER,tlkr.KOVEA,tlkr.SHAOT_UNI,tlkr.MISHKAL,tlkr.SHAOT_SCL,\n" +
            "                kr.TEUR_K, kr.TEUR_ENG_K, kr.TEUR_KURS,kr.TEUR_ENG,\n" +
            "                kr.SHAOT_UNI SHAOT_KURS,kr.MISHKAL MISHKAL_KURS,kr.LSHKLL LSHKLL_KURS,\n" +
            "                kr.OFEN_HORAA1,t002.TEUR_K1 TEUR_OFEN,t002.TEUR_ENG_K1 TEUR_OFEN_ENG,\n" +
            "                t071.TEUR TEUR_TZIUN, t071.TEUR_ENG TEUR_TZIUN_ENG,\n" +
            "                t036.TEUR TEUR_MATZAV_TZIUN\n" +
            "        FROM    STUD.TA_PERSON pr,\n" +
            "                STUD.TL_TOCHNIT tl,\n" +
            "                STUD.TL_KURS tlkr,\n" +
            "                STUD.KR_KURS kr,\n" +
            "                STUD.TB_071_SIMUL_TZIUN t071,\n" +
            "                STUD.TB_036_MATZAV_TZIUN t036,\n" +
            "                STUD.TB_002_OFEN_HORAA t002\n" +
            "        WHERE tlkr.MEVUTAL='0' AND\n" +
            "        tlkr.K_KURS=kr.K_KURS AND\n" +
//            "                (tlkr.K_SEM BETWEEN kr.K_ME_SEM AND kr.AD_SEM) AND\n" +
            "        kr.OFEN_HORAA1=t002.K_CODE AND\n" +
            "        tlkr.KOD_TZIUN=t071.K_CODE AND\n" +
            "        tlkr.MATZAV_TZIUN=t036.K_CODE AND\n" +
            "        tlkr.K_PNIMI=tl.K_PNIMI AND\n" +
            "        tlkr.K_SIDURI_TOCHNIT=tl.K_SIDURI_TOCHNIT AND\n" +
            "        tlkr.K_SIDURI_TOAR =tl.K_SIDURI_TOAR AND\n" +
            "        pr.K_PNIMI=tl.K_PNIMI and\n" +
            "        pr.TALMID in ('01') AND" +
            "        pr.IDNO = '%s'\n" +
//            "        tlkr.SEM_KVUTZA like '%s'%\n";
            "        ORDER BY tlkr.K_SEM,tlkr.K_KURS";

    @Override
    public Integer routing() {
        return null;
    }

    public ArrayList<Person_Tziun_KursResponse> execute() {

        String query = String.format(USECASE_QUERY, request.getPERSON_IDNO(), request.getTL_KURS_SEM_KVUTZA());

        // A list to store the results
        ArrayList<Person_Tziun_KursResponse> responseList = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(properties.getProperty("driverurl"))) {

            logger.info("Got connection");
            Statement stmt = con.createStatement();
            logger.info("##### 1 #####");
            ResultSet resultSet = null;
            logger.info("##### 2 #####");

            try {
                 resultSet = stmt.executeQuery(query);
                logger.info("##### 3 #####");
            }catch(Exception e){
                logger.info("##### 4 Got Exception #####");
            }

            logger.info("##### 5 #####");

            while (resultSet.next()) {

                logger.info("##### 6 #####");

                // Compute each field from the result set
                Person_Tziun_KursResponse response = new Person_Tziun_KursResponse(resultSet);
                responseList.add(response);
            }

        } catch (SQLException sqlException) {
            logger.error("Cannot query database ", sqlException);
            return null;
        }
        return responseList;
    }
}
