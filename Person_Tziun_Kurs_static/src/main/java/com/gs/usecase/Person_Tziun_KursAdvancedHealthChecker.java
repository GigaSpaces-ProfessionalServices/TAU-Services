package com.gs.usecase;

import com.gs.infra.health.HealthChecker;
import org.openspaces.core.GigaSpace;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;


@Component
public class Person_Tziun_KursAdvancedHealthChecker implements HealthChecker {

    @Resource
    GigaSpace gigaSpace;

    @Override
    public int isHealthy() {

        //todo just for checking the NB registration on AWS where the types are missing.
        return 200;
    }
}