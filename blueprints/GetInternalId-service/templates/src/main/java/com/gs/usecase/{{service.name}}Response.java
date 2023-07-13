package com.gs.usecase;

import com.gs.infra.service.ServiceResponse;
import java.sql.*;

//todo change this properties and methods, so it will fit to the number of query params in the values.yaml file.
public class {{service.name}}Response implements ServiceResponse {

    String {{query.param1}};

    public {{service.name}}Response(ResultSet rs) throws SQLException{

        set{{query.param1}}(rs.getString("{{query.param1}}"));
    }

    public void set{{query.param1}}(String {{query.param1}}) {
        this.{{query.param1}} = {{query.param1}};
    }
}
