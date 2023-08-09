package com.gs.usecase;

import com.gs.infra.service.ServiceResponse;
import com.gigaspaces.document.SpaceDocument;
import java.math.BigDecimal;
import java.sql.*;

/**
 * a Response
 */
public class Person_TuitionResponse implements ServiceResponse {

    public Person_TuitionResponse(ResultSet rs) throws SQLException {

        setK_PNIMI(rs.getInt("k_pnimi"));
        setK_SEM(rs.getString("k_sem"));
        setBALANCE(rs.getBigDecimal("balance"));
        setTOTAL_CREDITS(rs.getBigDecimal("total_credits"));
        setTOTAL_DEBITS(rs.getBigDecimal("total_debits"));
    }

    Integer K_PNIMI;
    String K_SEM;
    BigDecimal BALANCE;
    BigDecimal TOTAL_CREDITS;
    BigDecimal TOTAL_DEBITS;

    public void setK_PNIMI(Integer k_PNIMI) {K_PNIMI = k_PNIMI;}
    public void setK_SEM(String k_SEM) {K_SEM = k_SEM;}
    public void setBALANCE(BigDecimal BALANCE) {this.BALANCE = BALANCE;}
    public void setTOTAL_CREDITS(BigDecimal TOTAL_CREDITS) {this.TOTAL_CREDITS = TOTAL_CREDITS;}
    public void setTOTAL_DEBITS(BigDecimal TOTAL_DEBITS) {this.TOTAL_DEBITS = TOTAL_DEBITS;}
 }