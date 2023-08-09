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
public class Person_TuitionServiceCatalogImpl implements ServiceCatalog {


    @Override
    public JsonObject
    getMetaData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("endpoint", "Person_Tuition");
        jsonObject.addProperty("project", "STUD");
        jsonObject.addProperty("description", "Get Person Tuition Balance");
        jsonObject.addProperty("reqested feilds", "IDNO format: ^[0-9]{9}$ " +
                "from_year format: ^[0-9]{5}$ " +
                "limit format: ^[0-9]{5}$ ");

        JsonArray jsonArray = new JsonArray();
        jsonArray.add("STUD.SL_TNUA");
        jsonArray.add("STUD.SL_HESHBON");
        jsonArray.add("STUD.TB_069_SCL_PEULA");

        jsonObject.add("metadata", jsonArray);

        return jsonObject;
    }
}