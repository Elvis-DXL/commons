package com.elvis.commons.utils;


import com.elvis.commons.pojo.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Elvis
 * @since : 2020/7/30 15:11
 */
public final class TreeUtil {

    private TreeUtil() {
    }

    public static List<TreeNode> formatTree(List<TreeNode> data) {
        List<TreeNode> result = new ArrayList<>(1000);
        if (data != null && data.size() != 0) {
            Map<String, TreeNode> tmpMap = new HashMap<>(1000);
            for (TreeNode node : data) {
                tmpMap.put(node.getId(), node);
            }
            List<TreeNode> children;
            for (TreeNode node : data) {
                if (StrUtil.isNotEmpty(node.getParentId()) && tmpMap.get(node.getParentId()) != null
                        && !node.getId().equalsIgnoreCase(node.getParentId())) {
                    children = tmpMap.get(node.getParentId()).getChildren() == null ?
                            new ArrayList<>(100) : tmpMap.get(node.getParentId()).getChildren();
                    children.add(node);
                    tmpMap.get(node.getParentId()).setChildren(children);
                } else {
                    result.add(node);
                }
            }
        }
        return result;
    }
}
