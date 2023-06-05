package com.gs.usecase.utils;

import org.openspaces.pu.container.integrated.IntegratedProcessingUnitContainerProvider;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class KR_CHEDERRunner {

    public static void main(String[] args) throws IOException, URISyntaxException {
        String group = args[0];
        String managers = args[1];

        System.setProperty("com.gs.jini_lus.groups", group);
        System.setProperty("com.gs.jini_lus.locators", managers);

        IntegratedProcessingUnitContainerProvider provider = new IntegratedProcessingUnitContainerProvider();

        URL resource = KR_CHEDERRunner.class.getClassLoader().getResource("META-INF/spring/pu.xml");
        provider.addConfigLocation(resource.toURI().toASCIIString());
        provider.createContainer();
    }
}
