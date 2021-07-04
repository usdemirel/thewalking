package com.thewalking.shop.model;

import java.io.Serializable;

public enum Roles implements Serializable {

    ADMIN,OWNER,MANAGER
    ,EMPLOYEE,CUSTOMER,GUEST,NEWHIRE,FIRE,UNAUTHORIZED;

    String role;

    Roles() {
       this.role=this.name();
    }

    public String getRole() {
        return role;
    }
}
