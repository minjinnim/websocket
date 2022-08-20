package datawebsocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/datasocket")
public class DataWebSocket2 {
	static Set<Session> sessions=new HashSet<Session>();
	static int[] arr= {0,0,0,0};
	
	@OnOpen
	public void handleOpen(Session session) {
		sessions.add(session);
	}
	@OnClose
	public void handleClose(Session session) {
		sessions.remove(session);
	}
	@OnError
	public void handleError(Throwable th) {
		
	}
	@OnMessage
	public void handleMessage(String message) {
		System.out.println(message);
		
		if(message.equals("1")) {
			
			arr[0]+=1;
		}else if(message.equals("2")) {
			arr[1]+=1;
		}
		else if(message.equals("3")) {
			arr[2]+=1;
		}
		else{
			arr[3]+=1;
		}
		
		try {
			StringBuffer msg=new StringBuffer("");
			for(int i:arr) {
				msg.append(String.valueOf(i)+":");
			} //-> 10:20:30:40:
			msg.delete(msg.length()-1, msg.length()); //-> 10:20:30:40
			
			Iterator<Session> sess=sessions.iterator();
			while(sess.hasNext()) {
				Session s = sess.next();
				s.getBasicRemote().sendText(msg.toString());
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
