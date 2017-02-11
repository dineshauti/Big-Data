package com.assignment1.ecgr6090;

import java.io.IOException;
import java.util.concurrent.Callable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

public class Thread3 implements Callable<String> {
	
	String input = null;
	
	public Thread3(String input) {
		// TODO Auto-generated constructor stub
		this.input = input;
	}
	
	public static String meaning(String s) {
		
		String t3 = "";
		
		try {
			
			Document doc = Jsoup.connect("https://www.vocabulary.com/dictionary/"+s)
					.userAgent("Mozilla")
					.cookie("auth", "token")
					.timeout(3000)
					.get();
				
			Elements trg = doc.getElementsByTag("h3");
			Elements heading = trg.select("h3.definition");
			String[] output = heading.html().split("> ");
			t3 = s +":"+ TranslateMeaning.translateMeaning(s) + "/" +output[1].split("<")[0];
						
		} catch (NullPointerException | FailingHttpStatusCodeException | IOException e) {
			;
		}
		return t3;
		
	}

	@Override
	public String call() throws Exception {
		String out = meaning(this.input);
		return out;
	}

}
