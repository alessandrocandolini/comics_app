package comics.com.app.domain.usecases.list;

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
import comics.com.app.domain.repositories.ComicsRepository;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

/**
 * Created by alessandro.candolini on 22/06/2017.
 */
public class GetComicsTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    ComicsRepository stubRepository;

    @InjectMocks
    GetComics usecase;

    @Test
    public void test_WhenRepositoryReturnListOfComics_MustReturnTheSameList() throws Exception {

        // given
        Comic fakeComic = Mockito.mock(Comic.class);
        List<Comic> fakeComics = Collections.singletonList(fakeComic);
        Mockito.doReturn(Observable.just(fakeComics)).when(stubRepository).comics(ArgumentMatchers.anyInt());

        // when
        TestObserver<List<? extends Comic>> testObserver = usecase.execute().test();

        // then
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(fakeComics);
        testObserver.assertComplete();

    }

    @Test
    public void test_WhenRepositoryReturnError_MustReturnError() throws Exception {

        // given
        Throwable fakeError = Mockito.mock(Throwable.class);
        Mockito.doReturn(Observable.error(fakeError)).when(stubRepository).comics(ArgumentMatchers.anyInt());

        // when
        TestObserver<List<? extends Comic>> testObserver = usecase.execute().test();

        // then
        testObserver.assertError(fakeError);
        testObserver.assertNoValues();

    }

    @Test
    public void test_WhenRepositoryReturnEmpty_MustReturnEmpty() throws Exception {

        // given
        Mockito.doReturn(Observable.empty()).when(stubRepository).comics(ArgumentMatchers.anyInt());

        // when
        TestObserver<List<? extends Comic>> testObserver = usecase.execute().test();

        // then
        testObserver.assertNoErrors();
        testObserver.assertNoValues();
        testObserver.assertComplete();

    }
}