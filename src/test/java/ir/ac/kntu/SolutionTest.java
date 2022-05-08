package ir.ac.kntu;

import ir.ac.kntu.logic.Answer;
import ir.ac.kntu.logic.Question;
import ir.ac.kntu.logic.Assignment;
import ir.ac.kntu.logic.Course;
import ir.ac.kntu.logic.User;
import ir.ac.kntu.logic.DateTime;
import ir.ac.kntu.logic.Options;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * @aouthor Hossein Yasbolaghi
 */
public class SolutionTest {
    private ArrayList<User> users;
    private ArrayList<Course> courses;
    private ArrayList<Question> questionBank;

    private Courses coursesManager;

    @Before
    public void setUp() {
        users = new ArrayList<User>();
        courses = new ArrayList<Course>();
        questionBank = new ArrayList<Question>();

        User user1 = new User("Hossein", "Yasbolaghi", "2126", "yas", "0912", "57100");
        User user2 = new User("Ali", "Talebi", "1380", "taleb", "0912", "57000");
        User user3 = new User("Reza", "Sharrahi", "1348", "sharra", "0912", "57101");
        User user4 = new User("Sara", "Yaaghoobi", "9700", "yaaghoob", "0912", "13924");
        User user5 = new User("Esna", "Ashari", "9090", "esna", "0912", "98548");
        User user6 = new User("Mehdi", "Zamanian", "9191", "zamanian", "0912", "12003");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

        Course course1 = new Course("AP", "KNTU", user5, new DateTime(1400, 11, 18, 0, 0, 0), Course.CourseStatus.ACTIVE_PRIVATE, "ap4002", "Advance programming");
        Course course2 = new Course("AP", "KNTU", user6, new DateTime(1400, 11, 18, 0, 0, 0), Course.CourseStatus.ACTIVE_PRIVATE, "ap4002", "Advance programming");
    
        courses.add(course1);
        courses.add(course2);

        Question question1 = new Question("Q1", 15, "public modifier is a ... in java.", Question.QuestionLevel.EASY, Question.QuestionType.FILL_IN_THE_BLANK);
        Question question2 = new Question("Q2", 15, "private modifier is a ... in java.", Question.QuestionLevel.EASY, Question.QuestionType.FILL_IN_THE_BLANK);
        Question question3 = new Question("Q3", 15, "java is a ... language.", Question.QuestionLevel.EASY, Question.QuestionType.FILL_IN_THE_BLANK);
        Question question4 = new Question("Q4", 15, "Who invent the first computer?", Question.QuestionLevel.MEDIUM, Question.QuestionType.SHORT_ANSWER);

        questionBank.add(question1);
        questionBank.add(question2);
        questionBank.add(question3);
        questionBank.add(question4);

        coursesManager = new Courses(users, courses, questionBank);
    }

    @Test
    public void testGetUser() {
    }

    @Test
    public void testGetCourse() {
    }

    @Test
    public void testGetQuestion() {
    }

    @Test
    public void testGetAssignment() {
    }

    @Test
    public void testGetDateTime() {
    }

    @Test
    public void testGetOptions() {
    }

    @Test
    public void testGetName() {
    }

    @Test
    public void testGetScore() {
    }

    @Test
    public void testGetDescription() {
    }

}
