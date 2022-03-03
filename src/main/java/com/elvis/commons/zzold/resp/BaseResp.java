package com.elvis.commons.zzold.resp;

import java.io.Serializable;

/**
 * @author : Elvis
 * @since : 2020/7/30 11:26
 */
public class BaseResp implements Serializable {

    private int status = 200;
    private String msg;

    public BaseResp(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public BaseResp() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
