package websocket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

//@ServerEndpoint("/wsocket")
public class MultiWebSocket {
	//static으로 해주지 않으면 초기값으로만 남게되지않음
	//그리고 새로 들어오는 세션들을 프로그램이 종류될때까지 유지시켜줌
	static Session[] sessions=new Session[100];
	static int cnt=0;
	
	@OnMessage
	public void recvMessage(String message) {
		System.out.println(message);
		try {
			for(int i=0;i<cnt;i++) {
				System.out.println("i:"+i);
				sessions[i].getBasicRemote().sendText(message);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//기본적으로 설정해주어야 하는것을 설정해야 오류가 뜨지않음
	
	@OnOpen
	public void handleOpen(Session session) {
		System.out.println("연결세션: "+session);
		sessions[cnt++]=session;
		System.out.println("cnt:"+cnt);
	}
	@OnClose
	public void handleClose(Session session) {
		System.out.println("종료세션: "+session);
	}
	@OnError
	public void handleError(Throwable error) {
		
	}
	

	
}
