package com.elvis.commons.zzold.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : Elvis
 * @since : 2020/6/28 13:53
 */
@Data
@ApiModel(value = "PageDTO|分页查询入参")
public class PageDTO implements Serializable {

    @ApiModelProperty("页码")
    protected Integer page;

    @ApiModelProperty("每页条数")
    protected Integer limit;

    @ApiModelProperty("查询关键字")
    protected String searchKey;

    @ApiModelProperty("排序方式")
    protected String orderBy;
}
