package org.test.consultamovimiento.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.List;


public interface MovDao {	
	Collection<?> getCargosAndSuscripcionesTercerosByMsisdnAndDate(String msisdn, Date initdate, Date enddate) throws SQLException, IOException, ParseException;
	List<String> getListMsisdn(String customer_id) throws SQLException, IOException;
}
