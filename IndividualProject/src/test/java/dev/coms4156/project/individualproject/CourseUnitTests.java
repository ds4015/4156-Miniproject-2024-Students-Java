package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
/**
 * Test class for Course.java
 */

@SpringBootTest
@ContextConfiguration
public class CourseUnitTests {

  @BeforeAll
  public static void setupCourseForTesting() {
    testCourse = new Course("Griffin Newbold", "417 IAB", "11:40-12:55", 250);
  }


  @Test
  public void toStringTest() {
    String expectedResult = "\nInstructor: Griffin Newbold; Location: 417 IAB; Time: 11:40-12:55";
    assertEquals(expectedResult, testCourse.toString());
  }

  @Test
  public void testEnrollStudentFull() {
    testCourse.setEnrolledStudentCount(251);  //  Over capacity, should return false
    assertEquals(false, testCourse.enrollStudent());
  }

  @Test
  public void testEnrollStudentSpace() {
    testCourse.setEnrolledStudentCount(230);  // Under capacity, should return true
    assertEquals(true, testCourse.enrollStudent());
  }

  @Test
  public void testLocation() {
    String expectedResult = "417 IAB";
    assertEquals(expectedResult, testCourse.getCourseLocation());
  }

  @Test
  public void testInstructorName() {
    String expectedResult = "Griffin Newbold";
    assertEquals(expectedResult, testCourse.getInstructorName());
  }

  @Test
  public void testCourseTimeSlot() {
    String expectedResult = "11:40-12:55";
    assertEquals(expectedResult, testCourse.getCourseTimeSlot());
  }

  @Test
  public void testReassignInstructor() {
    String newInstructor = "Gail Kaiser";
    testCourse.reassignInstructor(newInstructor);
    assertEquals(newInstructor, testCourse.getInstructorName());
  }

  @Test
  public void testReassignLocation() {
    String newLocation = "501 NWC";
    testCourse.reassignLocation(newLocation);
    assertEquals(newLocation, testCourse.getCourseLocation());
  }

  @Test
  public void testReassignTime() {
    String newTime = "10:10-11:25";
    testCourse.reassignTime(newTime);
    assertEquals(newTime, testCourse.getCourseTimeSlot());
  }



  /** The test course instance used for testing. */
  public static Course testCourse;
}