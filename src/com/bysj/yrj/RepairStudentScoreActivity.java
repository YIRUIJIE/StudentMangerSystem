package com.bysj.yrj;

import com.bysj.yrj.bean.StudentScore;
import com.bysj.yrj.dao.AddStudentScoreDao;
import com.bysj.yrj.dao.ComData;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RepairStudentScoreActivity extends Activity {
	TextView repairnum,repairname;
	EditText repandroid,repjava,rephtml;
	Button repbut,delbut;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_repair_student_score);
		//调用方发 初始化 声明控件对象
		this.init();
		//调用方法   公共数据区 获取数据并显示
		this.showOldStudentData();
				//保存修改按钮
				this.repbut.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						saveAction();
					}
				});
				//删除成绩按钮
				this.delbut.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						deleteAction();
					}
				});
	}
	//初始化 声明的控件 对象
	private void init(){
		this.repairnum=(TextView) findViewById(R.id.repscorenumtext);
		this.repairname=(TextView) findViewById(R.id.repscorenametext);
		this.repandroid=(EditText) findViewById(R.id.repscoreandroidtext);
		this.repjava=(EditText) findViewById(R.id.repscorejavatext);
		this.rephtml=(EditText) findViewById(R.id.repscorehtmltext);
		this.repbut=(Button) findViewById(R.id.repscorebutton);
		this.delbut=(Button) findViewById(R.id.repdelscorebutton);
	}
	/**
	 * 从公共数据区 获取数据并显示
	 * 
	 */
	private void showOldStudentData(){
		//获取存储的数据
		StudentScore tem=ComData.stem;
		//显示获取的数据
		this.repairnum.setText(tem.getNum());
		this.repairname.setText(tem.getName());
		this.repandroid.setText(tem.getAndroid());
		this.repjava.setText(tem.getJava());
		this.rephtml.setText(tem.getHtml());
	}
	/**
	 * 删除 学生成绩按钮事件功能
	 */
	private void deleteAction(){
		String  num=ComData.stem.getNum();
		//调用方法 删除相关信息
		AddStudentScoreDao adao=new AddStudentScoreDao(this);
    	long n=adao.deleteById(num);
    	String mes="学生成绩删除失败";
    	if(n>0){
    		mes="学生成绩删除成功";
    	}
    	Toast.makeText(this, mes, Toast.LENGTH_LONG).show();
	}
	/**
	 * 保存学生成绩按钮事件功能
	 */
	private void saveAction(){
    	//1.获取用户输入的成绩（用户修改的成绩）
    	String num=this.repairnum.getText().toString();
    	String name=this.repairname.getText().toString();
		String android=this.repandroid.getText().toString();
    	String java=this.repjava.getText().toString();
    	String html=this.rephtml.getText().toString();
    	//修改公共数据库相关成绩
    	ComData.stem.setNum(num);
    	ComData.stem.setName(name);
    	ComData.stem.setAndroid(android);
    	ComData.stem.setJava(java);
    	ComData.stem.setHtml(html);
    	//调用 相关方法 修改数据库
    	AddStudentScoreDao adao=new AddStudentScoreDao(this);
    	long n=adao.updateById(ComData.stem);
    	//根据结果显示
    	String mes="学生成绩修改失败";
    	   if(n>0){
    		   mes="学生成绩修改成功";
    	   }
    	Toast.makeText(this, mes, Toast.LENGTH_LONG).show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.repair_student_score, menu);
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
