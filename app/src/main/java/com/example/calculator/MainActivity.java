package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;//储存组件类

    private MyViewModel myViewModel;//数据模型
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);//获取所有控件
        myViewModel = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);//获取数据模型
        //事件监听
        myViewModel.getMainNum().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {//监听 mainNum 数据发生变化
                binding.myTextView.setText(myViewModel.getMainNum().getValue());//让 myTextView 显示 mainNum的数值
                if(myViewModel.sign2.equals("")){
                if(myViewModel.sign.equals("")){
                    if(myViewModel.num[0].equals("√")&&(!myViewModel.getMainNum().getValue().equals("√"))&&(!myViewModel.getMainNum().getValue().equals(""))){ //判断是否为根号
                        //数值里带√
                        if(myViewModel.getMainNum().getValue().contains("√")){
                            String s1="",s2="";
                            int end=0;
                            for(int i=0;i<myViewModel.getMainNum().getValue().length();i++){
                                if(myViewModel.getMainNum().getValue().charAt(i)=='√'){
                                    end=i;
                                   s2 = myViewModel.getMainNum().getValue().substring(i+1,myViewModel.getMainNum().getValue().length());
                                   System.out.println("s2="+s2);
                                }else{
                                    s1 +=myViewModel.getMainNum().getValue().substring(0,end);
                                    System.out.println("s1="+s1);
                                }
                            }
                            if(Integer.valueOf(s2)<0) {
                                Toast.makeText(getApplicationContext(), "负数没有平方根", Toast.LENGTH_LONG).show();
                                myViewModel.sign2=""; //清空操作
                                myViewModel.num[1]="";
                                myViewModel.sign="";
                                myViewModel.num[0]="";
                                myViewModel.getMainNum().setValue("0");
                                myViewModel.havePoint=false;
                            }
                            else{ //被开根号数不小于零，进行操作
                                myViewModel.num[3]=s1;
                                myViewModel.num[1]=s2;
                                myViewModel.num[2]=Double.valueOf(myViewModel.Sqrt(s2))*Double.valueOf(s1)+"";
                            }
                        }else { //数值里不带“√”
                            System.out.println("test"+myViewModel.getMainNum().getValue());
                        if(Double.valueOf(myViewModel.getMainNum().getValue())<0) {
                            Toast.makeText(getApplicationContext(), "负数没有平方根", Toast.LENGTH_LONG).show();
                            myViewModel.sign2=""; //清空操作
                            myViewModel.num[1]="";
                            myViewModel.sign="";
                            myViewModel.num[0]="";
                            myViewModel.getMainNum().setValue("0");
                            myViewModel.havePoint=false;
                        }
                        else{ //被开根号数不小于零，进行操作
                            myViewModel.num[1]=myViewModel.getMainNum().getValue();
                            myViewModel.num[2]=myViewModel.Sqrt(myViewModel.getMainNum().getValue());
                        }
                        }

                    }else{
                        binding.textView.setText(myViewModel.getMainNum().getValue());//让 textView 显示式子
                    }
            }else{//如果是a+b形式的式子
                    binding.textView.setText(myViewModel.num[0] + myViewModel.sign+myViewModel.getMainNum().getValue());

                }
            }else{//如果是像a+b*c
                    binding.textView.setText(myViewModel.num[0]+myViewModel.sign+myViewModel.num[1]+myViewModel.sign2+myViewModel.getMainNum().getValue());

            }}
        });
        binding.button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("0");
            }

        });
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("1");
            }

        });        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("2");
            }

        });
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("3");
            }

        });
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("4");
            }

        });
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("5");
            }

        });
        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("6");
            }

        });
        binding.button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("7");
            }

        });
        binding.button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("8");
            }

        });
        binding.button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("9");
            }

        });
        binding.buttonSqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myViewModel.getMainNum().getValue().contains("√")){
                    System.out.println("ok");
                    Toast.makeText(getApplicationContext(),"暂不支持两个根号",Toast.LENGTH_LONG).show();
                    //Emp中的方法，清空
                    myViewModel.sign2="";
                    myViewModel.num[1]="";
                    myViewModel.sign="";
                    myViewModel.num[0]="";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;

                }
                myViewModel.setMainNum("√");
                myViewModel.num[0]="√";
            }
        });
        binding.buttonPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(! myViewModel.havePoint){
                    myViewModel.getMainNum().setValue(myViewModel.getMainNum().getValue()+".");
                    myViewModel.havePoint=true;
                }
            }
        });
        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign.equals("")){
                    myViewModel.sign="+";
                    myViewModel.num[0]=myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }else if(myViewModel.sign2.equals("")){//如果是a+b
                    myViewModel.num[0] = myViewModel.mainNumWithNum_0_Total();
                    myViewModel.sign="+";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }else {//如果是a+b*c
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_1_Total());
                    myViewModel.sign2="";
                    myViewModel.num[1]="";
                    myViewModel.num[0]=myViewModel.mainNumWithNum_0_Total();
                    myViewModel.sign="+";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;


                }
            }
        });
        binding.buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign.equals("")){
                    myViewModel.sign="-";
                    myViewModel.num[0]=myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }else if(myViewModel.sign2.equals("")){//如果是a+b
                    myViewModel.num[0] = myViewModel.mainNumWithNum_0_Total();
                    myViewModel.sign="-";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }else {//如果是a+b*c
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_1_Total());
                    myViewModel.sign2="";
                    myViewModel.num[1]="";
                    myViewModel.num[0]=myViewModel.mainNumWithNum_0_Total();
                    myViewModel.sign="-";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;


                }
            }
        });
        binding.buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign.equals("")){
                    myViewModel.sign="*";
                    myViewModel.num[0] = myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }else if(myViewModel.sign2.equals("")){
                    if(myViewModel.sign.equals("*")||myViewModel.sign.equals("/")){
                        myViewModel.num[0] = myViewModel.mainNumWithNum_0_Total();
                        myViewModel.sign="*";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint=false;
                    }else{//如果sign是减号或加号
                        myViewModel.num[1]=myViewModel.getMainNum().getValue();
                        myViewModel.sign2="*";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint=false;
                    }
                }else { //如果是像a+b*c的式子时
                    myViewModel.num[1] = myViewModel.mainNumWithNum_1_Total();
                    myViewModel.sign2="*";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }
            }
        });
        binding.buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign.equals("")){
                    myViewModel.sign="/";
                    myViewModel.num[0] = myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }else if(myViewModel.sign2.equals("")){
                    if(myViewModel.sign.equals("*")||myViewModel.sign.equals("/")){
                        myViewModel.num[0] = myViewModel.mainNumWithNum_0_Total();
                        myViewModel.sign="/";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint=false;
                    }else{//如果sign是减号或加号
                        myViewModel.num[1]=myViewModel.getMainNum().getValue();
                        myViewModel.sign2="/";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint=false;
                    }
                }else { //如果是像a+b*c的式子时
                    myViewModel.num[1] = myViewModel.mainNumWithNum_1_Total();
                    myViewModel.sign2="/";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }
            }
        });
        binding.buttonEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.sign2="";
                myViewModel.num[1]="";
                myViewModel.sign="";
                myViewModel.num[0]="";
                myViewModel.getMainNum().setValue("0");
                myViewModel.havePoint=false;
            }
        });
        binding.buttonCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign2.equals("")){
                    if(!myViewModel.sign.equals("")){
                        if(myViewModel.sign.equals("/")&&myViewModel.getMainNum().getValue().equals("0")){
                            Toast.makeText(getApplicationContext(),"不能除以0",Toast.LENGTH_LONG).show();
                            //清空
                            myViewModel.sign2="";
                            myViewModel.num[1]="";
                            myViewModel.sign="";
                            myViewModel.num[0]="";
                            myViewModel.getMainNum().setValue("0");
                            myViewModel.havePoint=false;
                            return;
                        }
                      myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_0_Total());

                      if(myViewModel.getMainNum().getValue().contains("."))
                          myViewModel.havePoint=true;
                      else
                          myViewModel.havePoint=false;
                      myViewModel.num[0]="";
                      myViewModel.sign="";
                    }
                }else{ //a+b*c格式
                    //判断不能/0；
                    if((myViewModel.sign.equals("/")||myViewModel.sign2.equals("/"))&&myViewModel.getMainNum().getValue().equals("0")){
                        Toast.makeText(getApplicationContext(),"不能除以0",Toast.LENGTH_LONG).show();
                        //清空
                        myViewModel.sign2="";
                        myViewModel.num[1]="";
                        myViewModel.sign="";
                        myViewModel.num[0]="";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint=false;
                        return;
                    }
                        myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_1_Total());
                        myViewModel.num[1]="";
                        myViewModel.sign2="";
                        myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_0_Total());
                        if(myViewModel.getMainNum().getValue().contains("."))
                            myViewModel.havePoint=true;
                        else
                            myViewModel.havePoint=false;
                        myViewModel.num[0]="";
                        myViewModel.sign="";

                    }
                if(myViewModel.num[0].equals("√")){
                    binding.textView.setText(myViewModel.num[3]+"√"+myViewModel.num[1]+"="+myViewModel.num[2]);
                    myViewModel.num[0]="";
                    myViewModel.num[1]="";
                    myViewModel.getMainNum().setValue(myViewModel.num[2]);
                    myViewModel.num[2]="";

                }
                else{
                  binding.textView.setText(myViewModel.getMainNum().getValue());
                }
                }

        });
        binding.imageButtonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!myViewModel.getMainNum().getValue().equals(("0"))){
                    myViewModel.getMainNum().setValue(myViewModel.getMainNum().getValue().substring(0,myViewModel.getMainNum().getValue().length()-1));
                    System.out.println("值"+myViewModel.getMainNum().getValue());
                    if(myViewModel.getMainNum().getValue().equals("")){
                        myViewModel.getMainNum().setValue("0");
                    }
                }
            }
        });
        binding.buttonPer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.getMainNum().setValue(String.valueOf(Double.valueOf(myViewModel.getMainNum().getValue())/100));
            }
        });
        binding.buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(myViewModel.getMainNum().getValue());
                myViewModel.getMainNum().setValue(String.valueOf(-1*Double.valueOf(myViewModel.getMainNum().getValue())));
            }
        });
//        binding.buttonSqrt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(Double.valueOf(myViewModel.getMainNum().getValue())<0){
//                    Toast.makeText(getApplicationContext(), "负数没有平方根", Toast.LENGTH_LONG).show();
//                    myViewModel.sign2="";
//                    myViewModel.num[1]="";
//                    myViewModel.sign="";
//                    myViewModel.num[0]="";
//                    myViewModel.getMainNum().setValue("0");
//                    myViewModel.havePoint=false;
//                }else{
//                    myViewModel.num[0]="√";
//                    myViewModel.num[1]=myViewModel.getMainNum().getValue();
//                    binding.myTextView.setText("√" +myViewModel.num[1]);
//                    System.out.println("输入"+myViewModel.num[1]);
//                    myViewModel.num[1]=myViewModel.Sqrt(myViewModel.num[1]);
//                }
//            }
//        });
    }
}