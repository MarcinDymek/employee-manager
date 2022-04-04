package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private EmployeeController employeeController;
	
	@Test
	@Transactional
	//@WithMockUser(roles = "ADMIN")
	void getEmployeeTest() throws Exception {
		// given		
		Employee newEmployee = new Employee();
		newEmployee.setFirstName("TestName");
		newEmployee.setLastName("TestLastName");
		newEmployee.setEmailId("TestEmail");
		employeeController.createEmployee(newEmployee);
		
		// when
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees/" + newEmployee.getId()))
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andReturn();		
		// then
		Employee employee = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Employee.class);
		assertThat(employee).isNotNull();
		assertThat(employee.getId()).isEqualTo(newEmployee.getId());
		assertThat(employee.getFirstName()).isEqualTo("TestName");
		assertThat(employee.getLastName()).isEqualTo("TestLastName");
		assertThat(employee.getEmailId()).isEqualTo("TestEmail");		
	}
	
	@Test
	//@WithMockUser(roles = "ADMIN")
	void getAllEmployeesTest() throws Exception {
		// given		
		// when
		List<Employee> employees = employeeController.getAllEmployees();		
		// then
		assertThat(employees).isNotNull();		
	}
	
	@Test
	@Transactional
	//@WithMockUser(roles = "ADMIN")
	void getSingleEmployeeTest() throws Exception {
		// given
		Employee newEmployee = new Employee();
		newEmployee.setFirstName("TestName");
		newEmployee.setLastName("TestLastName");
		newEmployee.setEmailId("TestEmail");
		employeeController.createEmployee(newEmployee);
		// when
		ResponseEntity<Employee> employee = employeeController.getEmployeeById(newEmployee.getId());		
		// then
		assertThat(employee).isNotNull();
		assertThat(employee.getBody().getId()).isEqualTo(newEmployee.getId());
	}
	
	@Test
	@Transactional
	//@WithMockUser(roles = "ADMIN")
	void createEmployeeTest() throws Exception {
		// given
		Employee newEmployee = new Employee();
		newEmployee.setFirstName("TestName");
		newEmployee.setLastName("TestLastName");
		newEmployee.setEmailId("TestEmail");
	
		int employeesAmount = employeeController.getAllEmployees().size();
		// when
		employeeController.createEmployee(newEmployee);		
		// then		
		assertThat(employeeController.getAllEmployees().size()).isEqualTo(employeesAmount+1);
	}
	
	@Test
	@Transactional
	//@WithMockUser(roles = "ADMIN")
	void deleteEmployeeTest() throws Exception {
		// given
		Employee newEmployee = new Employee();
		newEmployee.setFirstName("TestName");
		newEmployee.setLastName("TestLastName");
		newEmployee.setEmailId("TestEmail");
		employeeController.createEmployee(newEmployee);		
		
		int employeesAmount = employeeController.getAllEmployees().size();
		// when
		employeeController.deleteEmployee(newEmployee.getId());		
		// then		
		assertThat(employeeController.getAllEmployees().size()).isEqualTo(employeesAmount-1);
	}
	
	@Test
	@Transactional
	//@WithMockUser(roles = "ADMIN")
	void updateEmployeeTest() throws Exception {
		// given
		Employee newEmployee = new Employee();
		newEmployee.setFirstName("TestName");
		newEmployee.setLastName("TestLastName");
		newEmployee.setEmailId("TestEmail");
		employeeController.createEmployee(newEmployee);	
		
		Employee updatedEmployee = new Employee();
		updatedEmployee.setFirstName("UpdatedName");
		updatedEmployee.setLastName("UpdatedLastName");
		updatedEmployee.setEmailId("UpdatedEmail");
		// when
		employeeController.updateEmployee(newEmployee.getId(), updatedEmployee);
		// then		
		assertThat(employeeController.getEmployeeById(newEmployee.getId()).getBody().getFirstName()).isEqualTo(updatedEmployee.getFirstName());
		assertThat(employeeController.getEmployeeById(newEmployee.getId()).getBody().getLastName()).isEqualTo(updatedEmployee.getLastName());
		assertThat(employeeController.getEmployeeById(newEmployee.getId()).getBody().getEmailId()).isEqualTo(updatedEmployee.getEmailId());
	}

}
