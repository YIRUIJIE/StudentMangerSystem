package com.bysj.yrj;

import com.bysj.yrj.dao.UserDao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
    //成员变量
	EditText editname,editpass1,editpass2;
	Button butsave;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		//调用方法初始化
		this.init();
		//保存按钮添加事件监听
		this.butsave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//调用保存 方法 -- saveAction
				saveAction();
			}
		});
	}
	/**
	 * 
	 * 初始化
	 */
	private void init(){
		this.editname=(EditText) findViewById(R.id.regeditname);
		this.editpass1=(EditText) findViewById(R.id.regditpass1);
		this.editpass2=(EditText) findViewById(R.id.regditpass2);
		this.butsave=(Button) findViewById(R.id.regbutsave);
	}
	/**
	 * 保存按钮具体事件处理方法
	 */
	public void saveAction(){
		        //1.获取
				String name=this.editname.getText().toString();
				String pass1=this.editpass1.getText().toString();
				String pass2=this.editpass2.getText().toString();
				//2.判断输入是否为空
				if(name.length()==0||pass1.length()==0||pass2.length()==0){
					Toast.makeText(this,"输入数据不完整，请修改",Toast.LENGTH_LONG).show();
					//返回
					return;
				}
				//3.判断密码两次是否一致
				if(!pass1.equals(pass2)){
					//两次密码不一致
					Toast.makeText(this,"两次密码不一致，请修改",Toast.LENGTH_LONG).show();
					//返回
					return;
				}
				//4.调用相关方法 修改 数据库内容
				UserDao udao=new UserDao(this);
				long n=udao.addUser(name, pass1);
				if(n>0){
					//添加 --注册成功
					Toast.makeText(this,"新用户注册成功",Toast.LENGTH_LONG).show();
//					//从新用户注册界面进行跳转到用户的登录界面 
//					Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
//		    		startActivity(intent);
//		    		//跳转后 进行销毁新用户注册界面
//		    		this.finish();
				}else{
					//添加--注册失败
					Toast.makeText(this,"新用户注册失败",Toast.LENGTH_LONG).show();
				}
	}
	/**
	 * 重置按钮具体事件处理方法
	 */
	public void resetAction(){
		this.editname.setText("");
		this.editpass1.setText("");
		this.editpass2.setText("");
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
