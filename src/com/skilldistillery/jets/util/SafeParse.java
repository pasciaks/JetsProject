package com.skilldistillery.jets.util;

public class SafeParse {

	// NOTE: REF: There are no static methods other than method main.

	// This is an additional utility class that can be used to parse user input
	// therefore, these methods are static. Hope that is okay.

	public static ObjectResponse parseInt(String input) {
		ObjectResponse response = new ObjectResponse(500);
		int result = 0;
		try {
			result = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a number.");
			return response;
		}
		response.setStatusCode(200);
		response.setIntValue(result);
		response.setResult(true);
		return response;
	}

	public static ObjectResponse parseDouble(String input) {
		ObjectResponse response = new ObjectResponse(500);
		double result = 0;
		try {
			result = Double.parseDouble(input);
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a number.");
			return response;
		}
		response.setStatusCode(200);
		response.setDoubleValue(result);
		response.setResult(true);
		return response;

	}

	public static ObjectResponse parseFloat(String input) {
		ObjectResponse response = new ObjectResponse(500);
		float result = 0;
		try {
			result = Float.parseFloat(input);
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a number.");
			return response;
		}
		response.setStatusCode(200);
		response.setFloatValue(result);
		response.setResult(true);
		return response;
	}

	public static ObjectResponse parseLong(String input) {
		ObjectResponse response = new ObjectResponse(500);
		long result = 0;
		try {
			result = Long.parseLong(input);
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a number.");
			return response;
		}
		response.setStatusCode(200);
		response.setLongValue(result);
		response.setResult(true);
		return response;
	}

	public static ObjectResponse parseShort(String input) {
		ObjectResponse response = new ObjectResponse(500);
		short result = 0;
		try {
			result = Short.parseShort(input);
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a number.");
			return response;
		}
		response.setStatusCode(200);
		response.setShortValue(result);
		response.setResult(true);
		return response;
	}

}
