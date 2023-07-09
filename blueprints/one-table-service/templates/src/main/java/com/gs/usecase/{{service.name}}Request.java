package com.gs.usecase;

import com.gs.infra.service.ServiceRequest;

//todo change this properties and methods, so it will fit to the number of request params in the values.yaml file.
public class {{service.name}}Request implements ServiceRequest {

    private String {{request.param1}};
    private String {{request.param2}};

    public String get{{request.param1}}() {return this.{{request.param1}};}

    public void set{{request.param1}}(String {{request.param1}}) {this.{{request.param1}} = {{request.param1}};}

    public String get{{request.param2}}() {return this.{{request.param2}};}

    public void set{{request.param2}}(String {{request.param2}}) {this.{{request.param2}} = {{request.param2}};}
}
