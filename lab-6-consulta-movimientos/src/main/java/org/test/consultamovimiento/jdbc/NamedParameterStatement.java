package org.test.consultamovimiento.jdbc;
/**********************************************************************
 *  Copyright (c) 2012 - 2013, VLADIMIR All rights reserved
 * 
 *  Project: 
 *  Package: 
 *  File:    NamedParameterStatement.java
 *  User:    e019405
 *  Description: 
 * 
 *********************************************************************/


import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class NamedParameterStatement.
 */

public class NamedParameterStatement {
	
	/** The statement. */
	private final PreparedStatement statement;

    /** The index map. */
    @SuppressWarnings("rawtypes")
	private final Map indexMap;


    /**
	 * Instantiates a new named parameter statement.
	 * 
	 * @param connection
	 *            the connection
	 * @param query
	 *            the query
	 * @throws SQLException
	 *             the sQL exception
	 */
    @SuppressWarnings("rawtypes")
	public NamedParameterStatement(final Connection connection, final String query) throws SQLException {
        indexMap=new HashMap();
        String parsedQuery=parse(query, indexMap);
        statement=connection.prepareStatement(parsedQuery);
        statement.setFetchSize(1000);
    }


    /**
	 * Parses the.
	 * 
	 * @param query
	 *            the query
	 * @param paramMap
	 *            the param map
	 * @return the string
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	static final String parse(final String query, final Map paramMap) {
        // I was originally using regular expressions, but they didn't work well for ignoring
        // parameter-like strings inside quotes.
        int length=query.length();
        StringBuffer parsedQuery=new StringBuffer(length);
        boolean inSingleQuote=false;
        boolean inDoubleQuote=false;
        int index=1;

        for(int i=0;i<length;i++) {
            char c=query.charAt(i);
            if(inSingleQuote) {
                if(c=='\'') {
                    inSingleQuote=false;
                }
            } else if(inDoubleQuote) {
                if(c=='"') {
                    inDoubleQuote=false;
                }
            } else {
                if(c=='\'') {
                    inSingleQuote=true;
                } else if(c=='"') {
                    inDoubleQuote=true;
                } else if(c==':' && i+1<length 
                		&& Character.isJavaIdentifierStart(query.charAt(i+1))) {
                    int j=i+2;
                    while(j<length && Character.isJavaIdentifierPart(query.charAt(j))) {
                        j++;
                    }
                    String name=query.substring(i+1,j);
                    c='?'; // replace the parameter with a question mark
                    i+=name.length(); // skip past the end if the parameter

                    List indexList=(List)paramMap.get(name);
                    if(indexList==null) {
                        indexList=new LinkedList();
                        paramMap.put(name, indexList);
                    }
                    indexList.add(Integer.valueOf(index));

                    index++;
                }
            }
            parsedQuery.append(c);
        }

        // replace the lists of Integer objects with arrays of ints
        for(Iterator itr=paramMap.entrySet().iterator(); itr.hasNext();) {
            Map.Entry entry=(Map.Entry)itr.next();
            List list=(List)entry.getValue();
            int[] indexes=new int[list.size()];
            int i=0;
            for(Iterator itr2=list.iterator(); itr2.hasNext();) {
                Integer x=(Integer)itr2.next();
                indexes[i++]=x.intValue();
            }
            entry.setValue(indexes);
        }

        return parsedQuery.toString();
    }


    /**
	 * Gets the indexes.
	 * 
	 * @param name
	 *            the name
	 * @return the indexes
	 */
    private int[] getIndexes(final String name) {
        int[] indexes=(int[])indexMap.get(name);
        if(indexes==null) {
            throw new IllegalArgumentException("Parameter not found: "+name);
        }
        return indexes;
    }


    /**
	 * Sets the object.
	 * 
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @throws SQLException
	 *             the sQL exception
	 */
    public void setObject(final String name, final Object value) throws SQLException {
        int[] indexes=getIndexes(name);
        for(int i=0; i < indexes.length; i++) {
            statement.setObject(indexes[i], value);
        }
    }


    public void setNull(final String name) throws SQLException{
    	int[] indexes=getIndexes(name);
    	for(int i=0; i < indexes.length; i++) {
            statement.setNull(indexes[i], Types.NULL);
        }
    }
    
    /**
	 * Sets the string.
	 * 
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @throws SQLException
	 *             the sQL exception
	 */
    public void setString(final String name, final String value) throws SQLException {
        int[] indexes=getIndexes(name);
        for(int i=0; i < indexes.length; i++) {
            statement.setString(indexes[i], value);
        }
    }


    /**
	 * Sets the int.
	 * 
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @throws SQLException
	 *             the sQL exception
	 */
    public void setInt(final String name, final int value) throws SQLException {
        int[] indexes=getIndexes(name);
        for(int i=0; i < indexes.length; i++) {
            statement.setInt(indexes[i], value);
        }
    }


    /**
	 * Sets the long.
	 * 
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @throws SQLException
	 *             the sQL exception
	 */
    public void setLong(final String name, final long value) throws SQLException {
        int[] indexes=getIndexes(name);
        for(int i=0; i < indexes.length; i++) {
            statement.setLong(indexes[i], value);
        }
    }
    
    public void setFloat(final String name, final float value) throws SQLException {
    	int[] indexes=getIndexes(name);
        for(int i=0; i < indexes.length; i++) {
            statement.setFloat(indexes[i], value);
        }
    }


    /**
	 * Sets the timestamp.
	 * 
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @throws SQLException
	 *             the sQL exception
	 */
    public void setTimestamp(final String name, final Timestamp value) throws SQLException {
        int[] indexes=getIndexes(name);
        for(int i=0; i < indexes.length; i++) {
            statement.setTimestamp(indexes[i], value);
        }
    }

    /**
	 * Sets the date.
	 * 
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @throws SQLException
	 *             the sQL exception
	 */
    public void setDate(final String name, final Date value) throws SQLException {
        int[] indexes=getIndexes(name);
        for(int i=0; i < indexes.length; i++) {
            statement.setDate(indexes[i], value);
        }
    }
    
    /**
	 * Sets the array.
	 * 
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @throws SQLException
	 *             the sQL exception
	 */
    public void setArray(final String name, final Array value) throws SQLException {
        int[] indexes=getIndexes(name);
        for(int i=0; i < indexes.length; i++) {
            statement.setArray(indexes[i], value);
        }
    }

    /**
	 * Gets the statement.
	 * 
	 * @return the statement
	 */
    public PreparedStatement getStatement() {
        return statement;
    }


    /**
	 * Execute.
	 * 
	 * @return true, if successful
	 * @throws SQLException
	 *             the sQL exception
	 */
    public boolean execute() throws SQLException {
        return statement.execute();
    }


    /**
	 * Execute query.
	 * 
	 * @return the result set
	 * @throws SQLException
	 *             the sQL exception
	 */
    public ResultSet executeQuery() throws SQLException {
        return statement.executeQuery();
    }


    /**
	 * Execute update.
	 * 
	 * @return the int
	 * @throws SQLException
	 *             the sQL exception
	 */
    public int executeUpdate() throws SQLException {
        return statement.executeUpdate();
    }


    /**
	 * Close.
	 * 
	 * @throws SQLException
	 *             the sQL exception
	 */
    public void close() throws SQLException {
        statement.close();
    }


    /**
	 * Adds the batch.
	 * 
	 * @throws SQLException
	 *             the sQL exception
	 */
    public void addBatch() throws SQLException {
        statement.addBatch();
    }


    /**
	 * Execute batch.
	 * 
	 * @return the int[]
	 * @throws SQLException
	 *             the sQL exception
	 */
    public int[] executeBatch() throws SQLException {
        return statement.executeBatch();
    }
    
    public void clearBatch() throws SQLException{
        statement.clearBatch();
    }
    
    public void clearParameters() throws SQLException{
        statement.clearParameters();
    }
    
    
}
