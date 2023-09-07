package com.nantian.uccp.service.impl;

import com.nantian.uccp.mapper.UccpEngTaskJnlMapper;
import com.nantian.uccp.pojo.UccpEngTaskJnl;
import com.nantian.uccp.service.UccpEngTaskJnlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UccpEngTaskJnlServiceImpl implements UccpEngTaskJnlService {

    @Autowired
    UccpEngTaskJnlMapper uccpEngTaskJnlMapper;

    @Override
    public List<UccpEngTaskJnl> queryUccpEngTaskJnlListPage(Integer pageNum, Integer pageSize, String taskId) {
        List<UccpEngTaskJnl> uccpEngTaskJnls = uccpEngTaskJnlMapper.queryUccpEngTaskJnlListPage(pageNum, pageSize, taskId);
        return uccpEngTaskJnls;
    }

    @Override
    public Integer selectTotal(String taskId) {
        Integer integer = uccpEngTaskJnlMapper.selectTotal(taskId);
        return integer;
    }
}
