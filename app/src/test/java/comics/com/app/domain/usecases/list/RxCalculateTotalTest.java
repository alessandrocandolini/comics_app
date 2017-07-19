package comics.com.app.domain.usecases.list;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.entities.Price;
import io.reactivex.observers.TestObserver;

/**
 * Created by alessandro.candolini on 23/06/2017.
 */
public class RxCalculateTotalTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    RxCalculateTotal usecase;

    @Test
    public void test_WhenComicsHavePrices_MustReturnTotal() throws Exception {

        // given

        BigDecimal fakeAmount1 = BigDecimal.valueOf(5);
        BigDecimal fakeAmount2 = BigDecimal.valueOf(2);
        BigDecimal expectedTotal = BigDecimal.valueOf(7);

        Comic fakeComic1 = Mockito.mock(Comic.class);
        Comic fakeComic2 = Mockito.mock(Comic.class);
        Price fakePrice1 = Mockito.mock(Price.class);
        Price fakePrice2 = Mockito.mock(Price.class);
        Mockito.doReturn(fakePrice1).when(fakeComic1).price();
        Mockito.doReturn(fakePrice2).when(fakeComic2).price();

        Mockito.doReturn(fakeAmount1).when(fakePrice1).amount();
        Mockito.doReturn(fakeAmount2).when(fakePrice2).amount();

        List<Comic> list = new ArrayList<>();
        list.add(fakeComic1);
        list.add(fakeComic2);

        // when
        TestObserver<BigDecimal> testObserver = usecase.execute(list).test();

        // then
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(expectedTotal);
        testObserver.assertComplete();

    }

    @Test
    public void test_WhenSomeComicsHaveNoPrice_MustReturnTotalWithoutThoseItems() throws Exception {

        // given

        BigDecimal fakeAmount = BigDecimal.valueOf(5);

        Comic fakeComic1 = Mockito.mock(Comic.class);
        Comic fakeComic2 = Mockito.mock(Comic.class);
        Price fakePrice1 = Mockito.mock(Price.class);
        Price fakePrice2 = Mockito.mock(Price.class);
        Mockito.doReturn(fakePrice1).when(fakeComic1).price();
        Mockito.doReturn(fakePrice2).when(fakeComic2).price();

        Mockito.doReturn(fakeAmount).when(fakePrice1).amount();

        List<Comic> list = new ArrayList<>();
        list.add(fakeComic1);
        list.add(fakeComic2);

        // when
        TestObserver<BigDecimal> testObserver = usecase.execute(list).test();

        // then
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(fakeAmount);
        testObserver.assertComplete();

    }

    @Test
    public void test_WhenAllComicsHaveNoPrice_MustReturnZero() throws Exception {

        // given

        Comic fakeComic1 = Mockito.mock(Comic.class);
        Comic fakeComic2 = Mockito.mock(Comic.class);

        List<Comic> list = new ArrayList<>();
        list.add(fakeComic1);
        list.add(fakeComic2);

        // when
        TestObserver<BigDecimal> testObserver = usecase.execute(list).test();

        // then
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(BigDecimal.ZERO);
        testObserver.assertComplete();

    }

}