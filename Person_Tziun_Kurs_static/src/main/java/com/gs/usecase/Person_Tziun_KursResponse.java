package com.gs.usecase;

import com.gs.infra.service.ServiceResponse;
import com.gigaspaces.document.SpaceDocument;
import java.math.BigDecimal;
import java.sql.*;

/**
 * a Response
 */
public class Person_Tziun_KursResponse implements ServiceResponse {

    java.math.BigDecimal TL_KURS_K_PNIMI;
    String TA_PERSON_IDNO;
    String TA_PERSON_SHEM_MISHP;
    String TA_PERSON_SHEM_PRATI;
    String TA_PERSON_SHEM_MISHP_ENG;
    String TA_PERSON_SHEM_PRATI_ENG;
    String TL_TOCHNIT_CHUG;
    String TL_TOCHNIT_OFEN_LIMUD;
    String TL_TOCHNIT_MASLUL;
    String TL_KURS_K_SEM;
    String TL_KURS_K_KURS;
    String TL_KURS_KVUTZA;
    String TL_KURS_SEM_KVUTZA;
    String TL_KURS_MISGERET;
    java.math.BigDecimal TL_KURS_TZIUN_SOFI;
    String TL_KURS_KOD_TZIUN;
    String TL_KURS_MOED_KOVEA;
    String TL_KURS_MATZAV_TZIUN;
    String TL_KURS_PTOR;
    String TL_KURS_LSHKLL;
    String TL_KURS_HUSHLAM;
    String TL_KURS_CHOZER;
    String TL_KURS_KOVEA;
    BigDecimal TL_KURS_SHAOT_UNI;
    BigDecimal TL_KURS_MISHKAL;
    BigDecimal TL_KURS_SHAOT_SCL;
    String KR_KURS_TEUR_K;
    String KR_KURS_TEUR_ENG_K;
    String KR_KURS_TEUR_KURS;
    String KR_KURS_TEUR_ENG;
    BigDecimal KR_KURS_SHAOT_UNI;
    BigDecimal KR_KURS_MISHKAL;
    String KR_KURS_LSHKLL;
    String KR_KURS_OFEN_HORAA1;
    String TB_002_OFEN_HORAA_TEUR_K1;
    String TB_002_OFEN_HORAA_TEUR_ENG_K1;
    String TB_071_SIMUL_TZIUN_TEUR;
    String TB_071_SIMUL_TZIUN_TEUR_ENG;
    String TB_036_MATZAV_TZIUN_TEUR;

    public Person_Tziun_KursResponse(BigDecimal k_pnimi, String idno, String shemMishp, String shemPrati, String shemMishpEng, String shenPratiEng) {

        setTL_KURS_K_PNIMI(k_pnimi);
        setTA_PERSON_IDNO(idno);
        setTA_PERSON_SHEM_MISHP(shemMishp);
        setTA_PERSON_SHEM_PRATI(shemPrati);
        setTA_PERSON_SHEM_MISHP_ENG(shemMishpEng);
        setTA_PERSON_SHEM_PRATI_ENG(shenPratiEng);
        setTL_TOCHNIT_CHUG("0861");
        setTL_TOCHNIT_OFEN_LIMUD("05");
        setTL_TOCHNIT_MASLUL("0000");
        setTL_KURS_K_SEM("20101");
        setTL_KURS_K_KURS("08216380");
        setTL_KURS_KVUTZA("01");
        setTL_KURS_MISGERET("0861997");
        setTL_KURS_TZIUN_SOFI(new BigDecimal(83));
        setTL_KURS_SEM_KVUTZA("20101");
        setTL_KURS_KOD_TZIUN("260");
        setTL_KURS_MOED_KOVEA("2");
        setTL_KURS_MATZAV_TZIUN("");
        setTL_KURS_PTOR("0");
        setTL_KURS_LSHKLL("1");
        setTL_KURS_HUSHLAM("0");
        setTL_KURS_CHOZER("0");
        setTL_KURS_KOVEA("1");
        setTL_KURS_SHAOT_UNI(new BigDecimal(2.00));
        setTL_KURS_MISHKAL(new BigDecimal(2.0));
        setTL_KURS_SHAOT_SCL(new BigDecimal(2.00));
        setKR_KURS_TEUR_K("\"בקצה מערב:\" אמנות ותרבות");
        setKR_KURS_TEUR_ENG_K("Art and Civilization in M");
        setKR_KURS_TEUR_KURS("בקצה מערב:\" אמנות ותרבות בספרד וצפון אפריקה המוסלמיות");
        setKR_KURS_TEUR_ENG("Art and Civilization in Muslim Spain and North Africa");
        setKR_KURS_SHAOT_UNI(new BigDecimal(2.00));
        setKR_KURS_MISHKAL(new BigDecimal(2.00));
        setKR_KURS_LSHKLL("1");
        setKR_KURS_OFEN_HORAA1("01");
        setTB_002_OFEN_HORAA_TEUR_K1("שעור");
        setTB_002_OFEN_HORAA_TEUR_ENG_K1("LECT");
        setTB_071_SIMUL_TZIUN_TEUR("לא נבחן");
        setTB_071_SIMUL_TZIUN_TEUR_ENG("Did Not Take Exam.");
        setTB_036_MATZAV_TZIUN_TEUR("Did Not Take Exam.");
    }

    public void setTL_KURS_K_PNIMI(BigDecimal TL_KURS_K_PNIMI) {
        this.TL_KURS_K_PNIMI = TL_KURS_K_PNIMI;
    }

    public void setTA_PERSON_IDNO(String TA_PERSON_IDNO) {
        this.TA_PERSON_IDNO = TA_PERSON_IDNO;
    }

    public void setTA_PERSON_SHEM_MISHP(String TA_PERSON_SHEM_MISHP) {
        this.TA_PERSON_SHEM_MISHP = TA_PERSON_SHEM_MISHP;
    }

    public void setTA_PERSON_SHEM_PRATI(String TA_PERSON_SHEM_PRATI) {
        this.TA_PERSON_SHEM_PRATI = TA_PERSON_SHEM_PRATI;
    }

    public void setTA_PERSON_SHEM_MISHP_ENG(String TA_PERSON_SHEM_MISHP_ENG) {
        this.TA_PERSON_SHEM_MISHP_ENG = TA_PERSON_SHEM_MISHP_ENG;
    }

    public void setTA_PERSON_SHEM_PRATI_ENG(String TA_PERSON_SHEM_PRATI_ENG) {
        this.TA_PERSON_SHEM_PRATI_ENG = TA_PERSON_SHEM_PRATI_ENG;
    }

    public void setTL_TOCHNIT_CHUG(String TL_TOCHNIT_CHUG) {
        this.TL_TOCHNIT_CHUG = TL_TOCHNIT_CHUG;
    }

    public void setTL_TOCHNIT_OFEN_LIMUD(String TL_TOCHNIT_OFEN_LIMUD) {
        this.TL_TOCHNIT_OFEN_LIMUD = TL_TOCHNIT_OFEN_LIMUD;
    }

    public void setTL_TOCHNIT_MASLUL(String TL_TOCHNIT_MASLUL) {
        this.TL_TOCHNIT_MASLUL = TL_TOCHNIT_MASLUL;
    }

    public void setTL_KURS_K_SEM(String TL_KURS_K_SEM) {
        this.TL_KURS_K_SEM = TL_KURS_K_SEM;
    }

    public void setTL_KURS_K_KURS(String TL_KURS_K_KURS) {
        this.TL_KURS_K_KURS = TL_KURS_K_KURS;
    }

    public void setTL_KURS_KVUTZA(String TL_KURS_KVUTZA) {
        this.TL_KURS_KVUTZA = TL_KURS_KVUTZA;
    }

    public void setTL_KURS_SEM_KVUTZA(String TL_KURS_SEM_KVUTZA) {
        this.TL_KURS_SEM_KVUTZA = TL_KURS_SEM_KVUTZA;
    }

    public void setTL_KURS_MISGERET(String TL_KURS_MISGERET) {
        this.TL_KURS_MISGERET = TL_KURS_MISGERET;
    }

    public void setTL_KURS_TZIUN_SOFI(BigDecimal TL_KURS_TZIUN_SOFI) {
        this.TL_KURS_TZIUN_SOFI = TL_KURS_TZIUN_SOFI;
    }

    public void setTL_KURS_KOD_TZIUN(String TL_KURS_KOD_TZIUN) {
        this.TL_KURS_KOD_TZIUN = TL_KURS_KOD_TZIUN;
    }

    public void setTL_KURS_MOED_KOVEA(String TL_KURS_MOED_KOVEA) {
        this.TL_KURS_MOED_KOVEA = TL_KURS_MOED_KOVEA;
    }

    public void setTL_KURS_MATZAV_TZIUN(String TL_KURS_MATZAV_TZIUN) {
        this.TL_KURS_MATZAV_TZIUN = TL_KURS_MATZAV_TZIUN;
    }

    public void setTL_KURS_PTOR(String TL_KURS_PTOR) {
        this.TL_KURS_PTOR = TL_KURS_PTOR;
    }

    public void setTL_KURS_LSHKLL(String TL_KURS_LSHKLL) {
        this.TL_KURS_LSHKLL = TL_KURS_LSHKLL;
    }

    public void setTL_KURS_HUSHLAM(String TL_KURS_HUSHLAM) {
        this.TL_KURS_HUSHLAM = TL_KURS_HUSHLAM;
    }

    public void setTL_KURS_CHOZER(String TL_KURS_CHOZER) {
        this.TL_KURS_CHOZER = TL_KURS_CHOZER;
    }

    public void setTL_KURS_KOVEA(String TL_KURS_KOVEA) {
        this.TL_KURS_KOVEA = TL_KURS_KOVEA;
    }

    public void setTL_KURS_SHAOT_UNI(BigDecimal TL_KURS_SHAOT_UNI) {
        this.TL_KURS_SHAOT_UNI = TL_KURS_SHAOT_UNI;
    }

    public void setTL_KURS_MISHKAL(BigDecimal TL_KURS_MISHKAL) {
        this.TL_KURS_MISHKAL = TL_KURS_MISHKAL;
    }

    public void setTL_KURS_SHAOT_SCL(BigDecimal TL_KURS_SHAOT_SCL) {
        this.TL_KURS_SHAOT_SCL = TL_KURS_SHAOT_SCL;
    }

    public void setKR_KURS_TEUR_K(String KR_KURS_TEUR_K) {
        this.KR_KURS_TEUR_K = KR_KURS_TEUR_K;
    }

    public void setKR_KURS_TEUR_ENG_K(String KR_KURS_TEUR_ENG_K) {
        this.KR_KURS_TEUR_ENG_K = KR_KURS_TEUR_ENG_K;
    }

    public void setKR_KURS_TEUR_KURS(String KR_KURS_TEUR_KURS) {
        this.KR_KURS_TEUR_KURS = KR_KURS_TEUR_KURS;
    }

    public void setKR_KURS_TEUR_ENG(String KR_KURS_TEUR_ENG) {
        this.KR_KURS_TEUR_ENG = KR_KURS_TEUR_ENG;
    }

    public void setKR_KURS_SHAOT_UNI(BigDecimal KR_KURS_SHAOT_UNI) {
        this.KR_KURS_SHAOT_UNI = KR_KURS_SHAOT_UNI;
    }

    public void setKR_KURS_MISHKAL(BigDecimal KR_KURS_MISHKAL) {
        this.KR_KURS_MISHKAL = KR_KURS_MISHKAL;
    }

    public void setKR_KURS_LSHKLL(String KR_KURS_LSHKLL) {
        this.KR_KURS_LSHKLL = KR_KURS_LSHKLL;
    }

    public void setKR_KURS_OFEN_HORAA1(String KR_KURS_OFEN_HORAA1) {
        this.KR_KURS_OFEN_HORAA1 = KR_KURS_OFEN_HORAA1;
    }

    public void setTB_002_OFEN_HORAA_TEUR_K1(String TB_002_OFEN_HORAA_TEUR_K1) {
        this.TB_002_OFEN_HORAA_TEUR_K1 = TB_002_OFEN_HORAA_TEUR_K1;
    }

    public void setTB_002_OFEN_HORAA_TEUR_ENG_K1(String TB_002_OFEN_HORAA_TEUR_ENG_K1) {
        this.TB_002_OFEN_HORAA_TEUR_ENG_K1 = TB_002_OFEN_HORAA_TEUR_ENG_K1;
    }

    public void setTB_071_SIMUL_TZIUN_TEUR(String TB_071_SIMUL_TZIUN_TEUR) {
        this.TB_071_SIMUL_TZIUN_TEUR = TB_071_SIMUL_TZIUN_TEUR;
    }

    public void setTB_071_SIMUL_TZIUN_TEUR_ENG(String TB_071_SIMUL_TZIUN_TEUR_ENG) {
        this.TB_071_SIMUL_TZIUN_TEUR_ENG = TB_071_SIMUL_TZIUN_TEUR_ENG;
    }

    public void setTB_036_MATZAV_TZIUN_TEUR(String TB_036_MATZAV_TZIUN_TEUR) {
        this.TB_036_MATZAV_TZIUN_TEUR = TB_036_MATZAV_TZIUN_TEUR;
    }
 }

