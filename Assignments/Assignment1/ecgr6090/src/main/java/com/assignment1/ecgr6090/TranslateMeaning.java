package com.assignment1.ecgr6090;


import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.google.cloud.translate.Translate.TranslateOption;

public class TranslateMeaning{
	

	public static String translateMeaning(String s) {
		
	    // Instantiates a client
	    Translate translate = TranslateOptions.newBuilder().setApiKey("AIzaSyCoCOw8Efjo7tMF4VGU83O3hhyItUgcr6I").build().getService();

	    // The text to translate
	    //String text = "abate";

	    // Translates some text into French
	    Translation translationFrench =
	        translate.translate(
	            s,
	            TranslateOption.sourceLanguage("en"),
	            TranslateOption.targetLanguage("fr"));
	    
	    // Translates some text into German
	    Translation translationGerman =
	        translate.translate(
	            s,
	            TranslateOption.sourceLanguage("en"),
	            TranslateOption.targetLanguage("de"));

	    // Translates some text into Italian
	    Translation translationItalian =
	        translate.translate(
	            s,
	            TranslateOption.sourceLanguage("en"),
	            TranslateOption.targetLanguage("it"));
		
	    String translatedMeaning = translationFrench.getTranslatedText() +"|"+ translationItalian.getTranslatedText()+"|"+translationGerman.getTranslatedText();
		
		
		return translatedMeaning;
		
	}



    

}
