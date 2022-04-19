package com.elvis.commons.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 时间字符串对象
 *
 * @author : Elvis
 * @since : 2020/8/3 12:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("TimeStr|时间字符串对象")
public class TimeStr implements Serializable {

    @ApiModelProperty("开始时间字符串")
    protected String startTime;

    @ApiModelProperty("结束时间字符串")
    protected String endTime;
}
