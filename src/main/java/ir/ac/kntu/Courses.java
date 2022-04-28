package ir.ac.kntu;

import java.util.ArrayList;

import ir.ac.kntu.logic.*;
import ir.ac.kntu.util.ScannerWrapper;

public class Courses {

    User currentUser;

    ArrayList<User> users;

    ArrayList<Course> courses;

    ArrayList<Question> questionsBank;

    public Courses() {
        courses = new ArrayList<>();
        users = new ArrayList<>();
        questionsBank = new ArrayList<>();
        currentUser = null;
    }

    //Courses program, login-menu options handler
    public boolean signUp() {
        User user = User.read();
        if (user == null) {
            System.out.println("Sign up failed");
            return false;
        }
        currentUser = user;
        users.add(user);

        return true;
    }

    public boolean login() {
        if (users.size() == 0) {
            System.out.println("There is no user");
            return false;
        }
        String username = ScannerWrapper.readString("Username: ");
        String password = ScannerWrapper.readString("Password: ");
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                return true;
            }
        }
        System.out.println("Invalid username or password");

        return false;
    }

    public void logout() {
        currentUser = null;
    }

    //Courses program, search-menu options handler
    public void searchUser() {
        String username = ScannerWrapper.readString("Username: ");
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println(user);
                return;
            }
        }
    }

    public void searchCourse() {
        String courseName = ScannerWrapper.readString("Course name: ");
        for (Course course : courses) {
            if (course.getName().equals(courseName)) {
                System.out.println(course);
                return;
            }
        }
    }

    public void searchAnswersByEmail() {
        if (courses.size() == 0) {
            System.out.println("There is no course");
        }
        String courseName = ScannerWrapper.readString("Course name: ");
        Course course = null;
        for (Course c : courses) {
            if (c.getName().equals(courseName)) {
                course = c;
                break;
            }
        }
        if (course == null) {
            System.out.println("There is no course with this name");
        }
        if (course.getAssignments().size() == 0) {
            System.out.println("There is no assignment for this course");
        }
        String assignmentName = ScannerWrapper.readString("Assignment name: ");
        Assignment assignment = null;
        for (Assignment a : course.getAssignments()) {
            if (a.getName().equals(assignmentName)) {
                assignment = a;
                break;
            }
        }
        if (assignment == null) {
            System.out.println("There is no assignment with this name");
        }
        String questionName = ScannerWrapper.readString("Question name: ");
        Question question = null;
        for (Question q : assignment.getQuestions()) {
            if (q.getName().equals(questionName)) {
                question = q;
                break;
            }
        }
        if (question == null) {
            System.out.println("There is no question with this name");
        }
        if (question.getSentAnswers().size() == 0) {
            System.out.println("There is no sent answer to this question");
        }
        String userEmail = ScannerWrapper.readString("User Email: ");
        for (User user : question.getSentAnswers().keySet()) {
            if (user.getEmail().equals(userEmail)) {
                System.out.println(question.getSentAnswers().get(user));
            }
        }

    }

    //Courses program, add-menu options handler
    public void addUser() {
        User user = User.read();
        if (user == null) {
            System.out.println("add user process failed");
            return;
        }
        users.add(user);
    }

    public void addCourse() {
        Course course = Course.read(currentUser);
        if (course == null) {
            System.out.println("add course process failed");
            return;
        }
        courses.add(course);
    }

    public void addQuestionToAssignmentFromQuestionBank() {
        if (courses.size() == 0) {
            System.out.println("There is no course");
            return;
        }
        if (questionsBank.size() == 0) {
            System.out.println("There is no question in question bank");
            return;
        }
        String courseName = ScannerWrapper.readString("Course name: ");
        Course course = null;
        for (Course c : courses) {
            if (c.getName().equals(courseName)) {
                course = c;
                break;
            }
        }
        if (course == null) {
            System.out.println("There is no course with this name");
            return;
        }
        if (course.getAssignments().size() == 0) {
            System.out.println("There is no assignment for this course");
            return;
        }
        String assignmentName = ScannerWrapper.readString("Assignment name: ");
        Assignment assignment = null;
        for (Assignment a : course.getAssignments()) {
            if (a.getName().equals(assignmentName)) {
                assignment = a;
                break;
            }
        }
        if (assignment == null) {
            System.out.println("There is no assignment with this name");
            return;
        }

        String questionName = ScannerWrapper.readString("Question name: ");
        Question question = null;
        for (Question q : questionsBank) {
            if (q.getName().equals(questionName)) {
                question = q;
                break;
            }
        }
        if (question == null) {
            System.out.println("There is no question with this name");
            return;
        }
        assignment.addQuestion(question);
    }

    public void addQuestionToQuestionBank() {
        Question question = Question.read();
        if (question == null) {
            System.out.println("add question process failed");
            return;
        }
        questionsBank.add(question);
    }

    public void addAssignment() {
        if (courses.size() == 0) {
            System.out.println("There is no course");
            return;
        }
        String courseName = ScannerWrapper.readString("Course name: ");
        Course course = null;
        for (Course c : courses) {
            if (c.getName().equals(courseName)) {
                course = c;
                break;
            }
        }
        if (course == null) {
            System.out.println("There is no course with this name");
            return;
        }
        Assignment assignment = Assignment.read();
        if (assignment == null) {
            System.out.println("add assignment process failed");
            return;
        }
        course.addAssignment(assignment);
    }

    public void addAnswer() {
        if (courses.size() == 0) {
            System.out.println("There is no course");
            return;
        }
        String courseName = ScannerWrapper.readString("Course name: ");
        Course course = null;
        for (Course c : courses) {
            if (c.getName().equals(courseName)) {
                course = c;
                break;
            }
        }
        if (course == null) {
            System.out.println("There is no course with this name");
            return;
        }
        if (!course.getRegister().contains(currentUser)) {
            System.out.println("You are not enrolled in this course");
            return;
        }
        if (course.getAssignments().size() == 0) {
            System.out.println("There is no assignment for this course");
        }
        String assignmentName = ScannerWrapper.readString("Assignment name: ");
        Assignment assignment = null;
        for (Assignment a : course.getAssignments()) {
            if (a.getName().equals(assignmentName)) {
                assignment = a;
                break;
            }
        }
        if (assignment == null) {
            System.out.println("There is no assignment with this name");
            return;
        }
        if (!(assignment.getEndDate().compareTo(DateTime.now()) == 1)) {
            System.out.println("Practice deadline is over");
            return;
        }
        if (assignment.getQuestions().size() == 0) {
            System.out.println("There is no question is this assignment");
            return;
        }
        String questionName = ScannerWrapper.readString("Question name: ");
        Question question = null;
        for (Question q : assignment.getQuestions()) {
            if (q.getName().equals(questionName)) {
                question = q;
                break;
            }
        }
        if (question == null) {
            System.out.println("There is no question with this name");
            return;
        }
        question.getSentAnswers().get(currentUser).add(Answer.read());
    }

    //Courses program, change-menu options handler
    public void changeUser() {
        if (users.size() == 0) {
            System.out.println("There is no user");
            return;
        }
        String username = ScannerWrapper.readString("Username: ");
        String password = ScannerWrapper.readString("Password: ");
        User user = null;
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                user = u;
                break;
            }
        }
        if (user == null) {
            System.out.println("There is no user with username");
        }
        user.changeHandler();
    }

    public void changeCourse() {
        if (courses.size() == 0) {
            System.out.println("There is no course");
            return;
        }
        Course course = null;
        String courseName = ScannerWrapper.readString("Course name: ");
        for (Course c : courses) {
            if (c.getName().equals(courseName)) {
                course = c;
                break;
            }
        }
        if (course == null) {
            System.out.println("There is no course with this name");
            return;
        }
        if (!course.getOwner().equals(currentUser)) {
            System.out.println("You are not the owner of this course");
            return;
        }
        course.changeHandler();
    }

    public void changeAssignment() {
        if (courses.size() == 0) {
            System.out.println("There is no course");
            return;
        }
        Course course = null;
        String courseName = ScannerWrapper.readString("Course name: ");
        for (Course c : courses) {
            if (c.getName().equals(courseName)) {
                course = c;
                break;
            }
        }
        if (course == null) {
            System.out.println("There is no course with this name and lecturer");
            return;
        }
        if (!course.getOwner().equals(currentUser)) {
            System.out.println("You are not the owner of this course");
            return;
        }
        Assignment assignment = null;
        String assignmentName = ScannerWrapper.readString("Assignment name: ");
        for (Assignment a : course.getAssignments()) {
            if (a.getName().equals(assignmentName)) {
                assignment = a;
                break;
            }
        }
        if (assignment == null) {
            System.out.println("There is no assignment with this name");
            return;
        }
        assignment.changeHandler();
    }

    public void changeAnswer() {

    }

    //Courses program, remove-menu options handler
    public void removeUser() {
        if (users.size() == 0) {
            System.out.println("There is no user");
            return;
        }
        User user = null;
        String username = ScannerWrapper.readString("Enter username: ");
        String password = ScannerWrapper.readString("Enter password: ");
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                user = u;
                break;
            }
        }
        if (user == null) {
            System.out.println("There is no user with this username and password");
            return;
        }
        users.remove(user);
    }

    public void removeCourse() {
        if (courses.size() == 0) {
            System.out.println("There is no course");
            return;
        }
        Course course = null;
        String courseName = ScannerWrapper.readString("Course name: ");
        String courseLecturer = ScannerWrapper.readString("Course lecturer: ");
        for (Course c : courses) {
            if (c.getName().equals(courseName) && c.getLecturer().equals(courseLecturer)) {
                course = c;
                break;
            }
        }
        if (!course.getOwner().equals(currentUser)) {
            System.out.println("You are not the owner of this course");
            return;
        }
        courses.remove(course);
    }

    public void removeAssignment() {

    }

    public void removeAnswer() {

    }

    //Courses program, list-of menu options handler
    public void listOfUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void listOfCourses() {
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public void listOfAssignments() {
        if (courses.size() == 0) {
            System.out.println("There are no courses");
            return;
        }
        String courseName = ScannerWrapper.readString("Course name: ");
        String courseInstitute = ScannerWrapper.readString("Course institute: ");
        Course course = null;
        for (Course c : courses) {
            if (c.getName().equals(courseName) && c.getInstitute().equals(courseInstitute)) {
                course = c;
                break;
            }
        }
        if (course == null) {
            System.out.println("There is no course with this name and institute");
            return;
        }

        for (Assignment assignment : course.getAssignments()) {
            System.out.println(assignment);
        }
    }

    public void listOfAnswers() {

    }

    //Courses program, other-menu options handler
    public void registerToCourse() {
        if (courses.size() == 0) {
            System.out.println("There are no courses");
            return;
        }
        String courseName = ScannerWrapper.readString("Course name: ");
        String courseInstitute = ScannerWrapper.readString("Course institute: ");
        Course course = null;
        for (Course c : courses) {
            if (c.getName().equals(courseName) && c.getInstitute().equals(courseInstitute)) {
                course = c;
                break;
            }
        }
        if (course == null) {
            System.out.println("There is no course with this name and institute");
            return;
        }
        if (!course.isOpenCourse() && !course.getOwner().equals(currentUser)) {
            System.out.println("This course is not open");
            return;
        }

        if (course.isPrivateCourse()) {
            String password = ScannerWrapper.readString("Enter the password of the course");
            while (!course.getPassword().equals(password)) {
                System.out.println("Invalid password");
                if (ScannerWrapper.readString("Try again? Y/N").equals("N")) {
                    return;
                }
                password = ScannerWrapper.readString("Enter the password of the course");
            }
        }
        course.register(currentUser);
        System.out.println("Successfully registered to the course");
    }

    public void registerStudentToCourse() {
        if (users.size() == 0) {
            System.out.println("There is no user");
            return;
        }
        if (courses.size() == 0) {
            System.out.println("There are no courses");
            return;
        }
        User user = null;
        String username = ScannerWrapper.readString("Username: ");
        String password = ScannerWrapper.readString("Password: ");
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                user = u;
                break;
            }
        }
        if (user == null) {
            System.out.println("Invalid username or password");
            return;
        }
        String courseName = ScannerWrapper.readString("Course name: ");
        String courseInstitute = ScannerWrapper.readString("Course institute: ");
        Course course = null;
        for (Course c : courses) {
            if (c.getName().equals(courseName) && c.getInstitute().equals(courseInstitute)) {
                course = c;
                break;
            }
        }
        if (course == null) {
            System.out.println("There is no course with this name and institute");
            return;
        }
        if (!course.isOpenCourse() && !course.getOwner().equals(currentUser)) {
            System.out.println("This course is not open");
            return;
        }

        if (course.isPrivateCourse()) {
            String pass = ScannerWrapper.readString("Enter the password of the course");
            while (!course.getPassword().equals(pass)) {
                System.out.println("Invalid password");
                if (ScannerWrapper.readString("Try again? Y/N").equals("N")) {
                    return;
                }
                pass = ScannerWrapper.readString("Enter the password of the course");
            }
        }
        course.register(user);
        System.out.println("Successfully registered to the course");
    }

    public void scoreBoard() {

    }

}