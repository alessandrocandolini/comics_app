package comics.com.app.presentation.list;

import android.support.annotation.NonNull;

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

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.usecases.list.CalculateTotal;
import comics.com.app.domain.usecases.list.CountPages;
import comics.com.app.domain.usecases.list.GetComics;
import comics.com.app.domain.usecases.list.GetComicsFilteredByTotalAmount;
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

    @Mock
    GetComicsFilteredByTotalAmount getComicsFilteredByTotalAmount;

    @Mock
    CalculateTotal calculateTotal;

    @Mock
    CountPages countPages;

    @Spy
    ListComicViewMapper mapper;

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
        ListComic fakeListComic = Mockito.mock(ListComic.class);
        List<ListComic> fakeListComics = Collections.singletonList(fakeListComic);
        ComicInfoDisplay fakeComicInfoDisplay = Mockito.mock(ComicInfoDisplay.class);
        Mockito.doReturn(fakeListComics).when(fakeComicInfoDisplay).getComics();
        Mockito.doReturn(fakeComicInfoDisplay).when(mapper).apply(ArgumentMatchers.any(ComicInfo.class));
        Mockito.doReturn(Observable.just(fakeComics)).when(stubGetComics).execute();

        BigDecimal dummyTotal = Mockito.mock(BigDecimal.class);
        Mockito.doReturn(Observable.just(fakeComics)).when(getComicsFilteredByTotalAmount).execute(ArgumentMatchers.<Comic>anyList(),ArgumentMatchers.any(BigDecimal.class));
        Mockito.doReturn(Observable.just(dummyTotal)).when(calculateTotal).execute(fakeComics);
        Mockito.doReturn(Observable.just(10)).when(countPages).execute(fakeComics);

        // when
        presenter.attach(mockedView);
        presenter.loadComics();

        // then
        Mockito.verify(mockedView, Mockito.times(1)).showComics(fakeListComics);
        Mockito.verify(mockedView, Mockito.times(0)).showGenericError();
        Mockito.verify(mockedView, Mockito.times(0)).showNoComics();
        Mockito.verify(mockedView, Mockito.times(0)).showError(ArgumentMatchers.anyString());
    }

    @Test
    public void test_WhenViewAttachedAndUseCaseReturnsZeroItems_MustShowEmptyMessage() throws Exception {

        // given
        List<Comic> fakeComics = Collections.emptyList();
        Mockito.doReturn(Observable.just(fakeComics)).when(stubGetComics).execute();

        BigDecimal dummyTotal = Mockito.mock(BigDecimal.class);
        Mockito.doReturn(Observable.just(fakeComics)).when(getComicsFilteredByTotalAmount).execute(ArgumentMatchers.<Comic>anyList(),ArgumentMatchers.any(BigDecimal.class));
        Mockito.doReturn(Observable.just(dummyTotal)).when(calculateTotal).execute(fakeComics);
        Mockito.doReturn(Observable.just(10)).when(countPages).execute(fakeComics);

        // when
        presenter.attach(mockedView);
        presenter.loadComics();

        // then
        Mockito.verify(mockedView, Mockito.times(0)).showComics(ArgumentMatchers.<ListComic>anyList());
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
        Mockito.verify(mockedView, Mockito.times(0)).showComics(ArgumentMatchers.<ListComic>anyList());
        Mockito.verify(mockedView, Mockito.times(1)).showGenericError();
        Mockito.verify(mockedView, Mockito.times(0)).showNoComics();
        Mockito.verify(mockedView, Mockito.times(0)).showError(ArgumentMatchers.anyString());
    }
}
