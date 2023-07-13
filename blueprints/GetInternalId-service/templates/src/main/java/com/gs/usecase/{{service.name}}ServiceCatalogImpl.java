package com.gs.usecase;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.gs.infra.metadata.ServiceCatalog;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class {{service.name}}ServiceCatalogImpl implements ServiceCatalog {

    @Override
    public JsonObject
    getMetaData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("endpoint", "{{service.type}}");
        jsonObject.addProperty("description", "{{service.description}}");
        jsonObject.addProperty("reqested feilds", "{{request.param1}},{{request.param2}}");

        JsonArray jsonArray = new JsonArray();
        jsonArray.add("{{service.type}}");

        jsonObject.add("metadata", jsonArray);

        return jsonObject;
    }
}
