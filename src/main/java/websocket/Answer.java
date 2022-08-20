package websocket;

import java.io.IOException;
import java.util.Scanner;

import javax.websocket.Session;

public class Answer extends Thread{
	String message;
	Session session;
	
	public Answer() {
		
	}
	
	public Answer(String message,Session session) {
		this.message=message;
		this.session=session;
	}

	@Override
	public void run() {
		System.out.println("Q) "+message);
		Scanner scan= new Scanner(System.in);
		String a=scan.nextLine();
		
		try {
			session.getBasicRemote().sendText("Q)"+message+" A)"+a);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
