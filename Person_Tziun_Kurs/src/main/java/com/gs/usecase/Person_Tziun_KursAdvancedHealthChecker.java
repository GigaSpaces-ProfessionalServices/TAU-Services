package com.gs.usecase;

import com.gs.infra.health.HealthChecker;
import org.openspaces.core.GigaSpace;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;


@Component
public class Person_Tziun_KursAdvancedHealthChecker implements HealthChecker {

    private static final Logger logger_gsc = org.slf4j.LoggerFactory.getLogger(Person_Tziun_KursAdvancedHealthChecker.class);
//    private static final Logger logger_service = tau.ods.gs.model.logging.LoggerFactory.getLogger(Person_Tziun_KursAdvancedHealthChecker.class);
    static private final String type = "STUD.Person_Tziun_Kurs";

    @Resource
    GigaSpace gigaSpace;

    @Override
    public int isHealthy() {

        if (gigaSpace.getTypeManager().getTypeDescriptor(type) == null) {
            return 500;
        }else{
            return 200;
        }
    }
}
