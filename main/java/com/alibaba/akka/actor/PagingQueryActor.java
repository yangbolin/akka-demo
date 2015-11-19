/*
 * Copyright 1999-19 Nov 2015 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.akka.actor;

import java.util.UUID;

import com.alibaba.akka.model.PageQueryMsg;
import com.alibaba.akka.model.PageResultMsg;

import akka.actor.UntypedActor;

/**
 * @author yangbolin 19 Nov 2015 11:36:04 am
 */
public class PagingQueryActor extends UntypedActor {
    
    /* (non-Javadoc)
     * @see akka.actor.UntypedActor#onReceive(java.lang.Object)
     */
    @Override
    public void onReceive(Object arg0) throws Exception {
        if(arg0 instanceof PageQueryMsg) {
            PageResultMsg pageResultMsg = new PageResultMsg();
            pageResultMsg.setContent(UUID.randomUUID().toString());
            getSender().tell(pageResultMsg, getSelf());
        }
    }

}
