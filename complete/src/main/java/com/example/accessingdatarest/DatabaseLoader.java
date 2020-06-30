package com.example.accessingdatarest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.accessingdatarest.entity.Country;
import com.example.accessingdatarest.entity.Region;
import com.example.accessingdatarest.repository.CountryRepository;
import com.example.accessingdatarest.repository.RegionRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private RegionRepository regionRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Override
	public void run(String... args) throws Exception {
		this.personRepository.save(new Person("Frodo", "Baggins"));
		this.personRepository.save(new Person("Bilbo", "Baggins"));
		this.personRepository.save(new Person("Gandalf", "the Grey"));
		this.personRepository.save(new Person("Samwise", "Gamgee"));
		this.personRepository.save(new Person("Meriadoc", "Brandybuck"));
		this.personRepository.save(new Person("Peregrin", "Took"));

		this.regionRepository.save(new Region(1L, "Europe"));
		this.regionRepository.save(new Region(2L, "Americas"));
		this.regionRepository.save(new Region(3L, "Asia"));
		this.regionRepository.save(new Region(4L, "Middle East and Africa"));

		this.countryRepository.save(new Country("IT", "Italy", this.regionRepository.findById(1L).orElse(null)));
		this.countryRepository.save(new Country("JP", "Japan", this.regionRepository.findById(3L).orElse(null)));
		this.countryRepository
				.save(new Country("US", "United States of America", this.regionRepository.findById(2L).orElse(null)));
		this.countryRepository.save(new Country("CA", "Canada", this.regionRepository.findById(2L).orElse(null)));
		this.countryRepository.save(new Country("CN", "China", this.regionRepository.findById(3L).orElse(null)));
		this.countryRepository.save(new Country("IN", "India", this.regionRepository.findById(3L).orElse(null)));
		this.countryRepository.save(new Country("AU", "Australia", this.regionRepository.findById(3L).orElse(null)));
		this.countryRepository.save(new Country("ZW", "Zimbabwe", this.regionRepository.findById(4L).orElse(null)));
		this.countryRepository.save(new Country("SG", "Singapore", this.regionRepository.findById(3L).orElse(null)));
		this.countryRepository
				.save(new Country("UK", "United Kingdom", this.regionRepository.findById(1L).orElse(null)));
		this.countryRepository.save(new Country("FR", "France", this.regionRepository.findById(1L).orElse(null)));
		this.countryRepository.save(new Country("DE", "Germany", this.regionRepository.findById(1L).orElse(null)));
		this.countryRepository.save(new Country("ZM", "Zambia", this.regionRepository.findById(4L).orElse(null)));
		this.countryRepository.save(new Country("EG", "Egypt", this.regionRepository.findById(4L).orElse(null)));
		this.countryRepository.save(new Country("BR", "Brazil", this.regionRepository.findById(2L).orElse(null)));
		this.countryRepository.save(new Country("CH", "Switzerland", this.regionRepository.findById(1L).orElse(null)));
		this.countryRepository.save(new Country("NL", "Netherlands", this.regionRepository.findById(1L).orElse(null)));
		this.countryRepository.save(new Country("MX", "Mexico", this.regionRepository.findById(2L).orElse(null)));
		this.countryRepository.save(new Country("KW", "Kuwait", this.regionRepository.findById(4L).orElse(null)));
		this.countryRepository.save(new Country("IL", "Israel", this.regionRepository.findById(4L).orElse(null)));
		this.countryRepository.save(new Country("DK", "Denmark", this.regionRepository.findById(1L).orElse(null)));
		this.countryRepository.save(new Country("HK", "HongKong", this.regionRepository.findById(3L).orElse(null)));
		this.countryRepository.save(new Country("NG", "Nigeria", this.regionRepository.findById(4L).orElse(null)));
		this.countryRepository.save(new Country("AR", "Argentina", this.regionRepository.findById(2L).orElse(null)));
		this.countryRepository.save(new Country("BE", "Belgium", this.regionRepository.findById(1L).orElse(null)));
	}
}