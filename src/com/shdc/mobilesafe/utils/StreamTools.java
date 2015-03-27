package com.shdc.mobilesafe.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTools {
	
	/**
	 * 把流转换成String类型
	 * @param in 输入流
	 * @return 返回String
	 * @throws IOException
	 */
	public static String readFromStream(InputStream in) throws IOException{
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		byte buffer[]=new byte[1024];
		int len=0;
		while((len=in.read(buffer))!=-1){
			bos.write(buffer,0,len);
		}
		in.close();
		String result=bos.toString();
		bos.close();
		return result;
	}
}
