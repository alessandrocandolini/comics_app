package comics.com.app.presentation.list;

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

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.usecases.list.GetComics;
import comics.com.app.presentation.base.ScheduleOn;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by alessandro.candolini on 23/06/2017.
 */
public class ListPresenterTest {

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
    ListPresenterImpl presenter;

    @Mock
    ListView mockedView;

    @Test
    public void test_WhenViewAttachedAndUseCaseReturnsItems_MustShowItems() throws Exception {

        // given
        Comic fakeComic = Mockito.mock(Comic.class);
        List<Comic> fakeComics = Collections.singletonList(fakeComic);
        Mockito.doReturn(Observable.just(fakeComics)).when(stubGetComics).execute();

        // when
        presenter.attach(mockedView);
        presenter.loadComics();

        // then
        Mockito.verify(mockedView, Mockito.times(1)).showComics(fakeComics);
        Mockito.verify(mockedView, Mockito.times(0)).showGenericError();
        Mockito.verify(mockedView, Mockito.times(0)).showNoComics();
        Mockito.verify(mockedView, Mockito.times(0)).showError(ArgumentMatchers.anyString());
    }

    @Test
    public void test_WhenViewAttachedAndUseCaseReturnsZeroItems_MustShowEmptyMessage() throws Exception {

        // given
        List<Comic> fakeComics = Collections.emptyList();
        Mockito.doReturn(Observable.just(fakeComics)).when(stubGetComics).execute();

        // when
        presenter.attach(mockedView);
        presenter.loadComics();

        // then
        Mockito.verify(mockedView, Mockito.times(0)).showComics(ArgumentMatchers.<Comic>anyList());
        Mockito.verify(mockedView, Mockito.times(0)).showGenericError();
        Mockito.verify(mockedView, Mockito.times(1)).showNoComics();
        Mockito.verify(mockedView, Mockito.times(0)).showError(ArgumentMatchers.anyString());
    }

    @Test
    public void test_WhenViewAttachedAndUseCaseReturnsError_MustShowError() throws Exception {

        // given
        Throwable fakeError = Mockito.mock(Throwable.class);
        Mockito.doReturn(Observable.error(fakeError)).when(stubGetComics).execute();

        // when
        presenter.attach(mockedView);
        presenter.loadComics();

        // then
        Mockito.verify(mockedView, Mockito.times(0)).showComics(ArgumentMatchers.<Comic>anyList());
        Mockito.verify(mockedView, Mockito.times(1)).showGenericError();
        Mockito.verify(mockedView, Mockito.times(0)).showNoComics();
        Mockito.verify(mockedView, Mockito.times(0)).showError(ArgumentMatchers.anyString());
    }
}
