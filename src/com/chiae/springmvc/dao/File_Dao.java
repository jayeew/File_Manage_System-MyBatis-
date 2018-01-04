package com.chiae.springmvc.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import com.chiae.springmvc.entities.File_;
import com.chiae.springmvc.handlers.FileMapper;

@Repository
public class File_Dao {

	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://*.*.*.*:3306/springmvc";
	String sqluser = "root";
	String sqlpassword = "123";

	String host_url = "http://localhost:8080/springmvc-/springmvc/";

	private static Map<String, File_> files = new HashMap<>();

	public Collection<File_> getAll() {
		return files.values();
	}
	
	
	public File_ get(String name) {
		return files.get(name);
	}

	public void delete(String name) {
		files.remove(name);
	}
	
public Boolean selectsql_limit(int pageNum, int pageSize) throws IOException {
		
//		boolean flag=false;
//		
//		//mybatis op
//		String resource = "com/chiae/springmvc/handlers/config.xml";
//		InputStream inputStream = Resources.getResourceAsStream(resource);
//		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//		SqlSession session = sqlSessionFactory.openSession();
//		try {
//		  FileMapper mapper = session.getMapper(FileMapper.class);
//		  List<File_> file = mapper.selectFileLimit(pageNum,pageSize);
//		  files.clear();
//		  int size=file.size();
//		  for(int i=0;i<size;i++) {
//			  File_ item=file.get(i);
//			  files.put(item.getFile_name(),
//					  new File_(item.getFile_name(), item.getFile_url(), item.getFile_data(),
//							  item.getFile_download(), item.getFile_currenttime(), item.getFile_size(),
//							  item.getFile_Uper(), item.getFile_desc()));
//		  }
//		} finally {
//		  session.close();
//		}
//		return flag;
		
		
		
		
		
		boolean n = false;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, sqluser, sqlpassword);
			if (!conn.isClosed())
				System.out.println("杩炴帴鏁版嵁搴撴垚鍔�!");
			Statement statement = conn.createStatement();
			String sql = "select * from File_T LIMIT "+pageNum+","+pageSize+";";
			ResultSet rs = statement.executeQuery(sql);
			System.out.println("鏁版嵁搴撴煡璇俊鎭�");
			files.clear();
			while (rs.next()) {
					files.put(rs.getString(2).toString(),
							new File_(rs.getString(2).toString(), rs.getString(3).toString(), rs.getString(4).toString(),
									Integer.parseInt(rs.getString(5)), rs.getString(6).toString(),
									Integer.parseInt(rs.getString(7).toString()), rs.getString(8).toString(),
									rs.getString(9).toString()));
			}
			rs.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			System.out.println("鍔犺浇MySQL椹卞姩澶辫触!");
		} catch (SQLException e1) {
			System.out.println("1.hellosql:" + e1.getMessage());
		} catch (Exception e2) {
			System.out.println("2.hellosql:" + e2.getMessage());
		}
		return n;
	}
	
	public Boolean add_downloadTimes(String name) throws IOException {
		boolean flag=false;
		
		//mybatis op
		String resource = "com/chiae/springmvc/handlers/config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		try {
		  FileMapper mapper = session.getMapper(FileMapper.class);
//		  int  downloadTimes = mapper.selectFileDownload(name);
//		  downloadTimes++;
		  File_ file=mapper.selectFileByName(name);
		  file.setFile_download(file.getFile_download()+1);
		  mapper.updateFileDownload(file);
		  session.commit();
		} finally {
		  session.close();
		}
		return flag;
		
		
		
		
		
//		boolean n = false;
//		try {
//			Class.forName(driver);
//			Connection conn = DriverManager.getConnection(url, sqluser, sqlpassword);
//			if (!conn.isClosed())
//				System.out.println("杩炴帴鏁版嵁搴撴垚鍔�!");
//			Statement statement = conn.createStatement();
//			String sql = "select file_download from File_T where file_name='" + name +"';";
//			ResultSet rs = statement.executeQuery(sql);
//			System.out.println("鏁版嵁搴撴煡璇俊鎭�");
//			int downloadTimes=0;
//			while (rs.next()) {
//					downloadTimes = Integer.parseInt(rs.getString("file_download"));
//			}
//			Statement statement_ = conn.createStatement();
//			String sql_add = "update File_T set file_download="+(downloadTimes+1)+" where file_name='" +name+"';";
//			int rs_add = statement_.executeUpdate(sql_add);
//			System.out.println(downloadTimes + "   " + rs_add);
//			rs.close();
//			conn.close();
//		} catch (ClassNotFoundException e) {
//			System.out.println("鍔犺浇MySQL椹卞姩澶辫触!");
//		} catch (SQLException e1) {
//			System.out.println("1.hellosql:" + e1.getMessage());
//		} catch (Exception e2) {
//			System.out.println("2.hellosql:" + e2.getMessage());
//		}
//		return n;
	}

	public Boolean selectsql_file() throws IOException {
boolean flag=false;
		
		//mybatis op
		String resource = "com/chiae/springmvc/handlers/config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		try {
		  FileMapper mapper = session.getMapper(FileMapper.class);
		  List<File_> file = mapper.selectFiles();
		  files.clear();
		  int size=file.size();
		  for(int i=0;i<size;i++) {
			  File_ item=file.get(i);
			  files.put(item.getFile_name(),
					  new File_(item.getFile_name(), item.getFile_url(), item.getFile_data(),
							  item.getFile_download(), item.getFile_currenttime(), item.getFile_size(),
							  item.getFile_Uper(), item.getFile_desc()));
		  }
		} finally {
		  session.close();
		}
		return flag;
		
		
		
		
		
//		boolean n = false;
//		try {
//			Class.forName(driver);
//			Connection conn = DriverManager.getConnection(url, sqluser, sqlpassword);
//			if (!conn.isClosed())
//				System.out.println("杩炴帴鏁版嵁搴撴垚鍔�!");
//			Statement statement = conn.createStatement();
//			String sql = "select * from File_T;";
//			ResultSet rs = statement.executeQuery(sql);
//			System.out.println("鏁版嵁搴撴煡璇俊鎭�");
//			while (rs.next()) {
//					files.put(rs.getString(2).toString(),
//							new File_(rs.getString(2).toString(), rs.getString(3).toString(), rs.getString(4).toString(),
//									Integer.parseInt(rs.getString(5)), rs.getString(6).toString(),
//									Integer.parseInt(rs.getString(7).toString()), rs.getString(8).toString(),
//									rs.getString(9).toString()));
//			}
//			rs.close();
//			conn.close();
//		} catch (ClassNotFoundException e) {
//			System.out.println("鍔犺浇MySQL椹卞姩澶辫触!");
//		} catch (SQLException e1) {
//			System.out.println("1.hellosql:" + e1.getMessage());
//		} catch (Exception e2) {
//			System.out.println("2.hellosql:" + e2.getMessage());
//		}
//		return n;
	}

	public Boolean deletesql_file(String file_name) throws IOException {
		boolean flag=false;
		
		//mybatis op
		String resource = "com/chiae/springmvc/handlers/config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		try {
		  FileMapper mapper = session.getMapper(FileMapper.class);
		  mapper.deleteFile(file_name);
		  session.commit();
		  
		} finally {
		  session.close();
		}
		return flag;
		
		
		
//		boolean n = false;
//		try {
//			Class.forName(driver);
//			Connection conn = DriverManager.getConnection(url, sqluser, sqlpassword);
//			if (!conn.isClosed())
//				System.out.println("杩炴帴鏁版嵁搴撴垚鍔�!");
//			Statement statement = conn.createStatement();
//			String sql = "delete from File_T where file_name=" + "'" + file_name + "';";
//			statement.executeUpdate(sql);
//			conn.close();
//		} catch (ClassNotFoundException e) {
//			System.out.println("鍔犺浇MySQL椹卞姩澶辫触!");
//		} catch (SQLException e1) {
//			System.out.println("1.hellosql:" + e1.getMessage());
//		} catch (Exception e2) {
//			System.out.println("2.hellosql:" + e2.getMessage());
//		}
//		return n;
	}
}