package dinnerPlanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

public class Menu {

	private Recipes allRecipes;
	private Recipes menu;
	private ArrayList<Integer> fixedDays = new ArrayList<Integer>();
	private ArrayList<Recipe> fixedRecipes = new ArrayList<Recipe>();
	private int numberOfDays;
	private Random random = new Random();
	
	public Menu(int days) {
		this.numberOfDays = days;
	}
	
	
	public void setFixedRecipe(Recipe recipe, int day) {
		fixedDays.add(day);
		fixedRecipes.add(recipe);
	}
	
	public void removeFixedRecipe(int day) {
		fixedRecipes.remove(fixedDays.indexOf(day));
		fixedDays.remove(Integer.valueOf(day));
	}
	
	private Recipe selectRandomRecipe() {
		return allRecipes.getRecipes().get(random.nextInt(allRecipes.size()));
	}
	
	public void generateMenu() {
		this.menu = new Recipes();
		for (int i = 0; i < numberOfDays; i++) {
			if(! fixedDays.contains(i)) {
				Recipe randomRecipe = selectRandomRecipe();
				while ((menu.contains(randomRecipe) || getFixedRecipesNames().contains(randomRecipe.getName()))
						&& menu.size() < allRecipes.size()) {
					//System.out.println("\nOppskrift: " + randomRecipe);
					randomRecipe = selectRandomRecipe();
				}
				
				menu.addRecipe(randomRecipe);
			}
			else {
				this.menu.addRecipe(fixedRecipes.get(fixedDays.indexOf(i)));
			}
		}
	}
	
	public Recipes getMenu(){
		return menu;
	}
	
	public void loadRecipesFromFile(String filepath) {
		allRecipes = new Recipes();
		allRecipes.loadFromFile(filepath);
	}
	
	public void saveWeeklyMenu(String filepath) {
		menu.saveToFile(filepath);
	}
	
	public void loadWeeklyMenu(String filepath) {
		menu = new Recipes();
		menu.loadFromFile(filepath);
	}
	
	public Recipes getAllRecipes() {
		return allRecipes;
	}


	public void setAllRecipes(Recipes allRecipes) {
		this.allRecipes = allRecipes;
	}
	
	
	public Collection<String> getFixedRecipesNames(){
		Collection<String> names = new ArrayList<String>();
		fixedRecipes.forEach(a -> names.add(a.getName()));
		return names;
	}
	
	
	
	public static void main(String[] args) {
		Ingredients ingredients = new Ingredients();
		ingredients.addIngredient("ingr1", 1.1);
		ingredients.addIngredient("ingr2", 2.2);
		ingredients.addIngredient("ingr3", 3.3);
		Recipes recipes = new Recipes();
		ArrayList<String> retter = new ArrayList<String>(Arrays.asList("Gulrotsuppe",
				"Quesedillas", "Risotto", "Pizza", "Wok", "Lasagne", "Soppsuppe",
				"Soppgryte", "Linsoli", "Kinesiske-pannekaker", "Linsepannekaker",
				"Curry", "Rødbetburger"));
		
		retter.forEach(a -> recipes.addRecipe(new Recipe(a, 4, ingredients)));
		Recipe taco = new Recipe("taco", 4, ingredients);
		recipes.addRecipe(taco);
		
		recipes.saveToFile("test.txt");
		
		Menu menu = new Menu(7);
		menu.loadRecipesFromFile("test.txt");
		
		menu.setFixedRecipe(taco, 4);
		
		menu.generateMenu();
		System.out.println("Ukesmeny er: " + menu.getMenu());
		
		/*menu.saveWeeklyMenu("testuke.txt");
		
		menu.generateMenu();
		System.out.println("Ukesmeny er: " + menu.getMenu());
		
		menu.loadWeeklyMenu("testuke.txt");
		System.out.println("Ukesmeny er: " + menu.getMenu());
		*/
		
		menu.generateMenu();
		System.out.println("Ukesmeny er: " + menu.getMenu());
		menu.generateMenu();
		System.out.println("Ukesmeny er: " + menu.getMenu());
		menu.generateMenu();
		System.out.println("Ukesmeny er: " + menu.getMenu());
		
		menu.removeFixedRecipe(4);
		menu.generateMenu();
		System.out.println("Ukesmeny er: " + menu.getMenu());
	}
}
