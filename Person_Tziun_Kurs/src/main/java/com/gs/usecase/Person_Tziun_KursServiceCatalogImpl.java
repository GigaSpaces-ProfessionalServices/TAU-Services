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

    private static final Logger logger_service = org.slf4j.LoggerFactory.getLogger(Person_Tziun_KursServiceCatalogImpl.class);
    // private static final Logger logger_service = tau.ods.gs.model.logging.LoggerFactory.getLogger(Person_Tziun_KursServiceCatalogImpl.class);
    static private final String type = "STUD.Person_Tziun_Kurs";

    @Override
    public JsonObject
    getMetaData() {

        logger_service.info(LogBuilder.get()
                .setStatusCode(200)
                .setLevel(LogMessage.Level.INFO)
                .setTimestamp(new Date(System.currentTimeMillis()))
                .setMessage("Query: metadata")
                .createLogMessage());

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("endpoint", "STUD.Person_Tziun_Kurs");
        jsonObject.addProperty("project", "STUD");
        jsonObject.addProperty("description", "Get Tizun by Kurs");
        jsonObject.addProperty("reqested feilds", "PERSON_IDNO format: ^[0-9]{9}$ TL_KURS_SEM_KVUTZA format: ^[0-9]{5}$");


        JsonArray jsonArray = new JsonArray();
        jsonArray.add(type);

        jsonObject.add("metadata", jsonArray);

        return jsonObject;
    }
}
