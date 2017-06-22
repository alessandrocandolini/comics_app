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
import comics.com.app.domain.utilities.PageCounter;
import io.reactivex.observers.TestObserver;

/**
 * Created by alessandro.candolini on 22/06/2017.
 */
public class RxCountPagesTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    RxCountPages usecase;

    @Test
    public void test_WhenComicsHavePages_MustReturnSumOfAllThePages() throws Exception {

        // given
        int dummyNumberOfPages1 = 74;
        Comic fakeComic1 = Mockito.mock(Comic.class);
        Mockito.doReturn(dummyNumberOfPages1).when(fakeComic1).pageCount();

        int dummyNumberOfPages2 = 1724;
        Comic fakeComic2 = Mockito.mock(Comic.class);
        Mockito.doReturn(dummyNumberOfPages2).when(fakeComic2).pageCount();

        List<Comic> fakeList = new ArrayList<>();
        fakeList.add(fakeComic1);
        fakeList.add(fakeComic2);
        int expectedTotalNumberOfPages = dummyNumberOfPages1 + dummyNumberOfPages2;

        // when
        TestObserver<Integer> testObserver = usecase.execute(fakeList).test();

        // then
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(expectedTotalNumberOfPages);
        testObserver.assertComplete();

    }

}