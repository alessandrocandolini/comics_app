package comics.com.app.presentation.list;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import comics.com.app.EspressoDaggerRule;
import comics.com.app.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.ArgumentMatchers.any;


/**
 * Unit tests of the methods of the view, mocking the presenter and testing ineractions with presenter
 * Created by alessandro.candolini on 25/06/2017.
 */
@RunWith(AndroidJUnit4.class)
public class ListActivityTest {

    @Rule public EspressoDaggerRule rule = new EspressoDaggerRule();

    @Rule
    public ActivityTestRule<ListActivity> activityTestRule =
            new ActivityTestRule<>(ListActivity.class,false, false); // do not automatically launch activity

    @Mock
    ListPresenter mockedListPresenter;

    @Test
    public void test_WhenLaunchingActivity_MustAttachPresenter() {
        // given

        // when
        activityTestRule.launchActivity(null);

        // then
        Mockito.verify(mockedListPresenter, Mockito.times(1)).attach(any(ListView.class));
        Mockito.verify(mockedListPresenter, Mockito.times(0)).detach();
        Mockito.verify(mockedListPresenter, Mockito.times(0)).abort();
    }

    @Test
    public void test_WhenShowingError_MustDisplayError() {

        // given
        activityTestRule.launchActivity(null);

        // when
        activityTestRule.getActivity().showGenericError();

        // then
        onView(withId(R.id.error)).check(matches(isDisplayed()));
    }

    @Test
    public void test_WhenShowingEmptyResults_MustDisplayEmptyResults() {

        // given
        activityTestRule.launchActivity(null);

        // when
        activityTestRule.getActivity().showNoComics();

        // then
        onView(withText(R.string.no_results)).check(matches(isDisplayed()));
    }

    @Test
    public void test_WhenShowingResults_MustDisplayResults() {

        // given
        ViewComic fakeComic = Mockito.mock(ViewComic.class);
        String fakeTitle = "frrwewe2";
        Mockito.doReturn(fakeTitle).when(fakeComic).getTitle();
        final List<ViewComic> fakeComics = Collections.singletonList(fakeComic);

        activityTestRule.launchActivity(null);

        // when
        activityTestRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activityTestRule.getActivity().showComics(fakeComics);
            }
        });

        // then
        onView(withText(fakeComic.getTitle())).check(matches(isDisplayed()));

    }

    @Test
    public void test_WhenShowingNumberOfPages_MustShowNumberOfPages() {

        // given
        final String dummyNumberOfPages = "10";
        activityTestRule.launchActivity(null);

        // when
        activityTestRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activityTestRule.getActivity().showNumberOfComics(dummyNumberOfPages);
            }
        });

        // then
        onView(withId(R.id.number_of_comics)).check(matches(withText(dummyNumberOfPages)));

    }


}