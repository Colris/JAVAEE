package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {


	//�ͱ��߳���ص�socket
	Socket socket=null;
	public ServerThread(Socket socket)
	{
		this.socket=socket;
	}
	//�߳�ִ�в�������Ӧ�ͻ��˵�����
	public void run()
	{
		PrintWriter pwPrintWriter=null;
		InputStream is=null;
		InputStreamReader isReader=null;//�ֽ���ת��Ϊ�ַ���
		BufferedReader br=null;
		OutputStream os=null;
		try {
			//��ȡ������
		    is=socket.getInputStream();
			isReader=new InputStreamReader(is);//�ֽ���ת��Ϊ�ַ���
			br=new BufferedReader(isReader);
			String info=null;
			String str2="";
			String str1="";
			while((info=br.readLine())!=null)
			{
				System.out.println("�ͻ��˵���ϢΪ�� "+info);
			str2+=info;	
			} 
			System.out.println(str2);
			for (int i = str2.length() - 1; i > -1; i--) {
				str1 += String.valueOf(str2.charAt(i));
			}

			socket.shutdownInput();//�ر�������
			
			//��ȡ���������Ӧ�ͻ�������
			os=socket.getOutputStream();
			pwPrintWriter=new PrintWriter(os);//��װΪ��ӡ��
			pwPrintWriter.write("���������ص���Ϣ�ǣ� "+str1);
			pwPrintWriter.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			
			try {
				//�ر�������Դ
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
