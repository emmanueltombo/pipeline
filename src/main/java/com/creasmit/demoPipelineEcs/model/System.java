/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.demoPipelineEcs.model;

/**
 *
 * @author emmanueltombo
 */
public class System {

    private String name;
    private String constructor;

    public System() {
    }

    public System(String name, String constructor) {
        this.name = name;
        this.constructor = constructor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConstructor() {
        return constructor;
    }

    public void setConstructor(String constructor) {
        this.constructor = constructor;
    }

}
