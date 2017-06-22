package comics.com.app.domain.utilities;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.entities.Price;

/**
 * Created by alessandro.candolini on 22/06/2017.
 */
public class TotalPriceCalculatorTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    TotalPriceCalculator totalPriceCalculator;

    @Test
    public void test_WhenListHasItemsWithPrice_MustReturnCorrectTotal() throws Exception {

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
        BigDecimal total = totalPriceCalculator.calculateTotal(list);

        // then
        Assert.assertEquals(0, expectedTotal.compareTo(total));

    }

    @Test
    public void test_WhenListHasOneIteWithPrice_MustReturnPriceOfItem() throws Exception {

        // given

        BigDecimal fakeAmount = BigDecimal.valueOf(5);
        Comic fakeComic = Mockito.mock(Comic.class);
        Price fakePrice = Mockito.mock(Price.class);
        Mockito.doReturn(fakePrice).when(fakeComic).price();
        Mockito.doReturn(fakeAmount).when(fakePrice).amount();

        List<Comic> list = Collections.singletonList(fakeComic);

        // when
        BigDecimal total = totalPriceCalculator.calculateTotal(list);

        // then
        Assert.assertEquals(0, fakeAmount.compareTo(total));

    }


    @Test
    public void test_WhenListHasItemsWithoutPrice_MustReturnCorrectTotalSkippingThoseitems() throws Exception {

        // given

        BigDecimal fakeAmount = BigDecimal.valueOf(5);

        Comic fakeComic1 = Mockito.mock(Comic.class);
        Comic fakeComic2 = Mockito.mock(Comic.class);
        Price fakePrice2 = Mockito.mock(Price.class);
        Mockito.doReturn(fakePrice2).when(fakeComic2).price();
        Mockito.doReturn(fakeAmount).when(fakePrice2).amount();

        List<Comic> list = new ArrayList<>();
        list.add(fakeComic1);
        list.add(fakeComic2);

        // when
        BigDecimal total = totalPriceCalculator.calculateTotal(list);

        // then
        Assert.assertEquals(0, fakeAmount.compareTo(total));

    }

}