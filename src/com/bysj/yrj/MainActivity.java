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
	//��Ա����
	GridView gvinfo;
	//�������� ������ݣ�ͼ��int�������֣�String��
	String[] title=new String[]{"ѧ����Ϣ���","ѧ����Ϣά��","ѧ����Ϣ��ѯ","ѧ���ɼ����","ѧ���ɼ�ά��","ѧ���ɼ���ѯ","ϵͳ����","ʹ�ð���","�˳����"};
	int[] image=new int[]{R.drawable.addinfo,R.drawable.weihuinfo,R.drawable.showinfo,R.drawable.addscore,
			R.drawable.weihuscore,R.drawable.showscore,R.drawable.userpass,R.drawable.help,R.drawable.exit};
	ArrayList<Map<String,Object>> data;//������װ������Դ 
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      // ����һ������ ����--׼������Դ
      		this.data=new ArrayList<Map<String,Object>>();
      		for(int i=0;i<title.length;i++){
      			//���� һ�� Map����  ���� hash map
      			Map tem=new HashMap();
      			//��--ֵ--��--���һ��С����
      			tem.put("mainimage",image[i]);
      			tem.put("miantitle",title[i]);
      			//���ֿ⡱��ӵ����� ���� map ����--��ÿһ��С���ַ��뵽��������ʾ
      			data.add(tem);
      		}
      		//��ʼ���򵥵�������  ����Դ  �� GridView �� ���š�  ������    ����Դ           ���� 
      		SimpleAdapter sadapter=new SimpleAdapter(this, data, R.layout.mygrid,
      				new String[]{"mainimage","miantitle"}, new int[]{R.id.gridimage,R.id.gridtext});
      		//��ʼ�� GridView����ؼ����а�
      		this.gvinfo=(GridView) findViewById(R.id.gridView1);
      		//����GridView��������
      		this.gvinfo.setAdapter(sadapter);
      		
      		//gvinfo����¼�����
      		this.gvinfo.setOnItemClickListener(new OnItemClickListener() {
      			@Override
      			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
      					long id) {
      				//�¼�����     position  ���GridView �е� ��Ԫ ���
      				mianAction(position);
      			}
      		});
      	}
      	/**
      	 * ��� ����ͼ�� �¼�����
      	 */
      	public void mianAction(int id){
      		//  �������� ����ͼƬ����ת����������ʾ ����
      		switch(id){
      		case 0:
      			//����ˡ�ѧ����Ϣ��ӡ�
      			Intent intent0=new Intent(this,AddStudentInfoActivity.class);
      			startActivity(intent0);
      			break;
      		case 1:
      			//����ˡ�ѧ����Ϣά����
      			Intent intent1=new Intent(this,WeihuStudentInfoActivity.class);
      			startActivity(intent1);
      			break;
      		case 2:
      			//����ˡ�ѧ����Ϣ��ѯ��
      			Intent intent2=new Intent(this,ShowStudentInfoActivity.class);
      			startActivity(intent2);
      			break;
      		case 3:
      			//����ˡ�ѧ���ɼ���ӡ�
      			Intent intent3=new Intent(this,AddStudentScoreActivity.class);
      			startActivity(intent3);
      			break;
      		case 4:
      			//����ˡ�ѧ���ɼ�ά����
      			Intent intent4=new Intent(this,WeihuStudentScoreActivity.class);
      			startActivity(intent4);
      			break;
      		case 5:
      			//����ˡ�ѧ���ɼ���ѯ��
      			Intent intent5=new Intent(this,ShowStudentScoreActivity.class);
      			startActivity(intent5);
      			break;
      		case 6:
      			//����ˡ�ϵͳ����
      			Intent intent6=new Intent(this,PassRepairActivity.class);
      			startActivity(intent6);
      			break;
      		case 7:
      			//����ˡ�ʹ�ð�����
      			AlertDialog.Builder builder0=new AlertDialog.Builder(this);
  				builder0.setTitle("���ʹ�ð���");
  				builder0.setMessage("�������9�����ܣ�����ѧ����Ϣ����ӡ�ѧ����Ϣά����ѧ����Ϣ��ѯ��ѧ���ɼ����" +
  						"ѧ���ɼ�ά����ѧ���ɼ���ѯ��ϵͳ����ʹ�ð���������˳���\n�����ʹ�ù����������������·�����ϵ���ߡ����з�����" +
  						"\n������ߣ������\n�����;��2020���ҵ���\n���ʱ�䣺2019��9��20��");
  				//Ϊ����֪���ˡ���ť����¼�����
  				builder0.setPositiveButton("��֪����", new DialogInterface.OnClickListener() {
  					
  					@Override
  					public void onClick(DialogInterface arg0, int arg1) {
  						// TODO Auto-generated method stub
  						
  					}
  				});
  				//Ϊ����ϵ���ߡ���ť����¼�����
  				builder0.setNegativeButton("��ϵ����", new DialogInterface.OnClickListener() {
  					
  					@Override
  					public void onClick(DialogInterface arg0, int arg1) {
  						// TODO Auto-generated method stub
  						//������ط������д���
  						lianxi();
  					}
  				});
  				builder0.create().show();
  				break;
      		case 8:
      				//�û�����ˡ��˳���
      				AlertDialog.Builder builder=new AlertDialog.Builder(this);
      				//builder.setTitle("��ʾ").setMessage("�Ƿ�ȷ���˳�����");
      				builder.setTitle("��ʾ");
      				builder.setMessage("�Ƿ�ȷ���˳�����");
      				
      				//ȷ����ť
      				builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
      					
      					@Override
      					public void onClick(DialogInterface arg0, int arg1) {
      						// TODO Auto-generated method stub
      						finish();
      					}
      				});
      				
      				//ȡ����ť
      				builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
      					
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
      * ���ʹ�ð����С���ϵ���ߡ��¼�������
      */
     public void lianxi(){
    	 //����һ
//     	PackageManager packageManager = getPackageManager();
//     	Uri uri = Uri.parse("http://qm.qq.com/cgi-bin/qm/qr?k=XALdOZEPRx8ndbgaQoetggHd-mGfeusx");
//     	Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//     	startActivity(intent);
//     	intent = packageManager.getLaunchIntentForPackage("com.tencent.mobileqq");
    	 //������
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
        	//�û�ѡ�����˳�
        	AlertDialog.Builder buidler=new AlertDialog.Builder(this);
//        	buidler.setTitle("��ʾ").setMessage("�Ƿ�ȷ���˳�����");
        	buidler.setTitle("��ʾ");
        	buidler.setMessage("�Ƿ�ȷ���˳�����");
        	//ȷ����ť
        	buidler.setPositiveButton("ȷ��", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					finish();
				}
			});
        	//ȡ����ť
        	buidler.setNegativeButton("ȡ��", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					return;
				}
			});
        	//�����Ի���--������ʾ�Ի���
        	buidler.create().show();
        }
        
        return super.onOptionsItemSelected(item);
    }
}
