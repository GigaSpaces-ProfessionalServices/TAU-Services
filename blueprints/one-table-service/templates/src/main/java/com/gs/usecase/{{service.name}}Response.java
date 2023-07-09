package com.gs.usecase;

import com.gs.infra.service.ServiceResponse;
import java.sql.*;

//todo change this properties and methods, so it will fit to the number of query params in the values.yaml file.
public class {{service.name}}Response implements ServiceResponse {

    String {{query.param1}};
    String {{query.param2}};
    String {{query.param3}};
    String {{query.param4}};

    public {{service.name}}Response(ResultSet rs) throws SQLException{

        set{{query.param1}}(rs.getString("{{query.param1}}"));
        set{{query.param2}}(rs.getString("{{query.param2}}"));
        set{{query.param3}}(rs.getString("{{query.param3}}"));
        set{{query.param4}}(rs.getString("{{query.param4}}"));
    }

    public void set{{query.param1}}(String {{query.param1}}) {
        this.{{query.param1}} = {{query.param1}};
    }

    public void set{{query.param2}}(String {{query.param2}}) {
        this.{{query.param2}} = {{query.param2}};
        }

    public void set{{query.param3}}(String {{query.param3}}) {
        this.{{query.param3}} = {{query.param3}};
        }

    public void set{{query.param4}}(String {{query.param4}}) {
        this.{{query.param4}} = {{query.param4}};
    }
}
