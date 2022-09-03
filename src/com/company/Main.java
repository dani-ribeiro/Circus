package com.company;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Queue<String> categories = new PriorityQueue<String>();
        /*
        Uses a scanner to read in each employee from the CircusEmployees.txt file
            - Creates an Employee object from each line in the file using their info
            - Add each Employee object to the employee queue for easy access/storage purposes
         */
        Queue<Employee> employeeQueue = new LinkedList<Employee>();
        Scanner reader = new Scanner(new File("src/CircusEmployees.txt"));
        while(reader.hasNextLine()){
            String lastName = reader.next();
            String firstName = reader.next();
            String midInitial = reader.next();
            String IDNum = reader.next();
            String category = reader.next();
            String nickname = reader.next();
            Employee temp = new Employee(lastName, firstName, midInitial, IDNum, category, nickname);
            employeeQueue.add(temp);
            if(!categories.contains(category)){
                categories.add(category);
            }
            reader.nextLine();
        }

        /*
        A comparator used to tell the priority queue structure
        to sort the Employees alphabetically by last name.
         */
        Comparator<Employee> alphabeticalComparator = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        };

        /*
        A comparator used to tell the priority queue structure
        to sort the Employees' ID Numbers in ascending order.
         */
        Comparator<Employee> numericalComparator = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getIDNum().compareTo(o2.getIDNum());
            }
        };

        System.out.println("WELCOME TO THE CIRCUS!\n");
        //printMenu();

        boolean playing = true;
        while(playing){
            Scanner  category;
            Scanner caseInputs;
            Scanner console = new Scanner(System.in);
            printMenu();
            System.out.println("\nWhat would you like to do?");
            System.out.print("Option ");
            int input = console.nextInt();
            switch (input){
                case 1:
                    //employee last names in alphabetical order
                    System.out.println("\nSAY HELLO TO THE JAVA CIRCUS!");
                    printNamesAlphabetically(employeeQueue, alphabeticalComparator);

                    System.out.println("\n==============================================================================");
                    break;
                case 2:
                    //employee IDs in ascending order
                    System.out.println("\nCALL US OVER & BOOK A CIRCUS EVENT NEAR YOU, WE'LL BE THERE!");
                    printIDNumerically(employeeQueue, numericalComparator);

                    System.out.println("\n==============================================================================");
                    break;
                case 3:
                    //inserts new employee
                    System.out.println("\nTHERE'S A NEW KID IN TOWN!\nFILL OUT OUR APPLICATION TO START WORKING" +
                            " AT THE CIRCUS!\n");
                    caseInputs = new Scanner(System.in);

                    System.out.print("First Name: ");
                    String firstName = caseInputs.next();
                    System.out.print("\nLast Name: ");
                    String lastName = caseInputs.next();
                    System.out.print("\nMiddle Initial: ");
                    String midInitial = caseInputs.next();
                    System.out.print("\nID Number: ");
                    String IDNum = caseInputs.next();
                    System.out.print("\nCategory: ");
                    String cat = caseInputs.next();
                    System.out.print("\nNickname: ");
                    String nickname = caseInputs.next();
                    employeeQueue.add(new Employee(lastName, firstName, midInitial, IDNum, cat, nickname));
                    if(!categories.contains(cat)){
                        categories.add(cat);
                    }
                    System.out.print("\nWELCOME TO THE FAMILY!");

                    System.out.println("\n==============================================================================");
                    break;
                case 4:
                    System.out.println("\nWHO ARE WE SENDING HOME?\nFILL OUT OUR PERFORMER COMPLAINT FORM\n");

                    //deletes an employee
                    caseInputs = new Scanner(System.in);
                    System.out.println("First & Last Name: (FIRSTNAME LASTNAME)");
                    String fName = console.next();
                    String lName = console.next();
                    if(employeeQueue.stream().anyMatch(employee -> employee.getLastName().equals(lName) && employee.getFirstName().equals(fName))){
                        Queue<Employee> tempQueue = new PriorityQueue<Employee>(alphabeticalComparator);
                        while(!employeeQueue.isEmpty()){
                            Employee e = employeeQueue.remove();
                            if(!e.getFirstName().equals(fName) && !e.getLastName().equals(lName)) {
                                tempQueue.add(e);
                            }
                        }
                        employeeQueue.addAll(tempQueue);
                        System.out.println("\nWE'LL TAKE CARE OF THE REST");
                    }else{
                        System.out.println("NO SUCH EMPLOYEE");
                    }

                    System.out.println("==============================================================================");
                    break;
                case 5:
                    //prints all employees in a specified category alphabetically
                    category = new Scanner(System.in);
                    System.out.print("\nWHICH CATEGORY ARE YOU INTERESTED IN? ");
                    String chosen = category.next();
                    if(!categories.stream().anyMatch(s -> s.equalsIgnoreCase(chosen))){
                        System.out.println("NO SUCH CATEGORY");
                        System.out.println("==============================================================================");
                        break;
                    }

                    //print
                    System.out.println("LADIES AND GENTLEMEN, PLEASE WELCOME OUR " + chosen.toUpperCase() + "S");
                    printEmployeesInCategory(chosen,employeeQueue, alphabeticalComparator);

                    System.out.println("\n==============================================================================");
                    break;
                case 6:
                    System.out.println("\nWELCOME TO THE CIRCUS, TODAY'S CIRCUS CREW INCLUDES\n");

                    //prints all employees sectioned in their categories in alphabetical order
                    Queue<String> temp = new PriorityQueue<String>(categories);
                    while(!temp.isEmpty()){
                        String c = temp.remove();
                        System.out.print(c.toUpperCase() + "S:");
                        printEmployeesInCategory(c,employeeQueue, alphabeticalComparator);
                        System.out.println("\n☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
                    }

                    break;
                case 7:
                    System.out.println("\nWANT TO ADD A NEW TALENT TO THE SHOW?\n");

                    category = new Scanner(System.in);
                    System.out.print("WHAT'S YOUR TALENT (CATEGORY)? ");
                    String picked = category.next();
                    if(categories.stream().anyMatch(s -> s.equalsIgnoreCase(picked))){
                        System.out.println("CATEGORY ALREADY EXISTS!");
                        System.out.println("==============================================================================");
                        break;
                    }

                    categories.add(picked);

                    System.out.println(picked.toUpperCase() + "S WILL BE PRESENT AT THE NEXT SHOW!");
                    System.out.println("==============================================================================");
                    break;
                case 8:
                    //removes category and all of its respective employees
                    System.out.println("\nYOU HATED THE SHOW? REALLY? WHAT PART OF THE SHOW?");
                    System.out.print("\nWHAT TALENT (CATEGORY) DO YOU NEVER WANT TO SEE AGAIN? ");
                    category = new Scanner(System.in);
                    String delete = category.next();
                    if(!categories.stream().anyMatch(s -> s.equalsIgnoreCase(delete))){
                        System.out.println("NO SUCH CATEGORY");
                        break;
                    }

                    //removing category
                    Queue<String> tempCategoryQueue = new LinkedList<>();
                    while(!categories.isEmpty()){
                        String c = categories.remove();
                        if(!c.equalsIgnoreCase(delete)){
                            tempCategoryQueue.add(c);
                        }
                    }
                    categories.addAll(tempCategoryQueue);

                    //removing employees
                    Queue<Employee> tempEmployeeQueue = new LinkedList<>();
                    while(!employeeQueue.isEmpty()){
                        Employee e = employeeQueue.remove();
                        if(!e.getCategory().equalsIgnoreCase(delete)){
                            tempEmployeeQueue.add(e);
                        }
                    }
                    employeeQueue.addAll(tempEmployeeQueue);

                    System.out.println("\nTHERE WILL BE NO MORE " + delete.toUpperCase() + "S AT THE CIRCUS!");
                    System.out.println("==============================================================================");
                    break;
                case 9:
                    //quit program
                    playing = false;

                    System.out.println("\nTHANKS FOR COMING! SEE YOU AGAIN NEXT YEAR!");
                    System.out.println("==============================================================================");
                    break;
            }
        }
    }

    public static void printEmployeesInCategory(String category, Queue<Employee> employeeQueue, Comparator<Employee> alphabeticalComparator){
        Queue<Employee> alphabeticalOrder = new PriorityQueue<Employee>(alphabeticalComparator);
        for(Employee n : employeeQueue){
            if(n.getCategory().equalsIgnoreCase(category)){
                alphabeticalOrder.add(n);
            }
        }
        int size = alphabeticalOrder.size();
        while(!alphabeticalOrder.isEmpty()){
            for(int i=0;i<size;i++){
                if(i % 10 == 0) {
                    System.out.println();
                }
                if(i < size - 1) {
                    System.out.print(alphabeticalOrder.remove().getLastName() + ", ");
                }else if(i < size && i != 0){
                    System.out.print("and " + alphabeticalOrder.remove().getLastName() + ".");
                }else{
                    System.out.print(alphabeticalOrder.remove().getLastName() + ".");
                }
            }
        }
    }

    //prints all ID numbers in ascending order, limiting 10 IDs to each line for formatting purposes
    public static void printIDNumerically(Queue<Employee> employeeQueue, Comparator<Employee> numericalComparator){
        Queue<Employee> numericallyOrdered = new PriorityQueue<Employee>(numericalComparator);
        numericallyOrdered.addAll(employeeQueue);

        while(!numericallyOrdered.isEmpty()){
            for(int i=0;i<employeeQueue.size();i++){
                if(i % 10 == 0) {
                    System.out.println();
                }
                if(i< employeeQueue.size() - 1) {
                    System.out.print(numericallyOrdered.remove().getIDNum() + ", ");
                }else if(i < employeeQueue.size() && i != 0){
                    System.out.print("and " + numericallyOrdered.remove().getIDNum() + ".");
                }else{
                    System.out.print(numericallyOrdered.remove().getIDNum() + ".");
                }
            }
        }
    }

    //prints all names alphabetically, limiting 10 names to each line for formatting purposes
    public static void printNamesAlphabetically(Queue<Employee> employeeQueue, Comparator<Employee> alphabeticalComparator){
        Queue<Employee> alphabeticalLastNamesOrder = new PriorityQueue<Employee>(alphabeticalComparator);
        alphabeticalLastNamesOrder.addAll(employeeQueue);

        while(!alphabeticalLastNamesOrder.isEmpty()){
            for(int i=0;i<employeeQueue.size();i++){
                if(i % 10 == 0) {
                    System.out.println();
                }
                if(i < employeeQueue.size()-1) {
                    System.out.print(alphabeticalLastNamesOrder.remove().getLastName() + ", ");
                }else if(i == employeeQueue.size() && i != 0){
                    System.out.print("and " + alphabeticalLastNamesOrder.remove().getLastName() + ".");
                }else{
                    System.out.print(alphabeticalLastNamesOrder.remove().getLastName() + ".");
                }
            }
        }
    }

    public static void printMenu(){
        System.out.println("1) Print List Alphabetically\n" +
                "2) Print List by ID Number\n" +
                "3) Insert New Employee\n" +
                "4) Delete An Employee\n" +
                "5) Print only a particular category list of employees alphabetically\n" +
                "6) Print entire list of all employees by category alphabetically\n" +
                "7) Add A Category\n" +
                "8) Delete A Category (And All Employees)\n" +
                "9) Quit");
        System.out.println("==============================================================================");
    }
}
