package com.glotov.access;

import com.healthmarketscience.jackcess.Database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

public class TestAccess {

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        String dbName;
        if (args.length > 0) {
            dbName = args[0];
        } else {
            dbName = "bd.mdb";
        }
        printAllAccessTables(dbName);
        System.out.println(System.currentTimeMillis() - startTime);
    }

    private static void printAllAccessTables(String dbName) throws IOException {
        Database database = Database.open(new File(dbName));
        for (String tableName : database.getTableNames()) {
            System.out.println();
            System.out.println("=== TABLE NAME: " + tableName);
            System.out.println();
            int count = 0;
            for (Map<String, Object> row : database.getTable(tableName)) {
                System.out.print(count + "|   ");
                for (Map.Entry<String, Object> entry : row.entrySet()) {
                    System.out.print(entry.getKey() + ": " + entry.getValue() + "    ");
                }
                if (count > 50) {
                    System.out.println();
                    System.out.println("...");
                    break;
                }
                count++;
                System.out.println();
            }
        }
    }

    public static Connection getPostgreeConnection() {

        return null;
    }
}
