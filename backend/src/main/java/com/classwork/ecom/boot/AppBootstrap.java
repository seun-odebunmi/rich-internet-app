package com.classwork.ecom.boot;

import com.classwork.ecom.util.ProductUtil;
import com.classwork.ecom.util.UserTypeUtil;
import com.classwork.ecom.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class AppBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ProductUtil productUtil;
    @Autowired
    private UserTypeUtil userTypeUtil;
    @Autowired
    private UserUtil userUtil;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initialize();
    }

    private void initialize() {
        userTypeUtil.generateSampleUserTypes();
        userUtil.generateSampleUsers(50);
        productUtil.generateSampleProducts(50);
    }
}
