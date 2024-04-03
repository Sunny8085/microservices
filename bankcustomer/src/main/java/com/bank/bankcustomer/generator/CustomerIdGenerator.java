package com.bank.bankcustomer.generator;


import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerIdGenerator implements IdentifierGenerator{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private DataSource dataSource;
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			String yearMonth = new SimpleDateFormat("YYYYMM").format(new Date());
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT nextval('customer_id_seq')");
			if(resultSet.next()) {
				BigInteger id = resultSet.getBigDecimal(1).toBigInteger();
				BigInteger customerId = new BigInteger(yearMonth.substring(2, 6) + id.toString());
				return customerId;
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try {
					if (resultSet != null) resultSet.close();
			        if (statement != null) statement.close();
			        if (connection != null) connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return null;
	}
	
	
}
