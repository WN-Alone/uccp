package com.nantian.uccp.controller;

import com.nantian.uccp.pojo.UccpEngTaskJnl;
import com.nantian.uccp.service.impl.UccpEngTaskJnlServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task")
public class UccpEngTaskJnlController {


    private Logger logger = LoggerFactory.getLogger(UccpEngTaskJnlController.class);

    @Autowired
    private UccpEngTaskJnlServiceImpl uccpEngTaskJnlService;


    /**
     * @param pageNum
     * @param pageSize
     * @param taskId
     * @Author: AlanZz
     * @Description: 根据任务ID查询数据
     * @DateTime: 2023/8/2 16:25
     * @Return Map<String,Object>
     */
    @GetMapping("/queryUccpEngTaskJnlList/page")
    public Map<String, Object> queryUccpEngTaskJnlListPage(@RequestParam Integer pageNum,
                                                           @RequestParam Integer pageSize,
                                                           @RequestParam String taskId) {
        logger.info("pageNum：[{}]", pageNum);
        logger.info("pageSize：[{}]", pageSize);
        logger.info("taskID：[{}]", taskId);
        Map<String, Object> res = new HashMap<>();
        try{
            pageNum = (pageNum - 1) * pageSize;
            List<UccpEngTaskJnl> data = uccpEngTaskJnlService.queryUccpEngTaskJnlListPage(pageNum, pageSize, taskId);
            Integer total = uccpEngTaskJnlService.selectTotal(taskId);
            logger.info("一共查询到{}条数据", total);
            logger.info("查询到的数据集合：[{}]", data);
            res.put("data", data);
            res.put("total", total);
        }catch (Exception e){
            res.put("data", null);
            res.put("total", null);
            logger.error("查询数据异常！", e);
        }
        return res;
    }

}
