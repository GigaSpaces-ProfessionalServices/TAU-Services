package com.gs.usecase;


import com.gs.infra.service.GeneralTask;
import org.openspaces.core.executor.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.*;


public class Person_Tziun_KursJdbcTask extends GeneralTask<Person_Tziun_KursRequest, Person_Tziun_KursResponse> implements Task<ArrayList<Person_Tziun_KursResponse>> {
    private static final Logger logger = LoggerFactory.getLogger(Person_Tziun_KursResponse.class);

    static private final String USECASE_QUERY_PARAMETERIZED =" SELECT tlkr.K_PNIMI,\n" +
    "pr.IDNO,\n" +
    "pr.SHEM_MISHP,\n" +
    "pr.SHEM_PRATI,\n" +
    "pr.SHEM_MISHP_ENG,\n" +
    "pr.SHEM_PRATI_ENG,\n" +
    "tl.CHUG,\n" +
    "tl.TOAR,\n" +
    "tl.OFEN_LIMUD,\n" +
    "tl.MASLUL,\n" +
    "tlkr.K_SEM,\n" +
    "tlkr.K_KURS,\n" +
    "tlkr.KVUTZA,\n" +
    "tlkr.SEM_KVUTZA,\n" +
    "tlkr.MISGERET,\n" +
    "tlkr.TZIUN_SOFI,\n" +
    "tlkr.KOD_TZIUN,\n" +
    "tlkr.MOED_KOVEA,\n" +
    "tlkr.MATZAV_TZIUN,\n" +
    "tlkr.PTOR,\n" +
    "tlkr.LSHKLL,\n" +
    "tlkr.HUSHLAM,\n" +
    "tlkr.CHOZER,\n" +
    "tlkr.KOVEA,\n" +
    "tlkr.SHAOT_UNI,\n" +
    "tlkr.MISHKAL,\n" +
    "tlkr.SHAOT_SCL,\n" +
    "kr.TEUR_K,\n" +
    "kr.TEUR_ENG_K,\n" +
    "kr.TEUR_KURS,\n" +
    "kr.TEUR_ENG,\n" +
    "kr.SHAOT_UNI AS KR_SHAOT_UNI,\n" +
    "kr.MISHKAL,\n" +
    "kr.LSHKLL,\n" +
    "kr.OFEN_HORAA1,\n" +
    "t002.TEUR_K1,\n" +
    "t002.TEUR_ENG_K1,\n" +
    "t071.TEUR,\n" +
    "t071.TEUR_ENG \n" +
    "FROM\n" +
    "STUD.TA_PERSON pr\n" + // ##### 500K #####
    "join  STUD.TL_TOCHNIT tl \n" + // ##### 1M #####
    "on tl.k_pnimi=pr.k_pnimi \n" +
    "join STUD.TL_KURS tlkr \n" + // ##### 12M #####
    "on  tlkr.K_PNIMI = tl.K_PNIMI \n" +
    "AND tlkr.K_SIDURI_TOCHNIT = tl.K_SIDURI_TOCHNIT \n" +
    "AND tlkr.K_SIDURI_TOAR = tl.K_SIDURI_TOAR \n" +
    "RIGHT OUTER JOIN STUD.TB_071_SIMUL_TZIUN t071 \n" + // ##### 23 #####
    "on tlkr.kod_tziun = t071.k_code \n" +
    "join STUD.KR_KURS kr \n" + // ##### 120K #####
    "on kr.k_kurs=tlkr.k_kurs \n" +
    "AND (tlkr.K_SEM >= kr.K_ME_SEM AND tlkr.K_SEM <= kr.AD_SEM) \n" +
    "RIGHT OUTER JOIN STUD.TB_002_OFEN_HORAA t002 \n" + // ##### 20 #####
    "on kr.ofen_horaa1 = t002.k_code \n" +
    "WHERE \n" +
    "tlkr.MEVUTAL = '0' \n" +
    "and pr.TALMID in ('01') \n" +
    "AND pr.K_PNIMI = ? \n" +
    "AND tlkr.SEM_KVUTZA like '20201' \n" +
    "ORDER BY \n" +
    "tlkr.K_SEM, \n" +
    "tlkr.K_KURS \n" +
    "limit 1";

    // this is working
    static private final String USECASE_QUERY =" SELECT tlkr.K_PNIMI,\n" +
            "pr.IDNO,\n" +
            "pr.SHEM_MISHP,\n" +
            "pr.SHEM_PRATI,\n" +
            "pr.SHEM_MISHP_ENG,\n" +
            "pr.SHEM_PRATI_ENG,\n" +
            "tl.CHUG,\n" +
            "tl.TOAR,\n" +
            "tl.OFEN_LIMUD,\n" +
            "tl.MASLUL,\n" +
            "tlkr.K_SEM,\n" +
            "tlkr.K_KURS,\n" +
            "tlkr.KVUTZA,\n" +
            "tlkr.SEM_KVUTZA,\n" +
            "tlkr.MISGERET,\n" +
            "tlkr.TZIUN_SOFI,\n" +
            "tlkr.KOD_TZIUN,\n" +
            "tlkr.MOED_KOVEA,\n" +
            "tlkr.MATZAV_TZIUN,\n" +
            "tlkr.PTOR,\n" +
            "tlkr.LSHKLL,\n" +
            "tlkr.HUSHLAM,\n" +
            "tlkr.CHOZER,\n" +
            "tlkr.KOVEA,\n" +
            "tlkr.SHAOT_UNI,\n" +
            "tlkr.MISHKAL,\n" +
            "tlkr.SHAOT_SCL,\n" +
            "kr.TEUR_K,\n" +
            "kr.TEUR_ENG_K,\n" +
            "kr.TEUR_KURS,\n" +
            "kr.TEUR_ENG,\n" +
            "kr.SHAOT_UNI AS KR_SHAOT_UNI,\n" +
            "kr.MISHKAL,\n" +
            "kr.LSHKLL,\n" +
            "kr.OFEN_HORAA1,\n" +
            "t002.TEUR_K1,\n" +
            "t002.TEUR_ENG_K1,\n" +
            "t071.TEUR t071_TEUR,\n" +
            "t071.TEUR_ENG t071_TEUR_ENG\n" +
            "FROM\n" +
            "STUD.TA_PERSON pr\n" + // ##### 500K #####
            "join  STUD.TL_TOCHNIT tl \n" + // ##### 1M #####
            "on tl.k_pnimi=pr.k_pnimi \n" +
            "AND tl.K_PNIMI = %s \n" + // Test 1
            "join STUD.TL_KURS tlkr \n" + // ##### 12M #####
            "on  tlkr.K_PNIMI = tl.K_PNIMI \n" +
            "AND tlkr.K_PNIMI = %s \n" + // Test1
            "AND tlkr.K_SIDURI_TOCHNIT = tl.K_SIDURI_TOCHNIT \n" +
            "AND tlkr.K_SIDURI_TOAR = tl.K_SIDURI_TOAR \n" +
            "LEFT OUTER JOIN STUD.TB_071_SIMUL_TZIUN t071 \n" + // ##### 23 #####
            "on tlkr.kod_tziun = t071.k_code \n" +
            "join STUD.KR_KURS kr \n" + // ##### 120K #####
            "on kr.k_kurs=tlkr.k_kurs \n" +
            "AND (tlkr.K_SEM >= kr.K_ME_SEM AND tlkr.K_SEM <= kr.AD_SEM) \n" +
            "LEFT OUTER JOIN STUD.TB_002_OFEN_HORAA t002 \n" + // ##### 20 #####
            "on kr.ofen_horaa1 = t002.k_code \n" +
            "WHERE \n" +
            "tlkr.MEVUTAL = '0' \n" +
            "AND tlkr.K_PNIMI = %s \n" +
            "and pr.TALMID in ('01') \n" +
            "AND pr.K_PNIMI = %s \n" +
            "AND tlkr.SEM_KVUTZA like '%s' \n" +
            "ORDER BY \n" +
            "tlkr.K_SEM, \n" +
            "tlkr.K_KURS \n" +
            "limit %s";

    @Override
    public Integer routing() {
        return null;
    }

    public ArrayList<Person_Tziun_KursResponse> execute() {

        String query = String.format(USECASE_QUERY, request.getPERSON_IDNO(), request.getPERSON_IDNO(), request.getPERSON_IDNO(), request.getPERSON_IDNO(), request.getTL_KURS_SEM_KVUTZA(), request.getLimit());

        // A list to store the results
        ArrayList<Person_Tziun_KursResponse> responseList = new ArrayList<>();

        Properties props = new Properties();
        props.put("com.gs.embeddedQP.enabled", "false");

        try (Connection con = DriverManager.getConnection(properties.getProperty("driverurl"),props)) {

//            PreparedStatement preparedStatement = con.prepareStatement(query);
//            preparedStatement.setInt(1, Integer.parseInt(request.getPERSON_IDNO()));
//            preparedStatement.setString(2,request.getTL_KURS_SEM_KVUTZA());
//            preparedStatement.setInt(3,Integer.parseInt(request.getLimit()));

             Statement stmt = con.createStatement();
            ResultSet resultSet = null;
            logger.info("query = " + query);

            try {
                resultSet = stmt.executeQuery(query);

                 //resultSet = preparedStatement.executeQuery();
                logger.info("##### Got the resultSet ##### " + resultSet);
            }catch(Throwable e){
                logger.error("##### Got Exception while on stmt.executeQuery(query) ##### " + e);
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
