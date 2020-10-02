package com.bysj.yrj;

import com.bysj.yrj.LoginActivity;
import com.bysj.yrj.RegisterActivity;
import com.bysj.yrj.bean.UserInfo;
import com.bysj.yrj.dao.ComData;
import com.bysj.yrj.dao.UserDao;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends Activity {
    //成员变量
	EditText editname,editpass;
	Button butlogin,butreg;
	CheckBox checksave;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //调用方法初始化
        this.init();
        
        //调用方法 读取保存的用户信息
        this.readLoginInfo();
        
        //为“新用户注册” 添加按钮监听事件
        this.butreg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//切换到用户注册界面的切换方法	
				Intent abc=new Intent(LoginActivity.this,RegisterActivity.class);
				startActivity(abc);
				
			}
		});
        //点击登录按钮 为登录按钮事件添加监听
        this.butlogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//调用相关登录方法
				loginAction();
			}
		});
    }
    /**
     * 初始化界面
     */
    public void init(){
    	this.editname=(EditText) findViewById(R.id.loginditname);
    	this.editpass=(EditText) findViewById(R.id.logeditpass);
    	this.butlogin=(Button) findViewById(R.id.Logbutloging);
    	this.butreg=(Button) findViewById(R.id.logbutredister);
    	this.checksave=(CheckBox) findViewById(R.id.checkBox1);
    }
    
    /**
     * 登录按钮功能 
     */
    public void loginAction(){
    	//1.获取用户输入的用户名与密码
    	String name=this.editname.getText().toString();
    	String pass=this.editpass.getText().toString();
    	//2.判断输入是否为空
    	if(name.length()==0||pass.length()==0){
    		Toast.makeText(this, "数据输入不完整，请修改", Toast.LENGTH_LONG).show();
    		return;
    	}
    	//3.调用相关方法进行在用户信息表里查询数据库
    	UserDao udao=new UserDao(this);
    	UserInfo utem=udao.checkUser(name, pass);
    	//4.处理结果
    	if(utem==null){
    		//如果用户信息表中用户信息为空--登录失败
    		Toast.makeText(this, "用户名或密码错误，请修改！", Toast.LENGTH_LONG).show();
    	}else{
    		//如果用户信息表中有用户信息--登录成功--合法用户
    		Toast.makeText(this, "用户登录成功！", Toast.LENGTH_LONG).show();
    		SharedPreferences sharesave=getSharedPreferences("loginfo",Context.MODE_PRIVATE);
			Editor editor=sharesave.edit();
    		if(this.checksave.isChecked()){
    			//用户选择了保存用户信息 显示用户名与密码
    			editor.putString("uname", name);
    			editor.putString("upass", pass);
    			//提交
    			editor.commit();
    		}else{
    			//用户未选择保存 清空---或者清空 以保存内容
    			editor.clear();
    			//只进行显示用户登录过的用户名不显示密码
    			editor.putString("uname", name);
    			editor.commit();
    		}
    		//合法用户--登陆成功--跳转到用户使用界面
    		ComData.utem=utem;
    		Intent intent=new Intent(this,MainActivity.class);
    		startActivity(intent);
    		//销毁登陆注册界面--返回直接退出
    		this.finish();
    	}
    }
    /**
     * 读取保存的用户名 和 密码
     */
    public void readLoginInfo(){
    	SharedPreferences sharelogin=getSharedPreferences("loginfo",Context.MODE_PRIVATE);
    	//                           ”根据键名进行读取“   “未读取到显示默认的空值”  Context.private
    	String uname=sharelogin.getString("uname", "");
    	String upass=sharelogin.getString("upass", "");
    	//在用户名与密码的框里进行显示读取到的内容
    	this.editname.setText(uname);
    	this.editpass.setText(upass);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_exit) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}








