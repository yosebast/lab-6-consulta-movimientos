package org.test.consultamovimiento.comun;

import java.util.ArrayList;
import java.util.List;

import org.test.consultamovimiento.bean.MovAgrupProdAndPartnertsByMsisdn;

public abstract class DataComunTest {

	
	public List<MovAgrupProdAndPartnertsByMsisdn> getCollection() {	
		List<MovAgrupProdAndPartnertsByMsisdn> listMovAgrupProdAndPartnertsByMsisdn = new ArrayList<MovAgrupProdAndPartnertsByMsisdn>();	
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
		listMovAgrupProdAndPartnertsByMsisdn.add(movAgrupProdAndPartnertsByMsisdn);
		
		return listMovAgrupProdAndPartnertsByMsisdn;
	}
	
	public abstract void setUp();
	
}
