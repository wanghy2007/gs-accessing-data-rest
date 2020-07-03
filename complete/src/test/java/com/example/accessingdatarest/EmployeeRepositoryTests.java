package com.example.accessingdatarest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.accessingdatarest.repository.EmployeeRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeRepositoryTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EmployeeRepository employeeRepository;

	@BeforeEach
	public void deleteAllBeforeTests() throws Exception {
		employeeRepository.findById(BigInteger.ZERO).ifPresent(employee -> employeeRepository.delete(employee));
	}

	@Test
	public void shouldReturnRepositoryIndex() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(jsonPath("$._links.employees").exists());
	}

	@Test
	public void shouldCreateEntity() throws Exception {
		mockMvc.perform(post("/employees").content(
				"{\"employeeId\":\"0\",\"lastName\":\"lastName\",\"email\":\"email\",\"hireDate\":\"2000-01-01\",\"job\":\"/jobs/IT_PROG\"}"))
				.andExpect(status().isCreated()).andExpect(header().string("Location", containsString("employees")));
	}

	@Test
	public void shouldRetrieveEntity() throws Exception {
		MvcResult mvcResult = mockMvc.perform(post("/employees").content(
				"{\"employeeId\":\"0\",\"lastName\":\"lastName\",\"email\":\"email\",\"hireDate\":\"2000-01-01\",\"job\":\"/jobs/IT_PROG\"}"))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(jsonPath("$.lastName").value("lastName"))
				.andExpect(jsonPath("$.email").value("email")).andExpect(jsonPath("$.hireDate").value("2000-01-01"));
	}

	@Test
	public void shouldUpdateEntity() throws Exception {
		MvcResult mvcResult = mockMvc.perform(post("/employees").content(
				"{\"employeeId\":\"0\",\"lastName\":\"lastName\",\"email\":\"email\",\"hireDate\":\"2000-01-01\",\"job\":\"/jobs/IT_PROG\"}"))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(put(location).content(
				"{\"lastName\":\"lastName1\",\"email\":\"email1\",\"hireDate\":\"2000-01-02\",\"job\":\"/jobs/IT_PROG\"}"))
				.andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(jsonPath("$.lastName").value("lastName1"))
				.andExpect(jsonPath("$.email").value("email1")).andExpect(jsonPath("$.hireDate").value("2000-01-02"));
	}

	@Test
	public void shouldPartiallyUpdateEntity() throws Exception {
		MvcResult mvcResult = mockMvc.perform(post("/employees").content(
				"{\"employeeId\":\"0\",\"lastName\":\"lastName\",\"email\":\"email\",\"hireDate\":\"2000-01-01\",\"job\":\"/jobs/IT_PROG\"}"))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(patch(location).content("{\"lastName\":\"lastName1\"}")).andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(jsonPath("$.lastName").value("lastName1"));
	}

	@Test
	public void shouldDeleteEntity() throws Exception {
		MvcResult mvcResult = mockMvc.perform(post("/employees").content(
				"{\"employeeId\":\"0\",\"lastName\":\"lastName\",\"email\":\"email\",\"hireDate\":\"2000-01-01\",\"job\":\"/jobs/IT_PROG\"}"))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(delete(location)).andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isNotFound());
	}
}
