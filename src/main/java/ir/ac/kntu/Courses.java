package ir.ac.kntu;

import java.util.ArrayList;

import ir.ac.kntu.logic.*;
import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.util.Cipher;

public class Courses {

    User currentUser;

    ArrayList<User> users;

    ArrayList<Course> courses;

    ArrayList<Question> questionsBank;

    public Courses() {
        currentUser = null;
        users = new ArrayList<>();
        courses = new ArrayList<>();
        questionsBank = new ArrayList<>();
    }

    //Courses program, login menu options handler
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
        String passToHash = ScannerWrapper.readPassword("Password: ");
        for (User user : users) {
            if (user.getUsername().equals(username) &&
                    user.getHashedPassword().equals(Cipher.getInstance().sha256(passToHash))) {
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
    public User searchUser() {
        if (users.size() == 0) {
            System.out.println("There is no user");
            return null;
        }
        int choice = ScannerWrapper.readInt("Search user by National code(press 1) or Email(press 2)?");

        switch (choice) {
            case 1:
                String nationalCode = ScannerWrapper.readString("National code: ");
                for (User user : users) {
                    if (user.getNationalCode().equals(nationalCode)) {
                        return user;
                    }
                }
                System.out.println("User not found");
                break;
            case 2:
                String email = ScannerWrapper.readString("Email: ");
                for (User user : users) {
                    if (user.getEmail().equals(email)) {
                        return user;
                    }
                }
                System.out.println("User not found");
                break;
            default:
                System.out.println("Invalid choice");
        }

        return null;
    }

    public Course searchCourse() {
        if (courses.size() == 0) {
            System.out.println("There is no course");
            return null;
        }
        String courseName = ScannerWrapper.readString("Course name: ");
        for (Course course : courses) {
            if (course.getName().equals(courseName)) {
                return course;
            }
        }
        System.out.println("Course not found");

        return null;
    }

    public Assignment searchAssignment(Course course) {
        if (course.getAssignments().size() == 0) {
            System.out.println("There is no assignment");
            return null;
        }
        String assignmentName = ScannerWrapper.readString("Assignment name: ");
        for (Assignment assignment : course.getAssignments()) {
            if (assignment.getName().equals(assignmentName)) {
                return assignment;
            }
        }
        System.out.println("Assignment not found");

        return null;
    }

    public Question searchQuestion(Assignment assignment) {
        if (assignment.getQuestions().size() == 0) {
            System.out.println("There is no question");
            return null;
        }
        String questionName = ScannerWrapper.readString("Question name: ");
        for (Question question : assignment.getQuestions()) {
            if (question.getName().equals(questionName)) {
                return question;
            }
        }

        return null;
    }

    public Question searchQuestionInQuestionBank() {
        if (questionsBank.size() == 0) {
            System.out.println("There is no question in question bank");
            return null;
        }
        String questionName = ScannerWrapper.readString("Question name: ");
        for (Question question : questionsBank) {
            if (question.getName().equals(questionName)) {
                return question;
            }
        }
        System.out.println("Question not found");


        return null;
    }

    public Answer searchAnswer(Question question, User user) {
        if (question.getAnswers().size() == 0) {
            System.out.println("There is no answer");
            return null;
        }

        int answerIndex = ScannerWrapper.readInt("Answer index: ");
        if (answerIndex < 1 || answerIndex > question.getAnswers().size()) {
            System.out.println("Invalid answer index");
            return null;
        }

        return question.getAnswers().get(user).get(answerIndex - 1);
    }

    public void searchAnswersByEmail() {
        Course course = searchCourse();
        if (course == null) {
            System.out.println("Search failed");
            return;
        }

        Assignment assignment = searchAssignment(course);
        if (assignment == null) {
            System.out.println("Search failed");
            return;
        }

        Question question = searchQuestion(assignment);
        if (question == null) {
            System.out.println("Search failed");
            return;
        }

        if (question.getAnswers().size() == 0) {
            System.out.println("There is no answer");
            return;
        }

        String email = ScannerWrapper.readString("Email: ");
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                for (Answer answer : question.getAnswers().get(user)) {
                    System.out.println(answer);
                }
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
        Question question = searchQuestionInQuestionBank();
        if (question == null) {
            System.out.println("add question to assignment process failed");
            return;
        }

        Course course = searchCourse();
        if (course == null) {
            System.out.println("add question to assignment process failed");
            return;
        }

        Assignment assignment = searchAssignment(course);
        if (assignment == null) {
            System.out.println("add question to assignment process failed");
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
        Course course = searchCourse();
        if (course == null) {
            System.out.println("add assignment process failed");
            return;
        }
        System.out.println("Assignment details:");
        Assignment assignment = Assignment.read();
        if (assignment == null) {
            System.out.println("add assignment process failed");
            return;
        }
        course.addAssignment(assignment);
    }

    public void addAnswer() {
        Course course = searchCourse();
        if (course == null) {
            System.out.println("add answer process failed");
            return;
        }

        if (!course.getRegister().contains(currentUser)) {
            System.out.println("You are not enrolled in this course");
            return;
        }

        Assignment assignment = searchAssignment(course);
        if (assignment == null) {
            System.out.println("add answer process failed");
            return;
        }

        if (!(assignment.getDelayDateTime().compareTo(DateTime.now()) == 1)) {
            System.out.println("Practice deadline is over");
            return;
        }

        Question question = searchQuestion(assignment);
        if (question == null) {
            System.out.println("add answer process failed");
            return;
        }

        Answer answer = Answer.read();
        if (answer == null) {
            System.out.println("add answer process failed");
            return;
        }
        question.addAnswer(currentUser, answer);
    }

    //Courses program, change-menu options handler
    public void changeUser() {
        User user = searchUser();
        if (user == null) {
            System.out.println("change user process failed");
            return;
        }

        String passToHash = ScannerWrapper.readPassword("Enter password: ");

        if (user.getHashedPassword().equals(Cipher.getInstance().sha256(passToHash)) ||
                user.equals(currentUser)) {
            user.changeHandler();
        } else {
            System.out.println("Wrong password");
        }
    }

    public void changeCourse() {
        Course course = searchCourse();
        if (course == null) {
            System.out.println("change course process failed");
            return;
        }

        if (!course.getLecturer().equals(currentUser)) {
            System.out.println("You are not the owner of this course");
            return;
        }
        course.changeHandler();
    }

    public void changeAssignment() {
        Course course = searchCourse();
        if (course == null) {
            System.out.println("change assignment process failed");
            return;
        }

        if (!course.getLecturer().equals(currentUser)) {
            System.out.println("You are not the owner of this course");
            return;
        }

        Assignment assignment = searchAssignment(course);
        if (assignment == null) {
            System.out.println("change assignment process failed");
            return;
        }

        assignment.changeHandler();
    }

    public void changeAnswer() {
        Course course = searchCourse();
        if (course == null) {
            System.out.println("change answer process failed");
            return;
        }

        if (!course.getRegister().contains(currentUser)) {
            System.out.println("You are not enrolled in this course");
            return;
        }

        Assignment assignment = searchAssignment(course);
        if (assignment == null) {
            System.out.println("change answer process failed");
            return;
        }

        if (!(assignment.getDelayDateTime().compareTo(DateTime.now()) == 1)) {
            System.out.println("Practice deadline is over");
            return;
        }

        Question question = searchQuestion(assignment);
        if (question == null) {
            System.out.println("change answer process failed");
            return;
        }

        Answer answer = searchAnswer(question, currentUser);
        if (answer == null) {
            System.out.println("change answer process failed");
            return;
        }
        answer.setDescription(ScannerWrapper.readString("Enter new description: "));
    }

    //Courses program, remove-menu options handler
    public void removeUser() {
        User user = searchUser();
        if (user == null) {
            System.out.println("remove user process failed");
            return;
        }

        String passToHash = ScannerWrapper.readPassword("Enter password: ");
        if (user.getHashedPassword().equals(Cipher.getInstance().sha256(passToHash))) {
            users.remove(user);
        } else {
            System.out.println("Wrong password");
        }
    }

    public void removeCourse() {
        Course course = searchCourse();
        if (course == null) {
            System.out.println("remove course process failed");
            return;
        }

        if (!course.getLecturer().equals(currentUser)) {
            System.out.println("You are not the owner of this course");
            return;
        }

        courses.remove(course);
    }

    public void removeAssignment() {
        Course course = searchCourse();
        if (course == null) {
            System.out.println("remove assignment process failed");
            return;
        }

        if (!course.getLecturer().equals(currentUser)) {
            System.out.println("You are not the owner of this course");
            return;
        }

        Assignment assignment = searchAssignment(course);
        if (assignment == null) {
            System.out.println("remove assignment process failed");
            return;
        }

        course.removeAssignment(assignment);
    }

    public void removeAnswer() {
        Course course = searchCourse();
        if (course == null) {
            System.out.println("remove answer process failed");
            return;
        }

        if (!course.getRegister().contains(currentUser)) {
            System.out.println("You are not enrolled in this course");
            return;
        }

        Assignment assignment = searchAssignment(course);
        if (assignment == null) {
            System.out.println("remove answer process failed");
            return;
        }

        if (!(assignment.getDelayDateTime().compareTo(DateTime.now()) == 1)) {
            System.out.println("Practice deadline is over");
            return;
        }

        Question question = searchQuestion(assignment);
        if (question == null) {
            System.out.println("remove answer process failed");
            return;
        }

        Answer answer = searchAnswer(question, currentUser);
        if (answer == null) {
            System.out.println("remove answer process failed");
            return;
        }

        question.removeAnswer(currentUser, answer);
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
        Course course = searchCourse();
        if (course == null) {
            System.out.println("list of assignments process failed");
            return;
        }

        for (Assignment assignment : course.getAssignments()) {
            System.out.println(assignment);
        }
    }

    public void listOfAnswers() {
        Course course = searchCourse();
        if (course == null) {
            System.out.println("list of answers process failed");
            return;
        }

        if (!course.getRegister().contains(currentUser)) {
            System.out.println("You are not enrolled in this course");
            return;
        }

        Assignment assignment = searchAssignment(course);
        if (assignment == null) {
            System.out.println("list of answers process failed");
            return;
        }

        Question question = searchQuestion(assignment);
        if (question == null) {
            System.out.println("list of answers process failed");
            return;
        }

        for (Answer answer : question.getAnswers().get(currentUser)) {
            System.out.println(answer);
        }


    }

    //Courses program, other-menu options handler
    public void registerToCourse() {
        Course course = searchCourse();
        if (course == null) {
            System.out.println("register to course process failed");
            return;
        }

        if (course.getRegister().contains(currentUser)) {
            System.out.println("You are already enrolled in this course");
            return;
        }

        if (!course.isOpenCourse()) {
            System.out.println("This course is not open");
            return;
        }

        if (course.isPrivateCourse()) {
            do {
                String passToHash = ScannerWrapper.readPassword("Enter password of the course: ");
                if (course.getHashedPassword().equals(Cipher.getInstance().sha256(passToHash))) {
                    course.register(currentUser);
                    System.out.println("Successfully registered to the course");
                    break;
                } else {
                    System.out.println("Wrong password");
                }
            } while (ScannerWrapper.readString("Try again? (y/n)").equals("y"));
            return;
        }
        course.register(currentUser);
        System.out.println("Successfully registered to the course");
    }

    public void registerStudentToCourse() {
        Course course = searchCourse();
        if (course == null) {
            System.out.println("register student to course process failed");
            return;
        }

        if (!course.getLecturer().equals(currentUser)) {
            System.out.println("You are not the owner of this course");
            return;
        }

        User student = searchUser();
        if (student == null) {
            System.out.println("register student to course process failed");
            return;
        }

        course.register(student);
        System.out.println("Successfully registered to the course");
    }

    public void scoreBoard() {
        Course course = searchCourse();
        if (course == null) {
            System.out.println("score board process failed");
            return;
        }

        if (!course.getRegister().contains(currentUser)) {
            System.out.println("You are not enrolled in this course");
            return;
        }

        Assignment assignment = searchAssignment(course);
        if (assignment == null) {
            System.out.println("score board process failed");
            return;
        }

        assignment.scoreBoard();
    }

    public void pointing() {
        Course course = searchCourse();
        if (course == null) {
            System.out.println("pointing process failed");
            return;
        }

        if (!course.getLecturer().equals(currentUser)) {
            System.out.println("You are not the owner of this course");
            return;
        }

        Assignment assignment = searchAssignment(course);
        if (assignment == null) {
            System.out.println("pointing process failed");
            return;
        }

        assignment.pointing();
    }

}