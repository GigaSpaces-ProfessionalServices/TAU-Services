package com.gs.usecase;

import com.gs.infra.service.ServiceResponse;
import com.gigaspaces.document.SpaceDocument;
import java.math.BigDecimal;
import java.sql.*;

/**
 * a Response
 */
public class Person_ScheduleResponse implements ServiceResponse {

    public Person_ScheduleResponse(ResultSet rs) throws SQLException {

        setKR_KURS_K_PNIMI(rs.getInt("K_PNIMI"));
        setPortal_Calendary_View_SEM_KVUTZA(rs.getString("SEM_KVUTZA"));
        setKR_KURS_K_KURS(rs.getString("K_KURS"));
        setKR_KURS_TEUR_K(rs.getString("TEUR_K"));
        setKR_KURS_TEUR_ENG_K(rs.getString("TEUR_ENG_K"));
        setKR_KURS_TEUR_KURS(rs.getString("TEUR_KURS"));
        setKR_KURS_TEUR_ENG(rs.getString("TEUR_ENG"));
        setTL_KURS_KVUTZA(rs.getString("KVUTZA"));
        setKR_KURS_OFEN_HORAA1(rs.getString("OFEN_HORAA1"));
        setTB_002_OFEN_HORAA_TEUR_K1(rs.getString("TEUR_K1"));
        setTB_002_OFEN_HORAA_TEUR_ENG_K1(rs.getString("TEUR_ENG_K1"));
        setPortal_Calendary_View_SEM_KVUTZA_V_DATE(rs.getString("V_DATE"));
        setPortal_Calendary_View_SEM_KVUTZA_V_START(rs.getString("V_START"));
        setPortal_Calendary_View_SEM_KVUTZA_V_END(rs.getString("V_END"));
        setTB_962_TOAR_MORE_TEUR_K(rs.getString("TEUR_K"));
        setTB_962_TOAR_MORE_TEUR_ENG_K(rs.getString("TEUR_ENG_K"));
        setTA_PERSON_SHEM_PRATI(rs.getString("SHEM_PRATI"));
        setTA_PERSON_SHEM_EMTZAI(rs.getString("SHEM_EMTZAI"));
        setTA_PERSON_SHEM_MISHP(rs.getString("SHEM_MISHP"));
        setTA_PERSON_SHEM_PRATI_ENG(rs.getString("SHEM_PRATI_ENG"));
        setTA_PERSON_SHEM_EMTZAI_ENG(rs.getString("SHEM_EMTZAI_ENG"));
        setTA_PERSON_SHEM_MISHP_ENG(rs.getString("SHEM_MISHP_ENG"));
        setTB_911_BINYAN_TEUR_K(rs.getString("TEUR_K"));
        setTB_911_BINYAN_TEUR_ENG_K(rs.getString("TEUR_ENG_K"));
        setTB_911_BINYAN_NZ_ORECH(rs.getString("NZ_ORECH"));
        setTB_911_BINYAN_NZ_ROHAV(rs.getString("NZ_ROHAV"));
        setPortal_Calendary_View_ROOM(rs.getString("ROOM"));
        setPortal_Calendary_View_ROOM_EXTRA_CODE(rs.getString("ROOM_EXTRA_CODE"));
    }


    Integer KR_KURS_K_PNIMI;
    String Portal_Calendary_View_SEM_KVUTZA;
    String KR_KURS_K_KURS;
    String KR_KURS_TEUR_K;
    String KR_KURS_TEUR_ENG_K;
    String KR_KURS_TEUR_KURS;
    String KR_KURS_TEUR_ENG;
    String TL_KURS_KVUTZA;
    String KR_KURS_OFEN_HORAA1;
    String TB_002_OFEN_HORAA_TEUR_K1;
    String TB_002_OFEN_HORAA_TEUR_ENG_K1;
    String Portal_Calendary_View_SEM_KVUTZA_V_DATE;
    String Portal_Calendary_View_SEM_KVUTZA_V_START;
    String Portal_Calendary_View_SEM_KVUTZA_V_END;
    String TB_962_TOAR_MORE_TEUR_K;
    String TB_962_TOAR_MORE_TEUR_ENG_K;
    String TA_PERSON_SHEM_PRATI;
    String TA_PERSON_SHEM_EMTZAI;
    String TA_PERSON_SHEM_MISHP;
    String TA_PERSON_SHEM_PRATI_ENG;
    String TA_PERSON_SHEM_EMTZAI_ENG;
    String TA_PERSON_SHEM_MISHP_ENG;
    String TB_911_BINYAN_TEUR_K;
    String TB_911_BINYAN_TEUR_ENG_K;
    String TB_911_BINYAN_NZ_ORECH;
    String TB_911_BINYAN_NZ_ROHAV;
    String Portal_Calendary_View_ROOM;
    String Portal_Calendary_View_ROOM_EXTRA_CODE;

    public void setKR_KURS_K_PNIMI(Integer KR_KURS_K_PNIMI) {
        this.KR_KURS_K_PNIMI = KR_KURS_K_PNIMI;
    }

    public void setPortal_Calendary_View_SEM_KVUTZA(String portal_Calendary_View_SEM_KVUTZA) {
        Portal_Calendary_View_SEM_KVUTZA = portal_Calendary_View_SEM_KVUTZA;
    }

    public void setKR_KURS_K_KURS(String KR_KURS_K_KURS) {
        this.KR_KURS_K_KURS = KR_KURS_K_KURS;
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

    public void setTL_KURS_KVUTZA(String TL_KURS_KVUTZA) {
        this.TL_KURS_KVUTZA = TL_KURS_KVUTZA;
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

    public void setPortal_Calendary_View_SEM_KVUTZA_V_DATE(String portal_Calendary_View_SEM_KVUTZA_V_DATE) {
        Portal_Calendary_View_SEM_KVUTZA_V_DATE = portal_Calendary_View_SEM_KVUTZA_V_DATE;
    }

    public void setPortal_Calendary_View_SEM_KVUTZA_V_START(String portal_Calendary_View_SEM_KVUTZA_V_START) {
        Portal_Calendary_View_SEM_KVUTZA_V_START = portal_Calendary_View_SEM_KVUTZA_V_START;
    }

    public void setPortal_Calendary_View_SEM_KVUTZA_V_END(String portal_Calendary_View_SEM_KVUTZA_V_END) {
        Portal_Calendary_View_SEM_KVUTZA_V_END = portal_Calendary_View_SEM_KVUTZA_V_END;
    }

    public void setTB_962_TOAR_MORE_TEUR_K(String TB_962_TOAR_MORE_TEUR_K) {
        this.TB_962_TOAR_MORE_TEUR_K = TB_962_TOAR_MORE_TEUR_K;
    }

    public void setTB_962_TOAR_MORE_TEUR_ENG_K(String TB_962_TOAR_MORE_TEUR_ENG_K) {
        this.TB_962_TOAR_MORE_TEUR_ENG_K = TB_962_TOAR_MORE_TEUR_ENG_K;
    }

    public void setTA_PERSON_SHEM_PRATI(String TA_PERSON_SHEM_PRATI) {
        this.TA_PERSON_SHEM_PRATI = TA_PERSON_SHEM_PRATI;
    }

    public void setTA_PERSON_SHEM_EMTZAI(String TA_PERSON_SHEM_EMTZAI) {
        this.TA_PERSON_SHEM_EMTZAI = TA_PERSON_SHEM_EMTZAI;
    }

    public void setTA_PERSON_SHEM_MISHP(String TA_PERSON_SHEM_MISHP) {
        this.TA_PERSON_SHEM_MISHP = TA_PERSON_SHEM_MISHP;
    }

    public void setTA_PERSON_SHEM_PRATI_ENG(String TA_PERSON_SHEM_PRATI_ENG) {
        this.TA_PERSON_SHEM_PRATI_ENG = TA_PERSON_SHEM_PRATI_ENG;
    }

    public void setTA_PERSON_SHEM_EMTZAI_ENG(String TA_PERSON_SHEM_EMTZAI_ENG) {
        this.TA_PERSON_SHEM_EMTZAI_ENG = TA_PERSON_SHEM_EMTZAI_ENG;
    }

    public void setTA_PERSON_SHEM_MISHP_ENG(String TA_PERSON_SHEM_MISHP_ENG) {
        this.TA_PERSON_SHEM_MISHP_ENG = TA_PERSON_SHEM_MISHP_ENG;
    }

    public void setTB_911_BINYAN_TEUR_K(String TB_911_BINYAN_TEUR_K) {
        this.TB_911_BINYAN_TEUR_K = TB_911_BINYAN_TEUR_K;
    }

    public void setTB_911_BINYAN_TEUR_ENG_K(String TB_911_BINYAN_TEUR_ENG_K) {
        this.TB_911_BINYAN_TEUR_ENG_K = TB_911_BINYAN_TEUR_ENG_K;
    }

    public void setTB_911_BINYAN_NZ_ORECH(String TB_911_BINYAN_NZ_ORECH) {
        this.TB_911_BINYAN_NZ_ORECH = TB_911_BINYAN_NZ_ORECH;
    }

    public void setTB_911_BINYAN_NZ_ROHAV(String TB_911_BINYAN_NZ_ROHAV) {
        this.TB_911_BINYAN_NZ_ROHAV = TB_911_BINYAN_NZ_ROHAV;
    }

    public void setPortal_Calendary_View_ROOM(String portal_Calendary_View_ROOM) {
        Portal_Calendary_View_ROOM = portal_Calendary_View_ROOM;
    }

    public void setPortal_Calendary_View_ROOM_EXTRA_CODE(String portal_Calendary_View_ROOM_EXTRA_CODE) {
        Portal_Calendary_View_ROOM_EXTRA_CODE = portal_Calendary_View_ROOM_EXTRA_CODE;
    }
 }

