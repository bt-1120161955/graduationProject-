package com.plj.back.Entities;

import java.util.List;

public class Data {
    List<List<String>> datas;
    DataTables dataTables;

    public List<List<String>> getDatas() {
        return datas;
    }

    public void setDatas(List<List<String>> datas) {
        this.datas = datas;
    }

    public DataTables getDataTables() {
        return dataTables;
    }

    public void setDataTables(DataTables dataTables) {
        this.dataTables = dataTables;
    }
}
