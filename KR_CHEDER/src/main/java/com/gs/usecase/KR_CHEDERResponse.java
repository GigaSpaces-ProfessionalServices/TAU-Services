package com.gs.usecase;

import com.gs.infra.service.ServiceResponse;
import com.gigaspaces.document.SpaceDocument;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * a Response
 */
public class KR_CHEDERResponse implements ServiceResponse {

    public KR_CHEDERResponse(SpaceDocument spaceDocument){

        setK_BINYAN(spaceDocument.getProperty("K_BINYAN"));
        setK_MIS_CHEDER(spaceDocument.getProperty("K_MIS_CHEDER"));
        setK_ZIHUY_NOSAF(spaceDocument.getProperty("K_ZIHUY_NOSAF"));
        setAZARIM(spaceDocument.getProperty("AZARIM"));
        setKIBOLET(spaceDocument.getProperty("KIBOLET"));
        setKIBOLET_BCHINA(spaceDocument.getProperty("KIBOLET_BCHINA"));
        setPAIL(spaceDocument.getProperty("PAIL"));
        setSHETACH(spaceDocument.getProperty("SHETACH"));
        setSHIBUTZ(spaceDocument.getProperty("SHIBUTZ"));
        setSUG_CHEDER(spaceDocument.getProperty("SUG_CHEDER"));
        setTEUR(spaceDocument.getProperty("TEUR"));
        setTEUR_ENG(spaceDocument.getProperty("TEUR_ENG"));
        setT_IDKUN(spaceDocument.getProperty("T_IDKUN"));
        setT_PTICHA(spaceDocument.getProperty("T_PTICHA"));
        setUSER_IDK(spaceDocument.getProperty("USER_IDK"));
        setYEHIDA_IRGUNIT(spaceDocument.getProperty("YEHIDA_IRGUNIT"));
     }

    String K_BINYAN;
    String K_MIS_CHEDER;
    String K_ZIHUY_NOSAF;
    String AZARIM;
    Short KIBOLET;
    Short KIBOLET_BCHINA;
    String PAIL;
    BigDecimal SHETACH;
    String SHIBUTZ;
    String SUG_CHEDER;
    String TEUR;
    String TEUR_ENG;
    Timestamp T_IDKUN;
    Timestamp T_PTICHA;
    String USER_IDK;
    String YEHIDA_IRGUNIT;

    public void setK_BINYAN(String k_BINYAN) {K_BINYAN = k_BINYAN;}
    public void setK_MIS_CHEDER(String k_MIS_CHEDER) {K_MIS_CHEDER = k_MIS_CHEDER;}
    public void setK_ZIHUY_NOSAF(String k_ZIHUY_NOSAF) {K_ZIHUY_NOSAF = k_ZIHUY_NOSAF;}
    public void setAZARIM(String AZARIM) {this.AZARIM = AZARIM;}
    public void setKIBOLET(Short KIBOLET) {this.KIBOLET = KIBOLET;}
    public void setKIBOLET_BCHINA(Short KIBOLET_BCHINA) {this.KIBOLET_BCHINA = KIBOLET_BCHINA;}
    public void setPAIL(String PAIL) {this.PAIL = PAIL;}
    public void setSHETACH(BigDecimal SHETACH) {this.SHETACH = SHETACH;}
    public void setSHIBUTZ(String SHIBUTZ) {this.SHIBUTZ = SHIBUTZ;}
    public void setSUG_CHEDER(String SUG_CHEDER) {this.SUG_CHEDER = SUG_CHEDER;}
    public void setTEUR(String TEUR) {this.TEUR = TEUR;}
    public void setTEUR_ENG(String TEUR_ENG) {this.TEUR_ENG = TEUR_ENG;}
    public void setT_IDKUN(Timestamp t_IDKUN) {T_IDKUN = t_IDKUN;}
    public void setT_PTICHA(Timestamp t_PTICHA) {T_PTICHA = t_PTICHA;}
    public void setUSER_IDK(String USER_IDK) {this.USER_IDK = USER_IDK;}
    public void setYEHIDA_IRGUNIT(String YEHIDA_IRGUNIT) {this.YEHIDA_IRGUNIT = YEHIDA_IRGUNIT;}

 }

