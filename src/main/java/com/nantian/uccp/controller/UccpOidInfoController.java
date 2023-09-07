package com.nantian.uccp.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.fastjson.JSON;
import com.nantian.uccp.pojo.UccpOidInfo;
import com.nantian.uccp.service.impl.UccpOidInfoServiceImpl;
import com.nantian.uccp.utils.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/oid")
public class UccpOidInfoController {
    private Logger logger = LoggerFactory.getLogger(UccpOidInfoController.class);

    String PATH = "D:\\Javaworkspace\\uccp";

    @Autowired
    private UccpOidInfoServiceImpl uccpOidInfoService;

    /**
     * @param pageNum
     * @param pageSize
     * @param oid
     * @Author: AlanZz
     * @Description: 利用oid进行分页查询
     * @DateTime: 2023/8/2 10:51
     * @Return Map<String, Object>
     */
    @GetMapping("/queryUccpOidInfoList/page")
    public Map<String, Object> queryUccpOidInfoListPage(@RequestParam Integer pageNum,
                                                        @RequestParam Integer pageSize,
                                                        @RequestParam String oid) {
        logger.info("pageNum：[{}]", pageNum);
        logger.info("pageSize：[{}]", pageSize);
        logger.info("OID：[{}]", oid);
        Map<String, Object> res = new HashMap<>();
        try {
            Integer total = uccpOidInfoService.selectTotal(oid);
            logger.info("一共查询到{}条数据", total);
            //limit第一个参数（偏移量） = （pageNum - 1）* pageSize
            if (total <= pageSize || total <= (pageNum - 1) * pageSize) {
                pageNum = 0;
            } else {
                pageNum = (pageNum - 1) * pageSize;
            }
            List<UccpOidInfo> data = uccpOidInfoService.queryUccpOidInfoListPage(pageNum, pageSize, oid);
            logger.info("查询到的数据集合：[{}]", data);
            res.put("data", data);
            res.put("total", total);
            res.put("pageNum", pageNum / pageSize + 1);
        } catch (Exception e) {
            res.put("data", null);
            res.put("total", null);
            res.put("pageNum", null);
            logger.error("查询数据异常！", e);
        }
        return res;
    }

    /**
     * @param map
     * @Author: AlanZz
     * @Description: 新增oid信息
     * @DateTime: 2023/8/2 10:52
     * @Return String
     */
    @PostMapping("/addOID")
    public String add(@RequestBody(required = false) Map<String, String> map) {
        logger.info("要增加的数据：[{}]", map);
        ResponseBody responseBody = new ResponseBody();
        if (map.get("oid") == null) {
            responseBody.setCode("500");
            responseBody.setMessage("OID参数不能为空");
            return JSON.toJSONString(responseBody);
        } else {
            UccpOidInfo uccpOidInfo = new UccpOidInfo(map.get("oid"),
                    map.get("oidName"), map.get("pubFlag"), map.get("oidDeviceType"), map.get("enterprise"),
                    map.get("oidDeviceCode"), map.get("oidDeviceVersion"), map.get("description"));
            Integer total = uccpOidInfoService.selectTotal(map.get("oid"));
            if (total == 0) {
                try {
                    uccpOidInfoService.addOID(uccpOidInfo);
                    responseBody.setCode("200");
                    responseBody.setMessage("成功插入数据");
                } catch (Exception e) {
                    responseBody.setCode("500");
                    responseBody.setMessage("插入数据错误");
                    logger.error("插入数据错误", e);
                }
                return JSON.toJSONString(responseBody);
            } else {
                responseBody.setCode("500");
                responseBody.setMessage("数据已存在，无法插入");

                return JSON.toJSONString(responseBody);
            }
        }
    }

    /**
     * @param map
     * @Author: AlanZz
     * @Description: 修改oid信息
     * @DateTime: 2023/8/2 14:43
     * @Return String
     */
    @PostMapping("/updateOID")
    public String update(@RequestBody(required = false) Map<String, String> map) {
        logger.info("要更新的数据：[{}]", map);
        ResponseBody responseBody = new ResponseBody();
        if (map.get("oid") == null) {
            responseBody.setCode("500");
            responseBody.setMessage("OID参数不能为空");
            return JSON.toJSONString(responseBody);
        } else {
            //获取表格内的数据
            UccpOidInfo uccpOidInfo = new UccpOidInfo(map.get("oid"),
                    map.get("oidName"), map.get("pubFlag"), map.get("oidDeviceType"), map.get("enterprise"),
                    map.get("oidDeviceCode"), map.get("oidDeviceVersion"), map.get("description"));
            //判断该oid信息是否存在
            Integer total = uccpOidInfoService.selectTotal(map.get("oid"));
            if (total == 0) {
                responseBody.setCode("500");
                responseBody.setMessage("数据不存在，无法修改");
                return JSON.toJSONString(responseBody);
            } else {
                try {
                    uccpOidInfoService.updateOID(uccpOidInfo);
                    responseBody.setCode("200");
                    responseBody.setMessage("成功修改数据");
                } catch (Exception e) {
                    responseBody.setCode("500");
                    responseBody.setMessage("修改数据错误");
                    logger.error("修改数据错误", e);
                }
                return JSON.toJSONString(responseBody);
            }
        }
    }

    /**
     * @param oid
     * @Author: AlanZz
     * @Description: 根据oid删除对应的一条oid信息
     * @DateTime: 2023/8/2 10:53
     * @Return String
     */
    @DeleteMapping("/{oid}")
    public String deleteOID(@PathVariable String oid) {
        logger.info("要删除的OID：[{}]", oid);
        ResponseBody responseBody = new ResponseBody();
        try {
            uccpOidInfoService.deleteOID(oid);
            responseBody.setCode("200");
            responseBody.setMessage("成功删除数据");
        } catch (Exception e) {
            responseBody.setCode("500");
            responseBody.setMessage("删除数据错误");
            logger.error("删除数据错误", e);
        }
        return JSON.toJSONString(responseBody);
    }

    /**
     * @param oid
     * @Author: AlanZz
     * @Description: 根据多条oid删除多条对应信息
     * @DateTime: 2023/8/2 10:54
     * @Return String
     */
    @PostMapping("/delBatch")
    public String deleteBatch(@RequestBody List<String> oid) {
        logger.info("要删除的OID：[{}]", oid);
        ResponseBody responseBody = new ResponseBody();
        if (oid.isEmpty()) {
            responseBody.setCode("500");
            responseBody.setMessage("未找到要删除的数据！");
            return JSON.toJSONString(responseBody);
        } else {
            try {
                uccpOidInfoService.deleteOIDs(oid);
                responseBody.setCode("200");
                responseBody.setMessage("成功删除数据");
            } catch (Exception e) {
                responseBody.setCode("500");
                responseBody.setMessage("删除数据错误");
                logger.error("删除数据错误", e);
            }
            return JSON.toJSONString(responseBody);
        }
    }


    /**
     * @param response
     * @Author: AlanZz
     * @Description: 导出OID信息表
     * @DateTime: 2023/8/3 10:59
     * @Return String
     */
    @GetMapping("/export")
    public String exportOid(HttpServletResponse response) throws Exception {
        ResponseBody responseBody = new ResponseBody();
        List<UccpOidInfo> list = uccpOidInfoService.list();
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //设置背景颜色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        //设置头字体
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short)13);
        headWriteFont.setBold(true);
        headWriteCellStyle.setWriteFont(headWriteFont);
        //设置头居中
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);

        //内容策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //设置 水平居中
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);

        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        String fileName = URLEncoder.encode("OID信息表","UTF-8");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(outputStream, UccpOidInfo.class)
                .registerWriteHandler(horizontalCellStyleStrategy)
                .sheet("OID信息")
                .doWrite(() -> {
                    // 分页查询数据
                    return list;
                });
        responseBody.setCode("200");
        responseBody.setMessage("导出成功！");
        return JSON.toJSONString(responseBody);
    }

}
