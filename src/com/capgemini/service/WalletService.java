package com.capgemini.service;

import java.math.BigDecimal;

import com.capgemini.bean.Customer;

public interface WalletService {
	public Customer createAccount(String name ,String mobileno, BigDecimal amount);
	public Customer showBalance (String mobileno);
	public void acceptCustomerDetails(Customer cust);
	public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, BigDecimal amount);
	public Customer depositAmount (String mobileNo,BigDecimal amount );
	public Customer withdrawAmount(String mobileNo, BigDecimal amount);
}
