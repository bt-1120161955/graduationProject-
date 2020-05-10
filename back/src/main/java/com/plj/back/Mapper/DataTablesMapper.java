package com.plj.back.Mapper;

import com.plj.back.Entities.DataTables;
import com.plj.back.Entities.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.core.annotation.Order;

import java.util.List;

@Mapper
@Order(1)
public interface DataTablesMapper {
    @Select("select * from `datatables`")
    List<DataTables> selectAll();

    @Insert("insert into `datatables`(`title`, `createBy`, `createTime`)" + "values(#{title},#{createBy},#{createTime})")
    Integer addDataTables(DataTables dataTables);
}
