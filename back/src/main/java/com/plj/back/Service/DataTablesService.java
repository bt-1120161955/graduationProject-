package com.plj.back.Service;

import com.plj.back.Entities.DataTables;
import com.plj.back.Mapper.DataTablesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataTablesService {
    @Autowired
    DataTablesMapper dataTablesMapper;

    public List<DataTables> selectAll() {
        return dataTablesMapper.selectAll();
    }

    public Integer addDataTables(DataTables dataTables) {
        return dataTablesMapper.addDataTables(dataTables);
    }
}
