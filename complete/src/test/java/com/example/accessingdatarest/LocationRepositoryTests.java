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

import com.example.accessingdatarest.repository.LocationRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class LocationRepositoryTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private LocationRepository locationRepository;

	@BeforeEach
	public void deleteAllBeforeTests() throws Exception {
		locationRepository.findById(BigInteger.ZERO).ifPresent(location -> locationRepository.delete(location));
	}

	@Test
	public void shouldReturnRepositoryIndex() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(jsonPath("$._links.locations").exists());
	}

	@Test
	public void shouldCreateEntity() throws Exception {
		mockMvc.perform(post("/locations").content(
				"{\"locationId\":\"0\",\"streetAddress\":\"streetAddress\",\"postalCode\":\"postalCode\",\"city\":\"city\",\"stateProvince\":\"stateProvince\"}"))
				.andExpect(status().isCreated()).andExpect(header().string("Location", containsString("locations")));
	}

	@Test
	public void shouldRetrieveEntity() throws Exception {
		MvcResult mvcResult = mockMvc.perform(post("/locations").content(
				"{\"locationId\":\"0\",\"streetAddress\":\"streetAddress\",\"postalCode\":\"postalCode\",\"city\":\"city\",\"stateProvince\":\"stateProvince\"}"))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(get(location)).andExpect(status().isOk())
				.andExpect(jsonPath("$.streetAddress").value("streetAddress"))
				.andExpect(jsonPath("$.postalCode").value("postalCode")).andExpect(jsonPath("city").value("city"))
				.andExpect(jsonPath("$.stateProvince").value("stateProvince"));
	}

	@Test
	public void shouldUpdateEntity() throws Exception {
		MvcResult mvcResult = mockMvc.perform(post("/locations").content(
				"{\"locationId\":\"0\",\"streetAddress\":\"streetAddress\",\"postalCode\":\"postalCode\",\"city\":\"city\",\"stateProvince\":\"stateProvince\"}"))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(put(location).content(
				"{\"locationId\":\"0\",\"streetAddress\":\"streetAddress1\",\"postalCode\":\"postalCode1\",\"city\":\"city1\",\"stateProvince\":\"stateProvince1\"}"))
				.andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isOk())
				.andExpect(jsonPath("$.streetAddress").value("streetAddress1"))
				.andExpect(jsonPath("$.postalCode").value("postalCode1")).andExpect(jsonPath("$.city").value("city1"))
				.andExpect(jsonPath("$.stateProvince").value("stateProvince1"));
	}

	@Test
	public void shouldPartiallyUpdateEntity() throws Exception {
		MvcResult mvcResult = mockMvc.perform(post("/locations").content(
				"{\"locationId\":\"0\",\"streetAddress\":\"streetAddress\",\"postalCode\":\"postalCode\",\"city\":\"city\",\"stateProvince\":\"stateProvince\"}"))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(patch(location).content(
				"{\"streetAddress\":\"streetAddress1\",\"postalCode\":\"postalCode1\",\"city\":\"city1\",\"stateProvince\":\"stateProvince1\"}"))
				.andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isOk())
				.andExpect(jsonPath("$.streetAddress").value("streetAddress1"))
				.andExpect(jsonPath("$.postalCode").value("postalCode1")).andExpect(jsonPath("$.city").value("city1"))
				.andExpect(jsonPath("$.stateProvince").value("stateProvince1"));
	}

	@Test
	public void shouldDeleteEntity() throws Exception {
		MvcResult mvcResult = mockMvc.perform(post("/locations").content(
				"{\"locationId\":\"0\",\"streetAddress\":\"streetAddress\",\"postalCode\":\"postalCode\",\"city\":\"city\",\"stateProvince\":\"stateProvince\"}"))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(delete(location)).andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isNotFound());
	}
}
