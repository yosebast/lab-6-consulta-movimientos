package org.test.consultamovimiento.jdbc;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class SqlUtils {
    
	private SqlUtils() {
		throw new IllegalAccessError("Utils class");
	}
	
    public static void closeResulSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // No action required
            }
        }
    }
    
    public static void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
            	preparedStatement.close();
            } catch (SQLException e) {
                // No action required
            }
        }
    }
    
    public static void closeNamedParameterStatement(NamedParameterStatement namedParameterStatement){
        if(namedParameterStatement!=null){
        	try{
				namedParameterStatement.close();
			}catch (SQLException e){
				//No action required
			}
        }
	}
    
    public static String adaptToDynamicInClause(String sqlQuery, String textToReplace, int numParameters) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numParameters; i++) {
            builder.append("?,");
        }
        return sqlQuery.replace(textToReplace, builder.deleteCharAt(builder.length() - 1).toString());
    }
    
    public static java.sql.Date obtainSqlDate(Date date){
    	java.sql.Date sqlDate = null;
    	if(date!=null) {
    		sqlDate = new java.sql.Date(date.getTime());
    	}
    	return sqlDate;
    }
}
