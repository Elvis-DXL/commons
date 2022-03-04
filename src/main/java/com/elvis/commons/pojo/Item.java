package com.elvis.commons.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : Elvis
 * @since : 2020/6/28 10:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Item|项对象")
public final class Item<T> implements Serializable {

    @ApiModelProperty("标签")
    private String label;

    @ApiModelProperty("值")
    private String value;

    @ApiModelProperty("对象值")
    private T objValue;

    public Item(String label, String value) {
        this.label = label;
        this.value = value;
    }
}
