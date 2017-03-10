package test.api.rest.club.async;

import javastrava.api.v3.model.StravaClubEvent;
import test.api.rest.callback.APIListCallback;
import test.api.rest.club.ListClubEventsTest;

/**
 * @author Dan Shannon
 *
 */
public class ListClubEventsAsyncTest extends ListClubEventsTest {
	/**
	 * @see test.api.rest.club.ListClubEventsTest#listCallback()
	 */
	@Override
	protected APIListCallback<StravaClubEvent, Integer> listCallback() {
		return (api, id) -> api.listClubGroupEventsAsync(id).get();
	}
}
