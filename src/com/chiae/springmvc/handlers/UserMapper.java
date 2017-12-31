package com.chiae.springmvc.handlers;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.chiae.springmvc.entities.User;
public interface UserMapper {
	@Select("select * from User_db ")
	List<User> selectUsers();
	
	@Select("select * from User_db where id=#{id}")
	User selectUserById(int id);
	
	@Update("update User_db set email=#{email} where id = #{id}")
	int updateUser(User user);
	
	@Select("select password from User_db where email=#{email}")
	String selectUserPasswordByEmail(String email);
	
	@Select("select id from User_db where email=#{email}")
	String selectUserIdByEmail(String email);
	
	@Insert("insert into User_db (email, password}) values (#{email}, #{password})")
	int insertUser(User user);
}
