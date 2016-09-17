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
			//记录客户端的数量
			int count=0;
			System.out.println("服务器即将启动");
			//循环监听等待客户端链接
			while (true) {
			//调用accept方法进行监听，等待客户端连接
			socket=serverSocket.accept();
			//创建一个新的线程
			ServerThread serverThread=new ServerThread(socket);
			//启动线程
			serverThread.run();
			count++;//获取客户端的数量
			System.out.println("客户端的数量为： "+count);
			InetAddress inetAddress=socket.getInetAddress();
			System.err.println("当前客户端的IP为： "+inetAddress.getHostAddress());
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
