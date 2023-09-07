package com.nantian.uccp.mapper;

import com.nantian.uccp.pojo.UccpEngJobJnl;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

//@Mapper
@Repository
public interface UccpEngJobJnlMapper {


    List<UccpEngJobJnl> queryUccpOidInfoListPage(Integer pageNum, Integer pageSize, String plat, String jobState, String jobRlt, Date startTime);


    Integer selectTotal(String plat, String jobState, String jobRlt, Date startTime);


}
