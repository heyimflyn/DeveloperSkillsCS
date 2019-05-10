package com.ibm.training.bootcamp.rest.sample01.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.lang3.StringUtils;

import com.ibm.training.bootcamp.rest.sample01.domain.Developer;

public class DeveloperJdbc implements DeveloperDao{
	private static DeveloperJdbc INSTANCE;

	static public DeveloperJdbc getInstance() {

		DeveloperJdbc instance;
		if (INSTANCE != null) {
			instance = INSTANCE;
		} else {
			instance = new DeveloperJdbc();
			INSTANCE = instance;
		}

		return instance;
	}
	
	private DeveloperJdbc() {
		getConnection();
	}
	
	public Connection getConnection() {
		Connection connection = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver"); //load driver in the program
			DriverManager.setLoginTimeout(10); //optional
			                                         //constant                //port//database //username //password
			connection = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/dscs","root","root");
		    System.out.println("CONNECTION: " + connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	@Override
	public List<Developer> findAll(String firstname, String middlename, String lastname, String birthdate,
			String position) {
		List<Developer> devs = new ArrayList<>();
		
		String sql = "SELECT DEV_ID, FIRSTNAME, MIDDLENAME, LASTNAME, BIRTHDATE, POSITION FROM DEVELOPERS ";

		try (Connection con = getConnection();
			  PreparedStatement ps = con.prepareStatement(sql);)
		    {
			ps.setString(1, createSearchValue(firstname));
			ps.setString(2, createSearchValue(middlename));
			ps.setString(3, createSearchValue(lastname));
			ps.setString(4, createSearchValue(birthdate));
			ps.setString(5, createSearchValue(position));
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Developer dev = new Developer(Long.valueOf(rs.getLong("devID")), 
						                 rs.getString("firstname"),
						                 rs.getString("middlename"),
						                 rs.getString("lastname"),
						                 rs.getString("birthdate"),
				                         rs.getString("position"));
				                         devs.add(dev);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return devs;
	}
	
	@Override
	public Developer find(Long devID) {
		
		Developer dev = null;
		
		if (devID != null) {
			String sql = "SELECT * FROM DEVELOPERS WHERE DEV_ID = ?";
			try (Connection con = getConnection();
			     PreparedStatement ps = con.prepareStatement(sql))
			   
				{
				ps.setInt(1, devID.intValue()); 
				   ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					dev = new Developer(Long.valueOf(rs.getInt("devID")), 
							 rs.getString("firstname"),
			                 rs.getString("middlename"),
			                 rs.getString("lastname"),
			                 rs.getString("birthdate"),
	                         rs.getString("position"));

				}

			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		return dev;
	}

	@Override
	public List<Developer> findByName(String firstname, String lastname) {
		List<Developer> devs = new ArrayList<>();

		String sql = "SELECT DEV_ID, FIRSTNAME, LASTNAME FROM DEVELOPERS WHERE FIRSTNAME LIKE ? AND LASTNAME LIKE ?";

		try (Connection con = getConnection();
			  PreparedStatement ps = con.prepareStatement(sql))
		    {
			ps.setString(1, createSearchValue(firstname));
			ps.setString(2, createSearchValue(lastname));
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Developer dev = new Developer(Long.valueOf(rs.getInt("devID")), 
						                 rs.getString("firstname"),
						                 rs.getString("lastname"));
				                         devs.add(dev);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return devs;
	}
	
	private String createSearchValue(String string) {
		
		String value;
		
		if (StringUtils.isBlank(string)) {
			value = "%";
		} else {
			value = string;
		}
		
		return value;
	}
	
	@Override
	public void addDeveloper(Developer adddev) {
		String sql = "INSERT INTO DEVELOPERS values (?,?,?,?,?)";
		
		try (Connection con = getConnection(); 
			 PreparedStatement ps = con.prepareStatement(sql))
		    { 
			ps.setString(1, adddev.getFirstname());
			ps.setString(2, adddev.getMiddlename());
			ps.setString(3, adddev.getLastname());
			ps.setString(4, adddev.getBirthdate());
			ps.setString(5, adddev.getPosition());
			ps.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
			
		}
	}


	@Override
	public void delDeveloper(Long devID) {
		String sql = "DELETE FROM DEVELOPERS WHERE DEV_ID = ?";

		try (Connection con = getConnection();
		     PreparedStatement ps = con.prepareStatement(sql))
			{ 
			ps.setLong(1, devID);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
			
		}
	}

	@Override
	public List<Developer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
