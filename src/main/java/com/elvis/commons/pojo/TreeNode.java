package com.elvis.commons.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 树节点对象
 *
 * @author : Elvis
 * @since : 2020/7/30 15:06
 */
@Data
@ApiModel("TreeNode|树节点对象")
public final class TreeNode implements Serializable {

    @ApiModelProperty("节点ID")
    private String id;

    @ApiModelProperty("节点名称")
    private String name;

    @ApiModelProperty("父节点ID")
    private String parentId;

    @ApiModelProperty("子节点集")
    private List<TreeNode> children;

    @ApiModelProperty("节点类型")
    private String type;

    @ApiModelProperty("节点对象信息")
    private Object self;

    public TreeNode() {
    }

    public TreeNode(String id, String parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public TreeNode(String id, String parentId, String name, String type) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.type = type;
    }

    public TreeNode(String id, String parentId, String name, Object self) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.self = self;
    }

    public TreeNode(String id, String parentId, String name, String type, Object self) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.type = type;
        this.self = self;
    }

}
