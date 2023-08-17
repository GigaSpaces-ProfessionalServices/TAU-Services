package com.gs.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * a Response
 */
public class Person_TuitionResponseCredit {

    private static final Logger logger = LoggerFactory.getLogger(Person_TuitionResponseCredit.class);

    public Person_TuitionResponseCredit(ResultSet rs) throws SQLException {

        // credit
        setK_PNIMI_Q2(rs.getInt("k_pnimi_q2"));
        setK_SEM_Q2(rs.getString("k_sem_q2"));
        setTEUR_Q2(rs.getString("teur_q2"));
        setTEUR_ENG_Q2(rs.getString("teur_eng_q2"));
        setSCHUM_Q2(rs.getBigDecimal("schum_q2"));
        setT_PTICHA_Q2(rs.getString("t_pticha_q2"));

    }

    // Credit
    Integer K_PNIMI_Q2;

    public String getK_SEM_Q2() {
        return K_SEM_Q2;
    }

    String K_SEM_Q2;
    String TEUR_Q2;
    String TEUR_ENG_Q2;
    String T_PTICHA_Q2;
    BigDecimal SCHUM_Q2;

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

 }