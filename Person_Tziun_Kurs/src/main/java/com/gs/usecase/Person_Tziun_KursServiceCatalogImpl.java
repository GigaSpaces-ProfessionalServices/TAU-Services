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
public class Person_Tziun_KursServiceCatalogImpl implements ServiceCatalog {


    @Override
    public JsonObject
    getMetaData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("endpoint", "Person_Tziun_Kurs");
        jsonObject.addProperty("project", "STUD");
        jsonObject.addProperty("description", "Get Tizun by Kurs");
        jsonObject.addProperty("reqested feilds", "PERSON_IDNO format: ^[0-9]{9}$ TL_KURS_SEM_KVUTZA format: ^[0-9]{5}$");

        JsonArray jsonArray = new JsonArray();
        jsonArray.add("STUD.TA_PERSON");
        jsonArray.add("STUD.TL_TOCHNIT");
        jsonArray.add("STUD.TL_KURS");
        jsonArray.add("STUD.TB_071_SIMUL_TZIUN");
        jsonArray.add("STUD.TB_002_OFEN_HORAA");

        jsonObject.add("metadata", jsonArray);

        return jsonObject;
    }
}