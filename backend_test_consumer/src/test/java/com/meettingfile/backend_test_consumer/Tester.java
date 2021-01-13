package com.meettingfile.backend_test_consumer;
public class Tester{
	public static void main(String args[]){
		Base b = new Base();
		Sub s = (Sub) b;
	}

	static class Base {}
	static class Sub extends Base {}
}