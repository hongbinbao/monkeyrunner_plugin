package com.bhb.verify;

public interface IVerify {
	public  boolean find(String childFile, String parentFile);
	public  boolean find(String childFile, String parentFile, double similarity);
	public  int[] getLocation(String childFile, String parentFile);
	public  int[] getLocation(String childFile, String parentFile, double similarity, int degree);
}
