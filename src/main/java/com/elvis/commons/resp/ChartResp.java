package com.elvis.commons.resp;

import java.io.Serializable;
import java.util.List;

/**
 * @author : Elvis
 * @since : 2020/7/30 11:30
 */
public class ChartResp<T> extends BaseResp implements Serializable {

    private List<String> xAxis;
    private List<Object> data;
    private T series;

    public ChartResp<T> status(int status) {
        this.setStatus(status);
        return this;
    }

    public ChartResp<T> msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    public ChartResp<T> xAxis(List<String> xAxis) {
        this.setXAxis(xAxis);
        return this;
    }

    public ChartResp<T> data(List<Object> data) {
        this.setData(data);
        return this;
    }

    public ChartResp<T> series(T series) {
        this.setSeries(series);
        return this;
    }

    public List<String> getXAxis() {
        return xAxis;
    }

    public void setXAxis(List<String> xAxis) {
        this.xAxis = xAxis;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public T getSeries() {
        return series;
    }

    public void setSeries(T series) {
        this.series = series;
    }
}
