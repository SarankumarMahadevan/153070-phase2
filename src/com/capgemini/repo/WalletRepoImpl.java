package com.capgemini.repo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.capgemini.bean.Customer;
import com.capgemini.exception.InvalidInputException;


public class WalletRepoImpl implements WalletRepo{
	
	Connection con;
	{
		String url ="jdbc:mysql://localhost:3306/test";
		String uld ="root";
		String pwd="corp123";
		try{
			this.con=DriverManager.getConnection(url,uld,pwd);
			}catch(SQLException e){
				e.printStackTrace();
			}
	}
	

	@Override
	public boolean save(Customer customer) {
		if(findOne(customer.getMobileNo())==null)
		{
			String query="insert into Clei(name,mobileno,balance) values(?,?,?)";
			try {
				con.setAutoCommit(false);
				java.sql.Statement stmt1= con.createStatement();
				stmt1.execute(query);
				con.commit();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			
			
		}
		else
		{
			String query="update Clei set balance"+customer.getWallet().getBalance()+"where mobileno ="+customer.getMobileNo();
			try {
				con.setAutoCommit(false);
				java.sql.Statement stmt1= con.createStatement();
				stmt1.execute(query);
				con.commit();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}}
			
	}

	@Override
	public Customer findOne(String mobileNo) {		
Customer customer=new Customer();
int flag=0;
String query="select * from Clei where mobileno="+customer.getMobileNo();
try {
	con.setAutoCommit(false);
	java.sql.Statement stmt1= con.createStatement();
ResultSet rs=stmt1.executeQuery(query);
if(rs.next())
{
	customer.setName(rs.getString(1));
	customer.setMobileNo(rs.getString(2));
	customer.getWallet().setBalance(rs.getBigDecimal(3));
     flag = 1;	}

} catch (SQLException e) {

	e.printStackTrace();
}
if(flag==0){
	return null;
}
return customer;
		 }
}
