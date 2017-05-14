package org.apache.fineract.organisation.b2binvoice.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.fineract.infrastructure.core.service.RoutingDataSource;
import org.apache.fineract.infrastructure.security.service.PlatformSecurityContext;
import org.apache.fineract.organisation.b2binvoice.data.B2BInvoiceDetailsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class B2BInvoiceReadPlatformServiceImpl implements B2BInvoiceReadPlatformService {

	private final PlatformSecurityContext context;
    private final JdbcTemplate jdbcTemplate;
    
    
    @Autowired
	public B2BInvoiceReadPlatformServiceImpl(final PlatformSecurityContext context, final RoutingDataSource dataSource) {
		
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}



	@Override
	public List<B2BInvoiceDetailsData> retriveB2BInvoiceDetailsData() {
       

        final B2BInvoiceDetailsDataMapper mapper = new B2BInvoiceDetailsDataMapper();
        String sql = "SELECT DISTINCT " + mapper.schema();


        return this.jdbcTemplate.query(sql, mapper, new Object[] {});
    }

	private static final class B2BInvoiceDetailsDataMapper implements RowMapper<B2BInvoiceDetailsData> {

        public String schema() {
            return " ind.id AS id, ind.GSTIN AS gstin, ind.ctin AS ctin, ind.action_flag AS actionFlag, ind.SUPPLIER_INV_NO AS supplierInvNo, " +
            	   " ind.SUPPLIER_INV_DATE AS supplierInvDate, ind.SUPPLIER_INV_VALUE AS supplierInvValue, ind.SUPPLY_PLACE AS supplyPlace, ind.IS_REVERSE AS isReverse, " +
                   " ind.IS_PROV_ASSESSMENT AS isProvAssessment, ind.ORDER_NO AS orderNo, ind.ORDER_DATE AS orderdate, ind.ETIN AS etin, ind.INVOICE_ID AS invoiceId " +
            	   " FROM GST_RT_INV_DLTS ind where ind.status = 0 ";
        }

        @Override
        public B2BInvoiceDetailsData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {
            
        	final Long id = rs.getLong("id");
        	final String gstin = rs.getString("gstin");
        	final String ctin = rs.getString("ctin");
        	final String actionFlag = rs.getString("actionFlag");
        	final String supplierInvNo = rs.getString("supplierInvNo");
        	final String supplierInvDate = rs.getString("supplierInvDate");
        	final Double supplierInvValue = rs.getDouble("supplierInvValue");
        	final String supplyPlace = rs.getString("supplyPlace");
        	final Boolean isReverse = rs.getBoolean("isReverse");
        	final Boolean isProvAssessment = rs.getBoolean("isProvAssessment");
        	final String orderNo = rs.getString("orderNo");
        	final String orderdate = rs.getString("orderdate");
        	final String etin = rs.getString("etin");
        	final Long invoiceId = rs.getLong("invoiceId");

            return new B2BInvoiceDetailsData(id, gstin, ctin, actionFlag, supplierInvNo, 
            		supplierInvDate, supplierInvValue, supplyPlace, isReverse, isProvAssessment, 
            		orderNo, orderdate, etin, invoiceId);
        }

    }
	
}
