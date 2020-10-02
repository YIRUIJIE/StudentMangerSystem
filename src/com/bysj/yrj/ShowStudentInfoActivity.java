package com.bysj.yrj;

import java.util.ArrayList;
import com.bysj.yrj.bean.StudentInfo;
import com.bysj.yrj.dao.AddStudentInfoDao;
import com.bysj.yrj.R;
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

public class ShowStudentInfoActivity extends Activity {
	//成员变量
	Button butall,butshow;
	TextView numedit;
	ListView listshow;
	ArrayList<StudentInfo> adata;//存储 查询的结果
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_student_info);
		//调用方法进行初始化
		this.init();
		//showdata在方法初始化之后调用进入直接进行显示学生的信息
//		this.showData();
		//为查询所有学生信息按钮事件添加监听
		this.butall.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//showdata在按钮监听事件里调用 点击查询进行查询全部
				showData();
			}
		});
		//根据学号查询所有学生信息按钮添加监听
		this.butshow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String num=numedit.getText().toString();
				shownumData(num);
			}
		});
	}
/*
 * 初始化声明的控件对象
 */
	private void init(){
		this.butall=(Button) findViewById(R.id.showallbutton);
		this.butshow=(Button) findViewById(R.id.showbutton);
		this.numedit=(TextView) findViewById(R.id.numedit);
		this.listshow=(ListView) findViewById(R.id.listView1);
	}
	/**
	 *查询所有学生信息 数据表并显示
	 * @param type
	 */
	private void showData(){
		//1.调用 相关方法  插寻数据
		AddStudentInfoDao adao=new AddStudentInfoDao(this);
		this.adata=adao.getStudentData();
		//2.构建列表搜需要的数据源
		ArrayList<String> sdata=new ArrayList<String>();
		for(int i=0;i<this.adata.size();i++){
			//从仓库中 读取 第i条 记录
			StudentInfo tem=this.adata.get(i);
			//构建列表 每行显示内容
			String str=tem.getNum()+"\t\t"+tem.getName()+"\t\t"+tem.getSex()+"\t\t"+tem.getAge()+"\t\t"+tem.getPro()+"\t\t"+tem.getMark();
			//添加数据源
			sdata.add(str);
		}
		//数据源 与 列表 “桥”
		ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, sdata);
		//设置列表的配置器
		this.listshow.setAdapter(adapter);	
	}
	/**
	 *根据学号查询单个学生信息 数据并显示
	 * @param type
	 */
	public void shownumData(String num){
		//1.调用 相关方法  查询数据
		AddStudentInfoDao adao=new AddStudentInfoDao(this);
		this.adata=adao.getStudentnumData(num);
		//2.构建列表搜需要的数据源
		ArrayList<String> sdata=new ArrayList<String>();
		for(int i=0;i<this.adata.size();i++){
			//从仓库中 读取 第i条 记录
			StudentInfo tem=this.adata.get(i);
			//构建列表 每行显示内容
			String str=tem.getNum()+"\t\t"+tem.getName()+"\t\t"+tem.getSex()+"\t\t"+tem.getAge()+"\t\t"+tem.getPro()+"\t\t"+tem.getMark();
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
		getMenuInflater().inflate(R.menu.show_student_info, menu);
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
