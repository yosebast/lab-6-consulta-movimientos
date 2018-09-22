/**********************************************************************
 *  Copyright (c) 2012 - 2013, VLADIMIR All rights reserved
 *
 *  Project: Ister
 *  Package: com.orange.jdbc
 *  File:    SQLReader.java
 *  User:    e019405
 *  Description:
 *
 *********************************************************************/
package org.test.consultamovimiento.jdbc;


import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.test.consultamovimiento.utils.KyuwDateUtils;
import org.test.consultamovimiento.utils.StringConstants;

/**
 * The Class SQLReader.
 */

@Controller
public class SQLReader {

	/** The Constant SQL_EXTENSION. */
	private static final String SQL_EXTENSION = ".sql";
	/** The Constant QUERYS_FOLDER. */
	private static final String QUERYS_FOLDER = "/com/orange/query/";	
	private static final String ISTERCONSULTAMOVIMIENTOS_FOLDER = "consultaMovimientos/";	
	

	/**
	 * The Enum QUERY_FOLDER.
	 */
	public enum QUERY_FOLDER {
		ISTERCONSULTAMOVIMIENTOS			
	}

	/**
	 * Gets the query.
	 *
	 * @param folder
	 *            the folder
	 * @param filename
	 *            the filename
	 * @return the query
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ParseException 
	 */		
	public String getQuery(final QUERY_FOLDER folder, final String filename)
			throws IOException {		
		String everything;
		
		final String strFolder = getQueryFolder(folder);		
		final String resourcePath = QUERYS_FOLDER + strFolder + filename + SQL_EXTENSION;
		final InputStream inputStream = this.getClass().getResourceAsStream(resourcePath);
		if(inputStream==null) {
			throw new IOException("Can't find " + resourcePath);
		}
		everything = IOUtils.toString(inputStream, "UTF-8");
		inputStream.close();		
		return everything;
	}	

	/**
	 * Gets the query folder.
	 *
	 * @param folder
	 *            the folder
	 * @return the query folder
	 */
	private String getQueryFolder(final QUERY_FOLDER folder) {
		String value;

		switch (folder) {
		case ISTERCONSULTAMOVIMIENTOS:
			value = ISTERCONSULTAMOVIMIENTOS_FOLDER;
			break;		
		default:
			value = "";
			break;
		}
		return value;
	}
	
	/**
	 * 
	 * @param initdate
	 * @param enddate
	 * @param everything
	 * @return
	 * @throws ParseException
	 */
	public static String getQueryWithPartitions(Date initdate, Date enddate, String everything) throws ParseException {	
		
		SimpleDateFormat formatter = new SimpleDateFormat(KyuwDateUtils.FORMAT_ISTER_DDMMYYYY);
		List<String> dates = new ArrayList<String>();
		Calendar cini = Calendar.getInstance();
		Calendar cfin = Calendar.getInstance();		
		cini.setTime(initdate);
		cfin.setTime(enddate);
		getListMonday(cini);

		while (!cfin.before(cini)) {
			dates.add(formatter.format(cini.getTime()));
			cini.add(Calendar.DATE, 7); // Siguiente domingo
		}

		// ahora obtengo el siguiente domingo a la fecha final
		getListMonday(cfin);
		dates.add(formatter.format(cfin.getTime()));	
		// obtenemos una nueva lista con los formatos correctos
		List<String> newFechas = new ArrayList<String>();
		for (String string : dates) {
			newFechas.add(KyuwDateUtils.obtainNewFormat(string, KyuwDateUtils.FORMAT_ISTER_DDMMYYYY, KyuwDateUtils.FORMAT_YYYYMMDD));
		}
		String query = getAllPartitions(newFechas);
		return everything.replace(StringConstants.ALL_PARTITIONS, query).toUpperCase();
	}

	/**
	 * 
	 * @param date
	 */
	private static void getListMonday(Calendar date) {

		int number_day = date.get(Calendar.DAY_OF_WEEK);
		int getDaysUntilMonday = Calendar.SUNDAY - number_day; // dias que faltan para el primer domingo.
		if (getDaysUntilMonday <= 0) {
			getDaysUntilMonday += 7;
		}
		date.add(Calendar.DATE, getDaysUntilMonday); // cini está en el primer domingo
	}

	/**
	 * 
	 * @param fechas
	 * @return
	 */
	private static String getAllPartitions(List<String> fechas) {

		StringBuilder sb = new StringBuilder();
		StringBuilder SQuery = null;		
		int i = 0;
		for (String string : fechas) {
			i++;
			SQuery = (fechas.size() != i)
					? sb.append(StringConstants.SELECT).append(StringConstants.SPACE).append(StringConstants.STAR_KEY)
							.append(StringConstants.SPACE).append(StringConstants.FROM).append(StringConstants.SPACE)
							.append(StringConstants.USUARIO_TABLA_PARTITIONS).append(string).append(StringConstants.ROUND_BRACKETS).append(StringConstants.SPACE)
							.append(StringConstants.UNION_ALL).append(StringConstants.SPACE)
					: sb.append(StringConstants.SELECT).append(StringConstants.SPACE).append(StringConstants.STAR_KEY)
							.append(StringConstants.SPACE).append(StringConstants.FROM).append(StringConstants.SPACE)
							.append(StringConstants.USUARIO_TABLA_PARTITIONS).append(string).append(StringConstants.ROUND_BRACKETS);
		}		
		return SQuery.toString();
	}
}
