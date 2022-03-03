package com.elvis.commons.old.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : Elvis
 * @since : 2020/6/28 13:57
 */
@Data
@ApiModel(value = "PageTimeStrDTO|分页时间字符串查询入参")
public class PageTimeStrDTO extends PageDTO implements Serializable {

    @ApiModelProperty("开始时间字符串")
    protected String startTime;

    @ApiModelProperty("结束时间字符串")
    protected String endTime;
}
