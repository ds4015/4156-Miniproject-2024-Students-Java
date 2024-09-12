package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
/**
 * Test class for Department.java
 */

@SpringBootTest
@ContextConfiguration
public class DepartmentUnitTests {

  @BeforeAll
  public static void setupDepartmentForTesting() {
    String[] times = {"11:40-12:55", "4:10-5:25", "", "2:40-3:55"};
    Course psyc1001 = new Course("Patricia G Lindemann", "501 SCH", "1:10-2:25", 200);
    psyc1001.setEnrolledStudentCount(191);
    Course psyc1610 = new Course("Christopher Baldassano", "200 SCH", times[2], 45);
    psyc1610.setEnrolledStudentCount(42);
    Course psyc2235 = new Course("Katherine T Fox-Glassman", "501 SCH", times[0], 125);
    psyc2235.setEnrolledStudentCount(128);
    Course psyc2620 = new Course("Jeffrey M Cohen", "303 URIS", "1:10-3:40", 60);
    psyc2620.setEnrolledStudentCount(55);
    Course psyc3212 = new Course("Mayron Piccolo", "200 SCH", "2:10-4:00", 15);
    psyc3212.setEnrolledStudentCount(15);
    Course psyc3445 = new Course("Mariam Aly", "405 SCH", "2:10-4:00", 12);
    psyc3445.setEnrolledStudentCount(12);
    Course psyc4236 = new Course("Trenton Jerde", "405 SCH", "6:10-8:00", 18);
    psyc4236.setEnrolledStudentCount(17);

    HashMap courses = new HashMap<>();
    courses.put("1001", psyc1001);
    courses.put("1610", psyc1610);
    courses.put("2235", psyc2235);
    courses.put("2620", psyc2620);
    courses.put("3212", psyc3212);
    courses.put("3445", psyc3445);
    courses.put("4236", psyc4236);

    testDept = new Department("PSYC", courses, "Nim Tottenham", 437);
  }


  @Test
  public void testGetNumberOfMajors() {
    int expectedValue = 437;
    assertEquals(expectedValue, testDept.getNumberOfMajors());
  }

  @Test
  public void testDepartmentChair() {
    String expectedValue = "Nim Tottenham";
    assertEquals(expectedValue, testDept.getDepartmentChair());
  }

  @Test
  public void testAddPersonToMajor() {
    int expectedResult = 438;
    testDept.addPersonToMajor();
    assertEquals(expectedResult, testDept.getNumberOfMajors());
  }

  @Test
  public void testDropPersonFromMajor() {
    int expectedResult = 435;
    testDept.dropPersonFromMajor();
    testDept.dropPersonFromMajor();
    assertEquals(expectedResult, testDept.getNumberOfMajors());
  }

  @Test
  public void testCreateCourse() {
    String courseID = "PSYC 4493";
    String instr = "Jennifer Blaze";
    String loc = "200 SCH";
    String time = "2:10-4:00";
    int cap = 15;
    testDept.createCourse(courseID, instr, loc, time, cap);
  }
/*
  @Test
  public void testGetCourseSelection() {
    String[] times = {"11:40-12:55", "4:10-5:25", "", "2:40-3:55"};

    Course psyc1001 = new Course("Patricia G Lindemann", "501 SCH", "1:10-2:25", 200);
    psyc1001.setEnrolledStudentCount(191);
    Course psyc1610 = new Course("Christopher Baldassano", "200 SCH", times[2], 45);
    psyc1610.setEnrolledStudentCount(42);
    Course psyc2235 = new Course("Katherine T Fox-Glassman", "501 SCH", times[0], 125);
    psyc2235.setEnrolledStudentCount(128);
    Course psyc2620 = new Course("Jeffrey M Cohen", "303 URIS", "1:10-3:40", 60);
    psyc2620.setEnrolledStudentCount(55);
    Course psyc3212 = new Course("Mayron Piccolo", "200 SCH", "2:10-4:00", 15);
    psyc3212.setEnrolledStudentCount(15);
    Course psyc3445 = new Course("Mariam Aly", "405 SCH", "2:10-4:00", 12);
    psyc3445.setEnrolledStudentCount(12);
    Course psyc4236 = new Course("Trenton Jerde", "405 SCH", "6:10-8:00", 18);
    psyc4236.setEnrolledStudentCount(17);
    Course psyc4493 = new Course("Jennifer Blaze", "200 SCH", "2:10-4:00", 15);
    psyc4493.setEnrolledStudentCount(9);

    HashMap courses = new HashMap<>();
    courses.put("1001", psyc1001);
    courses.put("1610", psyc1610);
    courses.put("2235", psyc2235);
    courses.put("2620", psyc2620);
    courses.put("3212", psyc3212);
    courses.put("3445", psyc3445);
    courses.put("4236", psyc4236);

    assertEquals(courses, testDept.getCourseSelection);
  } */

  @Test
  public void testToString() {
    String expectedValue = "PSYC 1001: \nInstructor: Patricia G Lindemann; Location: 501 SCH; Time: 1:10-2:25\n" +
      "PSYC 1610: \nInstructor: Christopher Baldassano; Location: 200 SCH; Time: 2:40-3:55\n" +
      "PSYC 2235: \nInstructor: Katherine T Fox-Glassman; Location: 501 SCH; Time: 11:40-12:55\n" +
      "PSYC 2620: \nInstructor: Jeffrey M Cohen; Location: 303 URIS; Time: 1:10-3:40\n" +
      "PSYC 3212: \nInstructor: Mayron Piccolo; Location: 200 SCH; Time: 2:10-4:00\n" +
      "PSYC 3445: \nInstructor: Mariam Aly; Location: 405 SCH; Time: 2:10-4:00\n" +
      "PSYC 4236: \nInstructor: Trenton Jerde; Location: 405 SCH; Time: 6:10-8:00\n" +
      "PSYC 4493: \nInstructor: Jennifer Blazer; Location: 200 SCH; Time: 2:10-4:00\n";

    assertEquals(expectedValue, testDept.toString());
  }


  /** The test department instance used for testing. */
  public static Department testDept;
}