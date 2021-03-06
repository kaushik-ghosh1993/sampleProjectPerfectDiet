package sampleProjectPerfectDiet;

import java.util.HashSet;
import java.util.Set;

public enum FoodDetail {

	RICE("Rice", 92, 0, 8), 
	YELLOWLENTILS("YellowLentils", 76, 0, 24), 
	EGG("Egg", 10, 50, 40),
	PEANUTS("Peanuts", 8, 60, 32), 
	CASHEW("Cashew", 0, 70, 30), 
	MILK("Milk", 30, 40, 30), 
	APPLE("Apple", 100, 0, 0),
	SPINACH("Spinach", 60, 0, 40), 
	BUTTER("Butter", 0, 100, 0), 
	CHEESE("Cheese", 0, 60, 40),
	ALMONDS("Almonds", 5, 55, 40);

	private String name;
	private Integer curbs;
	private Integer fat;
	private Integer protein;

	private static Set<FoodDetail> foodDetailCache = new HashSet<FoodDetail>();
	private static Set<FoodDetail> fatHeavyCache = new HashSet<FoodDetail>();
	private static Set<FoodDetail> curbsHeavyCache = new HashSet<FoodDetail>();

	private FoodDetail(String name, Integer curbs, Integer fat, Integer protein) {
		this.name = name;
		this.curbs = curbs;
		this.fat = fat;
		this.protein = protein;
	}

	public String getName() {
		return name;
	}

	public Integer getCurbs() {
		return curbs;
	}

	public Integer getFat() {
		return fat;
	}

	public Integer getProtein() {
		return protein;
	}
	
	

	public static Set<FoodDetail> getFoodDetailCache() {
		return foodDetailCache;
	}

	public static Set<FoodDetail> getFatHeavyCache() {
		return fatHeavyCache;
	}

	public static Set<FoodDetail> getCurbsHeavyCache() {
		return curbsHeavyCache;
	}



	static {
		for (FoodDetail foodDetail : FoodDetail.values()) {
			foodDetailCache.add(foodDetail);

			if (foodDetail.getCurbs() == 0 && foodDetail.getProtein() == 0)
				fatHeavyCache.add(foodDetail);

			if (foodDetail.getFat() == 0 && foodDetail.getProtein() == 0)
				curbsHeavyCache.add(foodDetail);

		}

	}

}
