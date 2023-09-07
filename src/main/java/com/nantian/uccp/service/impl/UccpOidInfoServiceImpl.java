package com.nantian.uccp.service.impl;

import com.nantian.uccp.mapper.UccpOidInfoMapper;
import com.nantian.uccp.pojo.UccpOidInfo;
import com.nantian.uccp.service.UccpOidInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UccpOidInfoServiceImpl implements UccpOidInfoService {

    @Autowired
    private UccpOidInfoMapper uccpOidInfoMapper;



    @Override
    public List<UccpOidInfo> queryUccpOidInfoListPage(Integer pageNum, Integer pageSize, String oid) {
        List<UccpOidInfo> uccpOidInfoList = uccpOidInfoMapper.queryUccpOidInfoListPage(pageNum, pageSize, oid);
        return uccpOidInfoList;
    }


    @Override
    public Integer selectTotal(String oid) {
        Integer integer = uccpOidInfoMapper.selectTotal(oid);
        return integer;
    }

    @Override
    public void addOID(UccpOidInfo uccpOidInfo) {
        uccpOidInfoMapper.addOID(uccpOidInfo);
    }

    @Override
    public void updateOID(UccpOidInfo uccpOidInfo) {
        uccpOidInfoMapper.updateOID(uccpOidInfo);
    }

    @Override
    public void deleteOID(String oid) {
        uccpOidInfoMapper.deleteOID(oid);
    }

    @Override
    public void deleteOIDs(List<String> oid) {
        uccpOidInfoMapper.deleteOIDs(oid);
    }

    @Override
    public List<UccpOidInfo> list() {
        List<UccpOidInfo> list = uccpOidInfoMapper.list();
        return list;
    }

}
