package com.bysj.yrj.bean;
/**
 * 
 * 与用户信息表对应的 实体类
 * @author k01
 *
 */
public class UserInfo {
   private int id;//编号
   private String username;//用户名
   private String userpass;//密码
   
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getUserpass() {
	return userpass;
}
public void setUserpass(String userpass) {
	this.userpass = userpass;
}
   
}
