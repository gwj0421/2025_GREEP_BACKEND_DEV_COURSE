package org.example.jpa;

import org.hibernate.resource.jdbc.spi.StatementInspector;

import java.util.ArrayList;
import java.util.List;

public class QueryCountInspector implements StatementInspector {
    //    private static final AtomicInteger queryCount = new AtomicInteger(0);
    private static List<String> EXECUTED_QUERIES = new ArrayList<>();

    public static List<String> getExecutedQueries() {
        return EXECUTED_QUERIES;
    }
    @Override
    public String inspect(String sql) {
        EXECUTED_QUERIES.add(sql);
        return sql;
    }

    public static void resetQueryCount() {
        EXECUTED_QUERIES = new ArrayList<>();
    }

    public static int getQueryCount() {
        return EXECUTED_QUERIES.size();
    }
}
