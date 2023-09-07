package com.nantian.uccp.service;

import com.nantian.uccp.pojo.UccpEngJobJnl;


import java.util.Date;
import java.util.List;

public interface UccpEngJobJnlService {

    List<UccpEngJobJnl> queryUccpOidInfoListPage(Integer pageNum, Integer pageSize, String plat, String jobState, String jobRlt, Date startTime);

    Integer selectTotal(String plat, String jobState, String jobRlt, Date startTime);


}
