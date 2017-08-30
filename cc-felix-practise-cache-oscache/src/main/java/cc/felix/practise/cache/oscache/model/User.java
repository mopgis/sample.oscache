package cc.felix.practise.cache.oscache.model;

import java.util.Date;

import lombok.Data;

@Data
public class User {

	private int id;
	private String name;
	private String sex;
	private int age;
	private Date accessTime;
	
	public User(int id){
		super();
		this.id = id;
		this.accessTime = new Date();
	}
	
	public User(String name,String sex,int age){
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
}
