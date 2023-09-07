package com.nantian.uccp.utils;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.CellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.util.Objects;

/**
 * @Author: AlanZz
 * @Description: Excel导出转换工具类
 * @DateTime: 2023/8/3 10:28
 **/
public class AddOrDelConverter implements Converter<String> {

    @Override
    public WriteCellData<String> convertToExcelData(String value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if(Objects.equals(value, "1")){
            return new WriteCellData<>("公共");
        }else if (Objects.equals(value, "0")){
            return new WriteCellData<>("私有");
        } else if (Objects.equals(value, "A")) {
            return new WriteCellData<>("计算机");
        } else if (Objects.equals(value, "B")) {
            return new WriteCellData<>("交换机");
        } else if (Objects.equals(value, "C")) {
            return new WriteCellData<>("路由器");
        } else if (Objects.equals(value, "D")) {
            return new WriteCellData<>("防火墙");
        }else {
            return new WriteCellData<>(value);
        }
    }

}
