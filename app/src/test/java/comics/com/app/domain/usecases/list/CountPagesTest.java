package comics.com.app.domain.usecases.list;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.utilities.PageCounter;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;

/**
 * Created by alessandro.candolini on 22/06/2017.
 */
public class CountPagesTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    PageCounter pageCounter;

    @InjectMocks
    CountPages usecase;

    @Test
    public void test_WhenPageCounterReturnsNumber_MustReturnNumber() throws Exception {

        // given
        int pages = 882;
        List<Comic> dummyList = new ArrayList<>();
        Mockito.doReturn(pages).when(pageCounter).countPages(dummyList);

        // when
        TestObserver<Integer> testObserver = usecase.execute(dummyList).test();

        // then
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(pages);
        testObserver.assertComplete();

    }

    @Test
    public void test_WhenUnexpectedExceptionOccursInPageCounter_MustReturnZeroWithoutErrors() throws Exception {

        // given
        List<Comic> dummyList = new ArrayList<>();
        RuntimeException fakeError = Mockito.mock(RuntimeException.class);
        Mockito.doThrow(fakeError).when(pageCounter).countPages(dummyList);

        // when
        TestObserver<Integer> testObserver = usecase.execute(dummyList).test();

        // then
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(0);
        testObserver.assertComplete();

    }
}