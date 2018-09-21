package org.test.consultamovimiento.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.test.consultamovimiento.bean.IsterDTO;
import org.test.consultamovimiento.bean.ResponseError;
import org.test.consultamovimiento.exception.MovException;
import org.test.consultamovimiento.exception.ValidationException;
import org.test.consultamovimiento.service.MovService;
import org.test.consultamovimiento.utils.KyuwDateUtils;
import org.test.consultamovimiento.utils.StringConstants;
import org.test.consultamovimiento.utils.ValidatorUtils;

@RestController
public class ConsultaMovimientoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaMovimientoController.class);		
	
	@Autowired
	MovService movService;	
	
	@GetMapping("movimientos/{Customer_id}/{MSISDN}/{Fecha_inicial}/{Fecha_final}")
	public IsterDTO getCargosAndSuscripcionesTercerosByMsisdnAndDate(@PathVariable("Customer_id") String customer_id, @PathVariable("MSISDN") String msisdn, @PathVariable("Fecha_inicial") String initdate, @PathVariable("Fecha_final") String enddate) {
			LOGGER.debug("Execute getCargosAndSuscripcionesTercerosByMsisdnAndDate [customer_id={}, msisdn={}, initdate={}, enddate={}]", new Object[] {customer_id, msisdn, initdate, enddate});
			IsterDTO obj_isterDto = null;			
		try {
			//validamos los datos de entrada
			ValidatorUtils.validaIfMsisdnIsCorrect(StringConstants.MSISDN, msisdn, true);
			ValidatorUtils.validateStringDate(StringConstants.MSISDN, initdate, KyuwDateUtils.FORMAT_ISTER_DDMMYYYY, true);
			ValidatorUtils.validateStringDate(StringConstants.MSISDN, enddate, KyuwDateUtils.FORMAT_ISTER_DDMMYYYY, true);			
			//buscamos si existe el msisdn informado para el customer_id informado		
			 movService.validaExisteMsisdn(customer_id, msisdn);			
			 obj_isterDto = movService.getCargosAndSuscripcionesTercerosByMsisdnAndDate(msisdn, initdate, enddate);
			
		} catch (MovException | ValidationException e) {		
			obj_isterDto = createdMessageError(e.getMessage());			
		}
		return obj_isterDto;
	}
	
	private IsterDTO createdMessageError(String messageException) {
		IsterDTO isterDTO = new IsterDTO();
		String code = messageException.substring(0, messageException.indexOf(StringConstants.COMMA)).trim();
		String message = messageException.substring(messageException.indexOf(StringConstants.COMMA)+1, messageException.length()).trim();
		ResponseError responseError = new ResponseError();
		if (!StringUtils.isEmpty(code)) {
			responseError.setCode(Integer.parseInt(code));
		}
		if (!StringUtils.isEmpty(message)) {
			responseError.setMessage(message);
		}
		isterDTO.setError(responseError);
		return isterDTO;
	}
}
