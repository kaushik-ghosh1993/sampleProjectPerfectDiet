package sampleProjectPerfectDiet;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DietPlanCalculation {
	private double total;
	private double totalcurb;
	private double totalFat;
	private double totalProtein;

	public void foodCalculation(FoodDetail foodDetail, double count) {
		total = total + (foodDetail.getCurbs() * count * 0.01 * 4.1) + (foodDetail.getFat() * count * 0.01 * 4.1)
				+ (foodDetail.getProtein() * count * 0.01 * 9);
		totalcurb = totalcurb + (foodDetail.getCurbs() * count * 0.01 * 4.1);
		totalFat = totalFat + (foodDetail.getFat() * count * 0.01 * 4.1);
		totalProtein = totalProtein + (foodDetail.getProtein() * count * 0.01 * 9);
	}

	public Map<String, Double> getFoodDetailList(String inputCalories, Integer curbPercent, Integer fatPercent,
			Integer proteinPercent) {
		int calories = Integer.valueOf(inputCalories);
		double curbCalories = calories * curbPercent / 100;
		double fatCalories = calories * fatPercent / 100;
		double proteinCalories = calories * proteinPercent / 100;
		Double totalProteinCalories = 0.0;
		Map<String, Double> foodList = new HashMap<String, Double>();
		for (FoodDetail foodDetail : FoodDetail.getFoodDetailCache()) {
			totalProteinCalories = totalProteinCalories + foodDetail.getProtein() * 0.01 * 9;
		}
		
		
		while (total < calories) {

			if (totalProtein <= proteinCalories) {
				double countProtein = proteinCalories / totalProteinCalories;
				for (FoodDetail foodDetail : FoodDetail.getFoodDetailCache()) {
					if (totalProtein >= proteinCalories)
						break;

					foodCalculation(foodDetail, countProtein);
					if (foodList.containsKey(foodDetail.getName())) {

						foodList.put(foodDetail.getName(), foodList.get(foodDetail.getName()) + countProtein);
					} else {

						foodList.put(foodDetail.getName(), countProtein);
					}

				}
			}

			if (totalProtein >= proteinCalories && totalFat < fatCalories) {
				for (FoodDetail foodDetail : FoodDetail.getFatHeavyCache()) {
					double countFat = ((fatCalories - totalFat) / (foodDetail.getFat() * 4.1 * 0.01));
					foodCalculation(foodDetail, countFat);
					if (foodList.containsKey(foodDetail.getName())) {
						foodList.put(foodDetail.getName(), foodList.get(foodDetail.getName()) + countFat);
					} else {
						foodList.put(foodDetail.getName(), countFat);
					}
				}
			}

			if (totalProtein >= proteinCalories && totalFat >= fatCalories && totalcurb < curbCalories) {

				for (FoodDetail foodDetail : FoodDetail.getCurbsHeavyCache()) {
					double countcurb = ((curbCalories - totalcurb) / (foodDetail.getCurbs() * 4.1 * 0.01));
					foodCalculation(foodDetail, countcurb);
					if (foodList.containsKey(foodDetail.getName())) {
						foodList.put(foodDetail.getName(), foodList.get(foodDetail.getName()) + countcurb);
					} else {
						foodList.put(foodDetail.getName(), countcurb);
					}
				}
			}

		}

		return foodList;

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Input Calories");
		String inputCalories = sc.next();
		System.out.println("Enter Macros percentage ");
		System.out.println("Curb");
		String curbPercentage = sc.next();
		System.out.println("Fat");
		String fatPercentage = sc.next();
		System.out.println("Protein");
		String prioteinPercentage = sc.next();
		DietPlanCalculation diet = new DietPlanCalculation();
		Map foodList = diet.getFoodDetailList(inputCalories, Integer.valueOf(curbPercentage),
				Integer.valueOf(fatPercentage), Integer.valueOf(prioteinPercentage));
		System.out.println("*****" + foodList);
		sc.close();
	}

}
