package comics.com.app.domain.usecases.list;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import comics.com.app.domain.entities.Comic;
import io.reactivex.observers.TestObserver;

/**
 * Created by alessandro.candolini on 22/06/2017.
 */
public class SortComicsByPriceTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    Comparator<Comic> comparator;

    @InjectMocks
    SortComicsByPrice usecase;

    @Test
    public void test_WhenListIsSorted_MustReturnListInTheSameOrder() throws Exception {

        // given
        Comic fakeComic1 = Mockito.mock(Comic.class);
        Comic fakeComic2 = Mockito.mock(Comic.class);
        String fakeTitle1 = "wehfiuew";
        String fakeTitle2 = "feihfuweww";
        Mockito.doReturn(fakeTitle1).when(fakeComic1).title();
        Mockito.doReturn(fakeTitle2).when(fakeComic2).title();

        List<Comic> list = new ArrayList<>();
        list.add(fakeComic1);
        list.add(fakeComic2);

        Assert.assertEquals("Error in setup of the list", fakeTitle1,list.get(0).title());
        Assert.assertEquals("Error in setup of the list", fakeTitle2,list.get(1).title());

        Mockito.doReturn(1).when(comparator).compare(fakeComic2,fakeComic1); // price2 > price1

        // when
        TestObserver<List<Comic>> testObserver = usecase.execute(list).test();

        // then
        testObserver.assertNoErrors();
        testObserver.assertComplete();
        testObserver.assertValueCount(1);
        Assert.assertEquals(fakeTitle1,testObserver.values().get(0).get(0).title());
        Assert.assertEquals(fakeTitle2,testObserver.values().get(0).get(1).title());

    }

    @Test
    public void test_WhenListIsNotSorted_MustReturnListInTheAscendingOrder() throws Exception {

        // given
        Comic fakeComic1 = Mockito.mock(Comic.class);
        Comic fakeComic2 = Mockito.mock(Comic.class);
        String fakeTitle1 = "wehfiuew";
        String fakeTitle2 = "feihfuweww";
        Mockito.doReturn(fakeTitle1).when(fakeComic1).title();
        Mockito.doReturn(fakeTitle2).when(fakeComic2).title();

        List<Comic> list = new ArrayList<>();
        list.add(fakeComic1);
        list.add(fakeComic2);

        Assert.assertEquals("Error in setup of the list", fakeTitle1,list.get(0).title());
        Assert.assertEquals("Error in setup of the list", fakeTitle2,list.get(1).title());

        Mockito.doReturn(-1).when(comparator).compare(fakeComic2,fakeComic1); // price2 < price1

        // when
        TestObserver<List<Comic>> testObserver = usecase.execute(list).test();

        // then
        testObserver.assertNoErrors();
        testObserver.assertComplete();
        testObserver.assertValueCount(1);
        Assert.assertEquals(fakeTitle2,testObserver.values().get(0).get(0).title());
        Assert.assertEquals(fakeTitle1,testObserver.values().get(0).get(1).title());

    }

    @Test
    public void test_WhenComparatorThrowsException_MustReturnError() throws Exception {

        // given
        Comic fakeComic1 = Mockito.mock(Comic.class);
        Comic fakeComic2 = Mockito.mock(Comic.class);

        List<Comic> list = new ArrayList<>();
        list.add(fakeComic1);
        list.add(fakeComic2);

        RuntimeException fakeError = Mockito.mock(RuntimeException.class);

        Mockito.doThrow(fakeError).when(comparator).compare(fakeComic2,fakeComic1);

        // when
        TestObserver<List<Comic>> testObserver = usecase.execute(list).test();

        // then
        testObserver.assertError(fakeError);
        testObserver.assertValueCount(0);

    }
}