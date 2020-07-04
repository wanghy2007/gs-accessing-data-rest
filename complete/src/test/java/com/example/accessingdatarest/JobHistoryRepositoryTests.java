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

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class JobHistoryRepositoryTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldReturnRepositoryIndex() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(jsonPath("$._links.jobHistory").exists());
	}

	@Test
	public void shouldCreateEntity() throws Exception {
		MvcResult mvcResult = mockMvc.perform(post("/jobHistory").content(
				"{\"employee\":\"/employees/119\",\"startDate\":\"2000-01-01\",\"endDate\":\"2020-01-01\",\"job\":\"/jobs/IT_PROG\",\"department\":\"/departments/200\"}"))
				.andExpect(status().isCreated()).andExpect(header().string("Location", containsString("jobHistory")))
				.andReturn();

		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(delete(location)).andExpect(status().isNoContent());
	}

	@Test
	public void shouldRetrieveEntity() throws Exception {
		MvcResult mvcResult = mockMvc.perform(post("/jobHistory").content(
				"{\"employee\":\"/employees/119\",\"startDate\":\"2000-01-01\",\"endDate\":\"2020-01-01\",\"job\":\"/jobs/IT_PROG\",\"department\":\"/departments/200\"}"))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(jsonPath("$.startDate").value("2000-01-01"))
				.andExpect(jsonPath("$.endDate").value("2020-01-01"));

		mockMvc.perform(delete(location)).andExpect(status().isNoContent());
	}

	@Test
	public void shouldUpdateEntity() throws Exception {
		MvcResult mvcResult = mockMvc.perform(post("/jobHistory").content(
				"{\"employee\":\"/employees/119\",\"startDate\":\"2000-01-01\",\"endDate\":\"2020-01-01\",\"job\":\"/jobs/IT_PROG\",\"department\":\"/departments/200\"}"))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(put(location).content(
				"{\"employee\":\"/employees/119\",\"startDate\":\"2001-01-01\",\"endDate\":\"2021-01-01\",\"job\":\"/jobs/IT_PROG\",\"department\":\"/departments/200\"}"))
				.andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(jsonPath("$.startDate").value("2001-01-01"))
				.andExpect(jsonPath("$.endDate").value("2021-01-01"));

		mockMvc.perform(delete(location)).andExpect(status().isNoContent());
	}

	@Test
	public void shouldPartiallyUpdateEntity() throws Exception {
		MvcResult mvcResult = mockMvc.perform(post("/jobHistory").content(
				"{\"employee\":\"/employees/119\",\"startDate\":\"2000-01-01\",\"endDate\":\"2020-01-01\",\"job\":\"/jobs/IT_PROG\",\"department\":\"/departments/200\"}"))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(patch(location).content("{\"startDate\":\"2002-01-01\",\"endDate\":\"2022-01-01\"}"))
				.andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(jsonPath("$.startDate").value("2002-01-01"))
				.andExpect(jsonPath("$.endDate").value("2022-01-01"));

		mockMvc.perform(delete(location)).andExpect(status().isNoContent());
	}

	@Test
	public void shouldDeleteEntity() throws Exception {
		MvcResult mvcResult = mockMvc.perform(post("/jobHistory").content(
				"{\"employee\":\"/employees/119\",\"startDate\":\"2000-01-01\",\"endDate\":\"2020-01-01\",\"job\":\"/jobs/IT_PROG\",\"department\":\"/departments/200\"}"))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(delete(location)).andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isNotFound());
	}
}
