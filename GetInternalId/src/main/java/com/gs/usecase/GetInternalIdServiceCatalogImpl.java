package com.gs.usecase;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.gs.infra.metadata.ServiceCatalog;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class GetInternalIdServiceCatalogImpl implements ServiceCatalog {

    @Override
    public JsonObject
    getMetaData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("endpoint", "STUD.TA_PERSON");
        jsonObject.addProperty("description", "Get K_PNIMI BY IDNO");
        jsonObject.addProperty("reqested feilds", "IDNO,");

        JsonArray jsonArray = new JsonArray();
        jsonArray.add("STUD.TA_PERSON");

        jsonObject.add("metadata", jsonArray);

        return jsonObject;
    }
}
