package com.app.authentification;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    ADMIN, CLIENT;
    public String getAuthority() {
        return name();
    }
}
