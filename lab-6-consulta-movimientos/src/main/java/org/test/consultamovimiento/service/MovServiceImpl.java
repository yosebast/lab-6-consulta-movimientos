package org.test.consultamovimiento.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.consultamovimiento.bean.IsterDTO;
import org.test.consultamovimiento.dao.MovDao;
import org.test.consultamovimiento.exception.MovException;
import org.test.consultamovimiento.utils.KyuwDateUtils;

@Service
public class MovServiceImpl implements MovService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovServiceImpl.class);
	
	private static final String ERROR_MSISDN_NO_ENCONTRADO = "600, El MSISDN informado no existe para el Customer_ID informado en la BBDD";
	private static final String ERROR_PARSEO_FECHAS = "124, No se han introducido correctamente las fechas Fecha_inicial y Fecha_final";
	private static final String ERROR_BBDD = "118, Error al recuperar los datos de la bbdd";	
	
	@Autowired
	MovDao movDao;	
	
	@Override
	public IsterDTO getCargosAndSuscripcionesTercerosByMsisdnAndDate(String msisdn, String initdate, String enddate) throws MovException {		
		final IsterDTO isterDto = toIsterDTO();
		try {		
			//add formatter to date			
			Date init_date = KyuwDateUtils.obtainDateFromString(initdate, KyuwDateUtils.FORMAT_ISTER_DDMMYYYY, true);
			Date end_date = KyuwDateUtils.obtainDateFromString(enddate, KyuwDateUtils.FORMAT_ISTER_DDMMYYYY, true);			
			isterDto.setResponseData(movDao.getCargosAndSuscripcionesTercerosByMsisdnAndDate(msisdn, init_date, end_date));
		} catch (SQLException e ) {
			LOGGER.error("MovServiceImpl - getCargosAndSuscripcionesTercerosByMsisdnAndDate:{}", e.getMessage());
			throw new MovException(ERROR_BBDD);
		} catch (ParseException e) {			
			LOGGER.error("MovServiceImpl - getCargosAndSuscripcionesTercerosByMsisdnAndDate:{}", e.getMessage());
			throw new MovException(ERROR_PARSEO_FECHAS);
		} catch (IOException e) {
			LOGGER.error("MovServiceImpl - getCargosAndSuscripcionesTercerosByMsisdnAndDate:{}", e.getMessage());
			throw new MovException(ERROR_PARSEO_FECHAS);//ESTO HAY QUE AGREAGAR UN TIPO DE ERROR ioexception de que no encuentra la ruta
		}		
		return isterDto;
	}
	
	private IsterDTO toIsterDTO() {
		IsterDTO isterDTO = new IsterDTO();		
		return isterDTO;
	}	

	@Override
	public void validaExisteMsisdn(String customer_id, String msisdn) throws MovException {
		List<String> listMsisdn;
		boolean existeMsisdn = false;
		try {
			listMsisdn = movDao.getListMsisdn(customer_id);
			// verificamos si existe el msisdn que hemos recibido
			if (listMsisdn != null && !listMsisdn.isEmpty()) {
				for (String smsisdn : listMsisdn) {
					if (smsisdn.equalsIgnoreCase(msisdn)) {
						existeMsisdn = true;
						break;
					}
				}
			}

			if (!existeMsisdn) {
				throw new MovException(ERROR_MSISDN_NO_ENCONTRADO);
			}

		} catch (SQLException e) {
			LOGGER.error("MovServiceImpl - getListMsisdn:{}", e.getMessage());
			throw new MovException(ERROR_BBDD);
		} catch (IOException e) {
			LOGGER.error("MovServiceImpl - getListMsisdn:{}", e.getMessage());
			throw new MovException(ERROR_PARSEO_FECHAS);//ESTO HAY QUE AGREAGAR UN TIPO DE ERROR ioexception de que no encuentra la ruta
		}
	}
	
/*	private IsterDTO createdMessageError(String messageException) {		
		IsterDTO isterDTO = new IsterDTO();
		isterDTO.setHasError(true);
		isterDTO.setMessage(messageException);
		return isterDTO;
	}*/
}
