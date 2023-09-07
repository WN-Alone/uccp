package com.nantian.uccp.service;

import com.nantian.uccp.pojo.UccpEngTaskJnl;

import java.util.List;

public interface UccpEngTaskJnlService {

    List<UccpEngTaskJnl> queryUccpEngTaskJnlListPage(Integer pageNum, Integer pageSize, String taskId);

    Integer selectTotal(String taskId);
}
