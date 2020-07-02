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

import com.example.accessingdatarest.repository.DepartmentRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class DepartmentRepositoryTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private DepartmentRepository departmentRepository;

	@BeforeEach
	public void deleteAllBeforeTests() throws Exception {
		departmentRepository.findById(BigInteger.ZERO).ifPresent(department -> departmentRepository.delete(department));
	}

	@Test
	public void shouldReturnRepositoryIndex() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(jsonPath("$._links.departments").exists());
	}

	@Test
	public void shouldCreateEntity() throws Exception {
		mockMvc.perform(post("/departments").content("{\"departmentId\":\"0\", \"departmentName\":\"Test\"}"))
				.andExpect(status().isCreated()).andExpect(header().string("Location", containsString("departments")));
	}

	@Test
	public void shouldRetrieveEntity() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(post("/departments").content("{\"departmentId\":\"0\", \"departmentName\":\"Test\"}"))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(jsonPath("$.departmentName").value("Test"));
	}

	@Test
	public void shouldUpdateEntity() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(post("/departments").content("{\"departmentId\":\"0\", \"departmentName\":\"Test\"}"))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(put(location).content("{\"departmentName\":\"Test1\"}")).andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isOk())
				.andExpect(jsonPath("$.departmentName").value("Test1"));
	}

	@Test
	public void shouldPartiallyUpdateEntity() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(post("/departments").content("{\"departmentId\":\"0\", \"departmentName\":\"Test\"}"))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(patch(location).content("{\"departmentName\":\"Test1\"}")).andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isOk())
				.andExpect(jsonPath("$.departmentName").value("Test1"));
	}

	@Test
	public void shouldDeleteEntity() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(post("/departments").content("{\"departmentId\":\"0\", \"departmentName\":\"Test\"}"))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(delete(location)).andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isNotFound());
	}
}
