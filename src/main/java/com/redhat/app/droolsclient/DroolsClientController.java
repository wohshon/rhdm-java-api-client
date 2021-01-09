package com.redhat.app.droolsclient;

import com.redhat.app.droolsclient.config.Config;

import org.kie.api.command.KieCommands;
import org.kie.server.client.RuleServicesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("/rest")
public class DroolsClientController {

    @Autowired
    private Config config;


    @GetMapping("/hello/{msg}")
    public String hello(@PathVariable String msg) {
        log.info("hello {}", msg);
        RuleServicesClient ruleServicesClient = config.getClient().getServicesClient(RuleServicesClient.class);
        log.info("client {}", ruleServicesClient);
        //rhdm-kieserver-demorules
        KieCommands cf = config.getCommandFactory();
        
        return msg;
    }
}
