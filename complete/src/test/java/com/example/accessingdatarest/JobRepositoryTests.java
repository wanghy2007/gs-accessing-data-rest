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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.accessingdatarest.repository.JobRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class JobRepositoryTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private JobRepository jobRepository;

	@BeforeEach
	public void deleteAllBeforeTests() throws Exception {
		jobRepository.findById("NIL").ifPresent(job -> jobRepository.delete(job));
	}

	@Test
	public void shouldReturnRepositoryIndex() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(jsonPath("$._links.jobs").exists());
	}

	@Test
	public void shouldCreateEntity() throws Exception {
		mockMvc.perform(post("/jobs")
				.content("{\"jobId\":\"NIL\",\"jobTitle\":\"Nil\",\"minSalary\":\"100000\",\"maxSalary\":\"200000\"}"))
				.andExpect(status().isCreated()).andExpect(header().string("Location", containsString("jobs/")));
	}

	@Test
	public void shouldRetrieveEntity() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(post("/jobs").content(
						"{\"jobId\":\"NIL\",\"jobTitle\":\"Nil\",\"minSalary\":\"100000\",\"maxSalary\":\"200000\"}"))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(jsonPath("$.jobTitle").value("Nil"))
				.andExpect(jsonPath("$.minSalary").value("100000")).andExpect(jsonPath("$.maxSalary").value("200000"));
	}

	@Test
	public void shouldUpdateEntity() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(post("/jobs").content(
						"{\"jobId\":\"NIL\",\"jobTitle\":\"Nil\",\"minSalary\":\"100000\",\"maxSalary\":\"200000\"}"))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(put(location).content(
				"{\"jobId\":\"NIL\",\"jobTitle\":\"Nil1\",\"minSalary\":\"100001\",\"maxSalary\":\"200001\"}"))
				.andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(jsonPath("$.jobTitle").value("Nil1"))
				.andExpect(jsonPath("$.minSalary").value("100001")).andExpect(jsonPath("$.maxSalary").value("200001"));
	}

	@Test
	public void shouldPartiallyUpdateEntity() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(post("/jobs").content(
						"{\"jobId\":\"NIL\",\"jobTitle\":\"Nil\",\"minSalary\":\"100000\",\"maxSalary\":\"200000\"}"))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(patch(location).content("{\"minSalary\":\"100001\",\"maxSalary\":\"200001\"}"))
				.andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(jsonPath("$.minSalary").value("100001"))
				.andExpect(jsonPath("$.maxSalary").value("200001"));
	}

	@Test
	public void shouldDeleteEntity() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(post("/jobs").content(
						"{\"jobId\":\"NIL\",\"jobTitle\":\"Nil\",\"minSalary\":\"100000\",\"maxSalary\":\"200000\"}"))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(delete(location)).andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isNotFound());
	}
}
