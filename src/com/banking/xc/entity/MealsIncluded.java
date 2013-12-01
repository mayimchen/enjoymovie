package com.banking.xc.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 和ratePlan想关联类
 * @author zhangyinhang
 *
 */
public class MealsIncluded implements Serializable{
	List<String> meals;

	public List<String> getMeals() {
		return meals;
	}

	public void setMeals(List<String> meals) {
		this.meals = meals;
	}
	
    
	
}
