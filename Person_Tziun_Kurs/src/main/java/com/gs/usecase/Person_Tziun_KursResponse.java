package com.gs.usecase;

import com.gs.infra.service.ServiceResponse;
import com.gigaspaces.document.SpaceDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;

/**
 * a Response
 */
public class Person_Tziun_KursResponse implements ServiceResponse {

    private static final Logger logger = LoggerFactory.getLogger(Person_Tziun_KursResponse.class);

    public Person_Tziun_KursResponse(ResultSet rs) throws SQLException {

        setTL_KURS_K_PNIMI(rs.getBigDecimal("K_PNIMI"));
        setTA_PERSON_IDNO(rs.getString("IDNO"));
        setTA_PERSON_SHEM_MISHP(rs.getString("SHEM_MISHP"));
        setTA_PERSON_SHEM_PRATI(rs.getString("SHEM_PRATI"));
        setTA_PERSON_SHEM_MISHP_ENG(rs.getString("SHEM_MISHP_ENG"));
        setTA_PERSON_SHEM_PRATI_ENG(rs.getString("SHEM_PRATI_ENG"));
        setTL_TOCHNIT_CHUG(rs.getString("CHUG"));
        setTL_TOCHNIT_OFEN_LIMUD(rs.getString("OFEN_LIMUD"));
        setTL_TOCHNIT_MASLUL(rs.getString("MASLUL"));
        setTL_KURS_K_SEM(rs.getString("K_SEM"));
        setTL_KURS_K_KURS(rs.getString("K_KURS"));
        setTL_KURS_KVUTZA(rs.getString("KVUTZA"));
        setTL_KURS_MISGERET(rs.getString("MISGERET"));
        setTL_KURS_TZIUN_SOFI(rs.getBigDecimal("TZIUN_SOFI"));
        setTL_KURS_SEM_KVUTZA(rs.getString("SEM_KVUTZA"));
        setTL_KURS_KOD_TZIUN(rs.getString("KOD_TZIUN"));
        setTL_KURS_MOED_KOVEA(rs.getString("MOED_KOVEA"));
        setTL_KURS_MATZAV_TZIUN(rs.getString("MATZAV_TZIUN"));
        setTL_KURS_PTOR(rs.getString("PTOR"));
        setTL_KURS_LSHKLL(rs.getString("tlkr_LSHKLL"));
        setTL_KURS_HUSHLAM(rs.getString("HUSHLAM"));
        setTL_KURS_CHOZER(rs.getString("CHOZER"));
        setTL_KURS_KOVEA(rs.getString("KOVEA"));
        setTL_KURS_SHAOT_UNI(rs.getBigDecimal("SHAOT_UNI"));
        setTL_KURS_MISHKAL(rs.getBigDecimal("MISHKAL"));
        setTL_KURS_SHAOT_SCL(rs.getBigDecimal("SHAOT_SCL"));
        setKR_KURS_TEUR_K(rs.getString("TEUR_K"));
        setKR_KURS_TEUR_ENG_K(rs.getString("TEUR_ENG_K"));
        setKR_KURS_TEUR_KURS(rs.getString("TEUR_KURS"));
        setKR_KURS_TEUR_ENG(rs.getString("TEUR_ENG"));
        setKR_KURS_SHAOT_UNI(rs.getBigDecimal("SHAOT_UNI"));
        setKR_KURS_MISHKAL(rs.getBigDecimal("MISHKAL"));
        setKR_KURS_LSHKLL(rs.getString("kr_LSHKLL"));
        setKR_KURS_OFEN_HORAA1(rs.getString("OFEN_HORAA1"));
        setTB_002_OFEN_HORAA_TEUR_K1(rs.getString("TEUR_K1"));
        setTB_002_OFEN_HORAA_TEUR_ENG_K1(rs.getString("TEUR_ENG_K1"));

        logger.debug("t071_TEUR" + rs.getString("t071_TEUR"));
        logger.debug("t071_TEUR_ENG" + rs.getString("t071_TEUR_ENG"));

        if (rs.getString("t071_TEUR") == null)
            setT071_TEUR(" ");
        else
            setT071_TEUR(rs.getString("t071_TEUR"));

        if (rs.getString("t071_TEUR_ENG") == null)
            setT071_TEUR_ENG(" ");
        else
            setT071_TEUR_ENG(rs.getString("t071_TEUR_ENG"));
    }

    /*
    This constructor is not being use, as we are using Person_Tziun_KursJdbcTask and not Person_Tziun_KursApiTask
     */
    public Person_Tziun_KursResponse(SpaceDocument spaceDocument){

        setTL_KURS_K_PNIMI(spaceDocument.getProperty("K_PNIMI"));
        setTA_PERSON_IDNO(spaceDocument.getProperty("IDNO"));
        setTA_PERSON_SHEM_MISHP(spaceDocument.getProperty("SHEM_MISHP"));
        setTA_PERSON_SHEM_PRATI(spaceDocument.getProperty("SHEM_PRATI"));
        setTA_PERSON_SHEM_MISHP_ENG(spaceDocument.getProperty("SHEM_MISHP_ENG"));
        setTA_PERSON_SHEM_PRATI_ENG(spaceDocument.getProperty("SHEM_PRATI_ENG"));
        setTL_TOCHNIT_CHUG(spaceDocument.getProperty("CHUG"));
        setTL_TOCHNIT_OFEN_LIMUD(spaceDocument.getProperty("OFEN_LIMUD"));
        setTL_TOCHNIT_MASLUL(spaceDocument.getProperty("MASLUL"));
        setTL_KURS_K_SEM(spaceDocument.getProperty("K_SEM"));
        setTL_KURS_K_KURS(spaceDocument.getProperty("K_KURS"));
        setTL_KURS_KVUTZA(spaceDocument.getProperty("KVUTZA"));
        setTL_KURS_MISGERET(spaceDocument.getProperty("MISGERET"));
        setTL_KURS_TZIUN_SOFI(spaceDocument.getProperty("TZIUN_SOFI"));
        setTL_KURS_SEM_KVUTZA(spaceDocument.getProperty("SEM_KVUTZA"));
        setTL_KURS_KOD_TZIUN(spaceDocument.getProperty("KOD_TZIUN"));
        setTL_KURS_MOED_KOVEA(spaceDocument.getProperty("MOED_KOVEA"));
        setTL_KURS_MATZAV_TZIUN(spaceDocument.getProperty("MATZAV_TZIUN"));
        setTL_KURS_PTOR(spaceDocument.getProperty("PTOR"));
        setTL_KURS_LSHKLL(spaceDocument.getProperty("tlkr_LSHKLL"));
        setTL_KURS_HUSHLAM(spaceDocument.getProperty("HUSHLAM"));
        setTL_KURS_CHOZER(spaceDocument.getProperty("CHOZER"));
        setTL_KURS_KOVEA(spaceDocument.getProperty("KOVEA"));
        setTL_KURS_SHAOT_UNI(spaceDocument.getProperty("SHAOT_UNI"));
        setTL_KURS_MISHKAL(spaceDocument.getProperty("MISHKAL"));
        setTL_KURS_SHAOT_SCL(spaceDocument.getProperty("SHAOT_SCL"));
        setKR_KURS_TEUR_K(spaceDocument.getProperty("TEUR_K"));
        setKR_KURS_TEUR_ENG_K(spaceDocument.getProperty("TEUR_ENG_K"));
        setKR_KURS_TEUR_KURS(spaceDocument.getProperty("TEUR_KURS"));
        setKR_KURS_TEUR_ENG(spaceDocument.getProperty("TEUR_ENG"));
        setKR_KURS_SHAOT_UNI(spaceDocument.getProperty("SHAOT_UNI"));
        setKR_KURS_MISHKAL(spaceDocument.getProperty("MISHKAL"));
        setKR_KURS_LSHKLL(spaceDocument.getProperty("kr_LSHKLL"));
        setKR_KURS_OFEN_HORAA1(spaceDocument.getProperty("OFEN_HORAA1"));
        setTB_002_OFEN_HORAA_TEUR_K1(spaceDocument.getProperty("TEUR_K1"));
        setTB_002_OFEN_HORAA_TEUR_ENG_K1(spaceDocument.getProperty("TEUR_ENG_K1"));
        setT071_TEUR(spaceDocument.getProperty("t071_TEUR"));
        setT071_TEUR_ENG(spaceDocument.getProperty("t071_TEUR_ENG"));
    }


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
    String T071_TEUR;
    String T071_TEUR_ENG;

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

    public void setT071_TEUR(String T071_TEUR) {
        this.T071_TEUR = T071_TEUR;
    }

    public void setT071_TEUR_ENG(String T071_TEUR_ENG) {
        this.T071_TEUR_ENG = T071_TEUR_ENG;
    }
 }

