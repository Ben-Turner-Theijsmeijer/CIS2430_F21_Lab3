package Lab3;

import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.io.PrintWriter;
// import java.io.FileOutputStream;
// import java.io.FilenameFilter;
import java.io.File;
import java.io.FileNotFoundException;

public class Student {

    private int year;
    private String program;
    private float averageGrade;
    static ArrayList <Student> studentRegister = new ArrayList <Student>();
    static Scanner scan = new Scanner(System.in);
    private static String delimiters = "[ ]+";

    //----------------------------Constructor, set, get, and to string functions---------------------------------
    //constructor
    public Student(String programOfStudent, int yearOfStudent, float studentAverageGrade){
        this.setProgram(programOfStudent);
        this.setYear(yearOfStudent);
        this.setAverageGrade(studentAverageGrade);
    }

    //set and get for student average grade
    public float getAverageGrade(){
        return averageGrade;
    }
    public void setAverageGrade(float enteredValue) {
        this.averageGrade = enteredValue;
    }
    //set and get for student program
    public String getProgram(){
        return program;
    }
    public void setProgram(String enteredString) {
        this.program = enteredString;
    }
    //set and get for student year
    public int getYear(){
        return year;
    }
    public void setYear(int enteredInt) {
        this.year = enteredInt; 
    }

    //toString for student class
    @Override
    public String toString() {
        DecimalFormat oneDecimal = new DecimalFormat();
        oneDecimal.setMaximumFractionDigits(1);
        return "Student Info: \nProgram: " + program + "\nYear: " + year + "\nAverage Grade: " + oneDecimal.format(averageGrade) + "%";
    }
    //-------------------------End of constructor, set, get, and toString Functions------------------------------

    //------------------------------------------Untility Functions-----------------------------------------------
    //displaying the menu
    private static void printMenu(){
        System.out.println("===================================================================================");
        System.out.println("Menu:");
        System.out.println("1) Enter information for a new Student");
        System.out.println("2) Enter information for a new GraduateStudent");
        System.out.println("3) Show all student information with each atttribute on a seperate line");
        System.out.println("4) Print the average of the average grades fro all students as wll as the number of students");
        System.out.println("5) Enter a specific program and print all student information for that program");
        System.out.println("6) Load student information from an input file");
        System.out.println("7) Save student informaiton to an output file");
        System.out.println("8) End the Program");
        System.out.println("===================================================================================");
    }

    //get command loop option
    private static int getCommandLoopOption(){
        int menuChoice = 0; // 0 = unset, 1 = enter new student, 2 = enter new gradstudent, 3 = display students, 4 = print average,
                            // 5 = specific program search, 6 = load from file, 7 = write to file 8 = end program
        boolean valid = true;
        do{
            if(valid == true){
                System.out.print("Enter choice: ");
                menuChoice = Integer.parseInt(scan.nextLine());
            }
            else if(valid == false){
                System.out.print("Sorry entered option was not valid, Enter choice: ");
                menuChoice = Integer.parseInt(scan.nextLine());
            }
            valid = false;
        }while(menuChoice > 8 || menuChoice < 1);
        return menuChoice;
    }

    //seperatting the program and the year of the student
    private static String[] seperate (String enteredString){
        String invalid;
        String [] parts = enteredString.split(delimiters);
        //checks if the correct number of inputs were given and if not loops until they are
        while(parts [0].isEmpty() || parts.length != 2){
            System.out.println("Sorry that was not a valid program and or year, Enter student Program and Year:");
            invalid = scan.nextLine();
            parts = invalid.split(delimiters);
        }
        return parts;
    }

    //setting the average grade of the student
    private static float averageGradeSetter(String inputString){
        float tempAverage;
        //checking if a value was entered if not defaulting to 0%
        if(inputString.isEmpty()){
            tempAverage = 0;
        }
        else{
            tempAverage = Float.parseFloat(inputString);
        }
        //if entered value is too large or too small, defaulting to max or min value
        if(tempAverage > 100){
            System.out.println("Sorry the entered value was larger than 100%, average has defaulted to 100%");
            tempAverage = 100;
        }
        else if(tempAverage < 0){
            System.out.println("Sorry the entered value was less than 0%, average has defaulted to 0%");
            tempAverage = 0;
        }
        return tempAverage;
    }

    //Setting the PhD value of the grad
    private static boolean PHDSetter(String yesNo){
        boolean passable = false;
        boolean tempIsPHD = false;
        while(passable == false){
            if(yesNo.equalsIgnoreCase("yes")){
            tempIsPHD = true;
            passable = true;
            }
            else if(yesNo.equalsIgnoreCase("no")){
                tempIsPHD = false;
                passable = true;
            }
            else{                                                                   //re-request while not equal to yes or no
                System.out.println("Error: must specify Yes or No");
                yesNo = scan.nextLine();
            } 
        }
        return tempIsPHD;
    }

    //checking if the student register arraylist is empty
    private static boolean checkStudentRegister(){
        boolean isEmpty;
        if(studentRegister.isEmpty()){
            System.out.println("Sorry, class list is currently empty");
            System.out.println("Please enter some information and try again");
            isEmpty = true;
        }
        else{
            isEmpty = false;
        }
        return isEmpty;
    }

    //setting filename for file
    private static String getFileName(){
        String fileName;
        fileName = scan.nextLine();
        while(fileName.isBlank()){
            System.out.println("Error: file was left blank. Please enter file name:");
            fileName = scan.nextLine();
        }
        return fileName;
    }

    //to string for writing to file in specified format
    public static String writeStudent(Student temp){
        DecimalFormat oneDecimal = new DecimalFormat();
        oneDecimal.setMaximumFractionDigits(1);
        return temp.getProgram() + " " + temp.getYear() + " " + oneDecimal.format(temp.getAverageGrade());
    }
    //------------------------------------Command Loop Option Functions-----------------------------------------
    //OPTION 1
    //generate new student
    private static void createNewStudent(){
        int tempYear;
        String tempProgram;
        float tempAverage;
        String inputString;
        String tempParsed[] = new String[2];

        //new student
        //getting year and program
        System.out.println("Enter student Program and Year: ");
        inputString = scan.nextLine();
        tempParsed = seperate(inputString);
        tempProgram = tempParsed[0];
        tempYear = Integer.parseInt(tempParsed[1]);

        //gettting average grade
        System.out.println("Enter Average grade, or leave blank:");
        inputString = scan.nextLine();
        tempAverage = averageGradeSetter(inputString);

        //creating a new student object and adding it to the arraylist
        Student tempStudent = new Student(tempProgram, tempYear, tempAverage);
        studentRegister.add(tempStudent);
    }

    //OPTION 2
    //generate new gradStudent
    private static void createNewGrad(){
        //regular studnet parameters
        int tempYear;
        String tempProgram;
        float tempAverage;
        //grad student parameters
        String tempSupervisor;
        boolean tempIsPHD = false;
        String tempUndergradSchool;
        //parsing variables
        String inputString;
        String tempParsed[] = new String[2];
        String yesNo;

        //new GradStudent
        //getting year and program
        System.out.println("Enter student Program and Year: ");
        inputString = scan.nextLine();
        tempParsed = seperate(inputString);
        tempProgram = tempParsed[0];
        tempYear = Integer.parseInt(tempParsed[1]);

        //gettting average grade
        System.out.println("Enter Average grade, or leave blank:");
        inputString = scan.nextLine();
        tempAverage = averageGradeSetter(inputString);

        //getting grad student parameters
        System.out.println("Enter supervisor:");
        tempSupervisor = scan.nextLine();                                   //get supervisor
        while(tempSupervisor.isBlank()){                                    //re-request information while left blank
            System.out.println("Error: must specify a supervisor:");        
            tempSupervisor = scan.nextLine();
        }
        System.out.println("Is this a PHD: Yes or No");
        yesNo = scan.nextLine();                                            //get if it is a PHD or not
        tempIsPHD = PHDSetter(yesNo);                                       //re-request while not equal to yes or no
        
        System.out.println("Enter undergraduate school, or leave blank:");
        tempUndergradSchool = scan.nextLine();                          //get the undergraduate school
        if(tempUndergradSchool.isBlank()){                              //set undergraduate school to UNSPECIFIED if left blank
            tempUndergradSchool = "UNSPECIFIED";
        }

        //creating a new student object and adding it to the arraylist
        Student tempStudent = new GraduateStudent(tempProgram, tempYear, tempAverage, tempSupervisor, tempIsPHD, tempUndergradSchool);
        studentRegister.add(tempStudent);
    }

    //OPTION 3
    //displaying the students information
    private static void printStudents(){
        String studentInfo;
        System.out.println("---------------------------------------");
        for(Student temp: studentRegister){                             //iterate through all objects in array
            studentInfo = temp.toString();
            System.out.println(studentInfo);                            //print object information
            System.out.println("---------------------------------------");
        }
    }

    //OPTION 4
    //suming all the average grades and averaging them for one overhead average grade
    private static void averageOfAverageGrades() {
        double overallAverage = 0;
        int i = 0;
        for(Student temp: studentRegister){                             //iterate through all objects in array
            overallAverage += temp.getAverageGrade();                   //sum average grade of all objects
            i++;                                                        //get number of objects
        }
        overallAverage /= i;                                            //get average grade across all objects
        System.out.printf("The overall average grade for all students is: %.1f%%\n", overallAverage);
        System.out.println("Across a total of " + i + " students"); 
        System.out.println();
    }

    //OPTION 5
    //displaying all information relevant the the specified program
    private static void displaySpecificInformation(){
        boolean found = false;
        String specifiedProgram;
        String studentInfo;
        System.out.println("What program would you like to look for: ");
        specifiedProgram = scan.nextLine();
        System.out.println("---------------------------------------");
        for(Student temp: studentRegister){                             //iterate though all objects in array list
            if(temp.getProgram().equals(specifiedProgram)){             //print program info if it matches search term
                studentInfo = temp.toString();
                System.out.println(studentInfo);
                found = true;
                System.out.println("---------------------------------------");
            }
        }
        if(found == false){
            System.out.println("Sorry, No information found for the specified program");
        }        
    }

    //OPTION 6
    //read student info from a textfile
    private static void readFromFile(){
        //regular student parameters
        int tempYear;
        String tempProgram;
        float tempAverage;
        //grad student parameters
        String tempSupervisor;
        boolean tempIsPHD = false;
        String tempUndergradSchool;

        String input;
        String[] parts = new String [6];
        System.out.println("Enter the file to read from. Be sure to include the file extension:");
        String fileName = getFileName();
        try{
            File inputFile = new File(fileName);
            Scanner scanFile = new Scanner(inputFile);
            while(scanFile.hasNextLine()){
                input = scanFile.nextLine();
                parts = input.split(" ");
                tempProgram = parts[0];
                tempYear = Integer.parseInt(parts[1]);
                tempAverage = Float.parseFloat(parts[2]);
                if(!parts[3].isEmpty()){             //meaning must be a grad student object
                    tempSupervisor = parts[3];
                    tempIsPHD = Boolean.parseBoolean(parts[4]);
                    tempUndergradSchool = parts[5];
                    //creating a new gradStudent object and adding it to the arraylist
                    Student tempStudent = new GraduateStudent(tempProgram, tempYear, tempAverage, tempSupervisor, tempIsPHD, tempUndergradSchool);
                    studentRegister.add(tempStudent);
                }
                else{
                    //creating a new student object and adding it to the arraylist
                    Student tempStudent = new Student(tempProgram, tempYear, tempAverage);
                    studentRegister.add(tempStudent);
                }
            }
            scanFile.close();
        }catch(FileNotFoundException e){
            System.out.println("Error: could not read from file " + fileName);
            return;
        }
        
        
        

    }
    //OPTION 7
    //write student info to a textfile
    private static void writeToFile(){
        System.out.println("Enter the file to write to. Be sure to include the file extension:");
        String fileName = getFileName();
        try{
            PrintWriter fileWriter = new PrintWriter(fileName);
            for(Student temp: studentRegister){                             //iterate though all objects in array list
                fileWriter.println(writeStudent(temp));
            }
            fileWriter.close();
        } catch(FileNotFoundException e){
            System.out.println("Error: could write to file " + fileName);
            return;
        }

    }

    //-------------------------------------------Main Function------------------------------------------------
    public static void main(String[] args){
        boolean isEmpty;
        int menuChoice = 0;
        while(menuChoice != 8){
            //printing the menu
            printMenu();
            //getting user choice for program option
            menuChoice = getCommandLoopOption();
            switch(menuChoice){
                case 1:
                    //new student
                    createNewStudent();
                    break;
                case 2:
                    //new gradStudent
                    createNewGrad();
                    break;
                case 3:
                    //displaying the students information
                    isEmpty = checkStudentRegister();
                    if(isEmpty == false){
                        printStudents();
                    }
                    break;
                case 4:
                    //suming all the average grades and averaging them for one overhead average grade
                    isEmpty = checkStudentRegister();
                    if(isEmpty == false){
                        averageOfAverageGrades();
                    }
                    break;
                case 5:
                    //displaying all information relevant the the specified program
                    isEmpty = checkStudentRegister();
                    if(isEmpty == false){
                        displaySpecificInformation();
                    }
                    break;
                case 6:
                    //load student information from a file
                    readFromFile();
                    break;
                case 7:
                    //write student information to a file
                    writeToFile();
                    break;
                case 8:
                    //ends the program
                    System.out.println("closing Program");
                    break;
            }

        }
        scan.close();
    }
}
