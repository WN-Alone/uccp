package com.nantian.uccp.service.impl;

import com.nantian.uccp.mapper.UccpEngJobJnlMapper;
import com.nantian.uccp.pojo.UccpEngJobJnl;
import com.nantian.uccp.service.UccpEngJobJnlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UccpEngJobJnlServiceImpl implements UccpEngJobJnlService {

    @Autowired
    private UccpEngJobJnlMapper uccpEngJobJnlMapper;

    @Override
    public List<UccpEngJobJnl> queryUccpOidInfoListPage(Integer pageNum, Integer pageSize, String plat, String jobState, String jobRlt, Date startTime) {
        List<UccpEngJobJnl> uccpEngJobJnlList = uccpEngJobJnlMapper.queryUccpOidInfoListPage(pageNum, pageSize, plat, jobState, jobRlt, startTime);
        return uccpEngJobJnlList;
    }

    @Override
    public Integer selectTotal(String plat, String jobState, String jobRlt, Date startTime) {
        Integer integer = uccpEngJobJnlMapper.selectTotal(plat, jobState, jobRlt, startTime);
        return integer;
    }

}
