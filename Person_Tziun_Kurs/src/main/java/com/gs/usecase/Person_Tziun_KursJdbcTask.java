package com.gs.usecase;


import com.gs.infra.service.GeneralTask;
import org.openspaces.core.executor.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;


public class Person_Tziun_KursJdbcTask extends GeneralTask<Person_Tziun_KursRequest, Person_Tziun_KursResponse> implements Task<ArrayList<Person_Tziun_KursResponse>> {
    private static final Logger logger = LoggerFactory.getLogger(Person_Tziun_KursResponse.class);

    // Working via Ops but getting com.j_spaces.jdbc.parser.grammar.ParseException: Encountered " <IDENTIFIER> "OUTER "" at line 15, column 25.
    static private final String USECASE_QUERY ="SELECT tlkr.K_PNIMI,pr.IDNO,pr.SHEM_MISHP,pr.SHEM_PRATI, pr.SHEM_MISHP_ENG,pr.SHEM_PRATI_ENG,\n" +
            "                tl.CHUG, tl.TOAR, tl.OFEN_LIMUD,tl.MASLUL,\n" +
            "                tlkr.K_SEM,tlkr.K_KURS,tlkr.KVUTZA,\n" +
            "                tlkr.SEM_KVUTZA,\n" +
            "                tlkr.MISGERET,\n" +
            "                tlkr.TZIUN_SOFI,tlkr.KOD_TZIUN,tlkr.MOED_KOVEA,tlkr.MATZAV_TZIUN,tlkr.PTOR,tlkr.LSHKLL,\n" +
            "                tlkr.HUSHLAM,tlkr.CHOZER,tlkr.KOVEA,tlkr.SHAOT_UNI,tlkr.MISHKAL,tlkr.SHAOT_SCL,\n" +
            "                kr.TEUR_K, kr.TEUR_ENG_K, kr.TEUR_KURS,kr.TEUR_ENG,\n" +
            "                kr.SHAOT_UNI,kr.MISHKAL,kr.LSHKLL,\n" +
            "                kr.OFEN_HORAA1,t002.TEUR_K1,t002.TEUR_ENG_K1,\n" +
            "                t071.TEUR, t071.TEUR_ENG\n" +
//          "                t036.TEUR\n" +
            "        FROM    STUD.TA_PERSON pr,\n" +
            "                STUD.TL_TOCHNIT tl,\n" +
            "                STUD.TL_KURS tlkr\n" +
            "                RIGHT OUTER JOIN STUD.TB_071_SIMUL_TZIUN t071\n" +
            "                on tlkr.kod_tziun=t071.k_code,\n" +
            "                STUD.KR_KURS kr\n" +
            "                RIGHT OUTER JOIN STUD.TB_002_OFEN_HORAA t002\n" +
            "                on kr.ofen_horaa1=t002.k_code\n" +
//           "               STUD.TL_KURS tlkr1\n" +  // when its on, I get OOM
//            "              RIGHT OUTER JOIN STUD.TB_036_MATZAV_TZIUN t036\n" +
//            "              on tlkr1.matzav_tziun=t036.k_code" +
            "        WHERE tlkr.MEVUTAL='0' AND\n" +
            "        tlkr.kod_tziun=t071.k_code AND\n" +
//          "        tlkr.K_KURS=kr.K_KURS AND\n" +   // when its on, the query return nothing
            "        (tlkr.K_SEM >= kr.K_ME_SEM AND tlkr.K_SEM <= kr.AD_SEM) AND\n" +
            "        tlkr.K_PNIMI=tl.K_PNIMI AND\n" +
            "        tlkr.K_SIDURI_TOCHNIT=tl.K_SIDURI_TOCHNIT AND\n" +
            "        tlkr.K_SIDURI_TOAR =tl.K_SIDURI_TOAR AND\n" +
            "        pr.K_PNIMI=tl.K_PNIMI and\n" +
            "        pr.TALMID in ('01') AND\n" +
            "        pr.IDNO = '%s' AND\n" +
            "        tlkr.SEM_KVUTZA like '%s'\n" +
            "        ORDER BY tlkr.K_SEM,tlkr.K_KURS limit %s";


    @Override
    public Integer routing() {
        return null;
    }

    public ArrayList<Person_Tziun_KursResponse> execute() {

        String query = String.format(USECASE_QUERY, request.getPERSON_IDNO(), request.getTL_KURS_SEM_KVUTZA(), request.getLimit());

        // A list to store the results
        ArrayList<Person_Tziun_KursResponse> responseList = new ArrayList<>();

        Properties props = new Properties();
        props.put("com.gs.embeddedQP.enabled", "false");

        try (Connection con = DriverManager.getConnection(properties.getProperty("driverurl"),props)) {

            Statement stmt = con.createStatement();
            ResultSet resultSet = null;
            logger.info("query = " + query);

            try {
                 resultSet = stmt.executeQuery(query);
                logger.info("##### Got the resultSet ##### " + resultSet);
            }catch(Exception e){
                logger.info("##### Got Exception while on stmt.executeQuery(query) ##### " + e);
            }

            if (resultSet == null) {
                logger.info("resultSet is null");
                return responseList;
            }

            while (resultSet.next()) {
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
