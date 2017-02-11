package com.assignment1.ecgr6090;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainThread {
	
	static String temp1 = "";
	static String temp2 = "";
	static String temp3 = "";
	static String output = "";
	
	public static void main(String[] args) throws IOException {
		
		
		String file = "out.txt";
		String line;
		
		PrintWriter outputStream = new PrintWriter(file);
		
		BufferedReader abc = new BufferedReader(new FileReader("inputFile.txt"));
		ArrayList<String> lines = new ArrayList<String>();
		
		while(( line = abc.readLine()) != null) {
		    lines.add(line);
		}
		
		Thread1 thread1 = new Thread1("");
		Thread2 thread2 = new Thread2("");
		Thread3 thread3 = new Thread3("");
		
		for (int i = 0; i < lines.size(); i++) {
		
			thread1.input = lines.get(i);
			thread2.input = lines.get(i);
			thread3.input = lines.get(i);
			
			ExecutorService executor1 = Executors.newCachedThreadPool();
			Future<String> future1 = executor1.submit(thread1);
			
			ExecutorService executor2 = Executors.newCachedThreadPool();
			Future<String> future2 = executor2.submit(thread2);
			
			ExecutorService executor3 = Executors.newCachedThreadPool();
			Future<String> future3 = executor3.submit(thread3);
			
			
			
			try {
				
				if(!future1.get().isEmpty()) {
					temp1 += future1.get();
					temp1 += "\r\n";
				}
				
				if(!future2.get().isEmpty()) {
					temp2 += future2.get();
					temp2 += "\r\n";
				}
				
				if(!future3.get().isEmpty()) {
					temp3 += future3.get();
					temp3 += "\r\n";
				}
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			
			
			
			executor1.shutdown();
			executor2.shutdown();
			executor3.shutdown();

			
		}
				
		
		output = temp1 + temp2 + temp3;
					
		outputStream.write(output);
		outputStream.close();
		
		abc.close();

	}

}

