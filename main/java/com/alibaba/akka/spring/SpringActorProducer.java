/*
 * Copyright 1999-2014 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.akka.spring;

import org.springframework.context.ApplicationContext;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;

/**
 * 类SpringActorProducer.java的实现描述：从Spring容器中创建Actor
 * 
 * @author yangbolin Nov 16, 2014 5:28:25 PM
 */
public class SpringActorProducer implements IndirectActorProducer {

    final ApplicationContext applicationContext;
    final String             actorBeanName;

    public SpringActorProducer(ApplicationContext applicationContext, String actorBeanName){
        this.applicationContext = applicationContext;
        this.actorBeanName = actorBeanName;
    }

    @Override
    public Actor produce() {
        return (Actor) applicationContext.getBean(actorBeanName);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) applicationContext.getType(actorBeanName);
    }
}
