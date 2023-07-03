package com.gs.usecase;

import org.slf4j.Logger;
import java.sql.Date;
import org.slf4j.*;
import com.gs.infra.health.HealthChecker;
import org.openspaces.core.GigaSpace;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;


@Component
public class {{service.name}}AdvancedHealthChecker implements HealthChecker {

    private static final Logger logger = LoggerFactory.getLogger({{service.name}}AdvancedHealthChecker.class);
    static private final String type = "{{service.type}}";

    @Resource
    GigaSpace gigaSpace;

    @Override
    public int isHealthy() {

        if (gigaSpace.getTypeManager().getTypeDescriptor(type) == null) {
            logger.error("{{service.name}} service is un-health because type " + type + " doesn't exist in dih-tau-space");

            return 500;
        }else{
            logger.info("Type "+type+" exist in dih-tau-space");
            return 200;
        }
    }
}
