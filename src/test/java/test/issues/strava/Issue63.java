/**
 *
 */
package test.issues.strava;

import javastrava.api.v3.model.StravaComment;
import javastrava.api.v3.rest.API;
import javastrava.api.v3.rest.ActivityAPI;

import org.junit.Test;

import test.utils.RateLimitedTestRunner;
import test.utils.TestUtils;

/**
 * <p>
 * Issue test for issue javastrava-api #63
 * </p>
 *
 * <p>
 * Tests will PASS if the issue is still a problem
 * </p>
 *
 * @author Dan Shannon
 * @see <a href="https://github.com/danshannon/javastravav3api/issues/63">https://github.com/danshannon/javastravav3api/issues/63</a>
 */
public class Issue63 {
	@Test
	public void testIssue() throws Exception {
		RateLimitedTestRunner.run(() -> {
			final ActivityAPI retrofit = API.instance(ActivityAPI.class, TestUtils.getValidToken());
			final StravaComment comment = retrofit.createComment(TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER, "Test - ignore");
			retrofit.deleteComment(TestUtils.ACTIVITY_FOR_AUTHENTICATED_USER, comment.getId());
		});
	}
}
