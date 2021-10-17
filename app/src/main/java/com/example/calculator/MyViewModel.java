package com.example.calculator;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.math.*;
//数据模型
public class MyViewModel extends ViewModel {
    private MutableLiveData<String> mainNum;//主数值（用户正在操作数）
    public String num[]={"","","",""};//储存参与计算的数值
    public String sign="",sign2="";//用于储存运算符号
    public boolean havePoint =false;//判断主数值是否有小数
    public MutableLiveData<String> getMainNum(){
        if(mainNum == null){
            mainNum = new MutableLiveData<>();
            mainNum.setValue("0");
        }
        return  mainNum;
    }
    public void setMainNum(String n){
        if(mainNum.getValue().equals("0")){
            mainNum.setValue(n);
        }else{
            if(mainNum.getValue().equals("√")){
                mainNum.setValue(n);
            } else {
            mainNum.setValue(mainNum.getValue() + n );
            }
        }
    }
    public String mainNumWithNum_0_Total(){
        String value="0";
        if(mainNum.getValue().contains(".")||num[0].contains(".")){//如果两个数的其中一个有小数点，那么全是小数
            switch (sign){
                case "+":
                    BigDecimal a1 = new BigDecimal(Double.valueOf(num[0]));
                    BigDecimal a2 = new BigDecimal(Double.valueOf(mainNum.getValue()));
                    value=String.valueOf(a1.add(a2));
                    break;
                case "-":
                    BigDecimal c1 = new BigDecimal(Double.valueOf(num[0]));
                    BigDecimal c2 = new BigDecimal(Double.valueOf(mainNum.getValue()));
                    value=String.valueOf(c1.subtract(c2));
                    break;
                case "*":
                    BigDecimal b1 = new BigDecimal(Double.valueOf(num[0]));
                    BigDecimal b2 = new BigDecimal(Double.valueOf(mainNum.getValue()));
                    value=String.valueOf(b1.multiply(b2));
                    break;
                case "/":
                    BigDecimal d1 = new BigDecimal(Double.valueOf(num[0]));
                    BigDecimal d2 = new BigDecimal(Double.valueOf(mainNum.getValue()));
                    value=String.valueOf(d1.divide(d2));
                    break;
            }
        }else {//如果两个都是整数
            switch (sign){
                case "+":
                    BigDecimal a1 = new BigDecimal(Double.valueOf(num[0]));
                    BigDecimal a2 = new BigDecimal(Double.valueOf(mainNum.getValue()));
                    value=String.valueOf(a1.add(a2));
                    break;
                case "-":
                    BigDecimal c1 = new BigDecimal(Double.valueOf(num[0]));
                    BigDecimal c2 = new BigDecimal(Double.valueOf(mainNum.getValue()));
                    value=String.valueOf(c1.subtract(c2));
                    break;
                case "*":
                    BigDecimal b1 = new BigDecimal(Double.valueOf(num[0]));
                    BigDecimal b2 = new BigDecimal(Double.valueOf(mainNum.getValue()));
                    value=String.valueOf(b1.multiply(b2));
                    break;
                case "/":
                    if(mainNum.getValue().equals("0")){
                        mainNum.setValue("1");
                    }
                    BigDecimal d1 = new BigDecimal(Double.valueOf(num[0]));
                    BigDecimal d2 = new BigDecimal(Double.valueOf(mainNum.getValue()));
                    value=String.valueOf(d1.divide(d2));
                    break;
            }
        }
        return value;
    }
    public String mainNumWithNum_1_Total(){
        String value="0";
        if(mainNum.getValue().contains(".")||num[1].contains(".")){//如果两个数的其中一个有小数点，那么全是小数
            switch (sign2){
                case "*":
                    BigDecimal b1 = new BigDecimal(Double.valueOf(num[1]));
                    BigDecimal b2 = new BigDecimal(Double.valueOf(mainNum.getValue()));
                    value=String.valueOf(b1.multiply(b2));
                    break;
                case "/":
                    BigDecimal d1 = new BigDecimal(Double.valueOf(num[1]));
                    BigDecimal d2 = new BigDecimal(Double.valueOf(mainNum.getValue()));
                    value=String.valueOf(d1.divide(d2));
                    break;
            }
        }else {//如果两个都是整数
            switch (sign2){
                case "*":
                    BigDecimal b1 = new BigDecimal(Double.valueOf(num[1]));
                    BigDecimal b2 = new BigDecimal(Double.valueOf(mainNum.getValue()));
                    value=String.valueOf(b1.multiply(b2));
                    break;
                case "/":
                    BigDecimal d1 = new BigDecimal(Double.valueOf(num[1]));
                    BigDecimal d2 = new BigDecimal(Double.valueOf(mainNum.getValue()));
                    value=String.valueOf(d1.divide(d2));
                    break;
            }
        }
        return value;

    }
    public String Sqrt(String s){
        String value="0";
        value=String.valueOf(Math.sqrt(Double.valueOf(s)));
        return value;
    }
}
