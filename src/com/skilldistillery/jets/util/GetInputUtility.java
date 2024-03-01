
package com.skilldistillery.jets.util;

import java.util.Scanner;

public class GetInputUtility {

	public GetInputUtility() {
		super();
	}

	@SuppressWarnings("unchecked")
	public <T extends Comparable<T>> T getInput(Object prompt, T min, T max, Scanner scanner, int... options) {

		boolean shouldTryAgain = true;

		T validUserInput = null;

		Class<?> clazz = min.getClass(); // Get the class of the input type

		do {

			if (prompt instanceof String[]) {
				int menuIndex = 0;
				for (String choice : (String[]) prompt) {
					menuIndex++;
					System.out.printf("%d. %s \n", menuIndex, choice);
				}
				System.out.print("Enter your choice:");
			} else {
				String promptResult = prompt.toString();
				System.out.print(promptResult);
			}

			String userInputString = scanner.nextLine();

			try {
				if (clazz.equals(Integer.class)) {
					// System.out.println("Integer");
					validUserInput = (T) (Integer) Integer.parseInt(userInputString);
				} else if (clazz.equals(Float.class)) {
					// System.out.println("Float");
					validUserInput = (T) (Float) Float.parseFloat(userInputString);
				} else if (clazz.equals(Double.class)) {
					// System.out.println("Double");
					validUserInput = (T) (Double) Double.parseDouble(userInputString);
				} else if (clazz.equals(Long.class)) {
					// System.out.println("Long");
					validUserInput = (T) (Long) Long.parseLong(userInputString);
				} else if (clazz.equals(String.class)) {
					// System.out.println("String");
					validUserInput = (T) userInputString;
				} else {
					// System.out.println("Unsupported input type");
					return null;
				}

			} catch (NumberFormatException numberFormatException) {

				// System.err.println(numberFormatException);

				shouldTryAgain = true;

			} catch (Exception exception) {

				// System.err.println(exception);

				shouldTryAgain = true;

			} finally {

				// System.err.println("Finally...");

				// Always executes

			}

			if (validUserInput != null) {
				if (validUserInput.compareTo(min) >= 0 && validUserInput.compareTo(max) <= 0) {
					// if String, validate length of string if options length(s) provided
					if (clazz.equals(String.class) && options.length > 0) {
						switch (options.length) {
						case 1: // string must equal length of single option
							if (userInputString.length() == options[0]) {
								shouldTryAgain = false;
							} else {
								shouldTryAgain = true;
							}
							break;
						case 2: // string must of length that is be between two options
							if (userInputString.length() >= options[0] && userInputString.length() <= options[1]) {
								shouldTryAgain = false;
							} else {
								shouldTryAgain = true;
							}
							break;
						default:
							shouldTryAgain = true;

						}
					} else {
						shouldTryAgain = false; // was not a string or no options for length limit provided
					}

				} else {
					shouldTryAgain = true;
				}
			}

		} while (shouldTryAgain);

		return validUserInput;
	}

}
