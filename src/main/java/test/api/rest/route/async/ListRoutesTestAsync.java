package test.api.rest.route.async;

import javastrava.api.v3.model.StravaRoute;
import javastrava.api.v3.rest.API;
import test.api.rest.callback.APIListCallback;
import test.api.rest.route.ListRoutesTest;

/**
 * <p>
 * Tests for {@link API#listAthleteRoutesAsync(Integer)}
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class ListRoutesTestAsync extends ListRoutesTest {
	@Override
	protected APIListCallback<StravaRoute, Integer> listCallback() {
		return ((api, id) -> api.listAthleteRoutesAsync(id).get());
	}

}
