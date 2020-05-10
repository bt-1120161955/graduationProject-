package com.plj.back.Mapper;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.plj.back.Entities.User;
import org.apache.ibatis.annotations.*;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
@Order(1)
public interface TableMapper {
//    @SelectProvider(type = sqlBuild.class, method = "selectTableByName")
//    List<String> selectTableByName(String tableName);
//


    @Select("select * from ${tableName}")
    List<Map<String, Object>> selectTableByName(@Param("tableName") String tableName);

    @SelectProvider(type = sqlBuild.class, method = "createTable")
    Integer createTable(List<String> colomnName, String tableName);

    @InsertProvider(type = sqlBuild.class, method = "insertTable")
    Integer insertTable(String sql, List<String> values);

    class sqlBuild{
        public String createTable(final List<String> colomnName, final String tableName) {
            String sql = "";
            sql += "CREATE TABLE " + tableName + "(";
            for(int i=0; i<colomnName.size(); i++) {
                if( i == 0){
                    sql = sql + "`" + colomnName.get(i) + "` " + "varchar(255)";
                } else {
                    sql = sql + ", " + "`" + colomnName.get(i) + "` " + "varchar(255)";
                }
            }
            sql += ")character set = utf8;";

            return sql;
        }

        public String insertTable(final String sql, final List<String> values) {
            String sqlFinall = sql;
            sqlFinall += " VALUES(";
            for(int i=0; i < values.size(); i++) {
                if(i == 0) {
                    sqlFinall = sqlFinall + "\"" + values.get(i) + "\"";
                } else {
                    sqlFinall = sqlFinall + ",\"" + values.get(i) + "\"";
                }
            }
            sqlFinall += ")";

            return sqlFinall;
        }
    }
}
