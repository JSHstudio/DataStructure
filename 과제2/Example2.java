package com.jsh.assignment2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example2 {
	protected class DataCell {
		private String word;
		private int count;

		DataCell(String word) {
			this.word = word;
			count += 1;
		}

		public void setWord(String word) {
			this.word = word;
		}

		public String getWord() {
			return word;
		}

		public int getCount() {
			return count;
		}

		public void plus() {
			count += 1;
		}

		@Override
		public String toString() {
			return "(" + word + ", " + count + ")";
		}
	}

	// 파일 오픈 변수
	private File file;
	private String encode = "UTF-16";

	// 단어 추출에 사용할 변수
	private String splitter = ",";

	Example2(String filePath) {
		file = new File(filePath);
	}

	Example2(String filePath, String encode) {
		this.encode = encode;
		file = new File(filePath);
	}

	Example2(String filePath, String encode, String splitter) {
		this.encode = encode;
		this.splitter = splitter;
		file = new File(filePath);
	}

	/*
	 * File open and return map
	 */
	protected Map<String, Integer> openFileToMap() throws IOException {
		Map<String, Integer> map = new HashMap<String, Integer>();

		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis, encode);
		BufferedReader br = new BufferedReader(isr);

		String readLine = null;
		String[] array = null;

		while ((readLine = br.readLine()) != null) {
			array = readLine.split(splitter);
			for (int i = 0; i < array.length; i++) {
				if (map.get(array[i]) == null)
					map.put(array[i], 1);
				else
					map.replace(array[i], map.get(array[i]) + 1);
			}
		}

		fis.close();

		if (map.size() > 0)
			return map;
		else
			return null;
	}

	/*
	 * File open and return list<DataCell>
	 */
	public List<DataCell> openFileToList() throws IOException {
		List<DataCell> list = new ArrayList<DataCell>();

		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis, encode);
		BufferedReader br = new BufferedReader(isr);

		String readLine = null;
		String[] array = null;

		while ((readLine = br.readLine()) != null) {
			int index = 0;
			array = readLine.split(splitter);
			for (int i = 0; i < array.length; i++) {
				if ((index = search(list, array[i])) == -1)
					list.add(new DataCell(array[i]));
				else
					list.get(index).plus();
			}
		}

		fis.close();

//		for (int i = 0; i < 20; i++) {
//			System.out.println(list.get(i).getWord() + " = " + list.get(i).getCount());
//		}

		if (list.size() > 0)
			return list;
		else
			return null;
	}

	/*
	 * sort list
	 */
	public List<DataCell> sort(List<DataCell> list) {
		Collections.sort(list, new Comparator<DataCell>() {
			@Override
			public int compare(DataCell d1, DataCell d2) {
				return -Integer.compare(d1.getCount(), d2.getCount());
			};
		});
		return list;
	}

	/*
	 * Value를 기준으로 정렬 한 후, 정렬된 key값을 리스트로 반환
	 */
	@SuppressWarnings("unchecked")
	protected List sort(final Map map) {
		List<String> list = new ArrayList<String>();
		list.addAll(map.keySet());
		Collections.sort(list, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);
				return ((Comparable) v2).compareTo(v1);
			}
		});
		return list;
	}

	/*
	 * list에서 특정 문자열 탐색, 인덱스 리턴
	 */
	private int search(List<DataCell> list, String str) {
		int i = 0;
		for (i = 0; i < list.size(); i++) {
			if (list.get(i).getWord().contentEquals(str))
				return i;
		}
		return -1;
	}

	/*
	 * 상위 index개의 원소를 리스트로 반환
	 */
	public List<Object> top(List<Object> list, int index) {
		List<Object> resultList = new ArrayList<Object>(index);
		for (int i = 0; i < index; i++)
			resultList.add(list.get(i));
		return resultList;
	}

	/*
	 * get and set methods
	 */
	public Example2 setSplitter(String splitter) {
		this.splitter = splitter;
		return this;
	}

	public Example2 setEncode(String encode) {
		this.encode = encode;
		return this;
	}

	public String getSplitter() {
		return splitter;
	}

	public String getEncode() {
		return encode;
	}
}
