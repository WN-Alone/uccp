package com.nantian.uccp.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.nantian.uccp.utils.AddOrDelConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ContentRowHeight(20)
@HeadRowHeight(30)
@ColumnWidth(25)
public class UccpOidInfo {

    @ExcelProperty("OID")
    private String oid;

    @ExcelProperty("OID名称")
    private String oidName;

    /**
     * 1:公共OID  0:私有OID
     */
    @ExcelProperty(value = "是否公有", converter = AddOrDelConverter.class)
    private String pubFlag;

    /**
     * A:计算机 B:交换机 C:路由器  D:防火墙
     */
    @ExcelProperty(value = "OID类型", converter = AddOrDelConverter.class)
    private String oidDeviceType;

    @ExcelProperty("厂商")
    private String enterprise;

    @ExcelProperty("设备型号")
    private String oidDeviceCode;

    @ExcelProperty("版本")
    private String oidDeviceVersion;

    @ExcelProperty("描述")
    private String description;

}
