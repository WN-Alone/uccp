package com.nantian.uccp.mapper;

import com.nantian.uccp.pojo.UccpEngTaskJnl;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

//@Mapper
@Repository
public interface UccpEngTaskJnlMapper {


    Integer selectTotal(String taskId);

    List<UccpEngTaskJnl> queryUccpEngTaskJnlListPage(Integer pageNum, Integer pageSize, String taskId);
}
