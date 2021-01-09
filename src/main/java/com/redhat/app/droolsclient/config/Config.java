package com.redhat.app.droolsclient.config;

import javax.annotation.PostConstruct;

import org.kie.api.KieServices;
import org.kie.api.command.KieCommands;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Config {
    
    private KieServicesConfiguration conf;
    private MarshallingFormat FORMAT = MarshallingFormat.JSON;
    private KieServicesClient kieServicesClient;
    private KieCommands KieCommands;
    @Value( "${rhdm.url}" )
    String URL;

    @Value( "${rhdm.username}" )
    String USER;

    @Value( "${rhdm.password}" )
    String PASSWORD;

    @PostConstruct
    private void init() {

        log.info(("init methond {}"), URL);
        System.setProperty("javax.net.ssl.trustStore","/home/virtuser/workspace/drools-client/truststore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword","password");
        log.info("trust store {}",System.getProperty("javax.net.ssl.trustStore"));
        conf = KieServicesFactory.newRestConfiguration(URL, USER, PASSWORD);
        conf.setUseSsl(true);
        //If you use custom classes, such as Obj.class, add them to the configuration.
        /**
        Set<Class<?>> extraClassList = new HashSet<Class<?>>();
        extraClassList.add(Obj.class);
        conf.addExtraClasses(extraClassList);
        */
        conf.setMarshallingFormat(FORMAT);
        kieServicesClient = KieServicesFactory.newKieServicesClient(conf);        
        KieCommands = KieServices.Factory.get().getCommands();
        log.info("Kie Client Created : {}", kieServicesClient);
    }


    public KieServicesClient getClient() {
        return this.kieServicesClient;
    }
    
    public KieCommands getCommandFactory() {
        return this.KieCommands;
    }

    
}
