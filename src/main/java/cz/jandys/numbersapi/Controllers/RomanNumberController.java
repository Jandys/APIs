package cz.jandys.numbersapi.Controllers;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RomanNumberController {
    public int itter = 0;



    private int getValueFromJson(String json){
        return Integer.parseInt(fromRoman(json).replaceAll("\\D",""));
    }

    private int reCalc(int val) {
        if(val >= 1000){
            return 1000;
        }else if(val >= 900){
            return 900;
        }else if(val >= 500){
            return 500;
        }else if(val >= 400){
            return 400;
        }else if(val >= 100){
            return 100;
        }else if(val >= 90){
            return 90;
        }else if(val >= 50){
            return 50;
        }else if(val >= 40){
            return 40;
        }else if(val >= 10){
            return 10;
        }else if(val >= 9){
            return 9;
        }else if(val >= 5){
            return 5;
        }else if(val >= 4){
            return 4;
        }else{
            return 1;
        }
    }

    private String getChar(int val) {
        if(val >= 1000){
            return "M";
        }else if(val >= 900){
            return "CM";
        }else if(val >= 500){
            return "D";
        }else if(val >= 400){
            return "CD";
        }else if(val >= 100){
            return "C";
        }else if(val >= 90){
            return "XC";
        }else if(val >= 50){
            return "L";
        }else if(val >= 40){
            return "XL";
        }else if(val >= 10){
            return "X";
        }else if(val >= 9){
            return "IX";
        }else if(val >= 5){
            return "V";
        }else if(val >= 4){
            return "IV";
        }else{
            return "I";
        }
    }

    private int getValueFromRoman(String val) {
        int retutnValue = 0;
        if(val.length() == 0){
            return 0;
        }
        for(int i = 0; i < val.length(); i++){
            itter = 0;
            if(i<val.length()-1){
                retutnValue += getVals(""+val.charAt(i)+val.charAt(i+1));
            }else {
                retutnValue += getVals(""+val.charAt(i));
            }
        }
        return retutnValue;
    }

    private int getVals(String val){
        switch (val){
            case "i":
                return 1;
            case "v":
                return 5;
            case "x":
                return 10;
            case "l":
                return 50;
            case "c":
                return 100;
            case "d":
                return 500;
            case "m":
                return 1000;
            case "iv":
            case "ix":
                return -1;
            case "xl":
            case "xc":
                return -10;
            case "cd":
            case "cm":
                return -100;
            default:
                try{
                    itter ++;
                    if (itter < 5){
                        return getVals(""+val.charAt(0));
                    }
                    else {
                        return 0;
                    }
                }catch (Exception e){
                    return 0;
                }
        }
    }

    @RequestMapping(value="/fromRoman", produces = "application/json")
    public String fromRoman(@RequestParam(value = "val") String val){
        JSONObject jsonObject = new JSONObject();
        int result = getValueFromRoman(val.toLowerCase().replaceAll("\\d",""));
        jsonObject.put("result",result);
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/toRoman", produces = "application/json")
    public String toRoman(@RequestParam(value = "val") int val){
        JSONObject jsonObject = new JSONObject();
        String output="";
        if(val>0){
            do{
                output += getChar(val);
                val -= reCalc(val);
            }while (val>0);
            jsonObject.put("result", output);
            return jsonObject.toJSONString();
        }
        jsonObject.put("error","cannot do negative numbers");
        return jsonObject.toJSONString();

    }

    @RequestMapping(value = "/addRomans", produces = "application/json")
    public String additionOfRomans(@RequestParam(value = "val1") String val1,@RequestParam(value = "val2") String val2){
        return romanOperations(val1,val2,1);
    }

    @RequestMapping(value = "/subRomans",produces = "application/json")
    public String substractOfRomans(@RequestParam(value = "val1") String val1,@RequestParam(value = "val2") String val2){
        return romanOperations(val1,val2,2);
    }

    @RequestMapping(value = "/multiplyRomans",produces = "application/json")
    public String multiplyOfRomans(@RequestParam(value = "val1") String val1,@RequestParam(value = "val2") String val2){
        return romanOperations(val1,val2,3);
    }

    @RequestMapping(value = "/devideRomans",produces = "application/json")
    public String devisionOfRomans(@RequestParam(value = "val1") String val1,@RequestParam(value = "val2") String val2){
        return romanOperations(val1,val2,4);
    }

    private String romanOperations(String val1,String val2, int operator){
        int iVal1 = getValueFromJson(val1);
        int iVal2 = getValueFromJson(val2);
        int result = 0;
       switch (operator){
           case 1:
               result =iVal1 + iVal2;
               break;
           case 2:
               result =iVal1 - iVal2;
               break;
           case 3:
               result =iVal1 * iVal2;
               break;
           case 4:
               result =iVal1 / iVal2;
               break;
       }
        JSONObject object = new JSONObject();
        object.put("result",result);
        return object.toJSONString();
    }


}
