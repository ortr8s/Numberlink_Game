package main.utils;

import java.io.FileWriter;
import java.io.IOException;

public class Saving {
	
	public static void main(String[] args) {
    	
    }
    
    public static void rawGeneratorTableToTxt(char[][] generatedBoard) {
    	
    	try {
            FileWriter txtWriter = new FileWriter("raw_generator_table.txt");

            for (int j = 0; j < generatedBoard.length; j++) {
				for (int i = 0; i < generatedBoard.length; i++) {
					System.out.print(generatedBoard[j][i]);
					txtWriter.write(generatedBoard[j][i]);
					System.out.print("  ");
					txtWriter.write("  ");
				}
				System.out.println(" ");
				txtWriter.write("\n");
			}
            
            txtWriter.close();

            System.out.println("Text saved successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the text to the file.");
            e.printStackTrace();
        }
    	
    }
    
}
