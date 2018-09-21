package org.test.controller;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import org.test.consultamovimiento.bean.IsterDTO;
import org.test.consultamovimiento.bean.MovAgrupProdAndPartnertsByMsisdn;
import org.test.consultamovimiento.controller.ConsultaMovimientoController;
import org.test.consultamovimiento.exception.MovException;
import org.test.consultamovimiento.service.MovService;




@RunWith(MockitoJUnitRunner.class)
public class ConsultaMovimientoControllerTest {

	@Mock
	MovService movService;
	
	@InjectMocks
	ConsultaMovimientoController consultaMovimientoController;
	
	String customer_id;
	String msisdn;
	String initdate;
	String enddate;
	
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
	
	private Collection<?> getCollection() {	
		List<MovAgrupProdAndPartnertsByMsisdn> list_movAgrupProdAndPartnertsByMsisdn = new ArrayList<MovAgrupProdAndPartnertsByMsisdn>();	
		MovAgrupProdAndPartnertsByMsisdn movAgrupProdAndPartnertsByMsisdn = new MovAgrupProdAndPartnertsByMsisdn();
		movAgrupProdAndPartnertsByMsisdn.setAgrupadores("agrupadores");
		movAgrupProdAndPartnertsByMsisdn.setFlag_suscripcion("1");
		movAgrupProdAndPartnertsByMsisdn.setIdtrans(2);
		movAgrupProdAndPartnertsByMsisdn.setImporte(2.3);
		movAgrupProdAndPartnertsByMsisdn.setMarket("market");
		movAgrupProdAndPartnertsByMsisdn.setMsisdn("12345678");
		movAgrupProdAndPartnertsByMsisdn.setMultisim("123");
		movAgrupProdAndPartnertsByMsisdn.setProductName("netflix");
		movAgrupProdAndPartnertsByMsisdn.setTimestamp("09/09/18");
		list_movAgrupProdAndPartnertsByMsisdn.add(movAgrupProdAndPartnertsByMsisdn);
		
		return list_movAgrupProdAndPartnertsByMsisdn;
	}	
	
}
