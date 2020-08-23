/**
 * @(#)ATM.java
 *
 *
 * @author David Tormey
 * @version 1.00 2020/3/8
 */

import java.util.Scanner;
public class ATM { //start of class

	//declare variables
	static Scanner input = new Scanner(System.in);
	static String accountNames[]={"Aaron","Keith","Jason"}; //Names in the array
	static int passwords[]= {1234,2345,4567}; //Passwords in the array
	static int balance[]= {300,400,600}; //balance in the array
	static int index=0; //stores location of person in array (index)
	static int i;
	static int overdraft[]={1,1,0}; //This determines whether a user has an overdraft or not. 1=Allowed, 2=Disallowed.

	public static void main(String args[]){ //start of main
		index=loginMethod();
		displayMethod();
	} //end main

	//error check method
	public static int errorCheck(String stringinput){

		//While loop for error checking
       	while(!stringinput.matches("\\d+")){
       	System.out.println( "Please enter numbers only.");
		stringinput=input.next();
       	}//End While loop error checking

     	return Integer.parseInt(stringinput);
	}//end of check

	public static int loginMethod() { //start of loginMethod
		//declare variables
		String name;
		int password;
		int person=0;
		boolean found=false;
		//
		System.out.print("Please enter your name: ");
		name=input.next(); //Name input
		System.out.println("");
		System.out.print("Please enter your password: ");
	    password=input.nextInt(); //Password input


	    	while (found==false){

	    	for (int i=0; i<accountNames.length; i++){//to search for password in the list
     		if(accountNames[i].equals(name) && passwords[i]==password){
     			found=true;
     			index=i;
     			System.out.println("Welcome "+accountNames[i]); //accountNames[i] has the users position in the array
     			break;

     		} //end if

     	} //end for

     		//start of if(error check)

     		if(found == false){//Start of IF statement
    			System.out.println("Incorrect username and password.\n\nPlease try again");
	    		//take user input
	   				System.out.print("Please enter your name: ");
					name=input.next(); //Name input
					System.out.println("");
					System.out.print("Please enter your password: ");
		    		password=input.nextInt(); //Password input

     		}

		} //end of loginMethod

	return displayMethod(); //This allows the method to return to the display method which contains the main menu for the ATM.

	}

	public static int displayMethod(){
		int choice;
		System.out.println("\n\t\tWelcome, please choose one	\n\n\t\t1:Bank Statement\n\n\t\t2:Change Password\n\n\t\t3:Deposit Cash\n\n\t\t4:Withdraw Cash\n\n\t\t5:Exit\n");
		System.out.print("Please choose a menu:");
		choice = input.nextInt(); //This allows the user to select a menu option.
		if(choice==1){
     		bankStatement(); //This calls up the bank statement.
     	}
     		else if(choice==2){
     			changePassword(); //This calls up the password change method
     		}
     		else if(choice==3){
     			depositMethod(); //This calls up the deposit method
     		}
     		else if(choice==4){
				withdrawMethod(); //This calls up the withdraw method
     		}
     		else if(choice==5){
     			exitMethod(); //This calls up the exit method.
     		}

     		else{
				System.out.print("\nPlease select a valid option!\n");
				displayMethod(); //The display method will loop if the user does not select one of the five options.
     		}

		return index;
	}

	public static void bankStatement(){ //Start of bank statement method.
		System.out.println("Hi "+accountNames[index] +"\nYour balance is: "+balance[index]);
		displayMethod();
	} //end of bank statement method

	public static void changePassword(){ //start of password change method
		int newPassword; //this will contain the new password
		String stringnewPassword;
		boolean flag=false; //This flag will be used if the new password is not in the array
		System.out.print("\nPlease enter your new password:");
			//start looking for string
    		stringnewPassword = input.next();
    		newPassword=errorCheck(stringnewPassword); //This will check that the user inputs numeric passwords or not.
    		//end looking for string

    		for (i=0;i<passwords.length; i++){ //Start of for

    			if (newPassword==passwords[i]){ //Start of if
    				System.out.println("You cannot use this password, returning to main menu.");
    				flag=true;
    				break;
    			}//End of if



    		}//end of for
    		if (flag==false){ //start of if
    		passwords[index]=newPassword;
    		System.out.println("Your new password is: "+passwords[index]);
			} //end of if

    		System.out.print("\nThank you. Returning to main menu.\n");
    		displayMethod(); //Returns user to main menu




	}
	public static int depositMethod(){ //Start of deposit method
			String stringnum1;
			int depositAmount;
        	System.out.print("Please enter the amount you wish to deposit:");
            stringnum1=input.next();
            depositAmount=errorCheck(stringnum1);
            balance[index]=balance[index]+depositAmount; //This adds the deposit amount to the balance in the array
            System.out.println("Your new balance is: "+balance[index]+""); //This prints out the new balance
            return displayMethod(); //This returns the user back to the main menu
    } //End of deposit method

    public static void withdrawMethod(){ //Start of withdraw method
    	String stringnum2;
    	int withdrawAmount;
    	System.out.print("Please enter how much you want to withdraw:");
        stringnum2=input.next();
        //error checking so that the user must enter a number
          withdrawAmount=errorCheck(stringnum2);
		//if statement for bank overdraft
        if (withdrawAmount<balance[index]){
        	balance[index]=balance[index]-withdrawAmount; //This subtracts the withdrawl amount from the balance.
        }
        else if (withdrawAmount>balance[index]){
        	 if (overdraft[index]==1){ //This checks the overdraft is allowed
        	 	balance[index]=balance[index]-withdrawAmount;
        	 }
        	 else {
        	 	System.out.println("You have insufficient funds to do that."); //This will be printed if the user does not have an overdraft
        	 }
        }
    	System.out.println("The new balance is "+balance[index]);
    	displayMethod();
    }// End withdrawMethod

   public static void exitMethod(){ //Start of exit method
   		System.out.println("Thank you for chooing this ATM.");
   		System.exit(0);
   		return;

   } //end of exit method

} //end of class