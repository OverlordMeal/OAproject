package switches.test;

import java.util.Date;

public class People {

	//名字
	private String name;
	
	//年龄
	private Integer age;
	
	//出生日期
	private Date bir;

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}

	public Date getBir() {
		return bir;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(Integer age) {
		this.age = age;
	}


	public void setBir(Date bir) {
		this.bir = bir;
	}
	
}
