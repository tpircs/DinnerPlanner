package dinnerPlanner;

import java.util.ArrayList;
import java.util.Arrays;

public class Recipes {

	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	
	public Recipes() {
	}
	
	public ArrayList<Recipe> getRecipes() {
		return recipes;
	}
	
	public void addRecipe(Recipe recipe) {
		if( ! recipes.contains(recipe)) {
			recipes.add(recipe);
		}
	}
	
	public void removeRecipe(Recipe recipe) {
		recipes.remove(recipe);
	}
	
	public boolean loadFromFile(String filepath) {
		fileOperations f = new fileOperations(filepath);
		String text = f.read();
		if (text == null) {
			return false;
		}
		Arrays.asList(text.split("\n")).forEach(a -> recipes.add(new Recipe(a)));
		return true;
	}
	
	public void saveToFile(String filepath) {
		fileOperations f = new fileOperations(filepath);
		f.save(this);
	}
	
	public int size() {
		return recipes.size();
	}
	
	public Boolean contains(Recipe recipe) {
		return recipes.contains(recipe);
	}
	
	@Override
	public String toString() {
		String str = "";
		for (Recipe recipe : recipes) {
			str += recipe + ", ";
		}
		return str.substring(0, str.length() - 2);
	}
}
