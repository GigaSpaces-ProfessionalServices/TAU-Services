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
public class KR_CHEDERServiceCatalogImpl implements ServiceCatalog {

    @Override
    public JsonObject
    getMetaData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("endpoint", "STUD.KR_CHEDER");
        jsonObject.addProperty("project", "STUD");
        jsonObject.addProperty("description", "This table defines TAU rooms");
        jsonObject.addProperty("reqested feilds", "K_BINYAN format: ^[0-9]{2}$  K_MIS_CHEDER format: ^[0-9]{3}$");

        JsonArray jsonArray = new JsonArray();
        jsonArray.add("STUD.KR_CHEDER");

        jsonObject.add("metadata", jsonArray);

        return jsonObject;
    }
}
