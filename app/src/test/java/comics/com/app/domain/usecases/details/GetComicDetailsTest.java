package comics.com.app.domain.usecases.details;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Collections;
import java.util.List;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.entities.DetailedComic;
import comics.com.app.domain.repositories.ComicsRepository;
import comics.com.app.domain.repositories.DetailedComicRepository;
import comics.com.app.domain.usecases.list.GetComics;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;

/**
 * Created by alessandro.candolini on 22/06/2017.
 */
public class GetComicDetailsTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    DetailedComicRepository stubRepository;

    @InjectMocks
    GetComicDetails usecase;

    @Test
    public void test_WhenRepositoryReturnListOfComics_MustReturnTheSameList() throws Exception {

        // given
        Comic dummyComic = Mockito.mock(Comic.class);
        DetailedComic fakeDetailedComic = Mockito.mock(DetailedComic.class);
        Mockito.doReturn(Observable.just(fakeDetailedComic)).when(stubRepository).comic(dummyComic);

        // when
        TestObserver<DetailedComic> testObserver = usecase.execute(dummyComic).test();

        // then
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(fakeDetailedComic);
        testObserver.assertComplete();

    }

    @Test
    public void test_WhenRepositoryReturnError_MustReturnError() throws Exception {

        // given
        Throwable fakeError = Mockito.mock(Throwable.class);
        Comic dummyComic = Mockito.mock(Comic.class);
        DetailedComic fakeDetailedComic = Mockito.mock(DetailedComic.class);
        Mockito.doReturn(Observable.error(fakeError)).when(stubRepository).comic(dummyComic);

        // when
        TestObserver<DetailedComic> testObserver = usecase.execute(dummyComic).test();

        // then
        testObserver.assertError(fakeError);
        testObserver.assertNoValues();

    }

    @Test
    public void test_WhenRepositoryReturnEmpty_MustReturnEmpty() throws Exception {

        // given
        Comic dummyComic = Mockito.mock(Comic.class);
        Mockito.doReturn(Observable.empty()).when(stubRepository).comic(dummyComic);

        // when
        TestObserver<DetailedComic> testObserver = usecase.execute(dummyComic).test();

        // then
        testObserver.assertNoErrors();
        testObserver.assertNoValues();
        testObserver.assertComplete();

    }

}