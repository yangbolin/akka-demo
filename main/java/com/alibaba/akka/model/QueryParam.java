/*
 * Copyright 1999-19 Nov 2015 Alibaba.com All right reserved. This software is the confidential and proprietary
 * information of Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.akka.model;

/**
 * 类QueryParam.java的实现描述：TODO 类实现描述
 * 
 * @author yangbolin 19 Nov 2015 11:45:13 am
 */
public class QueryParam {

    Long startIndex;
    int  pageSize;

    public Long getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Long startIndex) {
        this.startIndex = startIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
