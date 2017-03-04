package test.api.rest.activity;

import java.util.Arrays;

import javastrava.api.v3.model.StravaPhoto;
import test.api.model.StravaPhotoTest;
import test.api.rest.APIListTest;
import test.api.rest.TestListArrayCallback;
import test.issues.strava.Issue68;
import test.service.standardtests.data.ActivityDataUtils;

public class ListActivityPhotosTest extends APIListTest<StravaPhoto, Long> {
	@Override
	protected TestListArrayCallback<StravaPhoto, Long> listCallback() {
		return ((api, id) -> api.listActivityPhotos(id));
	}

	/**
	 *
	 */
	public ListActivityPhotosTest() {
		super();
		this.listOtherReturns401Unauthorised = true;
	}

	/**
	 * @see test.api.rest.APIListTest#invalidId()
	 */
	@Override
	protected Long invalidId() {
		return ActivityDataUtils.ACTIVITY_INVALID;
	}

	/**
	 * @see test.api.rest.APIListTest#privateId()
	 */
	@Override
	protected Long privateId() {
		return ActivityDataUtils.ACTIVITY_PRIVATE_WITH_PHOTOS;
	}

	/**
	 * @see test.api.rest.APIListTest#privateIdBelongsToOtherUser()
	 */
	@Override
	protected Long privateIdBelongsToOtherUser() {
		return ActivityDataUtils.ACTIVITY_PRIVATE_OTHER_USER;
	}

	/**
	 * @see test.api.rest.APITest#validate(java.lang.Object)
	 */
	@Override
	protected void validate(final StravaPhoto photo) throws Exception {
		StravaPhotoTest.validate(photo);

	}

	/**
	 * @see test.api.rest.APIListTest#validateArray(java.lang.Object[])
	 */
	@Override
	protected void validateArray(final StravaPhoto[] list) {
		StravaPhotoTest.validateList(Arrays.asList(list));
	}

	/**
	 * @see test.api.rest.APIListTest#validId()
	 */
	@Override
	protected Long validId() {
		return ActivityDataUtils.ACTIVITY_WITH_PHOTOS;
	}

	/**
	 * @see test.api.rest.APIListTest#validIdBelongsToOtherUser()
	 */
	@Override
	protected Long validIdBelongsToOtherUser() {
		return ActivityDataUtils.ACTIVITY_FOR_UNAUTHENTICATED_USER;
	}

	/**
	 * @see test.api.rest.APIListTest#validIdNoChildren()
	 */
	@Override
	protected Long validIdNoChildren() {
		return ActivityDataUtils.ACTIVITY_WITHOUT_PHOTOS;
	}

	/**
	 * @see test.api.rest.APIListTest#list_private()
	 */
	@Override
	public void list_private() throws Exception {
		if (new Issue68().isIssue()) {
			return;
		}
		super.list_private();
	}

}
