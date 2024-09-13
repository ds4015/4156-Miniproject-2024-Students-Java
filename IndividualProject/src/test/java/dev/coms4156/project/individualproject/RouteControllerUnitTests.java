package dev.coms4156.project.individualproject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test class for RouteController.java
 */

@SpringBootTest
public class RouteControllerUnitTests {

  @Autowired
  private RouteController routeController;

  @BeforeEach
  public void buildObjects() {
    Course course = new Course("Patricia G Lindemann","501 SCH", "1:10-2:25", 200);

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

    HashMap<String, Course> courses = new HashMap<>();
    courses.put("1001", psyc1001);
    courses.put("1610", psyc1610);
    courses.put("2235", psyc2235);
    courses.put("2620", psyc2620);
    courses.put("3212", psyc3212);
    courses.put("3445", psyc3445);
    courses.put("4236", psyc4236);

    Department department = new Department("PSYC", courses, "Nim Tottenham", 437);
  }


  @Test
  public void testIndex() {
    String expectedResult = "Welcome, in order to make an API call direct your browser"
      + " or Postman to an endpoint "
      + "\n\n This can be done using the following format: \n\n http:127.0.0"
      + ".1:8080/endpoint?arg=value";

    assertEquals(expectedResult, routeController.index());
  }

  @Test
  public void testRetrieveDepartmentSuccess() {

    ResponseEntity<?> response = routeController.retrieveDepartment("PSYC");
    assertThat(response.getStatusCode().value()).isEqualTo(200);
  }

  @Test
  public void testRetrieveDepartmentFail() {

    ResponseEntity<?> response = routeController.retrieveDepartment("ITAL");
    assertThat(response.getStatusCode().value()).isEqualTo(400);
  }

  @Test
  public void testRetrieveCourseSuccess() {

    ResponseEntity<?> response = routeController.retrieveCourse("PSYC",1001);
    assertThat(response.getStatusCode().value()).isEqualTo(403);
  }

  @Test
  public void testRetrieveCourseFail() {

    ResponseEntity<?> response = routeController.retrieveCourse("PSYC",3293);
    assertThat(response.getStatusCode().value()).isEqualTo(400);
  }


  @Test
  public void testIsCourseFullSuccess() {

    ResponseEntity<?> response = routeController.isCourseFull("PSYC",1001);
    System.out.println(response.getBody());
    assertThat(response.getBody()).isEqualTo(true);
  }

  @Test
  public void testIsCourseFullFail() {

    ResponseEntity<?> response = routeController.isCourseFull("PSYC",2235);
    assertThat(response.getBody()).isEqualTo(false);
  }
  @Test
  public void testIsCourseFullFail2() {

    ResponseEntity<?> response = routeController.isCourseFull("PHYS",9999);
    assertThat(response.getBody()).isEqualTo("Course Not Found");
  }

  @Test
  public void testGetMajorCountFromDeptSuccess() {

    ResponseEntity<?> response = routeController.getMajorCtFromDept("PHYS");
    assertThat(response.getStatusCode().value()).isEqualTo(200);
  }
  @Test
  public void testGetMajorCountFromDeptFail() {

    ResponseEntity<?> response = routeController.getMajorCtFromDept("ABCD");
    assertThat(response.getStatusCode().value()).isEqualTo(403);
  }

  @Test
  public void testDisplayDeptChairSuccess() {

    ResponseEntity<?> response = routeController.identifyDeptChair("PHYS");
    assertThat(response.getStatusCode().value()).isEqualTo(200);
  }
  @Test
  public void testDisplayDeptChairFail() {

    ResponseEntity<?> response = routeController.identifyDeptChair("ABCD");
    assertThat(response.getStatusCode().value()).isEqualTo(403);
  }

  @Test
  public void testFindCourseLocationSuccess() {

    ResponseEntity<?> response = routeController.findCourseLocation("PHYS",1001);
    assertThat(response.getStatusCode().value()).isEqualTo(200);
  }
  @Test
  public void testFindCourseLocationFail() {

    ResponseEntity<?> response = routeController.findCourseLocation("COMS",9234);
    assertThat(response.getStatusCode().value()).isEqualTo(404);
  }

  @Test
  public void testFindCourseTime() {

    ResponseEntity<?> response = routeController.findCourseTime("ECON",4415);
    assertThat(response.getStatusCode().value()).isEqualTo(202);
  }

  @Test
  public void testFindCourseTimeFail() {

    ResponseEntity<?> response = routeController.findCourseTime("ECON",3316);
    assertThat(response.getStatusCode().value()).isEqualTo(404);
  }

  @Test
  public void testAddMajorToDept() {

    ResponseEntity<?> response = routeController.addMajorToDept("ECON");
    assertThat(response.getStatusCode().value()).isEqualTo(200);
  }

  @Test
  public void testAddMajorToDeptFail() {

    ResponseEntity<?> response = routeController.addMajorToDept("ITAL");
    assertThat(response.getStatusCode().value()).isEqualTo(404);
  }
  @Test
  public void testRemoveMajorFromDept() {

    ResponseEntity<?> response = routeController.removeMajorFromDept("ECON");
    assertThat(response.getStatusCode().value()).isEqualTo(200);
  }

  @Test
  public void testRemoveMajorFromDeptFail() {

    ResponseEntity<?> response = routeController.removeMajorFromDept("ITAL");
    assertThat(response.getStatusCode().value()).isEqualTo(404);
  }

  @Test
  public void testDropStudentFromCourse() {

    ResponseEntity<?> response = routeController.dropStudent("ECON",4710);
    assertThat(response.getStatusCode().value()).isEqualTo(200);
  }

  @Test
  public void testDropStudentFromCourseFail() {

    ResponseEntity<?> response = routeController.dropStudent("ITAL",1244);
    assertThat(response.getStatusCode().value()).isEqualTo(404);
  }

  @Test
  public void testDropStudentFromCourseFail2() {

    ResponseEntity<?> response = routeController.dropStudent("ECON",1931);
    assertThat(response.getStatusCode().value()).isEqualTo(400);
  }

  @Test
  public void testSetEnrollmentCount() {

    ResponseEntity<?> response = routeController.setEnrollmentCount("ECON",4710, 26);
    assertThat(response.getStatusCode().value()).isEqualTo(200);
  }

  @Test
  public void testSetEnrollmentCountFail() {

    ResponseEntity<?> response = routeController.setEnrollmentCount("ECON",1931, 31);
    assertThat(response.getStatusCode().value()).isEqualTo(404);
  }

  @Test
  public void testChangeTimeOfCourse() {

    ResponseEntity<?> response = routeController.changeCourseTime("ECON",4710, "4:10-5:25");
    assertThat(response.getStatusCode().value()).isEqualTo(200);
  }

  @Test
  public void testChangeTimeOfCourseFail() {

    ResponseEntity<?> response = routeController.changeCourseTime("ECON",1931, "10:20-12:00");
    assertThat(response.getStatusCode().value()).isEqualTo(404);
  }

  @Test
  public void testChangeInstructor() {

    ResponseEntity<?> response = routeController.changeCourseTeacher("ECON",4710, "Griffin Newbold.");
    assertThat(response.getStatusCode().value()).isEqualTo(200);
  }

  @Test
  public void testChangeInstructorFail() {

    ResponseEntity<?> response = routeController.changeCourseTeacher("ECON",1931, "Griffin Newbold");
    assertThat(response.getStatusCode().value()).isEqualTo(404);
  }

  @Test
  public void testChangeLocation() {

    ResponseEntity<?> response = routeController.changeCourseLocation("ECON",4710, "IAB 417.");
    assertThat(response.getStatusCode().value()).isEqualTo(200);
  }

  @Test
  public void testChangeLocationFail() {

    ResponseEntity<?> response = routeController.changeCourseLocation("ECON",1931, "HAV 309");
    assertThat(response.getStatusCode().value()).isEqualTo(404);
  }

}

