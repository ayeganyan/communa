package com.communa.server.service;

import com.communa.server.entity.Resident;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ResidentServiceIntegrationTest {

	private static Date birthDate;
	private final static String EMAIL = "email@email.com";
	private final static String PASSWORD = "password";
	private final static String NAME = "Daniel";
	private final static String SURNAME = "Goleman";

	@Autowired
	private ResidentService residentService;

	@BeforeAll
	static void initialize() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		try {
			birthDate = df.parse("2000");
		} catch (ParseException ignore) {}
	}


	@Test
	void testRegisterResident() {
		Resident resident = new Resident();
		resident.setBirthDate(birthDate);
		resident.setEmail(EMAIL);
		resident.setPassword(PASSWORD);
		resident.setName(NAME);
		resident.setSurname(SURNAME);

		Resident savedResident = residentService.registerResident(resident);

		assertNotNull(savedResident);
		assertNotNull(resident.getId());
		assertEquals(birthDate, savedResident.getBirthDate());
		assertEquals(EMAIL, savedResident.getEmail());
		assertEquals(NAME, savedResident.getName());
		assertEquals(SURNAME, savedResident.getSurname());
	}

	@Test()
	void testRegisterResidentWithNonValidAge() {
		Resident resident = new Resident();
		resident.setBirthDate(Calendar.getInstance().getTime());
		resident.setEmail(EMAIL);
		resident.setPassword(PASSWORD);
		resident.setName(NAME);
		resident.setSurname(SURNAME);

		assertThrows(RuntimeException.class,
				() -> residentService.registerResident(resident));
	}

}
