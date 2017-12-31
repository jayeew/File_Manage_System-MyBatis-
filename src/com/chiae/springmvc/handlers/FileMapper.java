package com.chiae.springmvc.handlers;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.chiae.springmvc.entities.File_;

public interface FileMapper {
	@Select("select * from File_T")
	List<File_> selectFiles() ;
	
	@Select("select * from File_T where file_name=#{name}")
	File_ selectFileByName(String name) ;
	
	@Select("select * from File_T LIMIT #{pageNum},#{pageSize}")
	List<File_> selectFileLimit(int pageNum,int pageSize);
	
//	@Select("select file_download from File_T where file_name= #{name}")
//	int selectFileDownload(String name);
	
	@Update("update File_T set file_download= #{file_download} where file_name= #{file_name}")
	int updateFileDownload(File_ file);
	
	@Delete("delete from File_T where file_name= #{file_name}")
	int deleteFile(String file_name);
	
	@Insert("insert into File_T (file_name, file_url, file_data, file_download, file_currenttime, file_size, file_Uper, file_desc) values (#{file_name}, #{file_url}, #{file_data}, #{file_download},#{file_currenttime}, #{file_size}, #{file_Uper}, #{file_desc})")
	int insertFile(File_ file);
}
