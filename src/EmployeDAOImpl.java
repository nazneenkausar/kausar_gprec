package com.cvs.attendance.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cvs.attendance.dao.EmployeDAO;
import com.cvs.attendance.entity.Employe;



public class EmployeDAOImpl implements EmployeDAO{

	@Override
	public Integer insertEmploye(Employe employe) throws Exception {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement ps = null;
				
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employe_attendance","root","transport"); 
			String insertQuery = "insert into employe(firstname,lastname,password,salary,email,mobile,joindate,dob,gender,address,role) values(?,?,?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(insertQuery); 
			
			ps.setString(1, employe.getFirstname());
			ps.setString(2, employe.getLastname());
			ps.setString(3, employe.getPassword());
			ps.setDouble(4, employe.getSalary());
			ps.setString(5, employe.getEmail());
			ps.setString(6, employe.getMobile());
			ps.setString(7, employe.getJoinDate());
			ps.setString(8, employe.getDob());
			ps.setString(9,employe.getGender());
			ps.setString(10,employe.getAddress());
			ps.setString(11,employe.getRole());
			
			Integer result = ps.executeUpdate(); 
			return result;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			 throw e;
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public Integer updateEmploye(Employe employe) throws Exception {
		
		Connection con = null;
		PreparedStatement ps = null;
				
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employe_attendance","root","transport"); 
			String updateQuery = "insert into employe(firstname,lastname,password,salary,email,mobileNumber,joinDate,dob,gender,address,role) values(?,?,?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(updateQuery); 
			
			ps.setString(1, employe.getLastname());
			ps.setString(2, employe.getFirstname());
			ps.setString(4, employe.getPassword());
			ps.setDouble(5, employe.getSalary());
			ps.setString(6, employe.getEmail());
			ps.setString(7, employe.getMobile());
			ps.setString(8, employe.getJoinDate());
			ps.setString(9, employe.getDob());
			ps.setString(10,employe.getGender());
			ps.setString(11,employe.getAddress());
			ps.setString(11,employe.getRole());
			
			int result = ps.executeUpdate(); 
			return result;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			 throw e;
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public Employe selectEmploye(Integer employId) throws Exception {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employe_attendance","root","transport"); 
			String updateQuery = "select firstname,lastname,salary,email,mobile,joindate,dob,gender,address,role from employe where id = ?";
			ps = con.prepareStatement(updateQuery); 
			ps.setInt(1, employId);
			resultSet = ps.executeQuery();
			Employe employe = new Employe ();
			while(resultSet.next()) {

				employe.setId(resultSet.getInt("id"));
				employe.setFirstname(resultSet.getString("firstname"));
				employe.setLastname(resultSet.getString("lastname"));
				employe.setSalary(resultSet.getDouble("salary"));
				employe.setEmail(resultSet.getString("email"));
				employe.setMobile(resultSet.getString("mobile"));
				employe.setJoinDate(resultSet.getString("joindate"));
				employe.setDob(resultSet.getString("dob"));
				employe.setGender(resultSet.getString("gender"));
				employe.setAddress(resultSet.getString("address"));
				employe.setRole(resultSet.getString("role"));
				
			}
			return employe;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			 throw e;
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public Integer deleteEmploye(Employe employe) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Employe> selectEmployeList() throws Exception {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employe_attendance","root","transport"); 
			String updateQuery = "select id,firstname,lastname,salary,email,mobile,joindate,dob,gender,address,role from employe where role='EMPLOYEE'";
			ps = con.prepareStatement(updateQuery); 
			
			resultSet = ps.executeQuery();
			
			List<Employe> employeList = new ArrayList<Employe> ();
			
			while(resultSet.next()) {
			
				Employe employe = new Employe ();
				
				employe.setId(resultSet.getInt("id"));
				employe.setFirstname(resultSet.getString("firstname"));
				employe.setLastname(resultSet.getString("lastname"));
				employe.setSalary(resultSet.getDouble("salary"));
				employe.setEmail(resultSet.getString("email"));
				employe.setMobile(resultSet.getString("mobile"));
				employe.setJoinDate(resultSet.getString("joindate"));
				employe.setDob(resultSet.getString("dob"));
				employe.setGender(resultSet.getString("gender"));
				employe.setAddress(resultSet.getString("address"));
				employe.setRole(resultSet.getString("role"));
				
				employeList.add(employe);
			}
			return employeList;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			 throw e;
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public Employe selectEmploye(String email, String password) throws Exception {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employe_attendance","root","transport"); 
			String selectQuery = "select id,firstname,lastname,salary,email,mobile,joindate,dob,gender,address,role from employe where email = ? and password=?";
			ps = con.prepareStatement(selectQuery); 
			
			ps.setString(1, email);
			ps.setString(2, password);
		
			resultSet = ps.executeQuery();
			
			Employe employe = null;
			while(resultSet.next()) {
				employe = new Employe ();
				employe.setId(resultSet.getInt("id"));
				employe.setFirstname(resultSet.getString("firstname"));
				employe.setLastname(resultSet.getString("lastname"));
				employe.setSalary(resultSet.getDouble("salary"));
				employe.setEmail(resultSet.getString("email"));
				employe.setMobile(resultSet.getString("mobile"));
				employe.setJoinDate(resultSet.getString("joindate"));
				employe.setDob(resultSet.getString("dob"));
				employe.setGender(resultSet.getString("gender"));
				employe.setAddress(resultSet.getString("address"));
				employe.setRole(resultSet.getString("role"));
				}
			return employe;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			 throw e;
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
