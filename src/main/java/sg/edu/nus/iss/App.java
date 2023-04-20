package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        String dirPath = args[0];

        // Use File class to check of directory exists (Task 1)
        // if directory doesn't exist, create the directory using variable dirPath
        File newDirectory = new File(dirPath);
        if (newDirectory.exists()) {
            System.out.println("The directory " + newDirectory + " already exists");
        } else {
            newDirectory.mkdir();
        }

        System.out.println("Welcome to your shopping cart");

        // List collection to store the cart items of different users
        List<String> cartItems = new ArrayList<String>();
        // refer to day 1 workshop

        // Use Console class to read from keyboard inputs
        // Input variable is defined; use the readLine function
        // Use while loop to do processing
        Console con = System.console();
        String input = "";

        // Used to keep track of current login user
        // This is also used as the filaname to store the user cart items
        String loginUser = "";

        // exit while loop is keyboard "input" equals to "quit"
        // Note: 'help' is skipped; refer to day 1 workshop if needed
        while (!input.equals("quit")) {
            input = con.readLine("What do you want to do? ");
        }

        // Create login; create file to store cart items of each user
        // Note: 6 because blank after login - comprsises 5 characters
        if (input.startsWith("login")) {
            Scanner scan = new Scanner(input.substring(6));

            while (scan.hasNext()) {
                loginUser = scan.next();

        // check if the file <loginUser> exists
        // if not exists, create a new file
        // else (maybe) overwrite

        File loginFile = new File(dirPath + File.separator + loginUser);
        if (loginFile.exists()) {
            System.out.println("File " + loginUser + "already exist");
        } else {
            loginFile.createNewFile();
        }
            }
        }


        // Create a list to see all users
        if (input.equals("users")) {
            File directoryPath = new File(dirPath);
            String[] directoryListing = directoryPath.list();
            System.out.println("List of files and directories in the specific folder " + dirPath);

            // Can do for or while loop
            for (String dirList : directoryListing) {
                System.out.println(dirList);
            }
        }
        if (input.startsWith("add")) {
            input = input.replace(',', ' ');

            // Instantiate
            // Need to create a file now and write to file
            // (Step 1) Use FileWriter & PrintWriter to write a <loginUser> file

            FileWriter fw = new FileWriter(dirPath + File.separator + loginUser, false);
            PrintWriter pw = new PrintWriter(fw);

            String currentScanString = " ";
            Scanner inputScanner = new Scanner(input.substring(4));

            while (inputScanner.hasNext()) {
                currentScanString = inputScanner.next();
                // cartItems.add(currentScanString);

                // (Step 2) Write to file using PrintWriter
                pw.write("\n" + currentScanString);
            }

            // (Step 3) flush and close the fileWriter and printWriter objects
            pw.flush();
            pw.close();
            fw.close();
        }

        //Note: user must be logged in first (e.g. login <loginUser>

        if(input.equals("list")){
            //Need a file class/object and 
            //any reader (e.g. Buffered Reader) because every item is stored as a line
            //1. Need a File class and BufferedReader class to read cartItems from the file
            File readFile = new File(dirPath + File.separator + loginUser);
            BufferedReader br = new BufferedReader(new FileReader(readFile)); 

            //reset cartItems list Collection
            cartItems = new ArrayList<String>(); 

            String readFileInput;
            //while loop to read through all the item records in the file 
            //condition allow Bufferedreader to read items line by line 
            while((readFileInput = br.readLine()) != null) {
                System.out.println(readFileInput);
                cartItems.add(readFileInput); 

            }

            //exist from while loop
            //just close the buffered reader (dont need to flush for reading)
            br.close(); 

            //delete file from memory; remove then use overwrite
            //Can use Scanner object or subString 
         if (input.startsWith("delete")){
            //stringVal[0] --> "delete"
            //stringVal[1] --> "index to delete"
            String [] stringVal = input.split(' '); 

            //delete item from cart
            // e.g. delete 2, remove 3rd item in cartItems ArrayList
            //need to check whether data is present to delete 
            int deleteIndex = Integer.parseInt(stringVal[1]);
            if (deleteIndex <=cartItems.size()){
            cartItems.remove(deleteIndex);
            }else {
                System.out.println("Index out of range error."); 
            }

            //1. Open FileWriter and BufferedWriter
            FileWriter fw = new FileWriter(dirPath + File.separator + loginUser, false);
            BufferedWriter bw = new BufferedWriter(fw);

            //2. Loop to write cartItems to file (for or while loop)
            int listIndex =0;
            while(listIndex < cartItems.size()){
                bw.write(cartItems.get(listIndex));
                bw.newLine(); //instead of bw.write("\n"); 

                listIndex++;
            }

            //3. Flush and close BufferedWriter and FileWriter (reverse order)
            bw.flush();
            bw.close();
            fw.close(); 

         }
        }//end of the while loop


    }//end of main function

}//class level
