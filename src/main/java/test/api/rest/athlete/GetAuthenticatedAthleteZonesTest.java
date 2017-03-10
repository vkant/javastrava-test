package test.api.rest.athlete;

import javastrava.api.v3.model.StravaAthleteZones;
import javastrava.api.v3.rest.API;
import test.api.model.StravaAthleteZonesTest;
import test.api.rest.APIGetTest;
import test.api.rest.callback.APIGetCallback;
import test.service.standardtests.data.AthleteDataUtils;

/**
 * <p>
 * Specific tests for {@link API#getAuthenticatedAthleteZones()}
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class GetAuthenticatedAthleteZonesTest extends APIGetTest<StravaAthleteZones, Integer> {

	@Override
	protected Integer invalidId() {
		return null;
	}

	@Override
	protected Integer privateId() {
		return null;
	}

	@Override
	protected Integer privateIdBelongsToOtherUser() {
		return null;
	}

	@Override
	protected Integer validId() {
		return AthleteDataUtils.ATHLETE_AUTHENTICATED_ID;
	}

	@Override
	protected Integer validIdBelongsToOtherUser() {
		return null;
	}

	@Override
	protected void validate(StravaAthleteZones result) throws Exception {
		StravaAthleteZonesTest.validate(result);

	}

	@Override
	protected APIGetCallback<StravaAthleteZones, Integer> getter() {
		return ((api, id) -> api.getAuthenticatedAthleteZones());
	}

}
