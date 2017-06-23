package comics.com.app.presentation.list;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.usecases.list.GetComics;
import comics.com.app.presentation.base.ScheduleOn;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;

/**
 * Created by alessandro.candolini on 23/06/2017.
 */
public class ListPresenterLoadingTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    GetComics stubGetComics;

    @Spy
    ScheduleOn scheduleOn = new ScheduleOn(
            Schedulers.trampoline(),
            Schedulers.trampoline(),
            Schedulers.trampoline()
    );

    @InjectMocks
    ListPresenter presenter;

    @Mock
    Contract.ListView mockedView;

    TestScheduler testScheduler = new TestScheduler();

    @Before
    public void setUp() throws Exception {

        RxJavaPlugins.setComputationSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
                return testScheduler;
            }
        });

    }

    @After
    public void tearDown() throws Exception {
        RxJavaPlugins.reset();
    }

    @Test
    public void test_WhenDataIsLoading_MustShowProgress() throws Exception {

        // given
        int fakeDelay = 10;
        Comic fakeComic = Mockito.mock(Comic.class);
        List<Comic> fakeComics = Collections.singletonList(fakeComic);
        Mockito.doReturn(Observable.just(fakeComics).delay(fakeDelay, TimeUnit.SECONDS))
                .when(stubGetComics).execute();

        // when
        presenter.attach(mockedView);
        presenter.loadComics();
        testScheduler.advanceTimeBy(fakeDelay - 1, TimeUnit.SECONDS);

        // then
        Mockito.verify(mockedView, Mockito.times(1)).showLoading();
        Mockito.verify(mockedView, Mockito.times(0)).hideLoading();
    }

    @Test
    public void test_WhenDataHasFinishLoading_MustDismissProgress() throws Exception {
        // given
        int fakeDelay = 10;
        Comic fakeComic = Mockito.mock(Comic.class);
        List<Comic> fakeComics = Collections.singletonList(fakeComic);
        Mockito.doReturn(Observable.just(fakeComics).delay(fakeDelay, TimeUnit.SECONDS))
                .when(stubGetComics).execute();

        // when
        presenter.attach(mockedView);
        presenter.loadComics();
        testScheduler.advanceTimeBy(fakeDelay + 1, TimeUnit.SECONDS);

        // then
        Mockito.verify(mockedView, Mockito.times(1)).showLoading();
        Mockito.verify(mockedView, Mockito.times(1)).hideLoading();
    }

    @Test
    public void test_WhenDataHasFinishLoadingWithError_MustDismissProgress() throws Exception {
        // given
        int fakeDelay = 10;
        Throwable fakeError = Mockito.mock(Throwable.class);
        Mockito.doReturn(Observable.error(fakeError).delay(fakeDelay, TimeUnit.SECONDS))
                .when(stubGetComics).execute();

        // when
        presenter.attach(mockedView);
        presenter.loadComics();
        testScheduler.advanceTimeBy(fakeDelay + 1, TimeUnit.SECONDS);

        // then
        Mockito.verify(mockedView, Mockito.times(1)).showLoading();
        Mockito.verify(mockedView, Mockito.times(1)).hideLoading();
    }
}