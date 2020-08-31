package dinnerPlanner;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class Ingredients {
	private HashMap<String, Double> ingredients = new HashMap<String, Double>();
	
	public Ingredients(String formattedText) {
		Arrays.asList(formattedText.split(",")).stream()
			.map(a -> Arrays.asList(a.split(":")))
			.forEach(a -> ingredients.put(a.get(0), Double.valueOf(a.get(1))));
	}

	public Ingredients() {
		
	}
	
	public void addIngredient(String name, Double amount) {
		//TODO validate input
		if (ingredients.containsKey(name)) {
			ingredients.put(name, ingredients.get(name) + amount);
		}
		else {
			ingredients.put(name, amount);
		}
	}
	
	public void removeIngredient(String name, Double amount) {
		//TODO validate input
		if (ingredients.containsKey(name)) {
			if (ingredients.get(name) == amount) {
				removeIngredient(name);
			}
			else {
				ingredients.put(name, ingredients.get(name) - amount);
			}
		}
	}
	
	public void removeIngredient(String name) {
		ingredients.remove(name);
	}
	
	public Collection<String> getIngredientNames () {
		return ingredients.keySet();
	}
	
	public double getIngredientValue(String key) {
		return ingredients.get(key);
	}
	
	@Override
	public String toString() {
		String string = "";
		for (String key : ingredients.keySet()) {
			string += key + ":" + ingredients.get(key) + ",";
		}
		return string.substring(0, string.length() - 1);
	}
	
	public static void main(String[] args) {
		String text = "sopp:2.0,løk:1.5,mais:10";
		Ingredients ingredients = new Ingredients(text);
		System.out.println(ingredients);
	}
}
