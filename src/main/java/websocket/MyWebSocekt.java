package websocket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

//ws://192.168.0.70:8080/wsocket 주소가 만들어진것
//@ServerEndpoint("/wsocket")
public class MyWebSocekt {
	//누가 연결됐는지 저장해 확인 가능하게 함 
	Session session;
	
	@OnMessage
	public void recvMessage(String message) {
		System.out.println(message);
		//답변을 보내는 스레드를 만들어줘야함
		//스레드 만든 이유: 
		Answer answer=new Answer(message,session);
		answer.start();
		try {
			session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//기본적으로 설정해주어야 하는것을 설정해야 오류가 뜨지않음
	
	@OnOpen
	public void handleOpen(Session session) {
		this.session=session;
	}
	@OnClose
	public void handleClose() {
		
	}
	@OnError
	public void handleError(Throwable error) {
		
	}
	

	
}
