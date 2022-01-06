package com.revature;

import java.util.ArrayList;
import java.util.List;

public class Q08 {

    public static List<String> palindromes(List<String> list){ 
        List<String> res = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            String word = list.get(i);
            boolean b = true;
            for(int j=0;j<word.length()/2;j++){
                if(word.charAt(j)!=word.charAt(word.length()-1-j)){
                    b=false;
                }
            }
            if(b){
                res.add(word);
            }
        }               
        return res;
    }
}
