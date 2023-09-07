package com.nantian.uccp.mapper;

import com.nantian.uccp.pojo.UccpOidInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper
@Repository
public interface UccpOidInfoMapper {

    int addOID(UccpOidInfo uccpOidInfo);

    int updateOID(UccpOidInfo uccpOidInfo);

    int deleteOID(@Param("oid") String oid);

    List<UccpOidInfo> queryUccpOidInfoListPage(Integer pageNum, Integer pageSize, String oid);

    Integer selectTotal(String oid);

    void deleteOIDs(List<String> oid);

    List<UccpOidInfo> list();

}
