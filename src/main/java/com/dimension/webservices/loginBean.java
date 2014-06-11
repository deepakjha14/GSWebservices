/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dimension.webservices;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Deepak
 */
@XmlRootElement(name = "change")
public class loginBean {
    @XmlAttribute
    public String usrName;
    public String surName;
    public int age;
    
    public loginBean(){
    }
    public loginBean(String usrName, String surName, int age){
        this.usrName=usrName;
        this.surName=surName;
        this.age=age;
        
    }
}
