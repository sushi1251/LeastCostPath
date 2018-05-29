package com.example.test.leastcostpath;

public class ResultSet {

	boolean pathMade;
	int totalCost;
	int[] rows;
	
	public ResultSet(int size) {

		pathMade = false;
		totalCost = 51;
		rows = new int[size];
		for (int i=0; i<size; i++) {
			rows[i] = -1;
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (pathMade) {
			sb.append("Yes\n");
			sb.append(totalCost+ "\n");
			for (int i : rows) {
				sb.append(i+1 + "  ");
				
			}
		} else {
			sb.append("No");
		}
		return sb.toString();
	}
}

