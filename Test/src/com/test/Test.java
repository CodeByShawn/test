package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Test {
	public static void main(String[] args) throws Exception {
		File srcDir = new File("d:\\java");
		if(!(srcDir.exists()&&srcDir.isDirectory())){
			throw new Exception("文件不存在");
		}
		
		File[] files = srcDir.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.endsWith(".java");
			}
		});
		
		File destDir = new File("d:\\jad");
		if(!destDir.exists()){
			destDir.mkdirs();
		}
		for (File file : files) {
			FileInputStream fis = new FileInputStream(file);
			String destFileName = file.getName().replaceAll("\\.java$", ".jap");
			FileOutputStream fos =new FileOutputStream(new File(destDir
					, destFileName));
			copy(fis, fos);
			fis.close();
			fos.close();
		}
	}
	//copy方法
	private static void copy(InputStream is,OutputStream os) throws IOException{
		int len=0;
		byte[] buf = new byte[1024];
		while((len=is.read(buf))!=-1){
			os.write(buf,0,len);
		}
	}
}
