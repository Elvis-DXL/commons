package com.elvis.commons.resp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Elvis
 * @since : 2020/7/30 11:29
 */
public class ListResp<T> extends BaseResp implements Serializable {

    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data == null ? new ArrayList<T>() : data;
    }

    public ListResp<T> status(int status) {
        this.setStatus(status);
        return this;
    }

    public ListResp<T> msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    public ListResp<T> data(List<T> data) {
        this.setData(data);
        return this;
    }
}
