package com.nantian.uccp.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UccpEngJobJnl {

    private String jobRunId;

    private String jobId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;

    private String jobType;

    private String plat;

    private String jobUser;

    private String jobRole;

    private String jobTenant;

    private String jobState;

    private String jobRlt;

    private String errInfo;

    private String execAction;

}
