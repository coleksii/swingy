package com.coleksii.swingy;

public class RedactorForHtml {
    public static String redact(String s){
        return "<html>" + s.replace("\n", "<br>")+ "</html>";
    }
}
