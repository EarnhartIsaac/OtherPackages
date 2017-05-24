package userInput;

import java.util.Iterator;
import java.util.Scanner;

public class Menu 
{
	private SingleLinkedList<MenuOption> options;
	private Scanner keyboard = new Scanner(System.in);
	
	public void setOption(int position,String optionName,char keyBind)
	{
		if(position > -1 && position < options.size())
		{
			this.options.set(position,new MenuOption(optionName,keyBind));
		}
	}
	
	public void addOption(String optionName,char keyBind)
	{
		this.options.add(new MenuOption(optionName,keyBind));
	}
	
	public char printMenu()
	{
		MenuOption temp;
		String userInputStr;
		char userInput;
		
		Iterator<MenuOption> itr = options.iterator();
		while(itr.hasNext())
		{
			temp = itr.next();
			System.out.println(temp.getKeyBind() + ") " + temp.getOptionName());
		}
		userInputStr = keyboard.nextLine();
		userInput = userInputStr.charAt(0);
		
		return userInput;
	}
	
	public class MenuOption 
	{
		//FORMAT LIKE A COMMAND ex. "Remove object", "Search for name"
		private String optionName;
		private char keyBind;
		
		public char getKeyBind()
		{
			return this.keyBind;
		}
		
		public String getOptionName()
		{
			return this.optionName;
		}
		
		public MenuOption(String optionName,char keyBind)
		{
			this.optionName = optionName;
			this.keyBind = keyBind;
		}
		
	}
}
