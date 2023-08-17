package com.gs.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * a Response
 */
public class Person_TuitionResponseDebit {

    private static final Logger logger = LoggerFactory.getLogger(Person_TuitionResponseDebit.class);

    public Person_TuitionResponseDebit(ResultSet rs) throws SQLException {


        // debit
        setK_PNIMI_Q3(rs.getInt("k_pnimi_q3"));
        setK_SEM_Q3(rs.getString("k_sem_q3"));
        setTEUR_Q3(rs.getString("teur_q3"));
        setTEUR_ENG_Q3(rs.getString("teur_eng_q3"));
        setSCHUM_Q3(rs.getBigDecimal("schum_q3"));
        setT_PTICHA_Q3(rs.getString("t_pticha_q3"));

    }

    // Debit
    Integer K_PNIMI_Q3;

    public String getK_SEM_Q3() {
        return K_SEM_Q3;
    }

    String K_SEM_Q3;
    String TEUR_Q3;
    String TEUR_ENG_Q3;
    String T_PTICHA_Q3;
    BigDecimal SCHUM_Q3;

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