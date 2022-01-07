package com.other;

public class StrOperations extends StrOperationsAbstract{

    @Override
    public boolean findUppercase(String str) {
        
        for(char c: str.toCharArray()){
            if(c>=65 && c<=90){
                return true;
            }
        }        
        return false;
    }

    @Override
    public String ConvertToUppercase(String str) {
        
        String result = "";
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            if(c>=97 && c<=122){
                c-=32;
            }
            result+=c;
        }        
        return result;
    }

    @Override
    public int ConvertToInteger(String str) {
        
        int nr = Integer.valueOf(str);        
        return nr+10;
    }

}
