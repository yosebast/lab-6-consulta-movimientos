package org.test.consultamovimiento.service;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.consultamovimiento.bean.IsterDTO;
import org.test.consultamovimiento.comun.DataComunTest;
import org.test.consultamovimiento.dao.MovDao;
import org.test.consultamovimiento.exception.MovException;
import org.test.consultamovimiento.exception.PartitionException;

@RunWith(MockitoJUnitRunner.class)
public class MovServiceImplTest extends DataComunTest {
	
	@Mock
	MovDao movDao;
	
	@InjectMocks
	MovServiceImpl movServiceImpl;
	
	String customer_id;
	String msisdn;
	String initdate;
	String enddate;
	IsterDTO isterDto;
	
	@Before
	public void setUp() {		
		msisdn = "691347667";
		initdate = "09.09.2018";
		enddate = "16.09.2018";
		isterDto = new IsterDTO();
	}
	
	
	@Test	
	public void testGetCargosAndSuscripcionesTercerosByMsisdnAndDate() throws MovException, SQLException, IOException, ParseException, PartitionException {
		when(movDao.getCargosAndSuscripcionesTercerosByMsisdnAndDate(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn(getCollection());		
		isterDto.setResponseData(movDao.getCargosAndSuscripcionesTercerosByMsisdnAndDate(Mockito.anyString(), Mockito.any(), Mockito.any()));		
		this.movServiceImpl.getCargosAndSuscripcionesTercerosByMsisdnAndDate(msisdn, initdate, enddate);		
	}
	
	@Test(expected=MovException.class)
	public void testExistMsisdnError() throws MovException, SQLException, IOException, ParseException, PartitionException   {
		when(movDao.getCargosAndSuscripcionesTercerosByMsisdnAndDate(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn(getCollection());
	 doThrow(MovException.class).when(movDao).getCargosAndSuscripcionesTercerosByMsisdnAndDate(Mockito.anyString(), Mockito.any(), Mockito.any());
	 this.movServiceImpl.getCargosAndSuscripcionesTercerosByMsisdnAndDate(msisdn, initdate, enddate);
	
	}
	
	@Test	
	public void testValidaExisteMsisdn() throws MovException, SQLException, IOException {
		when(movDao.getListMsisdn(Mockito.anyString())).thenReturn(getListMsisdn());
		this.movServiceImpl.validaExisteMsisdn(customer_id, msisdn);		
	}
	
	private List<String> getListMsisdn() {		
		List<String> lista = Arrays.asList(msisdn);		
		return lista;
	}	
	
}
