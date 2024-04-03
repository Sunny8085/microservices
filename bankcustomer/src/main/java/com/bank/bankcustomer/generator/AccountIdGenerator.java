package com.bank.bankcustomer.generator;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

@Component
public class AccountIdGenerator implements IdentifierGenerator{
	private static final long serialVersionUID = 1L;
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) {
		String yearMonth = new SimpleDateFormat("YYYYMM").format(new Date());
        long randomEightDigitNumber = (long) (Math.random() * 90000000L) + 10000000L;
        BigInteger accountId = new BigInteger(yearMonth.substring(2,6) + randomEightDigitNumber);
		return accountId;
	}
	
}
