package com.plj.back.Service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.plj.back.Entities.User;
import com.plj.back.Mapper.TableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class TableService {
    @Autowired
    TableMapper tableMapper;

    public List<Map<String, Object>> selectTableByName(String tableName) {
        return tableMapper.selectTableByName(tableName);
    }

    public Integer createTable(List<String> colomnName, String tableName) {
        return tableMapper.createTable(colomnName, tableName);
    }

    public Integer insertTable(String tableName, List<String> colomnName, List<List<String>> values) {
        String sql = "";
        sql = sql + "INSERT INTO " + tableName +"(";
        for(int i=0; i < colomnName.size(); i++) {
            if(i == 0) {
                sql = sql + "`" + colomnName.get(i) + "`";
            } else {
                sql = sql + ", " + "`" + colomnName.get(i) + "`";
            }
        }
        sql += ") ";
        Integer flag = 1;
        for(int i=0; i < values.size(); i++){
            Integer flag1 = tableMapper.insertTable(sql, values.get(i));
            if(flag1 == 0){
                flag = 0;
                break;
            }
        }
        if(flag == 1)
            return 1;
        else
            return 0;
    }
}
