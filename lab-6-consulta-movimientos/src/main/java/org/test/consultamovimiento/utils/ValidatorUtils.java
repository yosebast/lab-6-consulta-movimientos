package org.test.consultamovimiento.utils;

import org.apache.commons.lang.StringUtils;
import org.test.consultamovimiento.exception.ValidationException;


public class ValidatorUtils {
	
	private static final String ERROR_MSISDN = "115, El parámetro msisdn es obligatorio";
	private static final String ERROR_FORMATO_MSISDN = "116, Formato de MSISDN no valido";
	private static final String ERROR_FORMATO_FECHAS = "103, El formato de la fecha introducida no es correcto (dd.MM.YYYY)";
		
	
	private ValidatorUtils(){
		throw new IllegalAccessError("Utility class");
	}
	
	/**
	 * 
	 * @param fieldName
	 * @param value
	 * @throws ValidationException
	 */
	public static void validateNotEmpty(final String fieldName, final String value) throws ValidationException {
		if (StringUtils.isEmpty(value)) {
			throw new ValidationException(createValidationExceptionMessageForMandatoryField(fieldName));
		}
	}	
	
	/**
	 * 
	 * @param field
	 * @param fieldName
	 * @param mandatory
	 * @throws ValidationException
	 */
	public static final void validaIfMsisdnIsCorrect(String field, String fieldName, final boolean mandatory) throws ValidationException {
		
		if(StringUtils.isEmpty(fieldName) && mandatory) {
			throw new ValidationException(createValidationExceptionMessageForMandatoryField(field));
		}else if(!StringUtils.isEmpty(fieldName)) {			
				//validaremos si el msisdn tiene el formato de orange correctamente
			try {
				Integer.parseInt(fieldName);
			} catch (Exception e) {
				throw new ValidationException(createValidationExceptionMessageForInvalidValueMsisdn());
			}		
		}		
	}
	
	/**
	 * 
	 * @param fieldName
	 * @param dateStr
	 * @param format
	 * @param mandatory
	 * @throws ValidationException
	 */
	public static void validateStringDate(final String fieldName, final String dateStr, final String format, final boolean mandatory) throws ValidationException {
		if (StringUtils.isEmpty(dateStr) && mandatory) {
			throw new ValidationException(createValidationExceptionMessageForMandatoryField(fieldName));
		} else if (!StringUtils.isEmpty(dateStr)) {
			try {
				KyuwDateUtils.obtainDateFromString(dateStr, format, true);
			} catch (final Exception e) {
				throw new ValidationException(createValidationExceptionMessageForInvalidValueDate());
			}
		}
	}	

	/**
	 * 
	 * @param fieldName
	 * @return
	 */
	private static String createValidationExceptionMessageForMandatoryField(String fieldName) {
		StringBuilder sb = new StringBuilder();
		return sb.append(ERROR_MSISDN).toString();
	}
	
	/**
	 * 
	 * @return
	 */
	private static String createValidationExceptionMessageForInvalidValueMsisdn() {
		StringBuilder sb = new StringBuilder();
		return sb.append(ERROR_FORMATO_MSISDN).toString();		
	}
	
	/**
	 * 
	 * @return
	 */
	private static String createValidationExceptionMessageForInvalidValueDate() {
		StringBuilder sb = new StringBuilder();
		return sb.append(ERROR_FORMATO_FECHAS).toString();		
	}	
}
	
	