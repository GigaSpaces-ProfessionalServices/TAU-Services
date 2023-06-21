package com.gs.usecase;

import com.gs.infra.service.ServiceRequest;

public class KR_CHEDERRequest implements ServiceRequest {

    private String K_BINYAN;
    private String K_MIS_CHEDER;

    public String getK_BINYAN() {
        return K_BINYAN;
    }

    public void setK_BINYAN(String k_BINYAN) {
        K_BINYAN = k_BINYAN;
    }

    public String getK_MIS_CHEDER() {
        return K_MIS_CHEDER;
    }

    public void setK_MIS_CHEDER(String k_MIS_CHEDER) {
        K_MIS_CHEDER = k_MIS_CHEDER;
    }

}
