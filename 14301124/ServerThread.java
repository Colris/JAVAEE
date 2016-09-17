package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {


	//和本线程相关的socket
	Socket socket=null;
	public ServerThread(Socket socket)
	{
		this.socket=socket;
	}
	//线程执行操作，响应客户端的请求
	public void run()
	{
		PrintWriter pwPrintWriter=null;
		InputStream is=null;
		InputStreamReader isReader=null;//字节流转换为字符流
		BufferedReader br=null;
		OutputStream os=null;
		try {
			//获取输入流
		    is=socket.getInputStream();
			isReader=new InputStreamReader(is);//字节流转换为字符流
			br=new BufferedReader(isReader);
			String info=null;
			String str2="";
			String str1="";
			while((info=br.readLine())!=null)
			{
				System.out.println("客户端的信息为： "+info);
			str2+=info;	
			} 
			System.out.println(str2);
			for (int i = str2.length() - 1; i > -1; i--) {
				str1 += String.valueOf(str2.charAt(i));
			}

			socket.shutdownInput();//关闭输入流
			
			//获取输出流，响应客户端请求
			os=socket.getOutputStream();
			pwPrintWriter=new PrintWriter(os);//包装为打印流
			pwPrintWriter.write("服务器返回的信息是： "+str1);
			pwPrintWriter.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			
			try {
				//关闭其他资源
				if(pwPrintWriter!=null)
				{
					pwPrintWriter.close();
				}  
				if(os!=null)
				{
					os.close();
				}
				
				if(br!=null)
				{
					br.close();
				}
				
				if(is!=null)
				{
					is.close();
				}
				
				if(isReader!=null)
				{
					isReader.close();
				}
				
				if(socket!=null)
				{
					socket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
}
