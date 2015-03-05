package test.issues.strava;

import static org.junit.Assert.assertTrue;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.model.StravaSegmentEffort;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.api.v3.service.impl.retrofit.ActivityServicesRetrofit;
import javastrava.api.v3.service.impl.retrofit.Retrofit;

import org.junit.Test;

import test.utils.TestUtils;

/**
 * <p>
 * These tests will PASS if issue <a href="https://github.com/danshannon/javastravav3api/issues/11">javastrava-api #11</a> remains
 * </p>
 * 
 * @author Dan Shannon
 * @see <a href="https://github.com/danshannon/javastravav3api/issues/11">https://github.com/danshannon/javastravav3api/issues/11</a>
 *
 */
public class Issue11 {
	@Test
	public void testIssue() throws NotFoundException {
		ActivityServicesRetrofit retrofit = Retrofit.retrofit(ActivityServicesRetrofit.class, TestUtils.getValidToken());
		StravaActivity activity = retrofit.getActivity(245713183, null);
		boolean issue = false;
		for (StravaSegmentEffort effort : activity.getSegmentEfforts()) {
			if (effort.getActivity().getResourceState() == null) {
				issue = true;
			}
		}
		assertTrue(issue);
	}
}
