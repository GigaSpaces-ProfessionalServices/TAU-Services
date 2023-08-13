package com.gs.usecase;


import com.gs.infra.service.GeneralTask;
import org.openspaces.core.executor.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.*;


public class Person_TuitionJdbcTask extends GeneralTask<Person_TuitionRequest, Person_TuitionResponse> implements Task<ArrayList<Person_TuitionResponse>> {
    private static final Logger logger = LoggerFactory.getLogger(Person_TuitionJdbcTask.class);

    // STUD.SL_HESHBON sl - 2M rows
    // STUD.SL_TNUA st - 16.2M rows
    // STUD.TB_069_SCL_PEULA t069 - 197 rows
    // crd - see inside
    static private final String USECASE_QUERY_MAIN = "SELECT\n" +
            "sl.k_pnimi k_pnimi_q1,\n" +
            "sl.k_sem k_sem_q1,\n" +
            "sl.yitra balance_q1,\n" +
            "crd.total_credits total_credits_q1,\n" +
            "dbt.total_debits total_debits_q1\n" +
            "FROM\n" +
            "STUD.SL_HESHBON sl\n" +
            "LEFT JOIN\n" +
            // start crd definition
            "(SELECT st.k_pnimi, k_sem, sum(st.schum) total_credits\n" +
            "FROM STUD.SL_TNUA st\n" +
            "JOIN STUD.TB_069_SCL_PEULA t069\n" +
            "ON st.kod_tnua = t069.k_code AND st.k_pnimi = %s\n" + // 1 - idno
            "WHERE\n" +
            "((sug='03' AND tat_sug IN ('01','02','04','99','09')) OR\n" +
            "(kod_tnua IN ('0200','0210') AND schum < 0) OR\n" +
            "sug='01' OR\n" +
            "(sug='02' AND tat_sug='03'))\n" +
            "GROUP BY st.k_pnimi, k_sem) crd\n" +
            // end crd definition
            "ON crd.k_pnimi = sl.k_pnimi AND\n" +
            "sl.k_sem = crd.k_sem AND\n" +
            "crd.k_pnimi = %s\n" + // 2 - idno
            "AND sl.k_sem >= '%s'\n" + // 3 - from_year
            "LEFT JOIN (SELECT st.k_pnimi, k_sem, sum(st.schum) total_debits\n" +
            "FROM STUD.SL_TNUA st\n" +
            "JOIN STUD.TB_069_SCL_PEULA t069\n" +
            "ON st.kod_tnua = t069.k_code AND\n" +
            "st.k_pnimi = %s\n" + // 4 - idno
            "WHERE ((sug='02' AND tat_sug IN ('01','02','04','05','06','07','08','10','13')) OR\n" +
            "kod_tnua IN ('0140') OR\n" +
            "(sug='05' AND tat_sug='01'))\n" +
            "GROUP BY k_sem, st.k_pnimi) dbt\n" +
            "ON dbt.k_pnimi = sl.k_pnimi and sl.k_sem = dbt.k_sem\n" +
            "WHERE sl.k_pnimi = %s AND\n" + // 5 - idno
            "sl.k_sem >= '%s'\n" + // 6 - from_year
            "ORDER BY sl.k_sem\n" +
            "LIMIT %s"; // 7 - limit

    static private final String USECASE_QUERY_CREDIT = "SELECT\n" +
            "sl.k_pnimi k_pnimi_q2,\n" +
            "sl.k_sem k_sem_q2,\n" +
            "t069.teur teur_q2,\n" +
            "t069.teur_eng teur_eng_q2,\n" +
            "sl.schum schum_q2,\n" +
            "sl.t_pticha t_pticha_q2\n" +
            "FROM\n" +
            "STUD.SL_TNUA sl\n" +
            "JOIN STUD.TB_069_SCL_PEULA t069\n" +
            "ON sl.kod_tnua = t069.k_code\n" +
            "WHERE\n" +
            "((sug='03' AND tat_sug IN ('01','02','04','99','09')) OR\n" +
            "(kod_tnua IN ('0200','0210') AND schum<0 ) OR\n" +
            "sug='01' OR (sug='02' AND tat_sug='03')) AND\n" +
            "sl.k_pnimi = %s AND\n" + // 1 - idno
            "sl.k_sem <= '%s'\n" + //  2 - from_year
            "ORDER BY sl.t_pticha\n" +
            "LIMIT %s"; // 3 - limit

    static private final String USECASE_QUERY_DEBIT = "SELECT\n" +
        "sl.k_pnimi k_pnimi_q3,\n" +
        "sl.k_sem k_sem_q3,\n" +
        "t069.teur teur_q3,\n" +
        "t069.teur_eng teur_eng_q3,\n" +
        "sl.schum schum_q3,\n" +
        "sl.t_pticha t_pticha_q3\n" +
        "FROM\n" +
        "STUD.SL_TNUA sl\n" +
        "JOIN STUD.TB_069_SCL_PEULA t069\n" +
        "ON sl.kod_tnua = t069.k_code\n" +
        "WHERE\n" +
        "((sug='02' AND tat_sug IN ('01','02','04','05','06','07','08','10','13')) OR\n" +
        "kod_tnua IN ('0140') OR\n" +
        "sug='05' AND tat_sug='01') AND\n" +
        "sl.k_pnimi = %s AND\n" + // 1 - idno
        "sl.k_sem <= '%s'\n" + // 2 - from_year
        "ORDER BY sl.t_pticha\n" +
        "LIMIT %s"; // 3 - limit


    @Override
    public Integer routing() {
        return null;
    }

    public ArrayList<Person_TuitionResponse> execute() {

        String query_main = String.format(USECASE_QUERY_MAIN, request.getK_PNIMI(), request.getK_PNIMI(),
                request.getFROM_YEAR(), request.getK_PNIMI(),
                request.getK_PNIMI(), request.getFROM_YEAR(), request.getLimit());

        String query_credit = String.format(USECASE_QUERY_CREDIT, request.getK_PNIMI(), request.getFROM_YEAR(),request.getLimit());

        String query_debit = String.format(USECASE_QUERY_DEBIT, request.getK_PNIMI(), request.getFROM_YEAR(),request.getLimit());

        // A list to store the results
        ArrayList<Person_TuitionResponse> responseList = new ArrayList<>();

        Properties props = new Properties();
        props.put("com.gs.embeddedQP.enabled", "false");

        try (Connection con = DriverManager.getConnection(properties.getProperty("driverurl"), props)) {

            String[] queryArray = {query_main, query_credit, query_debit};
            String[] queryTypeArray = {"main", "credit", "debit"};

            ResultSet resultSet = null;
            int idex = 0;
            Statement stmt = con.createStatement();

            for (String query : queryArray) {

                logger.info("query = " + query);

                try {
                    resultSet = stmt.executeQuery(query);

                    ResultSetMetaData rsmd = resultSet.getMetaData();
                    int columnsNumber = rsmd.getColumnCount();

                    for (int k = 1; k <= columnsNumber; k++) {
                        if (k > 1) logger.info(",  ");
                        logger.info(rsmd.getColumnName(k));
                        logger.info("ClassName" + rsmd.getColumnClassName(k));
                    }

                    logger.info("##### Got the resultSet ##### " + resultSet);

                } catch (Throwable e) {
                    logger.error("##### Got Exception while on stmt.executeQuery(query) ##### " + e);
                }

                if (resultSet == null) {
                    logger.info("resultSet is null");
                    return responseList;
                }

                while (resultSet.next()) {
                    logger.info("#####  idex " + idex + " #####");
                    Person_TuitionResponse response = new Person_TuitionResponse(resultSet, queryTypeArray[idex]);
                    responseList.add(response);
                }

                idex++;
            }
        } catch(SQLException sqlException){
            logger.error("Cannot query database ", sqlException);
            return null;
        }

        return responseList;
    }
}