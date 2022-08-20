package datawebsocket;

import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

//@ServerEndpoint("/datasocket")
public class DataWebSocket {
	Session session;
	static int[] arr= {0,0,0,0};
	
	@OnOpen
	public void handleOpen(Session session) {
		this.session=session;
	}
	@OnClose
	public void handleClose(Session session) {
		
	}
	@OnError
	public void handleError(Throwable th) {
		
	}
	@OnMessage
	public void handleMessage(String message) {
		System.out.println(message);
		
		//String[] data= {"1","2","3","4"};
		
		if(message.equals("1")) {
			for(int i=0; i<4;i++) {
				arr[i]+=1;
			}
		}else if(message.equals("2")) {
			for(int i=0; i<4;i++) {
				arr[i]+=2;
			}
		}
		else if(message.equals("3")) {
			for(int i=0; i<4;i++) {
				arr[i]+=3;
			}
		}
		else{
			for(int i=0; i<4;i++) {
				arr[i]+=4;
			}
		}
		
		try {
			//session.getBasicRemote().sendText("문자열"); //sendText()는 문자열만 가능
			//session.getBasicRemote().sendObject(data);
			//session.getBasicRemote().sendText(arr.toString());
	
			StringBuffer msg=new StringBuffer("");
			for(int i:arr) {
				msg.append(String.valueOf(i)+":");
			} //-> 10:20:30:40:
			msg.delete(msg.length()-1, msg.length()); //-> 10:20:30:40
			session.getBasicRemote().sendText(msg.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
