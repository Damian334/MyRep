package com.revature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Driver {

    public static void main(String[] args) {
        
        
        int[] tab = {1,0,5,6,3,2,3,7,9,8,4};
        Q01.bubble(tab);    
        read(tab);
        
        int[] fib = Q02.execute(25);
        read(fib);
        
        String result = Q03.execute("reverse");
        System.out.println(result);
        
        int result2 = Q04.factorial(6);
        System.out.println("factorial: "+result2);
        
        String result3 = Q05.substring("reverse",3);
        System.out.println(result3);
        
        ////////////////////////////////
                
        Q06.execute(10);
        Q06.execute(13);
                
        List<Employe> list = new ArrayList<>();
        list.add(new Employe("Marck","sales",34));
        list.add(new Employe("Jhon","sales",25));
        System.out.println(list);
        Q07.execute(list);
        System.out.println(list);
        
        String[] words = {"karan", "madam", "tom", "civic", "radar", "jimmy", "kayak", "john", "refer", "billy", "did"};
        List<String> list2 = Arrays.asList(words);
        List<String> result4 = Q08.palindromes(list2);
        System.out.println(result4);
        
        Q09.execute();
        
        int result5 = Q10.minimum(5, 2);
        System.out.println("result: "+result5);
        
        ////////////////////////////////
        
        Q11.execute();
        
        Q12.execute();
        
        Q13.execute(4);
        
        //Q14.execute();
        
        Q15.execute();
    }
    
    public static void read(int[] tab){
        String str = "";
        for(int i=0;i<tab.length;i++){
            str += tab[i];
            str += i<tab.length-1 ? "," : "";
        }
        System.out.println(str);
    }
    
    public static void read(List<Integer> list){
        System.out.println(list.toString().replace("[","").replace("]","").replace(" ",""));
    }
}
