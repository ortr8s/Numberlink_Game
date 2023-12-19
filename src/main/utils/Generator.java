package main.utils;

public class Generator {
	
	public static int width;
	public static int height;
	public static int pairNumber;
	
	int[][] board;
	
	public Generator(int width, int height, int pairNumber) {
		
		this.width = width;
		this.height = height;
		this.pairNumber = pairNumber;
		
		board = new int[width][height];
		
	}
	
	public static void main(String[] args) {
		
	}
	
}
