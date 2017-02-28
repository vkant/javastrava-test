package test.service.impl.streamservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import javastrava.api.v3.model.StravaStream;
import javastrava.api.v3.model.reference.StravaStreamResolutionType;
import javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType;
import javastrava.api.v3.model.reference.StravaStreamType;
import javastrava.api.v3.service.Strava;
import test.api.model.StravaStreamTest;
import test.service.standardtests.ListMethodTest;
import test.service.standardtests.callbacks.ListCallback;
import test.utils.RateLimitedTestRunner;
import test.utils.TestUtils;

/**
 * <p>
 * Specific tests for {@link Strava#getSegmentStreams(Integer)} methods
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class GetSegmentStreamsTest extends ListMethodTest<StravaStream, Integer> {
	/**
	 * <p>
	 * All stream types
	 * </p>
	 *
	 * @throws Exception
	 *             if the test fails in an unexpected way
	 */
	@Test
	public void testGetSegmentStreams_allStreamTypes() throws Exception {
		RateLimitedTestRunner.run(() -> {
			final List<StravaStream> streams = TestUtils.strava().getSegmentStreams(TestUtils.SEGMENT_VALID_ID);
			validateList(streams);
		});
	}

	/**
	 * <p>
	 * Downsampled by distance
	 * </p>
	 *
	 * @throws Exception
	 *             if the test fails in an unexpected way
	 */
	@Test
	public void testGetSegmentStreams_downsampledByDistance() throws Exception {
		RateLimitedTestRunner.run(() -> {
			for (final StravaStreamResolutionType resolutionType : StravaStreamResolutionType.values()) {
				if ((resolutionType != StravaStreamResolutionType.UNKNOWN) && (resolutionType != null)) {
					final List<StravaStream> streams = TestUtils.strava().getSegmentStreams(TestUtils.SEGMENT_VALID_ID,
							resolutionType, StravaStreamSeriesDownsamplingType.DISTANCE);
					validateList(streams);
				}
			}
		});
	}

	/**
	 * <p>
	 * Downsampled by time - can't be done for segment streams as there's no time element
	 * </p>
	 *
	 * @throws Exception
	 *             if the test fails in an unexpected way
	 */
	@SuppressWarnings("static-method")
	@Test
	public void testGetSegmentStreams_downsampledByTime() throws Exception {
		RateLimitedTestRunner.run(() -> {
			for (final StravaStreamResolutionType resolutionType : StravaStreamResolutionType.values()) {
				if (resolutionType != StravaStreamResolutionType.UNKNOWN) {
					try {
						TestUtils.strava().getSegmentStreams(TestUtils.SEGMENT_VALID_ID, resolutionType,
								StravaStreamSeriesDownsamplingType.TIME);
					} catch (final IllegalArgumentException e) {
						// expected
						return;
					}
					fail("Can't return a segment stream which is downsampled by TIME!"); //$NON-NLS-1$
				}
			}
		});
	}

	/**
	 * <p>
	 * Invalid downsample resolution
	 * </p>
	 *
	 * @throws Exception
	 *             if the test fails in an unexpected way
	 */
	@SuppressWarnings("static-method")
	@Test
	public void testGetSegmentStreams_invalidDownsampleResolution() throws Exception {
		RateLimitedTestRunner.run(() -> {
			try {
				TestUtils.strava().getSegmentStreams(TestUtils.SEGMENT_VALID_ID, StravaStreamResolutionType.UNKNOWN, null);
			} catch (final IllegalArgumentException e) {
				// Expected
				return;
			}
			fail("Didn't throw an exception when asking for an invalid downsample resolution"); //$NON-NLS-1$
		});
	}

	/**
	 * <p>
	 * Invalid downsample type
	 * </p>
	 *
	 * @throws Exception
	 *             if the test fails in an unexpected way
	 */
	@SuppressWarnings("static-method")
	@Test
	public void testGetSegmentStreams_invalidDownsampleType() throws Exception {
		RateLimitedTestRunner.run(() -> {
			try {
				TestUtils.strava().getSegmentStreams(TestUtils.SEGMENT_VALID_ID, StravaStreamResolutionType.LOW,
						StravaStreamSeriesDownsamplingType.UNKNOWN);
			} catch (final IllegalArgumentException e) {
				// Expected
				return;
			}
			fail("Didn't throw an exception when asking for an invalid downsample type"); //$NON-NLS-1$
		});
	}

	/**
	 * <p>
	 * Invalid stream type
	 * </p>
	 *
	 * @throws Exception
	 *             if the test fails in an unexpected way
	 */
	@SuppressWarnings("static-method")
	@Test
	public void testGetSegmentStreams_invalidStreamType() throws Exception {
		RateLimitedTestRunner.run(() -> {
			try {
				TestUtils.strava().getSegmentStreams(TestUtils.SEGMENT_VALID_ID, null, null, StravaStreamType.UNKNOWN);
			} catch (final IllegalArgumentException e) {
				// Expected
				return;
			}
			fail("Should have got an IllegalArgumentException, but didn't"); //$NON-NLS-1$
		});
	}

	/**
	 * <p>
	 * Only one stream type
	 * </p>
	 *
	 * @throws Exception
	 *             if the test fails in an unexpected way
	 */
	@Test
	public void testGetSegmentStreams_oneStreamType() throws Exception {
		RateLimitedTestRunner.run(() -> {
			final List<StravaStream> streams = TestUtils.strava().getSegmentStreams(TestUtils.SEGMENT_VALID_ID, null, null,
					StravaStreamType.DISTANCE);
			assertNotNull(streams);
			assertEquals(1, streams.size());
			assertEquals(StravaStreamType.DISTANCE, streams.get(0).getType());
			validateList(streams);
		});
	}

	@Override
	protected ListCallback<StravaStream, Integer> lister() {
		return ((strava, id) -> strava.getSegmentStreams(id));
	}

	@Override
	protected Integer idPrivate() {
		return TestUtils.SEGMENT_PRIVATE_ID;
	}

	@Override
	protected Integer idPrivateBelongsToOtherUser() {
		return TestUtils.SEGMENT_OTHER_USER_PRIVATE_ID;
	}

	@Override
	protected Integer idValidWithEntries() {
		return TestUtils.SEGMENT_VALID_ID;
	}

	@Override
	protected Integer idValidWithoutEntries() {
		return null;
	}

	@Override
	protected Integer idInvalid() {
		return TestUtils.SEGMENT_INVALID_ID;
	}

	@Override
	protected void validate(StravaStream object) {
		StravaStreamTest.validate(object);
	}

}