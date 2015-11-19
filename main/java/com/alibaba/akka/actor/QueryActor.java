/*
 * Copyright 1999-19 Nov 2015 Alibaba.com All right reserved. This software is the confidential and proprietary
 * information of Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.akka.actor;

import java.util.List;
import java.util.UUID;

import com.alibaba.akka.model.PageQueryMsg;
import com.alibaba.akka.model.PageResultMsg;
import com.alibaba.akka.model.QueryMsg;
import com.alibaba.akka.model.ResultMsg;
import com.alibaba.akka.spring.SpringExtension;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.UntypedActor;

/**
 * @author yangbolin 19 Nov 2015 1:31:36 pm
 */
public class QueryActor extends UntypedActor {

    private ActorSystem actorSystem;

    private ActorRef actorSource;

    private int       totalPageNum = 0;
    private int       currentPage = 0;
    private ResultMsg resultMsg    = new ResultMsg();

    @Override
    public void onReceive(Object arg0) throws Exception {
        if (arg0 instanceof QueryMsg) {
            QueryMsg queryMsg = (QueryMsg) arg0;
            List<PageQueryMsg> queryMsgs = queryMsg.getPageQueryMsgList();
            totalPageNum = queryMsgs.size();
            actorSource = getSender();
            for (PageQueryMsg pageQueryMsg : queryMsgs) {
                UUID uuid = UUID.randomUUID();
                ActorRef pagingQueryActor = actorSystem.actorOf(SpringExtension.SpringExtProvider.get(actorSystem).props("pagingQueryActor"),
                                                                "pagingQueryActor" + uuid);
                pagingQueryActor.tell(pageQueryMsg, getSelf());
            }
        } else if (arg0 instanceof PageResultMsg) {
            PageResultMsg pageResultMsg = (PageResultMsg)arg0;
            resultMsg.addPageResultMsg(pageResultMsg);
            ++currentPage;
            if (currentPage == totalPageNum) {
                actorSource.tell(resultMsg, getSelf());
            }
        }
    }

    public void setActorSystem(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
    }
}
