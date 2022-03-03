package com.elvis.commons.old.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : Elvis
 * @since : 2021/6/21 10:32
 */
@Data
@ApiModel(value = "PageOTSDTO|分页单时间查询入参")
public class PageOTSDTO extends PageDTO implements Serializable {
    @ApiModelProperty("时间字符串")
    protected String timeStr;
}
