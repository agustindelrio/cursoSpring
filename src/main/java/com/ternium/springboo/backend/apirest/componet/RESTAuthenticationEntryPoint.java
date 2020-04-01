package com.ternium.springboo.backend.apirest.componet;

import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class RESTAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {


    @Override
    public void afterPropertiesSet() {
        setRealmName("AppNameHere");
        super.afterPropertiesSet();
    }
}
