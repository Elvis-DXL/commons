package com.elvis.commons.zzold.resp;

import java.io.Serializable;

/**
 * @author : Elvis
 * @since : 2020/7/30 11:28
 */
public class ObjectResp<T> extends BaseResp implements Serializable {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ObjectResp<T> status(int status) {
        this.setStatus(status);
        return this;
    }

    public ObjectResp<T> msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    public ObjectResp<T> data(T data) {
        this.setData(data);
        return this;
    }
}
