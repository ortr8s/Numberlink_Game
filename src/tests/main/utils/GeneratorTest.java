package main.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GeneratorTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testGenerate() {
		Generator generator = new Generator();

        char[][] generatedBoard5 = generator.generate(5);
        
        for (int j = 0; j < generatedBoard5.length; j++) {
            for (int i = 0; i < generatedBoard5.length; i++) {
                System.out.print(generatedBoard5[j][i]);
                System.out.print("  ");
            }
            System.out.println(" ");
        }
        
        char[][] generatedBoard7 = generator.generate(7);
        
        for (int j = 0; j < generatedBoard7.length; j++) {
            for (int i = 0; i < generatedBoard7.length; i++) {
                System.out.print(generatedBoard7[j][i]);
                System.out.print("  ");
            }
            System.out.println(" ");
        }
        
        char[][] generatedBoard9 = generator.generate(9);
        
        for (int j = 0; j < generatedBoard9.length; j++) {
            for (int i = 0; i < generatedBoard9.length; i++) {
                System.out.print(generatedBoard9[j][i]);
                System.out.print("  ");
            }
            System.out.println(" ");
        }
        
	}

	@Test
	final void testGeneratePaths() {
		Generator generator = new Generator();

		for (int i = 3; i<10; i++) {
			generator.generatePaths(i);
		}
	}

	@Test
	final void testFix() {
		Generator generator = new Generator();

		generator.fix(generator.generatePaths(7));
	}

	@Test
	final void testFix2Connections() {
		Generator generator = new Generator();

		generator.fix2Connections(generator.generatePaths(7));
	}

	@Test
	final void testIsClassic() {
		Generator generator = new Generator();

		generator.isClassic(generator.generatePaths(7));
	}

	@Test
	final void testFillWithNumbers() {
		Generator generator = new Generator();
		
		Generator.fillWithNumbers(generator.generate(9));
	}

}
