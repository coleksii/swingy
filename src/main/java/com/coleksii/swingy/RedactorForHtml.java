package com.coleksii.swingy;

public class RedactorForHtml {
    public static String redact(String s){
        String firstRed = s.split("\033")[0];
        String red =  "<html>" + firstRed.replace("\n", "<br>")+ "</html>";
        return red;
    }
}
