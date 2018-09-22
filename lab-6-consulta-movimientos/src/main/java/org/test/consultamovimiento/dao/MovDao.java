package org.test.consultamovimiento.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.test.consultamovimiento.bean.MovAgrupProdAndPartnertsByMsisdn;
import org.test.consultamovimiento.exception.PartitionException;


public interface MovDao {	
	List<MovAgrupProdAndPartnertsByMsisdn> getCargosAndSuscripcionesTercerosByMsisdnAndDate(String msisdn, Date initdate, Date enddate) throws SQLException, IOException, ParseException, PartitionException;
	List<String> getListMsisdn(String customer_id) throws SQLException, IOException;
}
