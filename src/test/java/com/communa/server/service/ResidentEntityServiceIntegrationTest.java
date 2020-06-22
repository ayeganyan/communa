package com.communa.server.service;

import com.communa.server.entity.ResidentEntity;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest()
@TestPropertySource(locations="classpath:test.properties")
public class ResidentEntityServiceIntegrationTest {

	private static Date birthDate;
	private final static String EMAIL = "email@email.com";
	private final static String PASSWORD = "password";
	private final static String NAME = "Daniel";
	private final static String SURNAME = "Goleman";

	@Autowired
	private ResidentService residentService;

	@BeforeClass
	public static void initialize() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		try {
			birthDate = df.parse("2000");
		} catch (ParseException ignore) {}
	}

	@Test
	public void testRegisterResident() {
		ResidentEntity residentEntity = new ResidentEntity();
		residentEntity.setBirthDate(birthDate);
		residentEntity.setEmail(EMAIL);
		residentEntity.setPassword(PASSWORD);
		residentEntity.setName(NAME);
		residentEntity.setSurname(SURNAME);

		ResidentEntity savedResidentEntity = residentService.registerResident(residentEntity);

		assertNotNull(savedResidentEntity);
		assertNotNull(residentEntity.getId());
		assertEquals(birthDate, savedResidentEntity.getBirthDate());
		assertEquals(EMAIL, savedResidentEntity.getEmail());
		assertEquals(NAME, savedResidentEntity.getName());
		assertEquals(SURNAME, savedResidentEntity.getSurname());
	}

	@Test(expected = RuntimeException.class)
	public void testRegisterResidentWithNonValidAge() {
		ResidentEntity residentEntity = new ResidentEntity();
		residentEntity.setBirthDate(Calendar.getInstance().getTime());
		residentEntity.setEmail(EMAIL);
		residentEntity.setPassword(PASSWORD);
		residentEntity.setName(NAME);
		residentEntity.setSurname(SURNAME);

		residentService.registerResident(residentEntity);
	}

}
