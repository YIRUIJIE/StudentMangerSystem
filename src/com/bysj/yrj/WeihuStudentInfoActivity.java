package com.bysj.yrj;

import java.util.ArrayList;
import com.bysj.yrj.RepairStudentInfoActivity;
import com.bysj.yrj.dao.ComData;
import com.bysj.yrj.bean.StudentInfo;
import com.bysj.yrj.dao.AddStudentInfoDao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class WeihuStudentInfoActivity extends Activity {
	//成员变量
		Button butshow;
		TextView numedit;
		ListView listshow;
		ArrayList<StudentInfo> adata;//存储 查询的结果
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weihu_student_info);
		//调用方法进行初始化
		this.init();
		//为根据学号进行查询按钮添加事件监听
		this.butshow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String num=numedit.getText().toString();
				shownumData(num);
			}
		});
//为 listshow 控件添加 监听
				this.listshow.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						// TODO Auto-generated method stub
						listAction(arg2);
					}
				});
		
	}
	/*
	 * 初始化声明的控件对象
	 */
		private void init(){
			this.butshow=(Button) findViewById(R.id.showbutton);
			this.numedit=(TextView) findViewById(R.id.numedit);
			this.listshow=(ListView) findViewById(R.id.listView1);
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
		/**
		 * 用户点击 查询出的学生信息列表 事件处理
		 */
		private void listAction(int num){
		     //1.从数据中 获取第 num的数据
			StudentInfo tem=this.adata.get(num);
			//2.将获取的数据存储到公共数据区   ---在学生信息维护界面 获取
			ComData.item=tem;
			//3.跳转到 信息修改 删除界面
			Intent intent=new Intent(this,RepairStudentInfoActivity.class);
			startActivity(intent);
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weihu_student_info, menu);
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
