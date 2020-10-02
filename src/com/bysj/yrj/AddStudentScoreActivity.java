package com.bysj.yrj;

import java.util.ArrayList;

import com.bysj.yrj.bean.StudentInfo;
import com.bysj.yrj.bean.StudentScore;
import com.bysj.yrj.dao.AddStudentInfoDao;
import com.bysj.yrj.dao.AddStudentScoreDao;

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

public class AddStudentScoreActivity extends Activity {
	//成员变量
	TextView num,name;
	EditText numtext,androidtext,javatext,htmltext;
	Button showbut,addscorebut,resscorebut;
	ArrayList<StudentInfo> adata;//存储 查询的结果
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_student_score);
		//调用init()方法
		this.init();
		
		//为按学号查询按钮添加事件监听
		this.showbut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String num=numtext.getText().toString();
				getStudentnumData(num);
			}
		});
		 //添加按钮事件添加监听
        this.addscorebut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View V) {
				//添加按钮的事件处理代码
				addscoreaction();
			}
		});
        //清空按钮事件处理方法
        this.resscorebut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				qingkongscoreaction();
			}
		});
	}
	//初始化声明的控件对象
	public void init(){
		this.numtext=(EditText) findViewById(R.id.numeditscore);
		this.num=(TextView) findViewById(R.id.addscorenumtext);
		this.name=(TextView) findViewById(R.id.addscorenametext);
		this.androidtext=(EditText) findViewById(R.id.addscoreandroidtext);
		this.javatext=(EditText) findViewById(R.id.addscorejavatext);
		this.htmltext=(EditText) findViewById(R.id.addscorehtmltext);
		this.showbut=(Button) findViewById(R.id.showbuttonscore);
		this.addscorebut=(Button) findViewById(R.id.addscorebutton);
		this.resscorebut=(Button) findViewById(R.id.resscorebutton);
	}
	/**
	 * 通过输入的学生学号进行查询 ，从学生信息表中获取数据并显示
	 * 
	 */
	public void getStudentnumData(String num){
		//1.调用 相关方法  查询数据
		AddStudentInfoDao adao=new AddStudentInfoDao(this);
		this.adata=adao.getStudentnumData(num);
		//2.构建列表搜需要的数据源
		//ArrayList<String> sdata=new ArrayList<String>();
		for(int i=0;i<this.adata.size();i++){
			//从仓库中 读取 第i条 记录
			StudentInfo tem=this.adata.get(i);
			//构建列表 每行显示内容
			String str=tem.getNum();
			String str1=tem.getName();
			//添加数据源
			 this.num.setText(str);
			 this.name.setText(str1);
		}
	}
	
	//添加按钮事件处理方法
    public void addscoreaction(){
 			//获取用户输入的信息
    		String num=this.num.getText().toString();
    		String name=this.name.getText().toString();
     		String android=this.androidtext.getText().toString();
     		String java=this.javatext.getText().toString();
 			String html=this.htmltext.getText().toString();
 			//调用相关 存储
 			StudentScore tem=new StudentScore();
 			tem.setNum(num);
 			tem.setName(name);
 			tem.setAndroid(android);
 			tem.setJava(java);
 			tem.setHtml(html);
 			AddStudentScoreDao adao=new AddStudentScoreDao(this);
 			long n=adao.addStudentScore(tem);
 			//3.根据结果显示
 			String mes="学生成绩添加失败";
 			if(n>0){
 				mes="学生成绩添加成功";
 			}
 			Toast.makeText(this, mes, Toast.LENGTH_LONG).show();  		
 	}
    //清空按钮事件处理方法
    public void qingkongscoreaction(){
    	this.numtext.setText("");
 	    this.num.setText("");
 	    this.name.setText("");
 	    this.androidtext.setText("");
 	    this.javatext.setText("");
 	    this.htmltext.setText("");
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_student_score, menu);
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
