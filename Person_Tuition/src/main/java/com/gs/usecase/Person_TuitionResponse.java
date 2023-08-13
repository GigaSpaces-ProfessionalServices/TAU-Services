package com.gs.usecase;

import com.gs.infra.service.ServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;

/**
 * a Response
 */
public class Person_TuitionResponse implements ServiceResponse {

    private static final Logger logger = LoggerFactory.getLogger(Person_TuitionResponse.class);

    public Person_TuitionResponse(ResultSet rs, String queryType) throws SQLException {

        if (queryType.equals("main")) {
            // Main
            setK_PNIMI_Q1(rs.getInt("k_pnimi_q1"));
            setK_SEM_Q1(rs.getString("k_sem_q1"));
            setBALANCE_Q1(rs.getBigDecimal("balance_q1"));
            setTOTAL_CREDITS_Q1(rs.getBigDecimal("total_credits_q1"));
            setTOTAL_DEBITS_Q1(rs.getBigDecimal("total_debits_q1"));
        } else if (queryType.equals("credit")) {
            // credit
            setK_PNIMI_Q2(rs.getInt("k_pnimi_q2"));
            setK_SEM_Q2(rs.getString("k_sem_q2"));
            setTEUR_Q2(rs.getString("teur_q2"));
            setTEUR_ENG_Q2(rs.getString("teur_eng_q2"));
            setSCHUM_Q2(rs.getBigDecimal("schum_q2"));
            setT_PTICHA_Q2(rs.getString("t_pticha_q2"));
        } else if (queryType.equals("debit")) {
            // credit
            setK_PNIMI_Q3(rs.getInt("k_pnimi_q3"));
            setK_SEM_Q3(rs.getString("k_sem_q3"));
            setTEUR_Q3(rs.getString("teur_q3"));
            setTEUR_ENG_Q3(rs.getString("teur_eng_q3"));
            setSCHUM_Q3(rs.getBigDecimal("schum_q3"));
            setT_PTICHA_Q3(rs.getString("t_pticha_q3"));
        }else {
            logger.error("Wrong query type " + queryType);
        }
    }

    // Main
    Integer K_PNIMI_Q1;
    String K_SEM_Q1;
    BigDecimal BALANCE_Q1;
    BigDecimal TOTAL_CREDITS_Q1;
    BigDecimal TOTAL_DEBITS_Q1;

    // Credit
    Integer K_PNIMI_Q2;
    String K_SEM_Q2;
    String TEUR_Q2;
    String TEUR_ENG_Q2;
    String T_PTICHA_Q2;
    BigDecimal SCHUM_Q2;


    // Debit
    Integer K_PNIMI_Q3;
    String K_SEM_Q3;
    String TEUR_Q3;
    String TEUR_ENG_Q3;
    String T_PTICHA_Q3;
    BigDecimal SCHUM_Q3;

    public void setK_PNIMI_Q1(Integer k_PNIMI_Q1) {
        K_PNIMI_Q1 = k_PNIMI_Q1;
    }

    public void setK_SEM_Q1(String k_SEM_Q1) {
        K_SEM_Q1 = k_SEM_Q1;
    }

    public void setBALANCE_Q1(BigDecimal BALANCE_Q1) {
        this.BALANCE_Q1 = BALANCE_Q1.setScale(2, BigDecimal.ROUND_HALF_UP);
}

    public void setTOTAL_CREDITS_Q1(BigDecimal TOTAL_CREDITS_Q1) {
        this.TOTAL_CREDITS_Q1 = TOTAL_CREDITS_Q1.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void setTOTAL_DEBITS_Q1(BigDecimal TOTAL_DEBITS_Q1) {
        this.TOTAL_DEBITS_Q1 = TOTAL_DEBITS_Q1.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void setK_PNIMI_Q2(Integer k_PNIMI_Q2) {
        K_PNIMI_Q2 = k_PNIMI_Q2;
    }

    public void setK_SEM_Q2(String k_SEM_Q2) {
        K_SEM_Q2 = k_SEM_Q2;
    }

    public void setTEUR_Q2(String TEUR_Q2) {
        this.TEUR_Q2 = TEUR_Q2;
    }

    public void setTEUR_ENG_Q2(String TEUR_ENG_Q2) {
        this.TEUR_ENG_Q2 = TEUR_ENG_Q2;
    }

    public void setT_PTICHA_Q2(String t_PTICHA_Q2) {
        T_PTICHA_Q2 = t_PTICHA_Q2;
    }

    public void setSCHUM_Q2(BigDecimal SCHUM_Q2) {
        this.SCHUM_Q2 = SCHUM_Q2.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void setK_PNIMI_Q3(Integer k_PNIMI_Q3) {
        K_PNIMI_Q3 = k_PNIMI_Q3;
    }

    public void setK_SEM_Q3(String k_SEM_Q3) {
        K_SEM_Q3 = k_SEM_Q3;
    }

    public void setTEUR_Q3(String TEUR_Q3) {
        this.TEUR_Q3 = TEUR_Q3;
    }

    public void setTEUR_ENG_Q3(String  TEUR_ENG_Q3) {
        this.TEUR_ENG_Q3 = TEUR_ENG_Q3;
    }

    public void setT_PTICHA_Q3(String t_PTICHA_Q3) {
        T_PTICHA_Q3 = t_PTICHA_Q3;
    }

    public void setSCHUM_Q3(BigDecimal SCHUM_Q3) {
        this.SCHUM_Q3 = SCHUM_Q3.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
 }