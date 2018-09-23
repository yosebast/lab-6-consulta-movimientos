package org.test.consultamovimiento.controller;



import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.consultamovimiento.bean.IsterDTO;
import org.test.consultamovimiento.comun.DataComunTest;
import org.test.consultamovimiento.exception.MovException;
import org.test.consultamovimiento.service.MovService;




@RunWith(MockitoJUnitRunner.class)
public class ConsultaMovimientoControllerTest extends DataComunTest {

	@Mock
	MovService movService;
	
	@InjectMocks
	ConsultaMovimientoController consultaMovimientoController;
	
	String customer_id;
	String msisdn;
	String initdate;
	String enddate;
	
	@Override
	@Before
	public void setUp() {
		customer_id = "2397";
		msisdn = "691347667";
		initdate = "09.09.2018";
		enddate = "16.09.2018";		
	}	
	
	@Test	
	public void testGetCargosAndSuscripcionesTercerosByMsisdnAndDate() throws MovException {		
		doNothing().when(movService).validaExisteMsisdn(Mockito.anyString(), Mockito.anyString());
		when(movService.getCargosAndSuscripcionesTercerosByMsisdnAndDate(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(getIsterDto());
		Assert.assertNotNull(this.consultaMovimientoController.getCargosAndSuscripcionesTercerosByMsisdnAndDate(customer_id, msisdn, initdate, enddate));
		
	}
	
	private IsterDTO getIsterDto() {
		IsterDTO isterDTO = new IsterDTO();
		isterDTO.setError(null);
		isterDTO.setResponseData(getCollection());
		isterDTO.setMessage("ok");
	
		return isterDTO;
	}	

}
