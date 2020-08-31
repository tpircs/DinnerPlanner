package dinnerPlanner;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class fileOperations {
	private String filename;
	
	public fileOperations(String filename) {
		this.filename = filename;
	}
	
	public String read() {
		String fileContent = "";
		try {
			Scanner file = new Scanner(new FileReader(filename));
			while (file.hasNext()) {
				fileContent += file.next() + "\n";
			}
			file.close();
		}
		catch(Exception e) {
			fileContent = null;
			System.out.println("Could not read from file: " + filename);
		}
		return fileContent;
	}
	
	public void save(Recipes allRecipes) {
		//format = recipeName%NumberOfServings%ingredient1:double1,ingredient2:double2,ingredient3:double3
		
		try {
			PrintWriter printer = new PrintWriter(new File(filename));
			
			for (Recipe recipe : allRecipes.getRecipes()) {
				printer.println(recipe.getName() + "%" + recipe.getNumberOfServings() + "%" + recipe.getIngredients());
			}
			
			printer.flush();
			printer.close();
			
			System.out.println("Recipes saved to file: " + filename);
		} catch (Exception e) {
			System.out.println("Could not save recipes");
		}
	}
}
