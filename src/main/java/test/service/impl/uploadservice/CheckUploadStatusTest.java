package test.service.impl.uploadservice;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import javastrava.model.StravaUploadResponse;
import javastrava.service.Strava;
import test.service.standardtests.data.ActivityDataUtils;
import test.utils.RateLimitedTestRunner;
import test.utils.TestUtils;

/**
 * <p>
 * Specific tests for {@link Strava#checkUploadStatus(Long)} methods
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class CheckUploadStatusTest {
	/**
	 * @throws Exception
	 *             if the test fails in an unexpected way
	 */
	@SuppressWarnings("static-method")
	@Test
	public void testCheckUploadStatus() throws Exception {
		RateLimitedTestRunner.run(() -> {
			final StravaUploadResponse response = TestUtils.strava().checkUploadStatus(ActivityDataUtils.ACTIVITY_FOR_AUTHENTICATED_USER);
			assertNotNull(response);
			ActivityDataUtils.validateUploadResponse(response);
		});
	}

}
