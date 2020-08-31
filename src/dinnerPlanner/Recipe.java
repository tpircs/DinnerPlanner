package dinnerPlanner;

import java.util.Arrays;
import java.util.List;

public class Recipe {
	private String name;

	private double numberOfServings;
	private Ingredients ingredients;
	
	public Recipe(String name, int numberOfServings, Ingredients ingredients) {
		this.name = name;
		this.numberOfServings = numberOfServings;
		this.ingredients = ingredients;
	}
	
	public Recipe (String formattedText) {
		List<String> values = Arrays.asList(formattedText.split("%"));
		this.name = values.get(0);
		this.numberOfServings = Double.valueOf(values.get(1));
		this.ingredients = new Ingredients(values.get(2));
	}
	
	public String getName() {
		return name;
	}

	public double getNumberOfServings() {
		return numberOfServings;
	}


	public Ingredients getIngredients() {
		return ingredients;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
