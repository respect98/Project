package com.message.websocket;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.member.model.MemberVO;
import com.message.mapper.MessageMapper;
import com.message.model.Message;



//문의 질문, 답변 insert 전부 여기로 바꿈

public class WebSocketAlarmHandler extends TextWebSocketHandler{
	
	
	private SqlSession sqlsession;
	
	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
		
	}

	
	private static Map<String, WebSocketSession> users = new HashMap<String, WebSocketSession>();

	private void log(String msg) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = simple.format(new Date());
		System.out.println(date + " : " + msg);
	}
	
	//연결
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("보캐슬 보이나");
		MemberVO user = (MemberVO)session.getAttributes().get("member"); 
		String userid = user.getUserid();  
		log(userid + " 님 접속하셨다");
		log(session.toString());
		users.put(userid, session); //userid 와 session 저장
		System.out.println("값이 찍히는건가"+ userid);
	}	
	
	//연결해제
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		MemberVO user = (MemberVO)session.getAttributes().get("member");
		String userid = user.getUserid(); 
		if(session.getId() != null) {
			if(users.containsKey(userid)) {
				users.remove(userid); //연결해제된 id 삭제
				log(userid + " 해제");
			}
		}
	}
	
	//데이터 전송
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{  
		System.out.println("여기까지 확인완료.");
		JSONParser parser = new JSONParser();
		System.out.println(message.getPayload());
		JSONObject obj = (JSONObject) parser.parse(message.getPayload());
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+obj);
		String type = (String) obj.get("type");
		System.out.println("타입이 뭐로와 :"+type);
		MemberVO user = (MemberVO)session.getAttributes().get("member");
		String userid = user.getUserid(); 
		MessageMapper messageMapper = sqlsession.getMapper(MessageMapper.class);
		System.out.println("userid 값을 보여주세요 :" + userid );
		
		if(type.equals("view")) {
		
			
			int count = 0;
			System.out.println("views"+ userid);
			System.out.println("users"+users);
			if(users.containsKey(userid) && userid.equals(userid)) { //연언의 후건은 불필요하지만 남겨둠
	
				System.out.println("타니?지금 여기는 안타고있네?");
				count = messageMapper.getCountNotRead(userid);
				System.out.println(count);
				System.out.println("타니?지금 여기는 안타고있네?");
				if(count > 0) {
					obj.put("text", "새로운 쪽지 알람이 있습니다.");
				} else {
					obj.put("text", "새로운 쪽지 알림이 없습니다.");
				}
				obj.put("count", count);
				System.out.println("userid가 뭐니?"+ userid);
				obj.put("now", "user");
				System.out.println("userid가 뭐니?"+ userid);
				System.out.println("여기들어오니??");

				System.out.println(obj.toString());
				TextMessage msg = new TextMessage(obj.toString());
				users.get(userid).sendMessage(msg);
				  log(userid + " / " + message.getPayload() + " / " + msg.getPayload());
				  
			} 
		
	
//			}
			
			
		} 
		
		else if(type.equals("user")) {
			
			
			String ruserid = (String)obj.get("ruserid");
			//System.out.println("sssssssssssss"+users.get(ruserid));
			String content = (String)obj.get("content");
			log(userid + " / " + content);
			
			Message mge = new Message();
			mge.setRuserid(ruserid);
			mge.setContent(content);
			mge.setSuserid(userid);
			
	
			System.out.println("userid가 뭘까??"+ userid);
			System.out.println("여기로 와주세요~~~~");
			messageMapper.writeMessage(mge);
			System.out.println("userid가 뭘까??"+ userid);
			
			
			int count = 0;
			count = messageMapper.getCountNotRead(ruserid);
			
			obj.put("count", count);
			System.out.println(obj.toString());
			TextMessage msg = new TextMessage(obj.toString());
			users.get(userid).sendMessage(msg); // 메시지 전달 핵심 코드
			log("userid" + " / " + message.getPayload());
			log("userid" + " / " + msg.getPayload());
			
		} 
		
		
		else {
			String ruserid = (String)obj.get("ruserid");
			String content = (String)obj.get("content");
			//String ref = (String)obj.get("qna_brd_ref");
			String to_userid = (String)obj.get("to_userid");
			System.out.println(to_userid+"너님 누구?");
			//log(title + " / " + content + " / " + ref + " / " + to_user_email);
			log(ruserid + " / " + content  + " / " + to_userid);
			Message mge = new Message();
			mge.setRuserid(ruserid);
			mge.setContent(content);
			mge.setSuserid(userid);
			
			
			//qna.setQna_brd_ref(Integer.parseInt(ref));
			System.out.println("userid가 뭘까용??"+ userid);
			messageMapper.writeMessage(mge);
			System.out.println("userid가 뭘까용??"+ userid);
			//qnadao.updateQnaRep(Integer.parseInt(ref));
			
			int count = messageMapper.getCountUserNotRead();
			System.out.println("유저 카운트 : " +count);
			//obj.put("count", count);
			System.out.println(obj.toString());
			
			TextMessage msg = new TextMessage(obj.toString());
			 users.get(to_userid).sendMessage(msg);
			 System.out.println(to_userid+"너님 누구?여기까지 오니?");
			  log(to_userid + " / " + message.getPayload() + " / " + msg.getPayload());
		}
		System.out.println(type);
	}
	
	//연결에 문제 발생시
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception{
		MemberVO user = (MemberVO)session.getAttributes().get("member");
		String userid = user.getUserid(); 
		log(userid + " / " + exception.getMessage());
	}

}

