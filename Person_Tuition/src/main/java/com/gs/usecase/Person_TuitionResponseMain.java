package com.gs.usecase;

import com.gs.infra.service.ServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * a Response
 */
public class Person_TuitionResponseMain implements ServiceResponse {

    private static final Logger logger = LoggerFactory.getLogger(Person_TuitionResponseMain.class);


    public Person_TuitionResponseMain() {
    }

    public Person_TuitionResponseMain(ResultSet rs) throws SQLException {

        // Main
        setK_PNIMI_Q1(rs.getInt("k_pnimi_q1"));
        setK_SEM_Q1(rs.getString("k_sem_q1"));
        setBALANCE_Q1(rs.getBigDecimal("balance_q1"));
        setTOTAL_CREDITS_Q1(rs.getBigDecimal("total_credits_q1"));
        setTOTAL_DEBITS_Q1(rs.getBigDecimal("total_debits_q1"));
    }

    // Main
    Integer K_PNIMI_Q1;
    String K_SEM_Q1;
    BigDecimal BALANCE_Q1;
    BigDecimal TOTAL_CREDITS_Q1;
    BigDecimal TOTAL_DEBITS_Q1;
    ArrayList<Person_TuitionResponseCredit> responseListCredit;
    ArrayList<Person_TuitionResponseDebit> responseListDebit;

    public String getK_SEM_Q1() {
        return K_SEM_Q1;
    }

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

    public void setResponseListCredit(ArrayList<Person_TuitionResponseCredit> responseListCredit) {
        this.responseListCredit = responseListCredit;
    }

    public void setResponseListDebit(ArrayList<Person_TuitionResponseDebit> responseListDebit) {
        this.responseListDebit = responseListDebit;
    }
 }