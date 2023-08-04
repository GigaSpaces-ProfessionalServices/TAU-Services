package com.gs.usecase;

import com.gs.infra.service.ServiceRequest;

public class Person_ScheduleRequest implements ServiceRequest {

    private String TL_KURS_K_PNIMI;
    private String Portal_Calendary_View_FROM_T_DATE;
    private String Portal_Calendary_View_TO_T_DATE;
    private String limit;

    public String getTL_KURS_K_PNIMI() {
        return TL_KURS_K_PNIMI;
    }

    public void setTL_KURS_K_PNIMI(String TL_KURS_K_PNIMI) {
        this.TL_KURS_K_PNIMI = TL_KURS_K_PNIMI;
    }

    public String getPortal_Calendary_View_FROM_T_DATE() {
        return Portal_Calendary_View_FROM_T_DATE;
    }

    public void setPortal_Calendary_View_FROM_T_DATE(String portal_Calendary_View_FROM_T_DATE) {
        Portal_Calendary_View_FROM_T_DATE = portal_Calendary_View_FROM_T_DATE;
    }

    public String getPortal_Calendary_View_TO_T_DATE() {
        return Portal_Calendary_View_TO_T_DATE;
    }

    public void setPortal_Calendary_View_TO_T_DATE(String portal_Calendary_View_TO_T_DATE) {
        Portal_Calendary_View_TO_T_DATE = portal_Calendary_View_TO_T_DATE;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}
