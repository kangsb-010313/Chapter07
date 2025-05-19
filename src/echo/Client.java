package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

//뉴욕
public class Client {

	public static void main(String[] args) throws IOException{
		
		//소켓생성 종이컵전화기
		Socket socket = new Socket();
		
		System.out.println("<클라이언트 시작>");
		System.out.println("==============================================");
		
		//서버에 연결 요청
		//ip 192.168.0.27  	port 10001
		System.out.println("[서버에 연결을 요청합니다.]");
		socket.connect(new InetSocketAddress("192.168.0.27", 10001));
		
		//쓰기 스트림 준비 
		//OutputStream out = new FileOutputStream("C:\\javaStudy\\MS949-copy.txt");
		OutputStream out = socket.getOutputStream(); //주스트림
		OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		
		//읽기 스트림 준비
		InputStream in = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(in, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		//스캐너 준비
		Scanner sc = new Scanner(System.in);
		/*
		InputStream sin = System.in;	//주스트림
		InputStreamReader sisr = new InputStreamReader(sin, "MS949");
		BufferedReader sbr = new BufferedReader(sisr);
		*/
		//----------------------------------------------
		
		while(true) {
			
			//메세지 키보드로 입력받기
			String msg = sc.nextLine(); //입력대기
			//String msg = sbr.readLine();
			
			if("/q".equals(msg)) { //종료 조건(끝내는 상황)
				break;
			}
			
			//메세지를 보내기
			bw.write(msg);
			bw.newLine();
			bw.flush(); //꽉 안 채워도 넘기는
			
			//메세지 받기
			String reMsg = br.readLine();
			System.out.println("sever:[" + reMsg + "]");
			//-----------------------------------------------
		}
		
		System.out.println("==============================================");
		//System.out.println("<클라이언트 종료>");
		
		//println 스트림
		OutputStream pout = System.out;
		OutputStreamWriter posw = new OutputStreamWriter(pout, "UTF-8"); //MS949해야 안 깨지네
		BufferedWriter pbw = new BufferedWriter(posw);
		
		pbw.write("<클라이언트 종료>");
		pbw.newLine(); //줄바꿈
		pbw.flush();
		
		//닫기
		pbw.close();
		sc.close();
		//sbr.close();
		br.close();
		bw.close();
		socket.close();
	
		
		
	}

}
