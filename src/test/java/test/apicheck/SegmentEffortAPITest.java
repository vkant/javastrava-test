package test.apicheck;

import java.io.IOException;

import javastrava.api.v3.model.StravaSegmentEffort;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.api.v3.service.impl.retrofit.Retrofit;
import javastrava.util.exception.JsonSerialisationException;

import org.junit.Test;

import retrofit.client.Response;
import test.apicheck.api.ResponseValidator;
import test.apicheck.api.SegmentEffortAPI;
import test.utils.TestUtils;

/**
 * @author dshannon
 *
 */
public class SegmentEffortAPITest {
	@Test
	public void testAPI_getSegmentEffort() throws NotFoundException, JsonSerialisationException, IOException {
		Response response = api().getSegmentEffort(TestUtils.SEGMENT_EFFORT_VALID_ID);
		ResponseValidator.validate(response, StravaSegmentEffort.class);
	}
	
	private SegmentEffortAPI api() {
		return Retrofit.retrofit(SegmentEffortAPI.class, TestUtils.getValidToken());
	}

}
