package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;

public class DAOUtility {
    
    public static final int TERMID_FA24 = 1;
    
    public static String getResultSetAsJson(ResultSet rs){
        
        JsonArray records = new JsonArray();
        
        try {
        
            if (rs != null) {

               // get metadata
                ResultSetMetaData rsmd = rs.getMetaData();

                // iterating through result set
                while (rs.next()) {
                    
                    // initializing jsonObject
                    JsonObject row = new JsonObject();
                    
                    // iterating through each column
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        
                        // getting name and value
                        String name = rsmd.getColumnName(i);
                        Object value = rs.getObject(i).toString();
                        
                        // put into jsonObject
                        row.put(name, value);
                    }
                    
                    // Add object to the jsonArray
                    records.add(row);
                }
            
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return Jsoner.serialize(records);
        
    }
    
}
