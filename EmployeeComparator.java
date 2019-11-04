package com.jsh.assignment2;

import java.util.Comparator;

public class EmployeeComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee e1, Employee e2) {
		// TODO Auto-generated method stub
		
		/*
		 * First Priority compare age(Descending order)
		 */
		if (e1.getAge() < e2.getAge()) {
			return 1;
		} else if (e1.getAge() == e2.getAge()) {	
			/*
			 * Second Priority compare salary(Descending order)
			 */
			if (e1.getSalary() < e2.getSalary()) {
				return 1;
			} else if (e1.getSalary() == e2.getSalary()) {
				/*
				 * Third Priority compare name(Ascending order)
				 */
				return e1.getName().compareTo(e2.getName());
			} else
				return -1;
		} else
			return -1;
		
	}

}
