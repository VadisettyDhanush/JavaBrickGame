package School;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IllegalArgumentException {
        List<Teacher> teacherList = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();
        School CU = new School(teacherList, studentList);

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter your ID:");
            int userId = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            String expectedPassword = "CU" + userId;
            System.out.println("Enter password:");
            String password = scanner.nextLine();

            if (!expectedPassword.equals(password)) {
                System.out.println("Invalid password for user ID: " + userId);
                return;
            }

            boolean exit = false;
            while (!exit) {
                System.out.println("\nMenu:");
                System.out.println("1. Add Teacher");
                System.out.println("2. Add Student");
                System.out.println("3. Pay Fees for a Student");
                System.out.println("4. Pay Salary to a Teacher");
                System.out.println("5. View Total Money Earned");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        try {
                            System.out.println("Enter teacher ID, name, and salary (separated by commas):");
                            String[] teacherInfo = scanner.nextLine().split(",");
                            if (teacherInfo.length != 3)
                                throw new IllegalArgumentException("Invalid input format for teacher.");
                            Teacher teacher = new Teacher(
                                    Integer.parseInt(teacherInfo[0].trim()),
                                    teacherInfo[1].trim(),
                                    Integer.parseInt(teacherInfo[2].trim())
                            );
                            CU.addTeacher(teacher);
                            teacherList.add(teacher);
                            System.out.println("Teacher added successfully.");
                        } catch (NumberFormatException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("Enter student ID, name, and grade (separated by commas):");
                        String[] studentInfo = scanner.nextLine().split(",");
                        if (studentInfo.length != 3)
                            throw new IllegalArgumentException("Invalid input format for student.");
                        Student student = new Student(
                                Integer.parseInt(studentInfo[0].trim()),
                                studentInfo[1].trim(),
                                Integer.parseInt(studentInfo[2].trim())
                        );
                        studentList.add(student);
                        System.out.println("Student added successfully.");
                        break;
                    case 3:
                        System.out.println("Enter student ID and fees amount (separated by commas):");
                        String[] feeInfo = scanner.nextLine().split(",");
                        if (feeInfo.length != 2) throw new IllegalArgumentException("Invalid input format for fees.");
                        int studentId = Integer.parseInt(feeInfo[0].trim());
                        int feesAmount = Integer.parseInt(feeInfo[1].trim());

                        boolean studentFound = false;
                        for (Student s : studentList) {
                            if (s.getId() == studentId) {
                                s.payFees(feesAmount);
                                studentFound = true;
                                System.out.println("Fees paid successfully.");
                                break;
                            }
                        }
                        if (!studentFound) {
                            System.out.println("Student not found.");
                        }
                        break;
                    case 4:
                        try {
                            System.out.println("Enter teacher ID:");
                            int teacherId = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character

                            boolean teacherFound = false;
                            for (Teacher t : teacherList) {
                                if (t.getId() == teacherId) {
                                    t.receiveSalary(t.getSalary());
                                    teacherFound = true;
                                    System.out.println("Salary paid successfully.");
                                    break;
                                }
                            }
                            if (!teacherFound) {
                                System.out.println("Teacher not found.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid teacher ID.");
                            scanner.next(); // Clear the invalid input
                        }
                        break;
                    case 5:
                        System.out.println("GHS has earned $" + CU.getTotalMoneyEarned());
                        break;
                    case 6:
                        exit = true;
                        System.out.println("Exiting the system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }
}
