package com.plj.back.Controller;

import com.plj.back.Entities.Data;
import com.plj.back.Entities.DataTables;
import com.plj.back.Service.DataTablesService;
import com.plj.back.Service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/api/tables")
public class DataTablesController {
    @Autowired
    TableService tableService;

    @Autowired
    DataTablesService dataTablesService;

    @GetMapping("/getTableData")
    @ResponseBody
    public List<Map<String, Object>> getTableByName(String name) {
        return tableService.selectTableByName(name);
    }

    @PostMapping("/createTable")
    @ResponseBody
    public Integer createTable(@RequestBody Data data){
        // 插入信息表中
        DataTables dataTables = data.getDataTables();
        Integer flag1 = dataTablesService.addDataTables(dataTables);
        // Integer flag1 = 1;
        if(flag1 == 0) {
            return 0;
        } else {
            List<List<String>> allDatas = data.getDatas();
            List<String> colomn = allDatas.get(0);
            allDatas.remove(0);

            Integer flag2 = tableService.createTable(colomn, dataTables.getTitle());
            // Integer flag2 = 1;
            if(flag2 == 0) {
                return 0;
            } else {
                Integer flag3 = tableService.insertTable(dataTables.getTitle(), colomn, allDatas);
                if(flag3 == 0)
                    return 0;
                else
                    return 1;
            }
        }
    }

    @GetMapping("/getAllTables")
    @ResponseBody
    public List<DataTables> getAllTables() {
        return dataTablesService.selectAll();
    }
}
