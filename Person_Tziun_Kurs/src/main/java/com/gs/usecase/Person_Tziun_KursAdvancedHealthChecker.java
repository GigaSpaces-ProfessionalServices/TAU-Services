package com.gs.usecase;

import com.gs.infra.health.HealthChecker;
import org.openspaces.core.GigaSpace;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;


@Component
public class Person_Tziun_KursAdvancedHealthChecker implements HealthChecker {

    static private final String type = "STUD.TA_PERSON";

    @Resource
    GigaSpace gigaSpace;

    @Override
    public int isHealthy() {

        if (gigaSpace.getTypeManager().getTypeDescriptor("STUD.TA_PERSON") == null ||
                gigaSpace.getTypeManager().getTypeDescriptor("STUD.TL_TOCHNIT") == null ||
                gigaSpace.getTypeManager().getTypeDescriptor("STUD.TL_KURS") == null ||
                gigaSpace.getTypeManager().getTypeDescriptor("STUD.TB_071_SIMUL_TZIUN") == null ||
                gigaSpace.getTypeManager().getTypeDescriptor("STUD.TB_002_OFEN_HORAA") == null) {
            return 500;
        }else{
            return 200;
        }
    }
}