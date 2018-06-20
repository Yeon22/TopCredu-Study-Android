package com.example.test.my_6_calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class BigDecimalCalculator { // 자바의 자료구조
    ArrayList<String> contents;
    String item;

    //method which deal with brackets separately
    public String brackets(String s){             

        while(s.contains(Character.toString('(')) || s.contains(Character.toString(')'))){
            for(int o=0; o<s.length();o++){
            	// i there is not sign between separate brackets or number and bracket,
            	// it treat it as a multiplication ignore out of range ex
                try{
                    if((s.charAt(o)==')' || Character.isDigit(s.charAt(o))) && s.charAt(o+1)=='('){ 
                        s=s.substring(0,o+1)+"*"+(s.substring(o+1));
                    }
                }catch (Exception ignored){}                       
                
                // search for a closing bracket
                if(s.charAt(o)==')'){                

                    for(int i=o; i>=0;i--){
                    	// search for a opening bracket
                        if(s.charAt(i)=='('){             
                            String in = s.substring(i+1,o);
                            in = this.recognize(in);
                            s=s.substring(0,i)+in+s.substring(o+1);
                            i=o=0;
                        }
                    }
                }
            }
            
			if (s.contains(Character.toString('(')) || s.contains(Character.toString(')'))) {
				return "Error: incorrect brackets placement";
			}
        }
        
        s=this.recognize(s);
        return s;
    }
    
	//method divide String on numbers and operators
    public String recognize(String s){              
        PutIt putIt = new PutIt();
        contents = new ArrayList<String>();         //holds numbers and operators
        item = "";
        
        // is scan String from right to left, Strings are added to list, 
        // if scan finds a operator, or beginning of String
        for(int i=s.length()-1;i>=0;i--){           

            if(Character.isDigit(s.charAt(i))){   
                item=s.charAt(i)+item;     

                if(i==0){
                    putIt.put();
                }
            }else{
                if(s.charAt(i)=='.'){
                    item=s.charAt(i)+item;
                }else if(s.charAt(i)=='-' && (i==0 || (!Character.isDigit(s.charAt(i-1))))){
                	// this part should recognize negative numbers
                    item=s.charAt(i)+item;          
                    putIt.put();
                }else{
                    putIt.put();                //it add already formed number and
                    item=""+s.charAt(i);          //operators to list
                    putIt.put();                //as separate Strings

                    if(s.charAt(i)=='|'){       //add empty String to list, before "|" sign,
                        item+=" ";          //to avoid removing of any meaningful String
                        putIt.put();        //in last part of result method
                    }
                }
            }
        }
        contents = putIt.result(contents, "^", "|");    //check Strings
        contents = putIt.result(contents, "*", "/");    //for chosen
        contents = putIt.result(contents, "+", "-");    //operators

        return contents.get(0);
    }
    
    public class PutIt{
    	
        public void put(){
            if(!item.equals("")){
                contents.add(0, item);
                item="";
            }
        }
        
        public ArrayList<String> result(ArrayList<String> arrayList, String op1, String op2){
        	//controls BigDecimal decimal point accuracy
            int scale = 10;                              
            BigDecimal result = new BigDecimal(0);
            
            for(int c = 0; c<arrayList.size();c++){
                if(arrayList.get(c).equals(op1)|| arrayList.get(c).equals(op2)){
                	
                    if(arrayList.get(c).equals("^")){
                        result = new BigDecimal(arrayList.get(c-1)).pow(Integer.parseInt(arrayList.get(c+1)));
                    }else if(arrayList.get(c).equals("|")){
                        result = new BigDecimal(Math.sqrt(Double.parseDouble(arrayList.get(c+1))));
                    }else if(arrayList.get(c).equals("*")){
                        result = new BigDecimal(arrayList.get(c-1)).multiply
                                (new BigDecimal(arrayList.get(c+1)));
                    }else if(arrayList.get(c).equals("/")){
                        result = new BigDecimal(arrayList.get(c-1)).divide
                                (new BigDecimal(arrayList.get(c+1)),scale,BigDecimal.ROUND_DOWN);
                    }else if(arrayList.get(c).equals("+")){
                        result = new BigDecimal(arrayList.get(c-1)).add(new BigDecimal(arrayList.get(c+1)));
                    }else if(arrayList.get(c).equals("-")){
                        result = new BigDecimal(arrayList.get(c-1)).subtract(new BigDecimal(arrayList.get(c+1)));
                    }
                    
                    try{       
                    	// in a case of to "out of range" ex
                        arrayList.set(c, (result.setScale(scale, RoundingMode.HALF_DOWN).stripTrailingZeros().toPlainString()));
                        // it replace the operator with result and remove used numbers from list
                        arrayList.remove(c+1);
                        arrayList.remove(c-1);
                    }catch (Exception ignored){}
                }else{
                    continue;            
                }
                c=0;
                //loop reset, as arrayList changed size
            }
            return arrayList;
        }
    }
}
