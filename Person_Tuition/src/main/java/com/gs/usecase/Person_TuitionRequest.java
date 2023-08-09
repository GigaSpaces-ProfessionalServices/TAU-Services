package com.gs.usecase;

import com.gs.infra.service.ServiceRequest;

public class Person_TuitionRequest implements ServiceRequest {

    private String K_PNIMI;
    private String FROM_DATE;
    private String limit;

    public String getK_PNIMI() {return K_PNIMI;}

    public void setK_PNIMI(String k_PNIMI) {K_PNIMI = k_PNIMI;}

    public String getFROM_YEAR() {return FROM_DATE;}

    public void setFROM_YEAR(String FROM_YEAR) {this.FROM_DATE = FROM_YEAR;}

    public String getLimit() {return limit;}

    public void setLimit(String limit) {this.limit = limit;}
}
