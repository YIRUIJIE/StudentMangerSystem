package com.bysj.yrj;

import com.bysj.yrj.R;
import com.bysj.yrj.dao.ComData;
import com.bysj.yrj.dao.UserDao;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PassRepairActivity extends Activity {
    //成员变量
	EditText editpass1,editpass2,editpass3;
	Button butsave,butres;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pass_repair);
		//初始化 调用方法
		this.init();
		
		//保存按钮 事件监听
		this.butsave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//调用保存按钮事件方法
				saveAction();
			}
		});
		
		//重置按钮 事件监听 
		this.butres.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//调用 重置按钮事件 方法
				resAction();
			}
		});
		
	}
	/**
	 * 初始化
	 */
	private void init(){
		this.editpass1=(EditText) findViewById(R.id.paeditpass1);
		this.editpass2=(EditText) findViewById(R.id.paeditpass2);
		this.editpass3=(EditText) findViewById(R.id.paeditpass3);
		this.butsave=(Button) findViewById(R.id.psbutsave);
		this.butres=(Button) findViewById(R.id.psbutres);
	}
	/**
	 *保存按钮的事件处理
	 */
	public void saveAction(){
		//1.获取用户的数据（原，新，重复）
		String pass1=this.editpass1.getText().toString();
		String pass2=this.editpass2.getText().toString();
		String pass3=this.editpass3.getText().toString();
		//为空判断  省略
		//2.判读原密码 是否正确  pass1 登陆 用密码  ComData.utem 中
		String pass=ComData.utem.getUserpass();
		if(!pass1.equals(pass)){
			Toast.makeText(this, "原密码输入有错，请修改", Toast.LENGTH_LONG).show();
			return ;
		}
		//3.判读两次 密码 是否一致
		if(!pass2.equals(pass3)){
			Toast.makeText(this, "两次密码不一致，请修改", Toast.LENGTH_LONG).show();
			return ;
		}
		//4.调用相关方法修改数据库
		UserDao udao=new UserDao(this);
		int id=ComData.utem.getId();
		long n=udao.repairPassById(id, pass2);
		//修改用户保存的密码  并验证
		if(n>0){
			//用户密码修改成功
			Toast.makeText(this, "密码修改成功", Toast.LENGTH_LONG).show();
			//进行设置更新 修改密码
			ComData.utem.setUserpass(pass2);
			return ;
		}else{
			//用户密码修改失败
			Toast.makeText(this, "密码修改失败", Toast.LENGTH_LONG).show();
			return ;
		}
	}
	/**
	 *重置按钮的事件处理
	 */
	public void resAction(){
		this.editpass1.setText("");
		this.editpass2.setText("");
		this.editpass3.setText("");
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pass_repair, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
