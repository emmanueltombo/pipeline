/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.demoPipelineEcs.controller;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.creasmit.demoPipelineEcs.model.System;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;

/**
 *
 * @author emmanueltombo
 */
@RestController
@RequestMapping(path = "/system", produces = MediaType.APPLICATION_JSON_VALUE)
public class SystemController {

    @GetMapping()
    public String getSystems() {
        List<System> list = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        list.add(new System("Ema", "CREAS MIT"));
        list.add(new System("MacOs", "Apple"));
        list.add(new System("Windows", "Microsoft"));
        list.add(new System("Ubuntu", "Ubuntu"));

        String response = "";
        try {
            response = mapper.writeValueAsString(list);
        } catch (Exception e) {
        }
        return response;
    }
}
