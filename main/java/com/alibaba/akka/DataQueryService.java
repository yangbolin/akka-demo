/*
 * Copyright 1999-19 Nov 2015 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.akka;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.alibaba.akka.model.Item;
import com.alibaba.akka.model.PageQueryMsg;
import com.alibaba.akka.model.PageResultMsg;
import com.alibaba.akka.model.QueryMsg;
import com.alibaba.akka.model.QueryParam;
import com.alibaba.akka.model.ResultMsg;
import com.alibaba.akka.spring.SpringExtension;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
/**
 * 类BootstrapAkk.java的实现描述：TODO 类实现描述 
 * @author yangbolin 19 Nov 2015 11:41:15 am
 */
public class DataQueryService implements ApplicationContextAware {
    
    private ApplicationContext applicationContext; 
    private ActorSystem        actorSystem;
    
    public List<Item> query(QueryParam param) {
        List<Item> itemList = new ArrayList<Item>();
        
        UUID uuid = UUID.randomUUID();
        SpringExtension.SpringExtProvider.get(actorSystem).initialize(applicationContext);
        final ActorRef queryActor = actorSystem.actorOf(SpringExtension.SpringExtProvider.get(actorSystem).props("queryActor"),
                                                                            "queryActor" + uuid);
        
        QueryMsg queryMsg = new QueryMsg();
        
        // 查询参数消息构造
        List<PageQueryMsg> pageQueryMsgList = new ArrayList<PageQueryMsg>();
        PageQueryMsg pageQueryMsg1 = new PageQueryMsg();
        PageQueryMsg pageQueryMsg2 = new PageQueryMsg();
        PageQueryMsg pageQueryMsg3 = new PageQueryMsg();
        
        pageQueryMsgList.add(pageQueryMsg1);
        pageQueryMsgList.add(pageQueryMsg2);
        pageQueryMsgList.add(pageQueryMsg3);
        
        queryMsg.setPageQueryMsgList(pageQueryMsgList);
        
        Timeout timeout = new Timeout(Duration.create(20, "seconds"));
        Future<Object> future = Patterns.ask(queryActor, queryMsg, timeout);
        try {
            ResultMsg resultMsg = (ResultMsg)Await.result(future,timeout.duration());
            for (PageResultMsg page : resultMsg.getResultList()) {
                Item item = new Item();
                item.setContent(page.getContent());
                itemList.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return itemList;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    public void setActorSystem(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
    }
}
