package com.gs.usecase;

import com.gs.infra.service.ServiceRequest;

//todo change this properties and methods, so it will fit to the number of request params in the values.yaml file.
public class GetInternalIdRequest implements ServiceRequest {

    private String IDNO;

    public String getIDNO() {return this.IDNO;}

    public void setIDNO(String IDNO) {this.IDNO = IDNO;}

}
