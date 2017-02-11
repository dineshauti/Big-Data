package com.assignment1.ecgr6090;

import java.io.IOException;
import java.util.concurrent.Callable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

public class Thread1 implements Callable<String> {
	
	String input = null;
	
	public Thread1(String input) {
		this.input = input;
		
	}
	
	public static String meaning(String s) {
		
		String t1 = "";
		
		try {
			
			Document doc = Jsoup.connect("https://en.oxforddictionaries.com/definition/"+s)
					.userAgent("Mozilla")
					.cookie("auth", "token")
					.timeout(3000)
					.get();
				
			Elements trg = doc.getElementsByClass("ind");
			String[] output = trg.text().split(":");
			t1 = s + ":" +TranslateMeaning.translateMeaning(s) + "/" + output[0];	
			
			
		} catch (NullPointerException | FailingHttpStatusCodeException | IOException e) {
			;
		}
		return t1;
		
	}

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		
		String out = meaning(input);
		return out;
		
	}

}
