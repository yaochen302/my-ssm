package com.ry.common;

public class StringTest {
	public static void main(String[] args) {
		String str1 = "a,b,c,,,,";
		String str2 = "a,,b,c";
		String []s1 = str1.split(",");
		String []s2 = str2.split(",");
		System.out.println(str1.split(","));
		System.out.println(str2.split(","));
		
		String []s3 = {"q","w",""};
		System.out.println(s3);
	}
}
