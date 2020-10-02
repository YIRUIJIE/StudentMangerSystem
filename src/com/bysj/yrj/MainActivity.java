package com.bysj.yrj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.bysj.yrj.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;


public class MainActivity extends Activity {
	//成员变量
	GridView gvinfo;
	//定义数组 相关数据：图（int）、文字（String）
	String[] title=new String[]{"学生信息添加","学生信息维护","学生信息查询","学生成绩添加","学生成绩维护","学生成绩查询","系统管理","使用帮助","退出软件"};
	int[] image=new int[]{R.drawable.addinfo,R.drawable.weihuinfo,R.drawable.showinfo,R.drawable.addscore,
			R.drawable.weihuscore,R.drawable.showscore,R.drawable.userpass,R.drawable.help,R.drawable.exit};
	ArrayList<Map<String,Object>> data;//用来包装的数据源 
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      // 创建一个对象 进行--准备数据源
      		this.data=new ArrayList<Map<String,Object>>();
      		for(int i=0;i<title.length;i++){
      			//构建 一个 Map对象  子类 hash map
      			Map tem=new HashMap();
      			//键--值--对--组成一个小布局
      			tem.put("mainimage",image[i]);
      			tem.put("miantitle",title[i]);
      			//“仓库”添加的内容 都是 map 对象--把每一个小布局放入到界面中显示
      			data.add(tem);
      		}
      		//初始化简单的适配器  数据源  与 GridView 中 “桥”  上下文    数据源           布局 
      		SimpleAdapter sadapter=new SimpleAdapter(this, data, R.layout.mygrid,
      				new String[]{"mainimage","miantitle"}, new int[]{R.id.gridimage,R.id.gridtext});
      		//初始化 GridView并与控件进行绑定
      		this.gvinfo=(GridView) findViewById(R.id.gridView1);
      		//设置GridView的适配器
      		this.gvinfo.setAdapter(sadapter);
      		
      		//gvinfo添加事件处理
      		this.gvinfo.setOnItemClickListener(new OnItemClickListener() {
      			@Override
      			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
      					long id) {
      				//事件处理     position  点击GridView 中的 单元 序号
      				mianAction(position);
      			}
      		});
      	}
      	/**
      	 * 点击 管理图像 事件处理
      	 */
      	public void mianAction(int id){
      		//  界面文字 布局图片与跳转二级界面显示 管理
      		switch(id){
      		case 0:
      			//点击了“学生信息添加”
      			Intent intent0=new Intent(this,AddStudentInfoActivity.class);
      			startActivity(intent0);
      			break;
      		case 1:
      			//点击了“学生信息维护”
      			Intent intent1=new Intent(this,WeihuStudentInfoActivity.class);
      			startActivity(intent1);
      			break;
      		case 2:
      			//点击了“学生信息查询”
      			Intent intent2=new Intent(this,ShowStudentInfoActivity.class);
      			startActivity(intent2);
      			break;
      		case 3:
      			//点击了“学生成绩添加”
      			Intent intent3=new Intent(this,AddStudentScoreActivity.class);
      			startActivity(intent3);
      			break;
      		case 4:
      			//点击了“学生成绩维护”
      			Intent intent4=new Intent(this,WeihuStudentScoreActivity.class);
      			startActivity(intent4);
      			break;
      		case 5:
      			//点击了“学生成绩查询”
      			Intent intent5=new Intent(this,ShowStudentScoreActivity.class);
      			startActivity(intent5);
      			break;
      		case 6:
      			//点击了“系统管理”
      			Intent intent6=new Intent(this,PassRepairActivity.class);
      			startActivity(intent6);
      			break;
      		case 7:
      			//点击了“使用帮助”
      			AlertDialog.Builder builder0=new AlertDialog.Builder(this);
  				builder0.setTitle("软件使用帮助");
  				builder0.setMessage("软件共有9个功能：包括学生信息的添加、学生信息维护、学生信息查询、学生成绩添加" +
  						"学生成绩维护、学生成绩查询、系统管理、使用帮助、软件退出。\n如果在使用过程中有问题请点击下方“联系作者”进行反馈！" +
  						"\n软件作者：易瑞杰\n软件用途：2020年毕业设计\n完成时间：2019年9月20日");
  				//为“我知道了”按钮添加事件监听
  				builder0.setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
  					
  					@Override
  					public void onClick(DialogInterface arg0, int arg1) {
  						// TODO Auto-generated method stub
  						
  					}
  				});
  				//为“联系作者”按钮添加事件监听
  				builder0.setNegativeButton("联系作者", new DialogInterface.OnClickListener() {
  					
  					@Override
  					public void onClick(DialogInterface arg0, int arg1) {
  						// TODO Auto-generated method stub
  						//调用相关方法进行处理
  						lianxi();
  					}
  				});
  				builder0.create().show();
  				break;
      		case 8:
      				//用户点击了“退出”
      				AlertDialog.Builder builder=new AlertDialog.Builder(this);
      				//builder.setTitle("提示").setMessage("是否确定退出程序");
      				builder.setTitle("提示");
      				builder.setMessage("是否确定退出程序");
      				
      				//确定按钮
      				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
      					
      					@Override
      					public void onClick(DialogInterface arg0, int arg1) {
      						// TODO Auto-generated method stub
      						finish();
      					}
      				});
      				
      				//取消按钮
      				builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
      					
      					@Override
      					public void onClick(DialogInterface arg0, int arg1) {
      						// TODO Auto-generated method stub
      						
      					}
      				});
      				builder.create().show();
      				break;
      		   default:
      		   break;
      		}
      	}
     /**
      * 软件使用帮助中“联系作者”事件处理方法
      */
     public void lianxi(){
    	 //方法一
//     	PackageManager packageManager = getPackageManager();
//     	Uri uri = Uri.parse("http://qm.qq.com/cgi-bin/qm/qr?k=XALdOZEPRx8ndbgaQoetggHd-mGfeusx");
//     	Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//     	startActivity(intent);
//     	intent = packageManager.getLaunchIntentForPackage("com.tencent.mobileqq");
    	 //方法二
    	Uri uri = Uri.parse("http://qm.qq.com/cgi-bin/qm/qr?k=XALdOZEPRx8ndbgaQoetggHd-mGfeusx");
     	Intent intent = new Intent(Intent.ACTION_VIEW, uri);
     	startActivity(intent);
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
        	//用户选择了退出
        	AlertDialog.Builder buidler=new AlertDialog.Builder(this);
//        	buidler.setTitle("提示").setMessage("是否确定退出程序？");
        	buidler.setTitle("提示");
        	buidler.setMessage("是否确定退出程序？");
        	//确定按钮
        	buidler.setPositiveButton("确定", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					finish();
				}
			});
        	//取消按钮
        	buidler.setNegativeButton("取消", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					return;
				}
			});
        	//创建对话框--进行显示对话框
        	buidler.create().show();
        }
        
        return super.onOptionsItemSelected(item);
    }
}
