package com.thewalking.shop.security.model;

import javax.persistence.Entity;
import java.io.Serializable;

public enum Roles implements Serializable {
//    ADMIN("ADMIN"),OWNER("OWNER"),MANAGER("MANAGER")
//    ,SALESPERSON("SALESPERSON"),CUSTOMER("CUSTOMER"),GUEST("GUEST");

    ADMIN,OWNER,MANAGER
    ,SALESPERSON,CUSTOMER,GUEST;

    String role;

    Roles() {
       this.role=this.name();
    }

    public String getRole() {
        return role;
    }
}
