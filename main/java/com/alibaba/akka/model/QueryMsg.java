/*
 * Copyright 1999-19 Nov 2015 Alibaba.com All right reserved. This software is the confidential and proprietary
 * information of Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.akka.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 类QueryMsg.java的实现描述：TODO 类实现描述
 * 
 * @author yangbolin 19 Nov 2015 12:56:45 pm
 */
public class QueryMsg {

    List<PageQueryMsg> pageQueryMsgList = new ArrayList<PageQueryMsg>();

    
    public List<PageQueryMsg> getPageQueryMsgList() {
        return pageQueryMsgList;
    }
    
    public void setPageQueryMsgList(List<PageQueryMsg> pageQueryMsgList) {
        this.pageQueryMsgList = pageQueryMsgList;
    }

}
