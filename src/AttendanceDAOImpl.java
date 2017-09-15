package com.cvs.attendance.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.cvs.attendance.dao.AttendanceDAO;
import com.cvs.attendance.entity.Attendance;


public class AttendanceDAOImpl implements AttendanceDAO {

	@Override
	public Integer insertAttendance(Attendance attendance) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employe_attendance","root","transport"); 
			
			String identifyInOutQuery = "select id, in_time, out_time, date  from attendance where employe_id=? and date=?";
			ps = con.prepareStatement(identifyInOutQuery, ResultSet.TYPE_SCROLL_SENSITIVE);
			ps.setInt(1, attendance.getEmpId());
			ps.setString(2,attendance.getDate());
			rs = ps.executeQuery();
			int counter = 0;
			while(rs.next()) {
				counter++;
			}
			String operationType = "";
			String inTimeVal = "";
			int  idToUpdate = 0;
			//TO CHECK DAY STARTING ATTENDANCE
			if(counter == 0) {
				operationType = "INSERT";
			} else {
				rs.last();
				String outTimeVal = rs.getString("out_time");
				inTimeVal = rs.getString("in_time");
				
				if(outTimeVal == null) {
					operationType = "UPDATE";
					idToUpdate = rs.getInt("id");
				} else {
					operationType = "INSERT";
				}
			}
			rs.close();
			ps.close();
			
			int result = 0;
			if("INSERT".equalsIgnoreCase(operationType)) {
			    String insertQuery = "insert into attendance(employe_id,in_time,out_time,date,total_time) values(?,?,?,?,?)";
				ps = con.prepareStatement(insertQuery); 
				ps.setInt(1, attendance.getEmpId());
				ps.setString(2, attendance.getTime());
				ps.setString(3, null);
				ps.setString(4, attendance.getDate());
				ps.setObject(5, null);
				result = ps.executeUpdate();
				return result;
			} else {
				
				DateFormat data = new SimpleDateFormat("HH:mm");
				Date inDate = data.parse(inTimeVal);
				Date outDate = data.parse(attendance.getTime());
				long duration = outDate.getTime() - inDate.getTime();
				long finalTime = duration/60000;
				
				
				String updateQuery = "update attendance set out_time = ?, total_time = ? where employe_id = ? and id = ?";
				ps = con.prepareStatement(updateQuery); 
				ps.setString(1, attendance.getTime());
				ps.setLong(2, finalTime);
				ps.setInt(3, attendance.getEmpId());
				ps.setInt(4, idToUpdate);
				result = ps.executeUpdate();
				return result;
			}
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
	public Integer updateAttendance(Attendance attendance) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Attendance> selectAttendance(Integer id) throws Exception {
		
		List<Attendance> attendanceList = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employe_attendance","root","transport"); 
			String insertQuery = "select id,employe_id,in_time,out_time,date,total_time from attendance where employe_id = ?";
			ps = con.prepareStatement(insertQuery); 
			ps.setInt(1, id);
		
			resultSet = ps.executeQuery();
		    attendanceList = new ArrayList<Attendance> ();
			
			while(resultSet.next()) {
				
				Attendance attendance = new Attendance();
				attendance.setId(resultSet.getInt("id"));
				attendance.setEmpId(resultSet.getInt("employe_id"));
				attendance.setInTime(resultSet.getString("in_time"));
				attendance.setOutTime(resultSet.getString("out_time"));
				attendance.setDate(resultSet.getString("date"));
				attendance.setTotalTime(resultSet.getLong("total_time"));
				attendanceList.add(attendance);
				
			}
			
			return attendanceList;
			
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
	public Integer deleteAttendance(Attendance attendance) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Attendance> selectAttendanceList() throws Exception {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employe_attendance","root","transport"); 
			String insertQuery = "select id,employ_id,in_time,out_time,date,total_time from attendance";
			ps = con.prepareStatement(insertQuery);
		
			resultSet = ps.executeQuery();
			
			List<Attendance> attendanceList = new ArrayList<Attendance> ();
			while(resultSet.next()) {
				Attendance attendance = new Attendance();
				attendance.setId(resultSet.getInt("id"));
				attendance.setEmpId(resultSet.getInt("employ_id"));
				attendance.setInTime(resultSet.getString("in_time"));
				attendance.setOutTime(resultSet.getString("out_time"));
				attendance.setDate(resultSet.getString("date"));
				attendance.setTotalTime(resultSet.getLong("total_time"));
				
				attendanceList.add(attendance);
			}
			return attendanceList;
			
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
	public String selectInOutStatusButtonText(Integer employeeId, String date) throws Exception {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employe_attendance","root","transport"); 
			
			String identifyInOutQuery = "select id, in_time, out_time, date  from attendance where employe_id=? and date=?";
			ps = con.prepareStatement(identifyInOutQuery, ResultSet.TYPE_SCROLL_SENSITIVE);
			ps.setInt(1, employeeId);
			ps.setString(2,date);
			rs = ps.executeQuery();
			int counter = 0;
			while(rs.next()) {
				counter++;
			}
			String operationType = "";
			
			//TO CHECK DAY STARTING ATTENDANCE
			if(counter == 0) {
				operationType = "in";
			} else {
				rs.last();
				String outTimeVal = rs.getString("out_time");
				System.out.println(outTimeVal);
				if(outTimeVal == null) {
					operationType = "out";
				} else {
					operationType = "in";
				}
			}
			
			return operationType;
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
