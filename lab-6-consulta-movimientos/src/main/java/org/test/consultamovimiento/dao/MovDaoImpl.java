package org.test.consultamovimiento.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.test.consultamovimiento.bean.MovAgrupProdAndPartnertsByMsisdn;
import org.test.consultamovimiento.jdbc.NamedParameterStatement;
import org.test.consultamovimiento.jdbc.SQLReader;
import org.test.consultamovimiento.jdbc.SqlUtils;
import org.test.consultamovimiento.utils.KyuwDateUtils;



@Repository
public class MovDaoImpl implements MovDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovDaoImpl.class);
	
	private static final String FILENAME_CONSULTA_MOVIMIENTOS_GET = "getAllConsultaMovimientos";
	private static final String FILENAME_GET_MSISDN_BY_IDCLI = "getMsisdnByIdcli";
	
	@Autowired
	DataSource dataSource;	
	
	public Collection<?> getCargosAndSuscripcionesTercerosByMsisdnAndDate(String msisdn, Date initdate, Date enddate) throws SQLException, IOException, ParseException {
		Connection conn = dataSource.getConnection();
		NamedParameterStatement namedParameterStatement = null;
		ResultSet rs = null;		
		List<MovAgrupProdAndPartnertsByMsisdn> list_movAgrupProdAndPartnertsByMsisdn = new ArrayList<MovAgrupProdAndPartnertsByMsisdn>();		
		
		try {
			final SQLReader sqlReader = new SQLReader();
			final String Query = sqlReader.getQuery(SQLReader.QUERY_FOLDER.ISTERCONSULTAMOVIMIENTOS, FILENAME_CONSULTA_MOVIMIENTOS_GET);			
			final String sQuery = SQLReader.getQueryWithPartitions(initdate, enddate, Query);	
			namedParameterStatement = new NamedParameterStatement(conn, sQuery);
			namedParameterStatement.setString("MSDIN", msisdn);
			namedParameterStatement.setDate("FECHA1", new java.sql.Date(initdate.getTime()));
			namedParameterStatement.setDate("FECHA2", new java.sql.Date(KyuwDateUtils.addOneDayToDate(enddate).getTime()));
		
			rs = namedParameterStatement.executeQuery();

			while (rs.next()) {
				MovAgrupProdAndPartnertsByMsisdn movAgrupProdAndPartnertsByMsisdn = new MovAgrupProdAndPartnertsByMsisdn();
				movAgrupProdAndPartnertsByMsisdn.setMsisdn(rs.getString("MSISDN"));
				movAgrupProdAndPartnertsByMsisdn.setMultisim(rs.getString("MULTISIM"));
				movAgrupProdAndPartnertsByMsisdn.setTimestamp(rs.getTimestamp("FECHA").toString());
				movAgrupProdAndPartnertsByMsisdn.setIdtrans(rs.getInt("IDTRANS"));
				movAgrupProdAndPartnertsByMsisdn.setImporte(rs.getDouble("IMPORTE"));
				movAgrupProdAndPartnertsByMsisdn.setAgrupadores(rs.getString("NAME"));
				movAgrupProdAndPartnertsByMsisdn.setProductName(rs.getString("PRODUCTO_NAME"));
				movAgrupProdAndPartnertsByMsisdn.setMarket(rs.getString("MARKET"));
				movAgrupProdAndPartnertsByMsisdn.setFlag_suscripcion(rs.getString("FLAG_SUSCRIPCION"));
				list_movAgrupProdAndPartnertsByMsisdn.add(movAgrupProdAndPartnertsByMsisdn);
				
				if(LOGGER.isDebugEnabled()) {
					LOGGER.debug("msisdn:{} | initdate:{} | enddate:{} | multisim:{} | fechaMov:{} | idtrans:{} | importe:{} | agrupadores:{} | productName:{} | market:{}", new Object[] {msisdn, initdate, enddate, rs.getString("MULTISIM"), rs.getTimestamp("FECHA").toString(), rs.getInt("IDTRANS"), rs.getDouble("IMPORTE"), rs.getString("NAME"), rs.getString("PRODUCTO_NAME"), rs.getString("MARKET")});
				}		 
			}		
		} finally {
			SqlUtils.closeResulSet(rs);
			SqlUtils.closeNamedParameterStatement(namedParameterStatement);
			conn.close();
		}		
		return list_movAgrupProdAndPartnertsByMsisdn;
	}


	@Override
	public List<String> getListMsisdn(String customer_id) throws SQLException, IOException {
		Connection conn = dataSource.getConnection();
		NamedParameterStatement namedParameterStatement = null;		
		ResultSet rs = null;
		List<String> listaMsisdn = new ArrayList<String>();			
		
		try {
			final SQLReader sqlReader = new SQLReader();
			final String sQuery = sqlReader.getQuery(SQLReader.QUERY_FOLDER.ISTERCONSULTAMOVIMIENTOS, FILENAME_GET_MSISDN_BY_IDCLI);			
			
			namedParameterStatement = new NamedParameterStatement(conn, sQuery);
			namedParameterStatement.setString("customer_id", customer_id);
			rs = namedParameterStatement.executeQuery();		
			while(rs.next()) {				
				listaMsisdn.add(rs.getString("MSISDN"));			
			}
		} finally {
			SqlUtils.closeResulSet(rs);
			SqlUtils.closeNamedParameterStatement(namedParameterStatement);
			conn.close();
		}
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("customer_id:{}", new Object[] {customer_id});
		}
		return listaMsisdn;
	}


	
	
	/*public static void main(String[] args) throws JsonProcessingException {
	
	ObjectMapper oMapper = new ObjectMapper();
	MovAgrupProdAndPartnertsByMsisdn isterDto = new MovAgrupProdAndPartnertsByMsisdn();
	
	//lo siguiente convierte un objeto java em un Ma
	//Map<String, Object> map = oMapper.convertValue(isterDto, Map.class);
	//System.out.println(map);
	
	
	//lo siguiente convierte un objeto java en Json
	String jsonInString = oMapper.writeValueAsString(isterDto);		
	System.out.println(jsonInString);
	
}*/
	
	//created method what build the query 
	
	
	
}
