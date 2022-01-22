package com.revature.app.views;

public class AdminView {

    private static String basicUrl;

    public AdminView(String basicUrl) {
        this.basicUrl = basicUrl;
    }

    public String format(String str) {
        return "<html>\n<head>\n</head>\n<body>\n"
                + str
                + "</body></html>\n";
    }
    
    public String adminPage(){
        return format("<h1>Admin Page</h1>\n"
                + "<div>use url to make a request</div>\n");
    }
    
    public String info(String info){
        String res = "";
        for(String s:info.split("\n")){
            res += "<div>" + s + "</div>\n";
        }
        return format(res);
    }
    
}
