package com.bhb.verify;

import org.sikuli.script.Settings;
import org.sikuli.script.Finder;
import org.sikuli.script.Region;
import org.sikuli.script.Location;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Verifier implements IVerify {
	public static final double SIMILARITY =  0.7;

	public Verifier() {
		Settings.InfoLogs = false;
	}

	@Override
	public boolean find(String childFile, String parentFile) {
		Finder finder = null;
		try {
			finder = new Finder(parentFile);
			finder.find(childFile, SIMILARITY);
			if(finder.hasNext()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			if(finder != null){
				finder.destroy();
			}
		}
		return false;
	}
	

	@Override
	public boolean find(String childFile, String parentFile, double similarity) {
		Finder finder = null;
		try {
			finder = new Finder(parentFile);
			finder.find(childFile, similarity);
			if(finder.hasNext()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			if(finder != null){
				finder.destroy();
			}
		}
		return false;
	}

	@Override
	public int[] getLocation(String childFile, String parentFile) {
		Finder finder = null;
		try {
			finder = new Finder(parentFile);
			finder.find(childFile, SIMILARITY);
			if(finder.hasNext()){
	            Region region = finder.next();
	            Location location= region.getCenter();
	            int [] coord = {(int) location.getX(), (int) location.getY()};
	            return coord;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			if(finder != null){
				finder.destroy();
			}
		}
		return null;
	}


	@Override
	public int[] getLocation(String childFile, String parentFile,
			double similarity, int degree) {
		Finder finder = null;
		try {
			finder = new Finder(parentFile);
			finder.find(childFile, similarity);
			if(finder.hasNext()){
	            Region region = finder.next();
	            Location location= region.getCenter();
	            int [] coord = {(int) location.getX(), (int) location.getY()};
	            int[] c = adaptRotation(coord, getImageSize(parentFile), degree);
	            return c;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			if(finder != null){
				finder.destroy();
			}
		}
		return null;
	}

   private int[] adaptRotation(int[] ori, int[] size, int degree){
	   //System.out.println(degree);
	   switch(degree){
	   case 0:
		   return ori;
	   case 90:
		   int w90 = size[0];
		   int h90 = size[1];
		   int x90 = ori[0];
		   int y90 = ori[1];
		   int[] v90 = {x90, y90};
		   int vx90 = y90;
		   int vy90 = w90-x90;
		   v90[0] = vx90;
		   v90[1] = vy90;
		   return v90;
	   case 180:
		   return ori;
	   case 270: 
		   int w270 = size[0];
		   int h270 = size[1];
		   int x270 = ori[0];
		   int y270 = ori[1];
		   int[] v270 = {x270, y270};
		   int vx270 = h270-y270;
		   int vy270 = x270;
		   v270[0] = vx270;
		   v270[1] = vy270;
		   return v270;
	   default: return ori;
	   }
   }
   
  private int[] getImageSize(String imgPath){
	BufferedImage img;
	try {
		img = ImageIO.read(new File(imgPath));
		int[] v = {img.getWidth(), img.getHeight()};
		return v;
	} catch (IOException e) {
		e.printStackTrace();
	}
	return null;
  }
  
}
