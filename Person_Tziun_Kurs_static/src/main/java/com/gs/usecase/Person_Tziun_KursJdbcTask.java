package com.gs.usecase;


import com.gs.infra.service.GeneralTask;
import org.openspaces.core.executor.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.*;
import java.sql.*;
import java.util.*;


public class Person_Tziun_KursJdbcTask extends GeneralTask<Person_Tziun_KursRequest, Person_Tziun_KursResponse> implements Task<ArrayList<Person_Tziun_KursResponse>> {
    private static final Logger logger = LoggerFactory.getLogger(Person_Tziun_KursResponse.class);

    static private final String USECASE_QUERY =" SELECT tlkr.K_PNIMI,\n" +
    "pr.IDNO,\n" +
    "pr.SHEM_MISHP,\n" +
    "pr.SHEM_PRATI,\n" +
    "pr.SHEM_MISHP_ENG,\n" +
    "pr.SHEM_PRATI_ENG,\n" +
    "tl.CHUG,\n" +
    "tl.TOAR,\n" +
    "tl.OFEN_LIMUD,\n" +
    "tl.MASLUL,\n" +
    "tlkr.K_SEM,\n" +
    "tlkr.K_KURS,\n" +
    "tlkr.KVUTZA,\n" +
    "tlkr.SEM_KVUTZA,\n" +
    "tlkr.MISGERET,\n" +
    "tlkr.TZIUN_SOFI,\n" +
    "tlkr.KOD_TZIUN,\n" +
    "tlkr.MOED_KOVEA,\n" +
    "tlkr.MATZAV_TZIUN,\n" +
    "tlkr.PTOR,\n" +
    "tlkr.LSHKLL,\n" +
    "tlkr.HUSHLAM,\n" +
    "tlkr.CHOZER,\n" +
    "tlkr.KOVEA,\n" +
    "tlkr.SHAOT_UNI,\n" +
    "tlkr.MISHKAL,\n" +
    "tlkr.SHAOT_SCL,\n" +
    "kr.TEUR_K,\n" +
    "kr.TEUR_ENG_K,\n" +
    "kr.TEUR_KURS,\n" +
    "kr.TEUR_ENG,\n" +
    "kr.SHAOT_UNI AS KR_SHAOT_UNI,\n" +
    "kr.MISHKAL,\n" +
    "kr.LSHKLL,\n" +
    "kr.OFEN_HORAA1,\n" +
    "t002.TEUR_K1,\n" +
    "t002.TEUR_ENG_K1,\n" +
    "t071.TEUR,\n" +
    "t071.TEUR_ENG \n" +
    "FROM\n" +
    "STUD.TA_PERSON pr\n" +
    "join  STUD.TL_TOCHNIT tl \n" +
    "on tl.k_pnimi=pr.k_pnimi \n" +
    "join STUD.TL_KURS tlkr \n" +
    "on  tlkr.K_PNIMI = tl.K_PNIMI \n" +
    "AND tlkr.K_SIDURI_TOCHNIT = tl.K_SIDURI_TOCHNIT \n" +
    "AND tlkr.K_SIDURI_TOAR = tl.K_SIDURI_TOAR \n" +
    "RIGHT OUTER JOIN STUD.TB_071_SIMUL_TZIUN t071 \n" +
    "on tlkr.kod_tziun = t071.k_code \n" +
    "join STUD.KR_KURS kr \n" +
    "on kr.k_kurs=tlkr.k_kurs \n" +
    "AND (tlkr.K_SEM >= kr.K_ME_SEM AND tlkr.K_SEM <= kr.AD_SEM) \n" +
    "RIGHT OUTER JOIN STUD.TB_002_OFEN_HORAA t002 \n" +
    "on kr.ofen_horaa1 = t002.k_code \n" +
    "WHERE \n" +
    "tlkr.MEVUTAL = '0' \n" +
    "and pr.TALMID in ('01') \n" +
    "AND pr.K_PNIMI = %s \n" +
    "AND tlkr.SEM_KVUTZA like '%s' \n" +
    "ORDER BY \n" +
    "tlkr.K_SEM, \n" +
    "tlkr.K_KURS \n" +
    "limit %s";

    @Override
    public Integer routing() {
        return null;
    }

    public ArrayList<Person_Tziun_KursResponse> execute() {

        ArrayList<Person_Tziun_KursResponse> responseList = new ArrayList<>();
        responseList.add(new Person_Tziun_KursResponse(new BigDecimal(8350), "58409186","אלקלעי","אורנה","Alcalay","Orna"));
        responseList.add(new Person_Tziun_KursResponse(new BigDecimal(8350), "58409200","כהן","יעקב","Chohen","Yakov"));
        responseList.add(new Person_Tziun_KursResponse(new BigDecimal(8350), "58409250","אזולאי","רמי","Azolay","Rami"));
        responseList.add(new Person_Tziun_KursResponse(new BigDecimal(1037364), "58409300","חמודי","רונן","Hamudi","Ronen"));
        responseList.add(new Person_Tziun_KursResponse(new BigDecimal(1037364), "58409350","שלום","יורם","Shalom","Yoram"));
        responseList.add(new Person_Tziun_KursResponse(new BigDecimal(1037364), "58409400","שלח","משה","Shelach","Moshe"));
        responseList.add(new Person_Tziun_KursResponse(new BigDecimal(314152), "58409450","מלח","אהרון","Melach","Aharon"));
        responseList.add(new Person_Tziun_KursResponse(new BigDecimal(314152), "58409500","מלל","מרים","Malal","Mriam"));
        responseList.add(new Person_Tziun_KursResponse(new BigDecimal(314152), "58409550","חמד","שרה","Hemed","Sara"));
        responseList.add(new Person_Tziun_KursResponse(new BigDecimal(314152), "58409600","בנימין","לילך","Bnyamin","Lilach"));
        return responseList;
    }
}
