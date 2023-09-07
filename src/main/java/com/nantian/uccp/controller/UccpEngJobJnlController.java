package com.nantian.uccp.controller;

import com.nantian.uccp.pojo.UccpEngJobJnl;
import com.nantian.uccp.service.impl.UccpEngJobJnlServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/job")
public class UccpEngJobJnlController {

    private Logger logger = LoggerFactory.getLogger(UccpEngJobJnlController.class);

    @Autowired
    private UccpEngJobJnlServiceImpl uccpEngJobJnlService;


    /**
     * @param pageNum
     * @param pageSize
     * @param plat
     * @param jobState
     * @param jobRlt
     * @param startTime
     * @Author: AlanZz
     * @Description: 根据作业来源、作业运行状态、作业运行结果、开始日期查询信息
     * @DateTime: 2023/8/2 16:30
     * @Return Map<String,Object>
     */
    @GetMapping("/uccpEngJobJnlList/page")
    public Map<String, Object> queryUccpEngJobJnlListPage(@RequestParam Integer pageNum,
                                                          @RequestParam Integer pageSize,
                                                          @RequestParam String plat,
                                                          @RequestParam String jobState,
                                                          @RequestParam String jobRlt,
                                                          @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime) {
        logger.info("pageNum：[{}]", pageNum);
        logger.info("pageSize：[{}]", pageSize);
        logger.info("plat：[{}]", plat);
        logger.info("jobState：[{}]", jobState);
        logger.info("jobRlt：[{}]", jobRlt);
        logger.info("startTime：[{}]", startTime);
        Map<String, Object> res = new HashMap<>();
        try {
            pageNum = (pageNum - 1) * pageSize;
            List<UccpEngJobJnl> data = uccpEngJobJnlService.queryUccpOidInfoListPage(pageNum, pageSize, plat, jobState, jobRlt, startTime);
            Integer total = uccpEngJobJnlService.selectTotal(plat, jobState, jobRlt, startTime);
            logger.info("一共查询到{}条数据", total);
            logger.info("查询到的数据集合：[{}]", data);
            res.put("data", data);
            res.put("total", total);

        } catch (Exception e) {
            res.put("data", null);
            res.put("total", null);
            logger.error("查询数据异常！", e);
        }
        return res;
    }

}
