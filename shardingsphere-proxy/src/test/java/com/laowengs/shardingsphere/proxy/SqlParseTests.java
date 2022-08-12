package com.laowengs.shardingsphere.proxy;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import org.apache.shardingsphere.sql.parser.api.CacheOption;
import org.apache.shardingsphere.sql.parser.api.SQLParserEngine;
import org.apache.shardingsphere.sql.parser.core.ParseASTNode;
import org.junit.jupiter.api.Test;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

public class SqlParseTests {
    @Test
    void name() {
        String sql = "select a+1 as b, name n from table1 join table2 where id=1 and name='lu’;";
        CacheOption cacheOption = new CacheOption(128, 1024L);
        SQLParserEngine parserEngine = new SQLParserEngine("MYSQL", cacheOption);
        ParseASTNode parseASTNode = parserEngine.parse(sql, false);
        System.out.println(parseASTNode);
    }

    @Test
    void sqlParse() {
        String sql = "select a+1 as b, name n from table1 join table2 where id=1 and name='lu’;";
        CacheOption cacheOption = new CacheOption(128, 1024L);
        SQLParserEngine parserEngine = new SQLParserEngine("MYSQL", cacheOption);
        ParseASTNode parseASTNode = parserEngine.parse(sql, false);
        System.out.println(parseASTNode);
    }

    static String dbType = "mysql";

    @Test
    void sqlParseByDruid() {
        String sql = "select  name ,id from acct where id =10";
        System.out.println("原始SQL 为 ： " + sql);
        List<SQLStatement> list = SQLUtils.parseStatements(sql, dbType);
        SQLSelectStatement statement = (SQLSelectStatement) list.get(0);
        SQLSelect select = statement.getSelect();
        SQLSelectQueryBlock query = (SQLSelectQueryBlock) select.getQuery();
        SQLExprTableSource tableSource = (SQLExprTableSource) query.getFrom();
        String tableName = tableSource.getExpr().toString();
        System.out.println("获取的表名为  tableName ：" + tableName);
        //修改表名为acct_1
        tableSource.setExpr("acct_1");
        System.out.println("修改表名后的SQL 为 ： [" + statement.toString() + "]");
    }


    @Test
    void sqlParseByDruidVisitor() {
        String sql = "select  name ,id ,select money from user  from acct where id =10";
        System.out.println("原始SQL 为 ： " + sql);
        List<SQLStatement> list = SQLUtils.parseStatements(sql, dbType);
        SQLSelectStatement statement = (SQLSelectStatement) list.get(0);
        MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
        statement.accept(visitor);
        System.out.println(visitor.getTables().toString());
    }
}
