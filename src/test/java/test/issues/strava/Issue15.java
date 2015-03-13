package test.issues.strava;

import static org.junit.Assert.assertTrue;
import javastrava.api.v3.model.StravaLap;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.api.v3.service.impl.retrofit.ActivityServicesRetrofit;
import javastrava.api.v3.service.impl.retrofit.Retrofit;

import org.junit.Test;

import test.utils.TestUtils;

/**
 * <p>
 * These tests will PASS if issue <a href="https://github.com/danshannon/javastravav3api/issues/15">javastrava-api #15</a> is still an issue
 * </p>
 * 
 * @see <a href="https://github.com/danshannon/javastravav3api/issues/15">https://github.com/danshannon/javastravav3api/issues/15</a>
 * @author Dan Shannon
 *
 */
public class Issue15 {
	@Test
	public void testIssue() throws NotFoundException {
		ActivityServicesRetrofit retrofit = Retrofit.retrofit(ActivityServicesRetrofit.class, TestUtils.getValidToken());
		StravaLap[] laps = retrofit.listActivityLaps(113202);
		boolean issue = false;
		for (StravaLap lap : laps) {
			if (lap.getActivity().getResourceState() == null) {
				issue = true;
			}
		}
		assertTrue(issue);
	}
}