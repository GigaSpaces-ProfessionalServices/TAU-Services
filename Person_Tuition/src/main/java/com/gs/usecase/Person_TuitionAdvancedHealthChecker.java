package com.gs.usecase;

import com.gs.infra.health.HealthChecker;
import org.openspaces.core.GigaSpace;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;


@Component
public class Person_TuitionAdvancedHealthChecker implements HealthChecker {

    @Resource
    GigaSpace gigaSpace;

    @Override
    public int isHealthy() {

        if (gigaSpace.getTypeManager().getTypeDescriptor("STUD.TL_KURS") == null || // total 12M, We bring 160K for 5 years
                gigaSpace.getTypeManager().getTypeDescriptor("STUD.TA_PERSON") == null || // 560K
                gigaSpace.getTypeManager().getTypeDescriptor("dbo.Portal_Calendary_View") == null || // 270K
                gigaSpace.getTypeManager().getTypeDescriptor("STUD.KR_KURS") == null || // 122K
                gigaSpace.getTypeManager().getTypeDescriptor("STUD.TB_911_BINYAN") == null || // 250
                gigaSpace.getTypeManager().getTypeDescriptor("STUD.TB_002_OFEN_HORAA") == null || // 20
                gigaSpace.getTypeManager().getTypeDescriptor("STUD.TB_962_TOAR_MORE") == null) { // 13

            return 500;
        }else{
            return 200;
        }
    }
}