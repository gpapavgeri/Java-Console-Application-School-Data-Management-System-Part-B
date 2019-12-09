/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc_privateschool;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author Georgia_Papavgeri
 */
public class DbCommunication {
    public Connection con;
    
    // Method to create connection with the database
    public void createConnection() {
        String url = "jdbc:mysql://localhost/private_school?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "mySQL2019!";
    
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    // Method to close connection with the database
    public void closeConnection() {
        try {
            con.close();
            System.out.println("Thank you for visiting our school!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    // Method to read input data
    private static String readInputData(String message) {
        Scanner input = new Scanner(System.in);
        System.out.print(message);
        return input.nextLine();
    }
    
    // Method to create and insert student
    public void processStudentData() {
        // Read data from user's input
        String studentFirstName = readInputData("Insert Student's First Name: ");
        String studentLastName = readInputData("Insert Student's Last Name: ");
        LocalDate studentDateOfBirth = LocalDate.parse(readInputData("Insert Student's Date of Birth (YYYY-MM-DD): "));
        double studentTuitionFees = Double.valueOf(readInputData("Insert Student's Tuition Fees (€): "));
        // Create and Insert student into sql list of students
        if (con != null){
            try (PreparedStatement pstm = con.prepareStatement("INSERT INTO students (first_name, last_name, date_of_birth, tuition_fees)\n" +
                    "VALUES (?, ?, ?, ?)")) {
                pstm.setString(1, studentFirstName);
                pstm.setString(2, studentLastName);
                pstm.setDate(3, Date.valueOf(studentDateOfBirth));
                pstm.setDouble(4, studentTuitionFees);
                pstm.executeUpdate();
                System.out.println("\nStudent (" + studentFirstName + " " + studentLastName + ") was added succesfully!");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }    
    
    // Method to create and insert trainer    
    public void processTrainerData() {
        // Read data from user's input
        String trainerFirstName = readInputData("Insert Trainer's First Name: ");
        String trainerLastName = readInputData("Insert Trainer's Last Name: ");
        String trainerSubject = readInputData("Insert Trainer's Subject: ");        
        // Create and Insert trainer into sql list of trainers
        if (con != null){
                try (PreparedStatement pstm = con.prepareStatement("INSERT INTO trainers (first_name, last_name, t_subject)\n" +
                        "VALUES (?, ?, ?)")) {
                    pstm.setString(1, trainerFirstName);
                    pstm.setString(2, trainerLastName);
                    pstm.setString(3, trainerSubject);
                    pstm.executeUpdate();
                    System.out.println("\nTrainer (" + trainerFirstName + " " + trainerLastName + ") was added succesfully!");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }        
    
    // Method to create and insert course
    public void processCourseData() {
        // Read data from user's input
        String courseTitle = readInputData("Insert Course's Title: ");
        String courseStream = readInputData("Insert Course's Stream: ");
        String courseType = readInputData("Insert Course's Type: ");
        LocalDate courseStartDate = LocalDate.parse(readInputData("Insert Course's Start Date (YYYY-MM-DD): "));
        LocalDate courseEndDate = LocalDate.parse(readInputData("Insert Course's End Date (YYYY-MM-DD): "));        
        // Create and Insert course into sql list of courses
        if (con != null){
                try (PreparedStatement pstm = con.prepareStatement("INSERT INTO courses (title, stream, c_type, start_date, end_date)\n" +
                        "VALUES (?, ?, ?, ?, ?)")) {
                    pstm.setString(1, courseTitle);
                    pstm.setString(2, courseStream);
                    pstm.setString(3, courseType);
                    pstm.setDate(4, Date.valueOf(courseStartDate));
                    pstm.setDate(5, Date.valueOf(courseEndDate));
                    pstm.executeUpdate();
                    System.out.println("\nCourse (" + courseTitle + ") was added succesfully!");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    // Method to create and insert assignment
    public void processAssignmentData() {
        // Read data from user's input
        String assignmentTitle = readInputData("Insert Assignment's Title: ");
        String assignmentDescription = readInputData("Insert Assignment's Description: ");
        LocalDateTime assignmentSubDateTime = LocalDateTime.parse(readInputData("Insert Assignment's Submission Date and Time(YYYY-MM-DDTHH:MM:SS): "));
        double assignmentOralMark = Double.valueOf(readInputData("Insert Assignment's Oral Mark: "));
        double assignmentTotalMark = Double.valueOf(readInputData("Insert Assignment's Total Mark: "));        
        // Create and Insert assignment into sql list of assignments
        if (con != null){
                try (PreparedStatement pstm = con.prepareStatement("INSERT INTO assignments (title, a_description, sub_DateTime, oral_mark, total_mark)\n" +
                        "VALUES (?, ?, ?, ?, ?)")) {
                    pstm.setString(1, assignmentTitle);
                    pstm.setString(2, assignmentDescription);
                    pstm.setTimestamp(3, Timestamp.valueOf(assignmentSubDateTime));
                    pstm.setDouble(4, assignmentOralMark);
                    pstm.setDouble(5, assignmentTotalMark);
                    pstm.executeUpdate();
                    System.out.println("\nCourse (" + assignmentTitle + ") was added succesfully!");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
        
    // Method to print a list of all the students and courses
    public void showStudentsAndCourses() {
        if (con != null){
            try {
                ResultSet rs1;
                Statement stm2;
                ResultSet rs2;
                try (Statement stm1 = con.createStatement()) {
                    System.out.println("Select the Student's ID and the Course's ID from the following tables:");
                    rs1 = stm1.executeQuery("SELECT student_id, first_name, last_name\n" +
                            "FROM students");
                    System.out.println("~~~~~~~~~~~~~~~~~~\nList of Students:\n~~~~~~~~~~~~~~~~~~");
                    while(rs1.next()){
                        System.out.println("Student: (" + rs1.getString(2) + " " + rs1.getString(3) + ") -> ID: (" + rs1.getInt(1) + ")");
                    }   stm2 = con.createStatement();
                    rs2 = stm2.executeQuery("SELECT course_id, title\n" +
                            "FROM courses");
                    System.out.println("~~~~~~~~~~~~~~~\nList of Courses:\n~~~~~~~~~~~~~~~");
                    while(rs2.next()){
                        System.out.println("Course: (" + rs2.getString(2) + ") -> ID: (" + rs2.getInt(1) + ")");
                    }
                }
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }
        }
    }
        
    // Method to assign student to course
    public void assignStudentToCourse() {    
        // Read data from user's input
        int studentId = Integer.valueOf(readInputData("\nInsert Student's ID: "));
        int courseId = Integer.valueOf(readInputData("Insert Course's ID: "));      
        // Insert student per course into sql list of students_per_course
        if (con != null){
            try (PreparedStatement pstm1 = con.prepareStatement("INSERT INTO students_per_course (course_id, student_id)\n" +
                    "VALUES (?, ?)")) {
            pstm1.setInt(1, courseId);
            pstm1.setInt(2, studentId);       
            pstm1.executeUpdate();
                System.out.println("The student was assigned successfully!");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + 
                    "\nPlease, try again!");
            }   
        }       
    }   
    
    // Method to print a list of all the trainers and courses
    public void showTrainersAndCourses() {
        if (con != null){
            try {
                ResultSet rs1;
                Statement stm2;
                ResultSet rs2;
                try (Statement stm1 = con.createStatement()) {
                    System.out.println("Select the Trainer's ID and the Course's ID from the following tables:");
                    rs1 = stm1.executeQuery("SELECT trainer_id, first_name, last_name\n" +
                            "FROM trainers");
                    System.out.println("~~~~~~~~~~~~~~~~~~\nList of Trainers:\n~~~~~~~~~~~~~~~~~~");
                    while(rs1.next()){
                        System.out.println("Trainer: (" + rs1.getString(2) + " " + rs1.getString(3) + ") -> ID: (" + rs1.getInt(1) + ")");
                    }   stm2 = con.createStatement();
                    rs2 = stm2.executeQuery("SELECT course_id, title\n" +
                            "FROM courses");
                    System.out.println("~~~~~~~~~~~~~~~\nList of Courses:\n~~~~~~~~~~~~~~~");
                    while(rs2.next()){
                        System.out.println("Course: (" + rs2.getString(2) + ") -> ID: (" + rs2.getInt(1) + ")");
                    }
                }
            }
            catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }
        }
    }
    
    // Method to assign trainer to course
    public void assignTrainerToCourse() {    
        // Read data from user's input
        int trainerId = Integer.valueOf(readInputData("\nInsert Trainer's ID: "));
        int courseId = Integer.valueOf(readInputData("Insert Course's ID: "));      
        // Insert trainer per course into sql list of trainers_per_course
        if (con != null){
                try (PreparedStatement pstm1 = con.prepareStatement("INSERT INTO trainers_per_course (course_id, trainer_id)\n" +
                        "VALUES (?, ?)")) {
                    pstm1.setInt(1, courseId);
                    pstm1.setInt(2, trainerId);
                    pstm1.executeUpdate();
                    System.out.println("The trainer was assigned successfully!");           
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + 
                    "\nPlease, try again!");
            }       
        }       
    }   
   
    // Method to print a list of all the assignments and courses
    public void showAssignmentsAndCourses() {
        if (con != null){
            try {
                ResultSet rs1;
                Statement stm2;
                ResultSet rs2;
                try (Statement stm1 = con.createStatement()) {
                    System.out.println("Select the Assignment's ID and the Course's ID from the following tables:");
                    rs1 = stm1.executeQuery("SELECT assignment_id, title\n" +
                            "FROM assignments");
                    System.out.println("~~~~~~~~~~~~~~~~~~\nList of Assignments:\n~~~~~~~~~~~~~~~~~~");
                    while(rs1.next()){
                        System.out.println("Assignment: (" + rs1.getString(2) + ") -> ID: (" + rs1.getInt(1) + ")");
                    }   stm2 = con.createStatement();
                    rs2 = stm2.executeQuery("SELECT course_id, title\n" +
                            "FROM courses");
                    System.out.println("~~~~~~~~~~~~~~~\nList of Courses:\n~~~~~~~~~~~~~~~");
                    while(rs2.next()){
                        System.out.println("Course: (" + rs2.getString(2) + ") -> ID: (" + rs2.getInt(1) + ")");
                    }
                }
            }
            catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }
        }
    }
    
    // Method to assign assignment to course
    public void assignAssignmentToCourse() {    
        // Read data from user's input
        int courseId = Integer.valueOf(readInputData("Insert Course's ID: "));      
        int assignmentId = Integer.valueOf(readInputData("Insert Assignment's ID: "));        
        // Insert assignment per course into sql list of assignments_per_course
        if (con != null){
                try (PreparedStatement pstm1 = con.prepareStatement("\nINSERT INTO assignments_per_course (course_id, assignment_id)\n" +
                        "VALUES (?, ?)")) {
                    pstm1.setInt(1, courseId);
                    pstm1.setInt(2, assignmentId);
                    pstm1.executeUpdate();
                    System.out.println("The assignment was added successfully!");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + 
                    "\nPlease, try again!");
            }
            
        }       
    }   

    
    // Method to print a list of all the students
    public void printStudents() {
        List<Student> students = new ArrayList<>();
        if (con != null){
            try (Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM students")) {
                while(rs.next()){
                    LocalDate localDate = (rs.getDate(4)!= null) ? rs.getDate(4).toLocalDate() : null;
                    Student st = new Student(rs.getInt(1), rs.getString(2), rs.getString(3), localDate, rs.getDouble(5));
                    students.add(st);
                }
                System.out.println(students);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } 
    }
    
    // Method to print a list of all the trainers
    public void printTrainers() {
        List<Trainer> trainers = new ArrayList<>();
        if (con != null){
            try (Statement stm = con.createStatement();
                 ResultSet rs = stm.executeQuery("SELECT * FROM trainers")) {
                while (rs.next()){
                    String trSubject = (rs.getString(4)!= null) ? rs.getString(4) : null;
                    Trainer tr = new Trainer(rs.getInt(1), rs.getString(2), rs.getString(3), trSubject);
                    trainers.add(tr);
                }
                    System.out.println(trainers);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    // Method to print a list of all the assignments
    public void printAssignments() {
        List<Assignment> assignments = new ArrayList<>();
        if (con != null) {
            try (Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM assignments")){
                while (rs.next()) {
                    LocalDateTime localDateTime = rs.getTimestamp(4).toLocalDateTime();
                    String oralMark = (rs.getString(5) != null) ? rs.getString(5) : null;
                    String totalMark = (rs.getString(6) != null) ? rs.getString(6) : null;
                    Assignment as = new Assignment(rs.getInt(1), rs.getString(2), rs.getString(3), localDateTime, oralMark, totalMark);
                    assignments.add(as);
                }
                System.out.println(assignments);
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
    
    // Method to print a list of all the courses
    public void printCourses() {
        List<Course> courses = new ArrayList<>();
        if (con != null){
            try (Statement stm = con.createStatement(); 
                ResultSet rs = stm.executeQuery("SELECT * FROM courses")) {
                while(rs.next()){
                    LocalDate startDate = rs.getDate(5).toLocalDate();
                    LocalDate endDate = rs.getDate(6).toLocalDate();
                    Course course = new Course(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), startDate, endDate);
                    courses.add(course);
                }
                System.out.println(courses);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } 
    }
    
    // Method to print all the students per course
    public void printStudentsPerCourse() {
        Map<Course, List<Student>> studentsPerCourse = new HashMap<>();
        if (con != null){
            try (Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery("SELECT c.course_id as courseId, c.title as course,\n" +
                        "s.student_id as studentId, s.first_name as fname, s.last_name as lname\n" +
                        "FROM students_per_course sp\n" +
                        "INNER JOIN students s \n" +
                        "ON s.student_id = sp.student_id\n" +
                        "INNER JOIN courses c \n" +
                        "ON c.course_id = sp.course_id\n" +
                        "order by c.course_id;")) {
                    while(rs.next()){
                        Student st = new Student(rs.getInt("studentId"), rs.getString("fname"), rs.getString("lname"));
                        Course course = new Course(rs.getInt("courseId"), rs.getString("course"));
                        List<Student> students = new ArrayList<>();
                        if (studentsPerCourse.containsKey(course)) {
                            students = studentsPerCourse.get(course);
                            students.add(st);
                        } else {
                            students.add(st);
                        }
                        studentsPerCourse.put(course, students);
                    }   for (Map.Entry<Course,List<Student>> entry : studentsPerCourse.entrySet())
                        System.out.println("COURSE: \nId: " + entry.getKey().getId() + ", " + "Title: " + entry.getKey().getTitle() + 
                                "\nSTUDENTS ASSIGNED TO COURSE:\n" + entry.getValue().toString() + "\n");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } 
    }
    
    // Method to print all the trainers per course    
    public void printTrainersPerCourse() {
        Map<Course, List<Trainer>> trainersPerCourse = new HashMap<>();
        if (con != null){
            try (Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery("SELECT c.course_id as courseId, c.title as course,\n" +
                        "t.trainer_id  as trainerId, t.first_name as fname, t.last_name as lname\n" +
                        "FROM trainers_per_course tp\n" +
                        "INNER JOIN trainers t \n" +
                        "ON t.trainer_id = tp.trainer_id\n" +
                        "INNER JOIN courses c \n" +
                        "ON c.course_id = tp.course_id\n" +
                        "order by c.course_id;")) {
                    while(rs.next()){
                        Trainer trainer = new Trainer(rs.getInt("trainerId"), rs.getString("fname"), rs.getString("lname"));
                        Course course = new Course(rs.getInt("courseId"), rs.getString("course"));
                        List<Trainer> trainers = new ArrayList<>();
                        if (trainersPerCourse.containsKey(course)) {
                            trainers = trainersPerCourse.get(course);
                            trainers.add(trainer);
                        } else {
                            trainers.add(trainer);
                        }
                        trainersPerCourse.put(course, trainers);
                    }   for (Map.Entry<Course,List<Trainer>> entry : trainersPerCourse.entrySet())
                        System.out.println("COURSE: \nId: " + entry.getKey().getId() + ", " + "Title: " + entry.getKey().getTitle() + 
                                "\nTRAINERS ASSIGNED TO COURSE:\n" + entry.getValue().toString() + "\n");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } 
    }
    
    // Method to print all the assignments per course    
    public void printAssignmentsPerCourse() {
        Map<Course, List<Assignment>> assignmentsPerCourse = new HashMap<>();
        if (con != null){
            try (Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery("SELECT c.course_id as courseId, c.title as course,\n" +
                        "a.assignment_id as assignmentId, a.title AS assignment,\n" +
                        "a.a_description as description, a.sub_DateTime AS subDateTime\n" +
                        "FROM assignments_per_course ap\n" +
                        "INNER JOIN assignments a \n" +
                        "ON a.assignment_id = ap.assignment_id\n" +
                        "INNER JOIN courses c \n" +
                        "ON c.course_id = ap.course_id\n" +
                        "order by c.course_id;")) {
                    while(rs.next()){
                        Assignment assignment = new Assignment(rs.getInt("assignmentId"), rs.getString("assignment"), rs.getString("description"), rs.getTimestamp("subDateTime").toLocalDateTime());
                        Course course = new Course(rs.getInt("courseId"), rs.getString("course"));
                        List<Assignment> assignments = new ArrayList<>();
                        if (assignmentsPerCourse.containsKey(course)) {
                            assignments = assignmentsPerCourse.get(course);
                            assignments.add(assignment);
                        } else {
                            assignments.add(assignment);
                        }
                        assignmentsPerCourse.put(course, assignments);
                    }   for (Map.Entry<Course,List<Assignment>> entry : assignmentsPerCourse.entrySet())
                        System.out.println("COURSE: \nId: " + entry.getKey().getId() + ", " + "Title: " + entry.getKey().getTitle() + 
                                "\nASSIGNMENTS ASSIGNED TO COURSE:\n" + entry.getValue().toString() + "\n");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } 
    }
    
    // Method to print all the assignments per course per student
    public void printAssignmentsPerCoursePerStudent() {
        if (con != null){
                try (Statement stm = con.createStatement(); 
                    ResultSet rs = stm.executeQuery("SELECT s.student_id as studentId, s.first_name as fname, s.last_name as lname,\n" +
                        "c.course_id as courseId, c.title as course,\n" +
                        "a.assignment_id as assignmentId, a.title AS assignment\n" +
                        "FROM assignments_per_course ap\n" +
                        "INNER JOIN assignments a\n" +
                        "ON a.assignment_id = ap.assignment_id\n" +
                        "INNER JOIN courses c \n" +
                        "ON c.course_id = ap.course_id\n" +
                        "INNER JOIN students_per_course sp\n" +
                        "ON sp.course_id = ap.course_id\n" +
                        "INNER JOIN students s\n" +
                        "ON s.student_id = sp.student_id\n"+
                        "ORDER BY s.student_id, c.course_id")) {
                    while(rs.next()){
                        System.out.println("Student: ID(" + rs.getInt(1) + "), First Name=" + rs.getString(2)+ ", Last Name=" + rs.getString(3)+
                            "\nCourse: ID(" + rs.getInt(4) + "), Title=" + rs.getString(5)+
                            "\nAssignment: ID(" + rs.getInt(6)+ "), Title=" + rs.getString(7) + "\n");
                    }
                } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } 
    }
        
    // Method to print a list of students that belong to more than one courses
    public void printStudentsInMoreThanOneCourses() {
        List<Student> studentsInMoreCourses = new ArrayList<>();
        if (con != null){
            try (Statement stm = con.createStatement(); 
                ResultSet rs = stm.executeQuery("SELECT s.student_id as studentId,\n" +
                        "s.first_name as fname, s.last_name as lname,\n" +
                        "s.date_of_birth as dateOfBirth, s.tuition_fees as tuitionFees,\n" +
                        "COUNT(sp.course_id) AS Number_of_courses\n" +
                        "FROM students_per_course sp\n" +
                        "INNER JOIN students s ON s.student_id = sp.student_id\n" +
                        "GROUP BY sp.student_id\n" +
                        "HAVING Number_of_courses > 1")){
                while(rs.next()){
                    LocalDate localDate = (rs.getDate(4)!= null) ? rs.getDate(4).toLocalDate() : null;
                    Student st = new Student(rs.getInt(1), rs.getString(2), rs.getString(3), localDate, rs.getDouble(5));
                    studentsInMoreCourses.add(st);
                }
                System.out.println(studentsInMoreCourses);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } 
    }
    
}
    
