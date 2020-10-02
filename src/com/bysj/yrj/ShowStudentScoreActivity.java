package com.bysj.yrj;

import java.util.ArrayList;
import com.bysj.yrj.bean.StudentScore;
import com.bysj.yrj.dao.AddStudentScoreDao;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ShowStudentScoreActivity extends Activity {
	//成员变量
	Button butscoreall,butscoreshow;
	TextView scoreedit;
	ListView listshow;
	ArrayList<StudentScore> adata;//存储 查询的结果
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_student_score);
		//调用方法进行初始化
				this.init();
				//showdata在方法初始化之后调用进入直接进行显示学生的信息
//				this.showData();
				//为查询所有学生信息按钮事件添加监听
				this.butscoreall.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//showdata在按钮监听事件里调用 点击查询进行查询全部
						showscoreData();
					}
				});
				//根据学号查询所有学生信息按钮添加监听
				this.butscoreshow.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String num=scoreedit.getText().toString();
						shownumscoreData(num);
					}
				});
	}
	/*
	 * 初始化声明的控件对象
	 */
		private void init(){
			this.butscoreall=(Button) findViewById(R.id.showallbutton);
			this.butscoreshow=(Button) findViewById(R.id.showscorebutton);
			this.scoreedit=(TextView) findViewById(R.id.numscoreedit);
			this.listshow=(ListView) findViewById(R.id.listView11);
		}
		/**
		 *查询所有学生成绩数据表并显示在ListView
		 * @param type
		 */
		private void showscoreData(){
			//1.调用 相关方法  插寻数据
			AddStudentScoreDao adao=new AddStudentScoreDao(this);
			this.adata=adao.getallscoreData();
			//2.构建列表搜需要的数据源
			ArrayList<String> sdata=new ArrayList<String>();
			for(int i=0;i<this.adata.size();i++){
				//从仓库中 读取 第i条 记录
				StudentScore tem=this.adata.get(i);
				//构建列表 每行显示内容
				String str=tem.getNum()+"\t\t"+tem.getName()+"\t\t"+tem.getAndroid()+"\t\t"+tem.getJava()+"\t\t"+tem.getHtml();
				//添加数据源
				sdata.add(str);
			}
			//数据源 与 列表 “桥”
			ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, sdata);
			//设置列表的配置器
			this.listshow.setAdapter(adapter);	
		}
		/**
		 *根据学号查询单个学生成绩 数据并显示在ListView
		 * @param type
		 */
		public void shownumscoreData(String num){
			//1.调用 相关方法  查询数据
			AddStudentScoreDao adao=new AddStudentScoreDao(this);
			this.adata=adao.getScorenumData(num);
			//2.构建列表搜需要的数据源
			ArrayList<String> sdata=new ArrayList<String>();
			for(int i=0;i<this.adata.size();i++){
				//从仓库中 读取 第i条 记录
				StudentScore tem=this.adata.get(i);
				//构建列表 每行显示内容
				String str=tem.getNum()+"\t\t"+tem.getName()+"\t\t"+tem.getAndroid()+"\t\t"+tem.getJava()+"\t\t"+tem.getHtml();
				//添加数据源
				sdata.add(str);
			}
			//数据源 与 列表 “桥”
			ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, sdata);
			//设置列表的配置器
			this.listshow.setAdapter(adapter);	
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_student_score, menu);
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
