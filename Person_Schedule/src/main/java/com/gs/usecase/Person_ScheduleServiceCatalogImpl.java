package com.gs.usecase;

import tau.ods.gs.model.logging.LogBuilder;
import tau.ods.gs.model.logging.LogMessage;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.gs.infra.metadata.ServiceCatalog;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class Person_ScheduleServiceCatalogImpl implements ServiceCatalog {


    @Override
    public JsonObject
    getMetaData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("endpoint", "Person_Schedule");
        jsonObject.addProperty("project", "STUD");
        jsonObject.addProperty("description", "Get Person Calendar");
        jsonObject.addProperty("reqested feilds", "TL_KURS_K_PNIMI format: ^[0-9]{9}$ " +
                "Portal_Calendary_View_FROM_T_DATE format: ^[0-9]{5}$ " +
                "Portal_Calendary_View_TO_T_DATE format: ^[0-9]{5}$ " +
                "limit format: ^[0-9]{5}$ ");


        JsonArray jsonArray = new JsonArray();
        jsonArray.add("STUD.TL_KURSN");
        jsonArray.add("STUD.TA_PERSON");
        jsonArray.add("dbo.Portal_Calendary_View");
        jsonArray.add("STUD.KR_KURS");
        jsonArray.add("STUD.TB_911_BINYAN");
        jsonArray.add("STUD.TB_002_OFEN_HORAA");
        jsonArray.add("STUD.TB_962_TOAR_MORE");

        jsonObject.add("metadata", jsonArray);

        return jsonObject;
    }
}