package comics.com.app.domain.usecases.details;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.repositories.DetailedComicRepository;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

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
        String  dummyId = "wkhfwu";
        Comic fakeComic = Mockito.mock(Comic.class);
        Mockito.doReturn(Observable.just(fakeComic)).when(stubRepository).comic(dummyId);

        // when
        TestObserver<Comic> testObserver = usecase.execute(dummyId).test();

        // then
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(fakeComic);
        testObserver.assertComplete();

    }

    @Test
    public void test_WhenRepositoryReturnError_MustReturnError() throws Exception {

        // given
        Throwable fakeError = Mockito.mock(Throwable.class);
        String dummyId = "ejkwheuk";
        Mockito.doReturn(Observable.error(fakeError)).when(stubRepository).comic(dummyId);

        // when
        TestObserver<Comic> testObserver = usecase.execute(dummyId).test();

        // then
        testObserver.assertError(fakeError);
        testObserver.assertNoValues();

    }

    @Test
    public void test_WhenRepositoryReturnEmpty_MustReturnEmpty() throws Exception {

        // given
        String dummyId = "gwe";
        Mockito.doReturn(Observable.empty()).when(stubRepository).comic(dummyId);

        // when
        TestObserver<Comic> testObserver = usecase.execute(dummyId).test();

        // then
        testObserver.assertNoErrors();
        testObserver.assertNoValues();
        testObserver.assertComplete();

    }

}