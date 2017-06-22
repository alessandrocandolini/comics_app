package comics.com.app.domain.usecases.list;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import comics.com.app.domain.entities.Comic;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static io.reactivex.Observable.just;


/**
 * Created by alessandro.candolini on 22/06/2017.
 */
public class CountPagesDoNotThrowErrorTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    CountPages countPages;

    @InjectMocks
    CountPagesDoNotThrowError usecase;

    @Test
    public void test_WhenBaseUseCaseReturnNumberOfPages_MustReturnSameNumberOfPages() throws Exception {

        // given
        int fakeNumberOfPages = 77;
        List<Comic> dummyList = new ArrayList<>();
        Mockito.doReturn(Observable.just(fakeNumberOfPages)).when(countPages).execute(dummyList);

        // when
        TestObserver<Integer> testObserver = usecase.execute(dummyList).test();

        // then
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(fakeNumberOfPages);
        testObserver.assertComplete();

    }

    @Test
    public void test_WhenUnexpectedExceptionOccursInPageCounter_MustReturnZeroWithoutErrors() throws Exception {

        // given
        List<Comic> dummyList = new ArrayList<>();
        Throwable fakeError = Mockito.mock(Throwable.class);
        Mockito.doReturn(Observable.error(fakeError)).when(countPages).execute(dummyList);

        // when
        TestObserver<Integer> testObserver = usecase.execute(dummyList).test();

        // then
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(0);
        testObserver.assertComplete();

    }

}