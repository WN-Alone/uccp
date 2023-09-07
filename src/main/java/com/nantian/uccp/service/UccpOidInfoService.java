package com.nantian.uccp.service;

import com.nantian.uccp.pojo.UccpOidInfo;

import java.util.List;

public interface UccpOidInfoService {

    List<UccpOidInfo> queryUccpOidInfoListPage(Integer pageNum, Integer pageSize, String oid);

    Integer selectTotal(String oid);

    void addOID(UccpOidInfo uccpOidInfo);

    void updateOID(UccpOidInfo uccpOidInfo);

    void deleteOID(String oid);

    void deleteOIDs(List<String> oid);

    List<UccpOidInfo> list();

}
