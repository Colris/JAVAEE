package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		try {
			ServerSocket serverSocket=new ServerSocket(8888);
			Socket socket=null;
			//��¼�ͻ��˵�����
			int count=0;
			System.out.println("��������������");
			//ѭ�������ȴ��ͻ�������
			while (true) {
			//����accept�������м������ȴ��ͻ�������
			socket=serverSocket.accept();
			//����һ���µ��߳�
			ServerThread serverThread=new ServerThread(socket);
			//�����߳�
			serverThread.run();
			count++;//��ȡ�ͻ��˵�����
			System.out.println("�ͻ��˵�����Ϊ�� "+count);
			InetAddress inetAddress=socket.getInetAddress();
			System.err.println("��ǰ�ͻ��˵�IPΪ�� "+inetAddress.getHostAddress());
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
