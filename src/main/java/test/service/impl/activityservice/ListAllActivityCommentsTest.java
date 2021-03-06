/**
 *
 */
package test.service.impl.activityservice;

import javastrava.model.StravaComment;
import test.service.standardtests.ListMethodTest;
import test.service.standardtests.callbacks.ListCallback;
import test.service.standardtests.data.ActivityDataUtils;
import test.service.standardtests.data.CommentDataUtils;

/**
 * <p>
 * Specific tests for methods that list all activity comments
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class ListAllActivityCommentsTest extends ListMethodTest<StravaComment, Long> {

	@Override
	protected Class<StravaComment> classUnderTest() {
		return StravaComment.class;
	}

	@Override
	protected Long idInvalid() {
		return ActivityDataUtils.ACTIVITY_INVALID;
	}

	@Override
	protected Long idPrivate() {
		return ActivityDataUtils.ACTIVITY_PRIVATE;
	}

	@Override
	protected Long idPrivateBelongsToOtherUser() {
		return ActivityDataUtils.ACTIVITY_PRIVATE_OTHER_USER;
	}

	@Override
	protected Long idValidWithEntries() {
		return ActivityDataUtils.ACTIVITY_WITH_COMMENTS;
	}

	@Override
	protected Long idValidWithoutEntries() {
		return ActivityDataUtils.ACTIVITY_WITHOUT_COMMENTS;
	}

	@Override
	protected ListCallback<StravaComment, Long> lister() {
		return ((strava, id) -> strava.listAllActivityComments(id));
	}

	@Override
	protected void validate(StravaComment comment) {
		CommentDataUtils.validateComment(comment);
	}

}
