package com.communa.server.service;

import com.communa.server.entity.CommunityEntity;
import com.communa.server.entity.ResidentEntity;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest()
@TestPropertySource(locations="classpath:application.properties")
@Transactional
@Sql("classpath:test_data_for_resident.sql")
public class ResidentEntityServiceIntegrationTest {

	private static Date birthDate;
	private final static String EMAIL = "email@email.com";
	private final static String PASSWORD = "password";
	private final static String NAME = "Daniel";
	private final static String SURNAME = "Goleman";

	private ResidentEntity residentEntity;

	@Autowired
	private ResidentService residentService;

	@Autowired
	private CommunityService communityService;

	@Autowired
	private ParkingLotService parkingLotService;

	@BeforeClass
	public static void initialize() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		try {
			birthDate = df.parse("2000");
		} catch (ParseException ignore) {}
	}

	@Before
	public void beforeTest() {
		residentService.getResidents().forEach(
				r -> residentService.deleteResident(r.getId())
		);

		residentEntity = createResident();
	}

	private ResidentEntity createResident() {
		ResidentEntity residentEntity = new ResidentEntity();
		residentEntity.setBirthDate(birthDate);
		residentEntity.setEmail(EMAIL);
		residentEntity.setPassword(PASSWORD);
		residentEntity.setName(NAME);
		residentEntity.setSurname(SURNAME);

		return residentEntity;
	}

	@Test
	public void testRegisterResident() {
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
		residentEntity.setBirthDate(Calendar.getInstance().getTime());
		ResidentEntity savedResidentEntity = residentService.registerResident(this.residentEntity);
	}

	@Test
	public void updateResident() {
		residentEntity.setEmail("new@mail.com");
		ResidentEntity savedResidentEntity = residentService.registerResident(this.residentEntity);
		assertNotNull(residentEntity);
		assertEquals("new@mail.com", savedResidentEntity.getEmail());
	}

	@Test(expected = Exception.class)
	public void createResidentWithDuplicateEmail() {
		residentService.registerResident(this.residentEntity);
		residentService.registerResident(this.residentEntity);
	}

	@Test
	public void getResident() {
		ResidentEntity savedResidentEntity = residentService.registerResident(this.residentEntity);

		ResidentEntity getResident = residentService.getResident(savedResidentEntity.getId());
		assertNotNull(residentEntity);
		assertNotNull(getResident.getId());
		assertEquals(birthDate, savedResidentEntity.getBirthDate());
		assertEquals(EMAIL, savedResidentEntity.getEmail());
		assertEquals(NAME, savedResidentEntity.getName());
		assertEquals(SURNAME, savedResidentEntity.getSurname());
		assertEquals(savedResidentEntity.getId(), savedResidentEntity.getId());
	}

	@Test
	public void getResidents() {
		ResidentEntity savedResidentEntity1 = residentService.registerResident(this.residentEntity);

		ResidentEntity residentEntity2 = createResident();
		residentEntity.setEmail("main@mail.com");
		ResidentEntity savedResidentEntity2 = residentService.registerResident(residentEntity2);

		Set<ResidentEntity> residents = residentService.getResidents();
		assertNotNull(residents);
		assertEquals(2, residents.size());
	}

	@Test
	public void deleteResident() {
		ResidentEntity savedResidentEntity = residentService.registerResident(this.residentEntity);

		residentService.deleteResident(savedResidentEntity.getId());

		Set<ResidentEntity> residents = residentService.getResidents();
		assertNotNull(residents);
		assertEquals(0, residents.size());
	}

	@Test
	public void joinCommunity() {
		ResidentEntity savedResidentEntity = residentService.registerResident(this.residentEntity);
		ResidentEntity residentEntity = residentService.joinCommunity(savedResidentEntity.getId(), 1L);
		assertNotNull(residentEntity);
		CommunityEntity community = residentEntity.getCommunity();
		assertEquals((Long)1L, community.getId());

		assertNotNull(community);
		Set<ResidentEntity> communityResidents = communityService.getCommunityResidents(1L);
		assertNotNull(communityResidents);
	}

	@Test
	public void leaveCommunity() {
		ResidentEntity savedResidentEntity = residentService.registerResident(this.residentEntity);
		ResidentEntity residentEntity = residentService.joinCommunity(savedResidentEntity.getId(), 1L);
		residentService.leaveCommunity(savedResidentEntity.getId());

		ResidentEntity resident = residentService.getResident(savedResidentEntity.getId());
		assertNotNull(resident);
		assertNull(resident.getCommunity());
	}

	@Test
	public void acquireParkingLot() {
		ResidentEntity savedResidentEntity = residentService.registerResident(this.residentEntity);

		savedResidentEntity.setParkingLot(parkingLotService.getParkingLot(3L));
		ResidentEntity residentEntity = residentService.updateResident(savedResidentEntity.getId(), savedResidentEntity);
		assertNotNull(residentEntity);
		assertNotNull(residentEntity.getParkingLot());
	}

	@Test
	public void releaseParkingLot() {
		residentEntity.setParkingLot(parkingLotService.getParkingLot(3L));
		ResidentEntity savedResidentEntity = residentService.registerResident(this.residentEntity);
		savedResidentEntity.setParkingLot(null);
		ResidentEntity updatedResidentEntity = residentService.updateResident(this.residentEntity.getId(), this.residentEntity);

		assertNotNull(updatedResidentEntity);
		assertNull(updatedResidentEntity.getParkingLot());
	}
}
