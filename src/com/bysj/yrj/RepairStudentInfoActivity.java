package com.bysj.yrj;

import com.bysj.yrj.RepairStudentInfoActivity;
import com.bysj.yrj.R;
import com.bysj.yrj.bean.StudentInfo;
import com.bysj.yrj.dao.ComData;
import com.bysj.yrj.dao.AddStudentInfoDao;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RepairStudentInfoActivity extends Activity {
	//成员变量
		TextView repaireditnum,repaireditname;
		EditText repaireditage,repaireditmark;
		RadioButton repairradiomen,repairradiowomen;
		ArrayAdapter<String> proadapter;
		String[] proname={"计算机应用","计算机网络","移动互联开发","WEB前端开发"};
		Spinner repairpro;
		Button butsave,butdel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_repair_student_info);

	//调用方发 初始化 声明控件对象
			this.init();
			//调用方法   公共数据区 获取数据并显示
			this.showOldStudentData();
					//保存按钮
					this.butsave.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							saveAction();
						}
					});
					//删除按钮
					this.butdel.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							deleteAction();
						}
					});
		}
		//初始化 声明的控件 对象
		private void init(){
			this.repaireditnum=(TextView) findViewById(R.id.repairnumtext);
			this.repaireditname=(TextView) findViewById(R.id.repairnametext);
			this.repairradiomen=(RadioButton) findViewById(R.id.repairradiomen);
			this.repairradiowomen=(RadioButton) findViewById(R.id.repairradiowoman);
			this.repaireditage=(EditText) findViewById(R.id.repairageedit);
			this.repairpro=(Spinner) findViewById(R.id.spinner1);
			this.repaireditmark=(EditText) findViewById(R.id.repairmarkedit);
			this.butsave=(Button) findViewById(R.id.repairsavebutton);
			this.butdel=(Button) findViewById(R.id.repairdelbutton);
		}
		/**
		 * 从公共数据区 获取数据并显示
		 * 
		 */
		private void showOldStudentData(){
			//获取存储的数据
			StudentInfo tem=ComData.item;
			//显示获取的数据
			this.repaireditnum.setText(tem.getNum());
			this.repaireditname.setText(tem.getName());
			
    		this.repaireditage.setText(tem.getAge());
    		if(tem.getSex().equals("男"))
    		{
    			repairradiomen.setChecked(true);
    		}
    		else
    		{
    			repairradiowomen.setChecked(true);
    		}
			int n=0;
			String pro=tem.getPro();
			if(pro.equals("计算机应用")){
				n=0;
			}else if(pro.equals("计算机网络")){
				n=1;
			}else if(pro.equals("移动互联开发")){
				n=2;
			}else if(pro.equals("WEB前端开发")){
				n=3;
			}else{
				n=4;
			}
			this.repairpro.setSelection(n);
			this.repaireditmark.setText(tem.getMark());
		}
		/**
		 * 删除 学生信息按钮事件功能
		 */
		private void deleteAction(){
			String  num=ComData.item.getNum();
			//调用方法 删除相关信息
			AddStudentInfoDao adao=new AddStudentInfoDao(this);
	    	long n=adao.deleteById(num);
	    	String mes="学生信息删除失败";
	    	if(n>0){
	    		mes="学生信息删除成功";
	    	}
	    	Toast.makeText(this, mes, Toast.LENGTH_LONG).show();
		}
		/**
		 * 保存学生信息按钮事件功能
		 */
		private void saveAction(){
	    	//1.获取用户输入的信息（用户修改的信息）
	    	String num=this.repaireditnum.getText().toString();
	    	String name=this.repaireditname.getText().toString();
			String sex="男";
    		if(this.repairradiowomen.isChecked()){
    			//
    			sex="女";
    		}
    		String age=this.repaireditage.getText().toString();
	    	String pro=this.repairpro.getSelectedItem().toString();
	    	String mark=this.repaireditmark.getText().toString();
	    	//修改公共数据库相关信息
	    	ComData.item.setNum(num);
	    	ComData.item.setName(name);
	    	ComData.item.setSex(sex);
	    	ComData.item.setAge(age);
	    	ComData.item.setPro(pro);
	    	ComData.item.setMark(mark);
	    	//调用 相关方法 修改数据库
	    	AddStudentInfoDao adao=new AddStudentInfoDao(this);
	    	long n=adao.updateById(ComData.item);
	    	//根据结果显示
	    	String mes="学生信息修改失败";
	    	   if(n>0){
	    		   mes="学生信息修改成功";
	    	   }
	    	Toast.makeText(this, mes, Toast.LENGTH_LONG).show();
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.repair_student_info, menu);
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
