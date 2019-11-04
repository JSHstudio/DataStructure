package com.jsh.assignment2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jsh.assignment2.Example2.DataCell;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Example1
		Example1 ex1 = null;
		try {
			ex1 = new Example1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(ex1);

		
		// Example2
		Example2 ex2 = new Example2("./src/한국언론진흥재단_공유경제_관련_기사.txt");
		try {
			long start = System.currentTimeMillis();

			Map<String, Integer> map = ex2.openFileToMap();
			List<Object> list = ex2.top(ex2.sort(map), 20);

			long end = System.currentTimeMillis();

			System.out.println("실행속도 : " + (end - start) + "ms");

			for (int i = 0; i < list.size(); i++)
				System.out.println(i + 1 + ". (" + list.get(i) + ", " + map.get(list.get(i)) + ")");

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			long start = System.currentTimeMillis();

			List dataList = ex2.openFileToList();
			List<DataCell> list = ex2.top(ex2.sort(dataList), 20);

			long end = System.currentTimeMillis();

			System.out.println("\n실행속도 : " + (end - start) + "ms");

			for (int i = 0; i < list.size(); i++)
				System.out.println(i + 1 + ". " + list.get(i));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
