package comics.com.app.domain.usecases.list;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import comics.com.app.domain.entities.Comic;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

/**
 * Created by alessandro.candolini on 22/06/2017.
 */
public class MaxListOfComincsWithGivenAmountTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    GetComicsFilteredByTotalAmount getComicsFilteredByTotalAmount;

    @Mock
    SortComicsByPrice sortComicsByPrice;

    @InjectMocks
    MaxListOfComincsWithGivenAmount usecase;

    @Test
    public void test_WhenSortingAndFilteringReturnsLists_MustReturnListConsistently() throws Exception {

        // given
        BigDecimal dummyThreshold = Mockito.mock(BigDecimal.class);
        List<Comic> fakeOriginalList = Collections.emptyList();
        List<Comic> fakeSortedList = Collections.emptyList();
        List<Comic> fakeFilteredList = Collections.emptyList();
        Mockito.doReturn(Observable.just(fakeSortedList)).when(sortComicsByPrice).execute(fakeOriginalList);
        Mockito.doReturn(Observable.just(fakeFilteredList)).when(getComicsFilteredByTotalAmount).execute(fakeSortedList,dummyThreshold);

        // when
        TestObserver<List<Comic>> testObserver = usecase.execute(fakeOriginalList,dummyThreshold).test();

        // then
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(fakeFilteredList);
        testObserver.assertComplete();

    }
}