package org.test.consultamovimiento.service;

import org.test.consultamovimiento.bean.IsterDTO;
import org.test.consultamovimiento.exception.MovException;

public interface MovService {	
	IsterDTO getCargosAndSuscripcionesTercerosByMsisdnAndDate(final String msisdn, final String initdate, final String enddate) throws MovException;
	void validaExisteMsisdn(String customer_id, String msisdn) throws MovException;
}
