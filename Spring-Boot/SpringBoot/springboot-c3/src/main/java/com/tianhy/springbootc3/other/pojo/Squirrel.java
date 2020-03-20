package com.tianhy.springbootc3.other.pojo;


import com.tianhy.springbootc3.pojo.definition.Animal;

/**
 * 不会被扫描到
 */
public class Squirrel implements Animal {

	@Override
	public void use() {
		System.out.println("松鼠可以采集松果");
	}
}
