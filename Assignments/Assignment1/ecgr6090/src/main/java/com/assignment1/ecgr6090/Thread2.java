package com.assignment1.ecgr6090;

import java.io.IOException;
import java.util.concurrent.Callable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

public class Thread2 implements Callable<String>{
	
	String input = null;
	
	public Thread2(String input) {
		this.input += input;
		
	}
	
	
	public static String meaning(String s) {
		String[] output = null;
		String t2 = "";
		
		try {
			
			Document doc = Jsoup.connect("http://www.macmillandictionary.com/us/dictionary/american/"+s)
					.userAgent("Mozilla")
					.cookie("auth", "token")
					.timeout(3000)
					.get();
				
			Elements trg = doc.getElementsByTag("span");
			Elements heading = trg.select("span.h2");
			
			for(@SuppressWarnings("unused") Element span : heading) {
				output = heading.html().split(":");
			}
			
			for(int i = 0; i < output.length; i++) {
				t2 = s +":"+ TranslateMeaning.translateMeaning(s) + "/"+ output[i];
			}
						
		} catch (NullPointerException | FailingHttpStatusCodeException | IOException e) {
			;
		}
		return t2;
		
	}

	@Override
	public String call() throws Exception {
		String out = meaning(this.input);
		return out;
	}

}
