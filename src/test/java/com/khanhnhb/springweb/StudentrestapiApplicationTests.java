package com.khanhnhb.springweb;

import com.khanhnhb.springweb.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentrestapiApplicationTests {

	@Value("${studentrestapi.services.url}")
	private String baseURL;

	@Test
	public void testGetStudent() {
		System.out.println(baseURL);

		RestTemplate restTemplate = new RestTemplate();
		Student student = restTemplate.getForObject(baseURL + "4", Student.class);
		assertNotNull(student);
		assertEquals("John", student.getName());
	}

	@Test
	public void testCreateStudent() {
		RestTemplate restTemplate = new RestTemplate();
		Student createStudent = new Student();
		createStudent.setName("Luxury");
		createStudent.setAge(14);
		createStudent.setEmail("luxury@gmail.com");
		createStudent.setPhone("+84444567891");
		Student createdStudent = restTemplate.postForObject(baseURL, createStudent, Student.class);

		assertNotNull(createdStudent);
		assertNotNull(createdStudent.getId());
		assertEquals(createStudent.getName(),createdStudent.getName());
	}

	@Test
	public void testUpdateStudent() {
		RestTemplate restTemplate = new RestTemplate();
		Student updateStudent = new Student();
		updateStudent.setPhone("+84444467891");
		restTemplate.put(baseURL + "19", updateStudent, Student.class);
	}
}
