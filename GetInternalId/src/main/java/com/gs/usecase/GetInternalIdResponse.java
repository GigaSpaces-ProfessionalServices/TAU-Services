package com.gs.usecase;

import com.gs.infra.service.ServiceResponse;

import java.math.*;
import java.sql.*;

//todo change this properties and methods, so it will fit to the number of query params in the values.yaml file.
public class GetInternalIdResponse implements ServiceResponse {

    String K_PNIMI;

    public GetInternalIdResponse(ResultSet rs) throws SQLException{
        BigDecimal bigDecimal = new BigDecimal(rs.getString("K_PNIMI"));

        setK_PNIMI(bigDecimal.toPlainString());
    }

    public void setK_PNIMI(String K_PNIMI) {
        this.K_PNIMI = K_PNIMI;
    }
}
