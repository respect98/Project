package com.message.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.message.model.Message;

public interface MessageMapper {
	
		// 쪽지 > 쪽지보내기  
			
			public int writeMessage(Message message);
			
			// 쪽지 > 쪽지 리스트 조회  
			public List<Message> getMessageList(int cpage, int pagesize, String SUSERID); 
			
			// 쪽지 > 쪽지 리스트 조회 추가
			public List<Message> getMessageSendList(int cpage, int pagesize, String RUSERID);
			
			// 쪽지 > 쪽지 상세 조회
			@Select("select * from MESSAGE where msindex = #{msindex}")
			public Message getMessage(@Param("msindex") String msindex);
			
			// 쪽지 > 쪽지 삭제
			@Update("delete from MESSAGE where msindex=#{msindex}")
			public int deleteMessage(Message msindex);
			
			// 쪽지 > 읽은 상태로 변경
			@Update("update MESSAGE set readtime = SYSDATE where msindex = #{msindex}")
			public int updateReadState(String msindex);
			
			// 쪽지 > 쪽지 개수 조회 //동적쿼리 적용할 것 
			public int getMessageCount();
			public int getMessageCount(String SUSERID);
			public int getMessageCount(String column, int search);
			
			
			//사용자가 사용자에게 쪽지를 보냈으나 아직 읽지 않은 모든 쪽지 카운트
			public int getCountNotRead(String ruserid)throws ClassNotFoundException, SQLException;	
			
			//사용자가 사용자에게 쪽지를 보냈으나 아직 읽지 않은 모든 쪽지 카운트
			public int getCountUserNotRead()throws ClassNotFoundException, SQLException;
}
