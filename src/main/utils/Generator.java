package main.utils;

import java.util.Random;

public class Generator {

	static Random random = new Random();

	static char[][] board5 = new char[5][5];
	static char[][] board7 = new char[7][7];
	static char[][] board9 = new char[9][9];

	public Generator() {
		System.out.println("Nie wprowadzono wielkości planszy");
	}

	public Generator(int rozmiar) {
		//TODO jakis switch
	}

	public static void main(String[] args) {
		// TODO usunac printy
		System.out.println(board5);
		System.out.println(board7);
		System.out.println(board9);

	}

	public void generate5(int wielkosc) {
		System.out.println("Wygenerowano planszę o wielkości 5");
	}

	public void generate7(int wielkosc) {
		System.out.println("Wygenerowano planszę o wielkości 7");
	}

	public void generate9(int wielkosc) {
		System.out.println("Wygenerowano planszę o wielkości 7");
	}
}
