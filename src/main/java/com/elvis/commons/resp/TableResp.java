package com.elvis.commons.resp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Elvis
 * @since : 2020/7/30 11:30
 */
public class TableResp<T> extends BaseResp implements Serializable {

    private TableData<T> data;

    public TableData<T> getData() {
        return data;
    }

    public void setData(TableData<T> data) {
        this.data = data;
    }

    public TableResp<T> status(int status) {
        this.setStatus(status);
        return this;
    }

    public TableResp<T> msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    public TableResp<T> data(long total, List<T> rows) {
        this.setData(new TableData<T>(total, rows));
        return this;
    }

    class TableData<T> {
        long total;
        List<T> rows;

        public TableData(long total, List<T> rows) {
            this.total = total;
            this.rows = rows == null ? new ArrayList<T>() : rows;
        }

        public TableData() {
        }

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public List<T> getRows() {
            return rows;
        }

        public void setRows(List<T> rows) {
            this.rows = rows;
        }
    }
}
