package userInput;

/**
 * @author Isaac Earnhart
 * @version cs113
 */

//TODO add nfe exception checks to all parse calls in methods
//Copy of cs111 class for userIO and error checking

import java.util.Scanner;

public class Cs113
{
	// CONSTANTS
	public static final String NAME = "Isaac Earnhart";
	public static final String COURSE = "CS 113 Data Structures and Algorithms";
	public static final String SECTION = "MW 3:30 - 5:20";

	public static double counterDbl;
	public static int counterInt;
	
	// DESCRIPTION:		Print author information for start of program
	// PRE-CONDITIONS:	All parameters are given valid values
	// POST-CONDITIONS:	Outputs author info to console
	public static void printHeader(int homework, int project, String lastModified)
	{
		//output
		System.out.println("/********************************************");
		System.out.println("* AUTHOR:        " + NAME);
		System.out.println("* COURSE:        " + COURSE);
		System.out.println("* SECTION:       " + SECTION);
		System.out.println("* HOMEWORK #:    " + homework);
		System.out.println("* PROJECT #:	 " + project);
		System.out.println("* LAST MODIFIED: " + lastModified);
		System.out.println("********************************************/");
	}


	// DESCRIPTION:		reads input from user, returns integer value (error-checked using bounds)
	// PRECONDITIONS:	key is instantiated, prompt is in form "Enter...: ", lower < upper
	// POSTCONDITIONS:	returns integer value between lower and upper (inclusive)
	public static int readInt(String prompt, Scanner key, int lower, int upper)
	{
		String temp;
		int result;
		boolean isNotValid;
		isNotValid = true;
		result = 0; //keep compiler happy
		do
		{
			System.out.print(prompt);
			temp = key.nextLine();
			try
			{
				result = Integer.parseInt(temp);
				//rest of code ONLY runs if above line doesn't cause error
				isNotValid = (result < lower) || (result > upper);
				if(isNotValid)
				{
					System.out.println("ERROR: please enter value between "
						+ lower + " - " + upper);
				}
			}
			catch (NumberFormatException nfe)
			{
				System.out.println("ERROR: integer input is required");
			}
		} while(isNotValid);
		return result;
	}
	
	// DESCRIPTION:		reads input from user, returns integer value (error-checked using bounds)
	// PRECONDITIONS:	key is instantiated, prompt is in form "Enter...: "
	// POSTCONDITIONS:	returns integer value between above lower(inclusive)
	public static int readInt(String prompt, Scanner key, int lower)
	{
		String temp;
		int result;
		boolean isNotValid;
		isNotValid = true;
		result = 0; //keep compiler happy
		do
		{
			System.out.print(prompt);
			temp = key.nextLine();
			try
			{
				result = Integer.parseInt(temp);
				//rest of code ONLY runs if above line doesn't cause error
				isNotValid = (result < lower);
				if(isNotValid)
				{
					System.out.println("ERROR: please enter value above "
						+ lower);
				}
			}
			catch (NumberFormatException nfe)
			{
				System.out.println("ERROR: integer input is required");
			}
		} while(isNotValid);
		return result;
	}


	
	// DESCRIPTION:		reads input from user, returns double value (error-checked using bounds)
	// PRECONDITIONS:	key is instantiated, prompt is in form "Enter...: ", lower < upper
	// POSTCONDITIONS:	returns double value between lower and upper (inclusive)
	public static double readDouble(String prompt, Scanner key, double lower, double upper)
	{
		String temp;
		double result;
		boolean isNotValid;
		
		do
		{
			System.out.print(prompt);
			temp = key.nextLine();
			result = Double.parseDouble(temp);
			isNotValid = (result < lower) || (result > upper);
			if (isNotValid)
			{
				System.out.println("ERROR: please enter value between " + lower + " - " + upper);
			}
		} while (isNotValid);
		
		return result;
	}
	
	// DESCRIPTION:		reads input from user, returns char value (error-checked using validChars)
	// PRECONDITIONS:	key is instantiated, prompt is in form "Enter...: " valid characters in String form
	// POSTCONDITIONS:	returns char value that is present in String of valid characters
    public static char readChar(String prompt, Scanner key, String validChar)
    {
        String temp, result;
        boolean isNotValid;
        
        do
        {
			System.out.print(prompt);
			temp = key.nextLine();
			result = temp.substring(0,1);
			isNotValid = validChar.indexOf(result) == -1;
			if(isNotValid)
			{
				System.out.println("ERROR: please enter one of the following characters " + validChar);
			}
		}while(isNotValid);
				
		return result.charAt(0);
    }
    
    
    // DESCRIPTION:		reads inputs from user, concatinates inputs into a string seperated by dataSeparate.
	//					stops data collection if input is equal to exitCase, returns concatinated string,
	//					for each user input that is not extiCase strCount is incremented by increment 
	//					*does not concatinate exitCase into returned string
	// PRECONDITIONS:	key is instantiated, prompt is in form "Enter...: ", strCount is initialized
	// POSTCONDITIONS:	returns String value that is concatination of user entered Strings
    public static String dataString(String prompt, 
									Scanner key, 
									String exitCase, 
									String dataSeparate,
									double strCount,
									double increment)
    {
		String temp, result;
		result = "";
		System.out.print(prompt);
		temp = key.nextLine();
		
		while (!temp.equals(exitCase))
		{
			strCount += increment;
			result = result + dataSeparate + temp;
			System.out.print(prompt);
			temp = key.nextLine();
		}
		counterDbl = strCount;
		return result;
	}
	
	// DESCRIPTION:		reads inputs from user, concatinates inputs into a string seperated by dataSeparate.
	//					stops data collection if input is equal to exitCase, returns concatinated string,
	//					*does not concatinate exitCase into returned string
	// PRECONDITIONS:	key is instantiated, prompt is in form "Enter...: "
	// POSTCONDITIONS:	returns String value that is concatination of user entered Strings
    public static String dataString(String prompt, 
									Scanner key, 
									String exitCase, 
									String dataSeparate)
    {
		String temp, result;
		result = "";
		
		System.out.print(prompt);
		temp = key.nextLine();
		
		do
		{
			result = result + dataSeparate + temp;
			System.out.print(prompt);
			temp = key.nextLine();
		}while (!temp.equals(exitCase));
		
		return result;
	}
	
	// DESCRIPTION:		reads inputs from user, concatinates inputs into a string seperated by dataSeparate.
	//					stops data collection if input is equal (Ignoring case) to exitCase, returns concatinated string,
	//					for each user input that is not extiCase strCount is incremented by increment 
	//					*does not concatinate exitCase into returned string
	// PRECONDITIONS:	key is instantiated, prompt is in form "Enter...: ", strCount is initialized
	// POSTCONDITIONS:	returns String value that is concatination of user entered Strings
    public static String dataStringIgnoreCase(String prompt, 
												Scanner key, 
												String exitCase, 
												String dataSeparate,
												double strCount,
												double increment)
    {
		String temp, result;
		result = "";
		System.out.print(prompt);
		temp = key.nextLine();
		
		while (!temp.equalsIgnoreCase(exitCase))
		{
			strCount += increment;
			result = result + dataSeparate + temp;
			System.out.print(prompt);
			temp = key.nextLine();
		}
		counterDbl = strCount;
		return result;
	}
	
    /**
	 * DESCRIPTION:		reads inputs from user, concatinates inputs into a string seperated by dataSeparate.
	 *					stops data collection if input is equal to exitCase, returns concatinated string,
	 *					for each user input that is not extiCase strCount is incremented by increment 
	 *					*does not concatinate exitCase into returned string
	 * PRECONDITIONS:	key is instantiated, prompt is in form "Enter...: ", strCount is initialized
	 * POSTCONDITIONS:	returns String value that is concatination of user entered Strings
	 * 
	 * @param prompt
	 * @param key
	 * @param exitCase
	 * @param dataSeparate
	 * @param strCount
	 * @param increment
	 * @return
	 */
    public static String dataString(String prompt, 
									Scanner key, 
									String exitCase, 
									String dataSeparate,
									int strCount,
									int increment)
    {
		String temp, result;
		result = "";
		System.out.print(prompt);
		temp = key.nextLine();
		
		while (!temp.equals(exitCase))
		{
			strCount += increment;
			result = result + dataSeparate + temp;
			System.out.print(prompt);
			temp = key.nextLine();
		}
		counterInt = strCount;
		return result;
	}
	
    /**
	 * DESCRIPTION:		reads inputs from user, concatinates inputs into a string seperated by dataSeparate.
	 *					stops data collection if input is equal (Ignoring case) to exitCase, returns concatinated string,
	 *				 	for each user input that is not extiCase strCount is incremented by increment 
	 *				    *does not concatinate exitCase into returned string
	 * PRECONDITIONS:	, prompt is in form "Enter...: ", strCount is initialized
	 * 
	 * @param prompt
	 * @param key key must is instantiated
	 * @param exitCase
	 * @param dataSeparate
	 * @param strCount
	 * @param increment
	 * @return String value that is concatination of user entered Strings
	 */
    public static String dataStringIgnoreCase(String prompt, 
												Scanner key, 
												String exitCase, 
												String dataSeparate,
												int strCount,
												int increment)
    {
		String temp, result;
		result = "";
		System.out.print(prompt);
		temp = key.nextLine();
		
		while (!temp.equalsIgnoreCase(exitCase))
		{
			strCount += increment;
			result = result + dataSeparate + temp;
			System.out.print(prompt);
			temp = key.nextLine();
		}
		counterInt = strCount;
		return result;
	}
	
	//DESCRIPTION:		
	public static void printArray(double[] original)
	{
		for(int counter = 0;counter < original.length;counter++)
		{
			System.out.println((counter + 1) + ") " + original[counter]);
		}
	}
	
	public static void initializeArray(double[] original,double value)
	{
		for(int counter = 0;counter < original.length;counter++)
		{
			original[counter] = value;
		}
	}
	
	public static double[] copyArray(double[] original)
	{
		double[] copy;
		copy = new double[original.length];
		for(int counter = 0;counter < original.length;counter++)
		{
			copy[counter] = original[counter];
		}
		return copy;
	}
	
	public static int[] copyArray(int[] original)
	{
		int[] copy;
		copy = new int[original.length];
		for(int counter = 0;counter < original.length;counter++)
		{
			copy[counter] = original[counter];
		}
		return copy;
	}
	
	public static void printArray(int[] original)
	{
		for(int counter = 0;counter < original.length;counter++)
		{
			System.out.println((counter + 1) + ") " + original[counter]);
		}
	}
	
	public static void initializeArray(int[] original,int value)
	{
		for(int counter = 0;counter < original.length;counter++)
		{
			original[counter] = value;
		}
	}
	
	public static void initializeArray(char[] original,char value,int numberUsed)
	{
		for(char counter = 0;counter < numberUsed;counter++)
		{
			original[counter] = value;
		}
	}
	
	
	public static boolean arrayEquals(double[] first,double[] second)
	{
		boolean result;
		if(first.length == second.length)
		{
			result = true;
			for(int counter = 0;counter < first.length;counter++)
			{
				if (first[counter] != second[counter])
				{
					result = false;
				}
			}
		}
		else
		{
			result = false;
		}
		return result;
	}
	
	public static boolean arrayEquals(int[] first,int[] second)
	{
		boolean result;
		if(first.length == second.length)
		{
			result = true;
			for(int counter = 0;counter < first.length;counter++)
			{
				if (first[counter] != second[counter])
				{
					result = false;
				}
			}
		}
		else
		{
			result = false;
		}
		return result;
	}
	
	public static char[] stringToArray(String data)
	{
		char[] array;
		array = new char[data.length()];
		
		for(int counter = 0;counter < data.length();counter++)
		{
			array[counter] = data.charAt(counter);
		}
		return array;
		
	}
	
	public static String arrayToString(char[] array,int numberUsed)
	{
		String data;
		data = "";
		for(int counter = 0;counter < numberUsed;counter++)
		{
			data = data + array[(numberUsed - 1) - counter];
		}
		return data;
		
	}
}

