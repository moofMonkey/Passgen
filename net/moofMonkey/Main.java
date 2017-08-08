package com.moofMonkey;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;

public class Main extends Thread {
	static String pwdgen = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" +
								"0123456789@#$%^&*()_+=-;:?\\/,.<>";
	static int length = pwdgen.length();
	static int count = 0;
	static String pwd = "";
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Throwable {
		System.out.println("> Print password length (integer)");
		System.out.print("< ");
		try {
			count = Integer.parseInt(in.readLine());
		} catch(Throwable t) { throw new Throwable("Invalid parameter."); }
		System.out.println();
		System.out.println("> Use spec. chars (true/false - boolean)?");
		System.out.print("< ");
		try {
			if(Boolean.parseBoolean(in.readLine())) {
				pwdgen = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" +
						 "0123456789@#$%^&*()_+=-;:?\\/,.<>";
				length = pwdgen.length();
			} else {
				pwdgen = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" +
						 "0123456789";
				length = pwdgen.length();
			}
		} catch(Throwable t) { throw new Throwable("Invalid parameter."); }
		System.out.println();
		System.out.println("> Use unicode && UTF-8 (true/false - boolean)?");
		System.out.print("< ");
		try {
			if(Boolean.parseBoolean(in.readLine())) {
				pwdgen += "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" + "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
						+ "رذدخحچجثتپباقفغعظطضصشسژزیهونملگک" + "رذدخحچجثتپباقفغعظطضصشسژزیهونملگک";
				length = pwdgen.length();
			}
		} catch(Throwable t) { throw new Throwable("Invalid parameter."); }
		System.out.println();
		
		try {
			new File("RandomizedPwd").delete();
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("RandomizedPwd", true), "UTF-8"));
			
			int i = count;
			int lastPercent = 0;
			Random rand = new Random();
			while(true) {
				if(i == 0) break;
				if(lastPercent != Math.round((i*100)/count)) {
					lastPercent = Math.round((i*100)/count);
					System.out.println("Generating password "+(100-lastPercent)+"%...");
				}
				pwd += pwdgen.charAt(rand.nextInt(length));
				i--;
			}
			out.write(pwd);
			out.close();
			
			System.out.println("Password generated =)");
			if(pwd.length() < 100)
				System.out.println(pwd);
		} catch(Throwable t) {t.printStackTrace();}
	}
}
