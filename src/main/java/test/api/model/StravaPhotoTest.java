package test.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.List;

import javastrava.api.v3.model.StravaPhoto;
import javastrava.api.v3.model.reference.StravaPhotoType;
import javastrava.api.v3.model.reference.StravaResourceState;
import test.utils.BeanTest;

/**
 * @author Dan Shannon
 *
 */
public class StravaPhotoTest extends BeanTest<StravaPhoto> {

	/**
	 * Validate the structure and content of a photo is as expected
	 * 
	 * @param photo
	 *            The photo to be validated
	 */
	public static void validate(final StravaPhoto photo) {
		validatePhoto(photo, photo.getId(), photo.getResourceState());

	}

	/**
	 * Validate a list of photos
	 * 
	 * @param list
	 *            The list of photos to be validated
	 */
	public static void validateList(final List<StravaPhoto> list) {
		for (final StravaPhoto photo : list) {
			validate(photo);
		}

	}

	/**
	 * Validate the structure and content of a photo is as expected
	 * 
	 * @param photo
	 *            The photo to be validated
	 * @param id
	 *            The expected id of the photo
	 * @param state
	 *            The expected resource state of the photo
	 */
	public static void validatePhoto(final StravaPhoto photo, final Integer id, final StravaResourceState state) {
		assertNotNull(photo);
		assertEquals(id, photo.getId());
		assertEquals(state, photo.getResourceState());

		if (state == StravaResourceState.DETAILED) {
			assertNotNull(photo.getCaption());
			assertNotNull(photo.getCreatedAt());
			// Optional assertNotNull(photo.getLocation());
			assertNotNull(photo.getRef());
			assertNotNull(photo.getSource());
			assertNotNull(photo.getType());
			assertFalse(photo.getType() == StravaPhotoType.UNKNOWN);
			assertNotNull(photo.getUid());
			assertNotNull(photo.getUploadedAt());
			if (photo.getUrls() != null) {
				StravaPhotoUrlsTest.validate(photo.getUrls());
			}
			return;
		}
		if ((state == StravaResourceState.SUMMARY) || (state == null)) {
			// Optional assertNotNull(photo.getCaption());
			// Optional assertNotNull(photo.getCreatedAt());
			// Optional assertNotNull(photo.getLocation());
			// Optional assertNotNull(photo.getRef());
			assertNotNull(photo.getSource());
			// Optional assertNotNull(photo.getType());
			assertFalse(photo.getType() == StravaPhotoType.UNKNOWN);
			// Optional assertNotNull(photo.getUid());
			// Optional assertNotNull(photo.getUploadedAt());
			if (photo.getUrls() != null) {
				StravaPhotoUrlsTest.validate(photo.getUrls());
			}
			return;
		}
		if (state == StravaResourceState.META) {
			assertNull(photo.getCaption());
			assertNull(photo.getCreatedAt());
			assertNull(photo.getLocation());
			assertNull(photo.getRef());
			assertNull(photo.getType());
			assertNull(photo.getUid());
			assertNull(photo.getUploadedAt());
			assertNull(photo.getUrls());
			return;
		}
		fail("Unexpected resource state " + state + " for photo " + photo); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	protected Class<StravaPhoto> getClassUnderTest() {
		return StravaPhoto.class;
	}
}
