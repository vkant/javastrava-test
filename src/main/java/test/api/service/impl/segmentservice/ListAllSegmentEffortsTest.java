package test.api.service.impl.segmentservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import javastrava.api.v3.model.StravaSegmentEffort;

import org.junit.Test;

import test.api.model.StravaSegmentEffortTest;
import test.api.service.StravaTest;
import test.utils.TestUtils;

public class ListAllSegmentEffortsTest extends StravaTest {
	@Test
	public void listAllSegmentEfforts_validSegment() {
		List<StravaSegmentEffort> efforts = service().listAllSegmentEfforts(TestUtils.SEGMENT_VALID_ID);
		assertNotNull(efforts);
		for (StravaSegmentEffort effort : efforts) {
			StravaSegmentEffortTest.validateSegmentEffort(effort);
		}
	}
	
	@Test
	public void listAllSegmentEfforts_invalidSegment() {
		List<StravaSegmentEffort> efforts = service().listAllSegmentEfforts(TestUtils.SEGMENT_INVALID_ID);
		assertNull(efforts);
		
	}
	
	@Test
	public void listAllSegmentEfforts_privateSegment() {
		List<StravaSegmentEffort> efforts = service().listAllSegmentEfforts(TestUtils.SEGMENT_PRIVATE_ID);
		assertNotNull(efforts);
		for (StravaSegmentEffort effort : efforts) {
			StravaSegmentEffortTest.validateSegmentEffort(effort);
		}
		
	}
	
	@Test
	public void listAllSegmentEfforts_privateSegmentOtherUser() {
		List<StravaSegmentEffort> efforts = service().listAllSegmentEfforts(TestUtils.SEGMENT_OTHER_USER_PRIVATE_ID);
		assertNotNull(efforts);
		assertTrue(efforts.isEmpty());
		
	}
	
	// TODO Needs a Workaround!
	//	DO NOT RUN IT DOESN'T SEEM TO WORK AS EXPECTED	
	@Test
	public void listAllSegmentEfforts_hazardousSegment() {
		List<StravaSegmentEffort> efforts = service().listAllSegmentEfforts(TestUtils.SEGMENT_HAZARDOUS_ID);
		assertNotNull(efforts);
		assertEquals(0,efforts.size());
		
	}
	
	@Test
	public void listAllSegmentEfforts_filterByValidAthlete() {
		List<StravaSegmentEffort> efforts = service().listAllSegmentEfforts(TestUtils.SEGMENT_VALID_ID, TestUtils.ATHLETE_AUTHENTICATED_ID, null, null);
		assertNotNull(efforts);
		for (StravaSegmentEffort effort : efforts) {
			assertEquals(TestUtils.ATHLETE_AUTHENTICATED_ID,effort.getAthlete().getId());
			StravaSegmentEffortTest.validateSegmentEffort(effort);
		}
	}
	
	@Test
	public void listAllSegmentEfforts_filterByInvalidAthlete() {
		List<StravaSegmentEffort> efforts = service().listAllSegmentEfforts(TestUtils.SEGMENT_VALID_ID, TestUtils.ATHLETE_INVALID_ID, null, null);
		assertNull(efforts);
	}
	
	@Test
	public void listAllSegmentEfforts_filterByBeforeDate() {
		LocalDateTime beforeDate = LocalDateTime.of(2013, Month.JANUARY, 1, 0, 0);
		List<StravaSegmentEffort> efforts = service().listAllSegmentEfforts(TestUtils.SEGMENT_VALID_ID, null, null, beforeDate);
		assertNotNull(efforts);
		for (StravaSegmentEffort effort : efforts) {
			assertTrue(effort.getStartDateLocal().isBefore(beforeDate));
			StravaSegmentEffortTest.validateSegmentEffort(effort);
		}
	}

	@Test
	public void listAllSegmentEfforts_filterByAfterDate() {
		LocalDateTime afterDate = LocalDateTime.of(2015, Month.JANUARY, 1, 0, 0);
		List<StravaSegmentEffort> efforts = service().listAllSegmentEfforts(TestUtils.SEGMENT_VALID_ID, null, afterDate, null);
		assertNotNull(efforts);
		for (StravaSegmentEffort effort : efforts) {
			assertTrue(effort.getStartDateLocal().isAfter(afterDate));
			StravaSegmentEffortTest.validateSegmentEffort(effort);
		}
	}

	@Test
	public void listAllSegmentEfforts_filterByDateRange() {
		LocalDateTime afterDate = LocalDateTime.of(2013, Month.JANUARY, 1, 0, 0);
		LocalDateTime beforeDate = LocalDateTime.of(2013, Month.DECEMBER, 1, 0, 0);
		List<StravaSegmentEffort> efforts = service().listAllSegmentEfforts(TestUtils.SEGMENT_VALID_ID, null, afterDate, beforeDate);
		assertNotNull(efforts);
		for (StravaSegmentEffort effort : efforts) {
			assertTrue(effort.getStartDateLocal().isAfter(afterDate));
			assertTrue(effort.getStartDateLocal().isBefore(beforeDate));
			StravaSegmentEffortTest.validateSegmentEffort(effort);
		}
	}
	
	@Test
	public void listAllSegmentEfforts_filterByEverything() {
		LocalDateTime afterDate = LocalDateTime.of(2013, Month.JANUARY, 1, 0, 0);
		LocalDateTime beforeDate = LocalDateTime.of(2013, Month.DECEMBER, 1, 0, 0);
		List<StravaSegmentEffort> efforts = service().listAllSegmentEfforts(TestUtils.SEGMENT_VALID_ID, TestUtils.ATHLETE_AUTHENTICATED_ID, afterDate, beforeDate);
		assertNotNull(efforts);
		for (StravaSegmentEffort effort : efforts) {
			assertTrue(effort.getStartDateLocal().isBefore(beforeDate));
			assertTrue(effort.getStartDateLocal().isAfter(afterDate));
			assertTrue(effort.getStartDateLocal().isBefore(beforeDate));
			StravaSegmentEffortTest.validateSegmentEffort(effort);
		}
	}

}