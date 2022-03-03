package com.elvis.commons.old.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : Elvis
 * @since : 2020/8/3 12:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Time|时间对象")
public class Time implements Serializable {

    @ApiModelProperty("开始时间")
    protected Date startTime;

    @ApiModelProperty("结束时间")
    protected Date endTime;
}
