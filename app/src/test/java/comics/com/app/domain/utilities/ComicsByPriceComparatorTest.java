package comics.com.app.domain.utilities;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.entities.Price;

/**
 * Created by alessandro.candolini on 22/06/2017.
 */
public class ComicsByPriceComparatorTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    ComicsByPriceComparator comparator;

    @Test
    public void test_WhenComicsAreNull_MustReturn0() throws Exception {

        // given
        Comic fakeComic1 = null;
        Comic fakeComic2 = null;

        // given
        int comparison = comparator.compare(fakeComic1,fakeComic2);

        // then
        Assert.assertEquals(0,comparison);

    }

    @Test
    public void test_WhenFirstComicIsNull_MustReturn0() throws Exception {

        // given
        Comic fakeComic1 = null;
        Comic fakeComic2 = Mockito.mock(Comic.class);
        Price fakePrice = Mockito.mock(Price.class);
        BigDecimal bigDecimal = BigDecimal.valueOf(5);
        Mockito.doReturn(bigDecimal).when(fakePrice).amount();
        Mockito.doReturn(fakePrice).when(fakeComic2).price();

        // given
        int comparison = comparator.compare(fakeComic1,fakeComic2);

        // then
        Assert.assertEquals(0,comparison);

    }

    @Test
    public void test_WhenSecondComicIsNull_MustReturn0() throws Exception {

        // given
        Comic fakeComic1 = null;
        Comic fakeComic2 = Mockito.mock(Comic.class);
        Price fakePrice = Mockito.mock(Price.class);
        BigDecimal bigDecimal = BigDecimal.valueOf(5);
        Mockito.doReturn(bigDecimal).when(fakePrice).amount();
        Mockito.doReturn(fakePrice).when(fakeComic2).price();

        // given
        int comparison = comparator.compare(fakeComic2,fakeComic1);

        // then
        Assert.assertEquals(0,comparison);

    }

    @Test
    public void test_WhenFirstComicHasNullPrice_MustReturn0() throws Exception {

        // given
        Comic fakeComic1 = Mockito.mock(Comic.class);
        Comic fakeComic2 = Mockito.mock(Comic.class);
        Price fakePrice = Mockito.mock(Price.class);
        BigDecimal bigDecimal = BigDecimal.valueOf(5);
        Mockito.doReturn(bigDecimal).when(fakePrice).amount();
        Mockito.doReturn(fakePrice).when(fakeComic2).price();

        // given
        int comparison = comparator.compare(fakeComic1,fakeComic2);

        // then
        Assert.assertEquals(0,comparison);

    }

    @Test
    public void test_WhenSecondComicHasNullPrice_MustReturn0() throws Exception {

        // given
        Comic fakeComic1 = Mockito.mock(Comic.class);;
        Comic fakeComic2 = Mockito.mock(Comic.class);
        Price fakePrice = Mockito.mock(Price.class);
        BigDecimal bigDecimal = BigDecimal.valueOf(5);
        Mockito.doReturn(bigDecimal).when(fakePrice).amount();
        Mockito.doReturn(fakePrice).when(fakeComic2).price();

        // given
        int comparison = comparator.compare(fakeComic2,fakeComic1);

        // then
        Assert.assertEquals(0,comparison);

    }

    @Test
    public void test_WhenFirstComicHasNullAmount_MustReturn0() throws Exception {

        // given
        Comic fakeComic1 = Mockito.mock(Comic.class);
        Comic fakeComic2 = Mockito.mock(Comic.class);
        Price fakePrice1 = Mockito.mock(Price.class);
        Price fakePrice2 = Mockito.mock(Price.class);
        Mockito.doReturn(fakePrice1).when(fakeComic1).price();
        Mockito.doReturn(fakePrice2).when(fakeComic2).price();
        BigDecimal bigDecimal = BigDecimal.valueOf(5);
        Mockito.doReturn(bigDecimal).when(fakePrice2).amount();

        // given
        int comparison = comparator.compare(fakeComic1,fakeComic2);

        // then
        Assert.assertEquals(0,comparison);

    }

    @Test
    public void test_WhenSecondComicHasNullAmount_MustReturn0() throws Exception {

        // given
        Comic fakeComic1 = Mockito.mock(Comic.class);
        Comic fakeComic2 = Mockito.mock(Comic.class);
        Price fakePrice1 = Mockito.mock(Price.class);
        Price fakePrice2 = Mockito.mock(Price.class);
        Mockito.doReturn(fakePrice1).when(fakeComic1).price();
        Mockito.doReturn(fakePrice2).when(fakeComic2).price();
        BigDecimal bigDecimal = BigDecimal.valueOf(5);
        Mockito.doReturn(bigDecimal).when(fakePrice2).amount();

        // given
        int comparison = comparator.compare(fakeComic2,fakeComic1);

        // then
        Assert.assertEquals(0,comparison);

    }

    @Test
    public void test_WhenFirstComicHasAmountGreaterThanTheSecond_MustReturnOne() throws Exception {

        // given
        Comic fakeComic1 = Mockito.mock(Comic.class);
        Comic fakeComic2 = Mockito.mock(Comic.class);
        Price fakePrice1 = Mockito.mock(Price.class);
        Price fakePrice2 = Mockito.mock(Price.class);
        Mockito.doReturn(fakePrice1).when(fakeComic1).price();
        Mockito.doReturn(fakePrice2).when(fakeComic2).price();
        BigDecimal fakeAmount1 = BigDecimal.valueOf(5);
        BigDecimal fakeAmount2 = BigDecimal.valueOf(2);
        Mockito.doReturn(fakeAmount1).when(fakePrice1).amount();
        Mockito.doReturn(fakeAmount2).when(fakePrice2).amount();

        // given
        int comparison = comparator.compare(fakeComic1,fakeComic2);

        // then
        Assert.assertEquals(1,comparison);

    }

    @Test
    public void test_WhenSecondComicHasAmountGreaterThanFirst_MustReturnMinusOne() throws Exception {

        // given
        Comic fakeComic1 = Mockito.mock(Comic.class);
        Comic fakeComic2 = Mockito.mock(Comic.class);
        Price fakePrice1 = Mockito.mock(Price.class);
        Price fakePrice2 = Mockito.mock(Price.class);
        Mockito.doReturn(fakePrice1).when(fakeComic1).price();
        Mockito.doReturn(fakePrice2).when(fakeComic2).price();
        BigDecimal fakeAmount1 = BigDecimal.valueOf(5);
        BigDecimal fakeAmount2 = BigDecimal.valueOf(2);
        Mockito.doReturn(fakeAmount1).when(fakePrice1).amount();
        Mockito.doReturn(fakeAmount2).when(fakePrice2).amount();

        // given
        int comparison = comparator.compare(fakeComic2,fakeComic1);

        // then
        Assert.assertEquals(-1,comparison);

    }
}