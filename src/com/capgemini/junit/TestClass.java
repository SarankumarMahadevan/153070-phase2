package com.capgemini.junit;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.capgemini.bean.Customer;
import com.capgemini.bean.Wallet;
import com.capgemini.service.WalletService;
import com.capgemini.service.WalletServiceImpl;
import com.capgemini.exception.InvalidInputException;

public class TestClass {

	WalletService service;
	Customer cust1,cust2,cust3;	
	Connection con;
	@Before
	public void initData(){
		service=new WalletServiceImpl();
		try{
           
			String url ="jdbc:mysql://localhost:3306/test";
			String uld ="root";
			String pwd="corp123";
			this.con=DriverManager.getConnection(url,uld,pwd);
			}catch(SQLException e){
				e.printStackTrace();
			}
		String query = "insert into Clei(name,mobileno,balance) values(Sanjay,9900346783,9000)";
		String query1 = "insert into Clei(name,mobileno,balance) values(Saran,9354466662,6000)";
		String query2 = "insert into Clei(name,mobileno,balance) values(Senthil,8907896756,7000)";
		try {
			con.setAutoCommit(false);
			java.sql.Statement stmt1= con.createStatement();
			stmt1.execute(query);
			stmt1.execute(query1);
			stmt1.execute(query2);
			con.commit();
		}catch(SQLException e){
			e.printStackTrace();
			}}
			
		@Test(expected=NullPointerException.class)
		public void testCreateAccount() {
			
			service.createAccount(null,null,null);
			
			
		}
		@Test
		public void testCreateAccount1() {
			
			
			Customer c=new Customer();
			Customer cust=new Customer();
			cust=service.createAccount("Saran","9790945652",new BigDecimal(7000));
			c.setMobileNo("9790945652");
			c.setName("Saran");
			c.setWallet(new Wallet(new BigDecimal(7000)));
			Customer actual =c;
			Customer expected=cust;
			assertEquals(expected, actual);
		
			
			
		}
	@Test	
	public void testCreateAccount2() {
			
			
			
			Customer cust=new Customer();
			cust=service.createAccount("Sanjay","5678934567",new BigDecimal(7000));
			assertEquals("Sanjay", cust.getName());
		
			
			
		}
	@Test
	public void testCreateAccount3() {
		
		Customer cust=new Customer();
		cust=service.createAccount("Raji","9955112213",new BigDecimal(7000));
		assertEquals("9955112213", cust.getMobileNo());

		
		
	}


	@Test(expected=InvalidInputException.class)
	public void testShowBalance() {
		Customer cust=new Customer();
	cust=service.showBalance("9579405744");

	}

	@Test
	public void testShowBalance2() {
		
		Customer cust=new Customer();
		
	cust=service.showBalance("8907896756");
	assertEquals(cust, cust3);

	}
	@Test
	public void testShowBalance3() {
		
		Customer cust=new Customer();
	cust=service.showBalance("8907896756");
	BigDecimal actual=cust.getWallet().getBalance();
	BigDecimal expected=new BigDecimal(9000);
	assertEquals(expected, actual);

	}

	@Test(expected=InvalidInputException.class)
	public void testFundTransfer() {
		service.fundTransfer(null, null,new BigDecimal(7000));
	}

	@Test
	public void testFundTransfer2() {
		cust1=service.fundTransfer("9900112212","9963242422",new BigDecimal(2000));
		BigDecimal actual=cust1.getWallet().getBalance();
		BigDecimal expected=new BigDecimal(8000);
		assertEquals(expected, actual);
	}
	@Test(expected=InvalidInputException.class)
	public void testDeposit()
	{
		service.depositAmount("900000000", new BigDecimal(2000));
	}
		
	@Test
	public void testDeposit2()
	{
		cust1=service.depositAmount("9963242422", new BigDecimal(2000));
		BigDecimal actual=cust1.getWallet().getBalance();
		BigDecimal expected=new BigDecimal(8000);
		assertEquals(expected, actual);
	}
	@Test(expected=InvalidInputException.class)
	public void testWithdraw()
	{
		service.withdrawAmount("900000000", new BigDecimal(2000));
	}
		
	@Test
	public void testWithdraw2()
	{
		cust1=service.withdrawAmount("9963242422", new BigDecimal(2000));
		BigDecimal actual=cust1.getWallet().getBalance();
		BigDecimal expected=new BigDecimal(4000);
		assertEquals(expected, actual);
	}	
	@Test
	public void TestValidate()
	{
		Customer customer=new Customer("Raji","8796543210",new Wallet(new BigDecimal(10)));
		service.acceptCustomerDetails(customer);
	}

	@After
	public void testAfter()
	{
		service=null;
	}

	}


