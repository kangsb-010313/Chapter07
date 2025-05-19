package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread{

	//필드
	private Socket socket;
	
	//생성자
	public ServerThread() {
		
	}

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	
	//메소드 gs
	//메소드 일반
	

	@Override
	public void run() {
		
		try {
			//--------------------------------------------------
			//읽기 스트림 준비
			//InputStream in = new FileInputStream("C:\\javaStudy\\MS949-copy.txt");
			InputStream in = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(in, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			
			//쓰기 스트림 준비
			OutputStream out = socket.getOutputStream(); //주 스트림
			OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			
			//---------------------------------------------------
			while(true) {
				//메세지를 받기
				String msg = br.readLine();
				
				if(msg == null) {
					break;
				}
				
				System.out.println("받은메세지: " + msg);
				
				//메세지를 보내기
				bw.write(msg);
				bw.newLine();
				bw.flush();
				//---------------------------------------------------
			}
			//--------------------------------------------------
			
		} catch (IOException e) {
			System.out.println(e.toString());
		}

	
	}

	
}
