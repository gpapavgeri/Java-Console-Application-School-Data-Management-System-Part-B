/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc_privateschool;

import java.util.Scanner;

/**
 * @author Georgia_Papavgeri
 */
public class JDBC_PrivateSchool {
    
    public static void main(String[] args) {
        
        DbCommunication dbc = new DbCommunication();
        dbc.createConnection();
        
        while (true){
         
        System.out.println(WELCOME_MESSAGE);
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        
        while (number == 1) {
            System.out.println(INPUT_MESSAGE);
            Scanner input1 = new Scanner(System.in);
            int inputValue1 = input1.nextInt();

            switch (inputValue1) {
                case 1: // INSERT Student
                    dbc.processStudentData();
                    break;
                case 2: // INSERT Trainer
                    dbc.processTrainerData();
                    break;
                case 3: // INSERT Course
                    dbc.processCourseData();
                    break;
                case 4: // INSERT Assignment
                    dbc.processAssignmentData();
                    break;
                case 5: // ASSIGN students to courses   
                    dbc.showStudentsAndCourses();
                    dbc.assignStudentToCourse();
                    break;
                case 6: // ASSIGN trainers to courses
                    dbc.showTrainersAndCourses();
                    dbc.assignTrainerToCourse();
                    break;
                case 7: // ASSIGN assignments to courses 
                    dbc.showAssignmentsAndCourses();
                    dbc.assignAssignmentToCourse();
                    break;  
                case 8: // Exit to the main menu
                    number = -1;
                    break;
                default:
                    System.out.println("Invalid entry");
                    break;
            }
        }   
                
        while (number == 2) {
            System.out.println(PRINT_MESSAGE);
            Scanner input2 = new Scanner(System.in);
            int inputValue2 = input2.nextInt();

            switch (inputValue2) {
                case 1: // PRINT students list 
                    System.out.println("\n~~~~~~~~~~~~~~~~~\nLIST OF STUDENTS:\n~~~~~~~~~~~~~~~~~");
                    dbc.printStudents();
                    break;
                case 2: // PRINT trainers list 
                    System.out.println("\n~~~~~~~~~~~~~~~~~\nLIST OF TRAINERS:\n~~~~~~~~~~~~~~~~~");
                    dbc.printTrainers();
                    break;
                case 3: // PRINT courses list 
                    System.out.println("LIST OF COURSES:\n~~~~~~~~~~~~~~~~");
                    dbc.printCourses();
                    break;
                case 4: // PRINT assignments list 
                    System.out.println("\n~~~~~~~~~~~~~~~~~~~~\nLIST OF ASSIGNMENTS:\n~~~~~~~~~~~~~~~~~~~~");
                    dbc.printAssignments();
                    break;
                case 5: // PRINT Students per Course 
                    System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nLIST OF STUDENTS PER COURSE:\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    dbc.printStudentsPerCourse();
                    break;
                case 6: // PRINT Trainers per Course 
                    System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nLIST OF TRAINERS PER COURSE:\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    dbc.printTrainersPerCourse();
                    break;
                case 7: // PRINT Assignments per Course
                    System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nLIST OF ASSIGNMENTS PER COURSE:\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    dbc.printAssignmentsPerCourse();
                    break;
                case 8: // PRINT Assignments per Course per Student
                    System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nLIST OF ASSIGNMENTS PER COURSE PER STUDENT:\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    dbc.printAssignmentsPerCoursePerStudent();
                    break;
                case 9: // PRINT students that belong to more than one courses 
                    System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nLIST OF STUDENTS THAT BELONG TO MORE THAN ONE COURSES:\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    dbc.printStudentsInMoreThanOneCourses();
                    break;
                case 10: // Exit to the main menu
                    number = -1;
                    break;
                default:
                    System.out.println("Invalid entry");
                    break;
            }
        }
               
        if (number == 3) {  
            dbc.closeConnection();
            break;
        }
        
        if (number != 1 && number != 2 && number != 3 && number != -1) {
            System.out.println("Invalid Entry!\nPlease, try again!");
        }
        
        }
    }
        
private static final String WELCOME_MESSAGE = new StringBuilder("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
            .append("\nWelcome to our Private School of Civil Engineers!\n")
            .append("Please press:\n")
            .append("(1) to INPUT/ASSIGN data, \n")
            .append("(2) to PRINT data, \n")
            .append("(3) to EXIT program. \n")
            .append("Enter the number here: ")
            .toString();
   
    private static final String INPUT_MESSAGE = new StringBuilder("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
            .append("\nPlease press one of the following numbers to continue:\n")
            .append("(1) to INSERT Student,\n")
            .append("(2) to INSERT Trainer,\n")
            .append("(3) to INSERT Course, \n")
            .append("(4) to INSERT Assignment, \n")
            .append("(5) to ASSIGN Student to Course, \n")
            .append("(6) to ASSIGN Trainer to Course, \n")
            .append("(7) to ASSIGN Assignment to Course, \n")
            .append("(8) to EXIT to the main menu. \n")
            .append("Enter the number here: ")
            .toString();
    
    private static final String PRINT_MESSAGE = new StringBuilder("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
            .append("\nPlease press one of the following numbers to continue:\n")
            .append("(1) to PRINT a list of Students, \n")
            .append("(2) to PRINT a list of Trainers, \n")
            .append("(3) to PRINT a list of Courses, \n")
            .append("(4) to PRINT a list of Assignments, \n")
            .append("(5) to PRINT all the Students per Course , \n")
            .append("(6) to PRINT all the Trainers per Course , \n")
            .append("(7) to PRINT all the Assignments per Course , \n")
            .append("(8) to PRINT all the Assignments per Course per Student , \n")
            .append("(9) to PRINT a list of students that belong to more than one courses , \n")
            .append("(10) to EXIT to the main menu. \n")
            .append("Enter the number here: ")
            .toString();

}
        

    

