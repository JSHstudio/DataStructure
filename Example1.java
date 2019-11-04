package com.jsh.assignment2;

import java.util.Iterator;
import java.util.PriorityQueue;

public class Example1 {
	// 데이터 선언
	private final String[] employeeName = { "John", "Lisa", "Chris", "Angelina", "Bob" };
	private final int[] employeeAge = { 31, 32, 32, 32, 31 };
	private final int[] employeeSalary = { 100000, 115000, 145000, 110000, 100000 };

	// ex1 변수 설정
	PriorityQueue<Employee> PQ;
	EmployeeComparator comparator = new EmployeeComparator();

	Example1() throws Exception {
		PQ = new PriorityQueue<Employee>(comparator);
		// 변수 길이 확인
		if (!(employeeName.length == employeeAge.length && employeeName.length == employeeSalary.length)) {
			throw new Exception("DataSetLenghtException");
		}
		// 데이터 add
		for (int i = 0; i < employeeName.length; i++) {
			PQ.add(new Employee(employeeName[i], employeeAge[i], employeeSalary[i]));
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		/*
		 * toString with Iterator
		 */
//		Iterator<Employee> itr = PQ.iterator();
//		while (itr.hasNext()) {
//			sb.append(itr.next().toString() + "\n");
//		}
		/*
		 * toString with dequeue
		 */
		while (!PQ.isEmpty())
			sb.append(PQ.poll().toString() + "\n");
		return sb.toString();
	}
}
