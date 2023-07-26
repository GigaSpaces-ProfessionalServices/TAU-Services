package com.gs.usecase;

import com.gs.infra.service.ServiceRequest;

public class Person_Tziun_KursRequest implements ServiceRequest {

    private String PERSON_IDNO;
    private String TL_KURS_SEM_KVUTZA;
    private String limit;

    public String getPERSON_IDNO() { return PERSON_IDNO; }
    public void setPERSON_IDNO(String PERSON_IDNO) {
        this.PERSON_IDNO = PERSON_IDNO;
    }
    public String getTL_KURS_SEM_KVUTZA() { return TL_KURS_SEM_KVUTZA; }
    public void setTL_KURS_SEM_KVUTZA(String TL_KURS_SEM_KVUTZA) { this.TL_KURS_SEM_KVUTZA = TL_KURS_SEM_KVUTZA; }
    public String getLimit() { return limit; }
    public void setLimit(String limit) { this.limit = limit; }
}
