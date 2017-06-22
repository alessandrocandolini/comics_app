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
 * Integration test to sort a list using the {@link ComicsByPriceComparator}.
 * For unit tests of the comparator, see {@link ComicsByPriceComparatorTest}.
 * Created by alessandro.candolini on 22/06/2017.
 */
public class ComicsByPriceComparatorIntegrationTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    ComicsByPriceComparator comparator;

    @Test
    public void test_WhenListIsNotSorted_MustReturnSortedInAscendingOrder() throws Exception {

        // given
        Comic fakeComic1 = Mockito.mock(Comic.class);
        Comic fakeComic2 = Mockito.mock(Comic.class);
        String fakeTitle1 = "wehfiuew";
        String fakeTitle2 = "feihfuweww";
        Mockito.doReturn(fakeTitle1).when(fakeComic1).title();
        Mockito.doReturn(fakeTitle2).when(fakeComic2).title();
        Price fakePrice1 = Mockito.mock(Price.class);
        Price fakePrice2 = Mockito.mock(Price.class);
        Mockito.doReturn(fakePrice1).when(fakeComic1).price();
        Mockito.doReturn(fakePrice2).when(fakeComic2).price();
        BigDecimal fakeAmount1 = BigDecimal.valueOf(5);
        BigDecimal fakeAmount2 = BigDecimal.valueOf(2);
        Mockito.doReturn(fakeAmount1).when(fakePrice1).amount();
        Mockito.doReturn(fakeAmount2).when(fakePrice2).amount();

        List<Comic> list = new ArrayList<>();
        list.add(fakeComic1);
        list.add(fakeComic2);

        Assert.assertEquals("Error in setup of the list", fakeTitle1,list.get(0).title());
        Assert.assertEquals("Error in setup of the list", fakeTitle2,list.get(1).title());

        // when
        Collections.sort(list,comparator);

        // then
        Assert.assertEquals(fakeTitle2,list.get(0).title());
        Assert.assertEquals(fakeTitle1,list.get(1).title());

    }

    @Test
    public void test_WhenListIsSorted_MustReturnSameOrder() throws Exception {

        // given
        Comic fakeComic1 = Mockito.mock(Comic.class);
        Comic fakeComic2 = Mockito.mock(Comic.class);
        String fakeTitle1 = "wehfiuew";
        String fakeTitle2 = "feihfuweww";
        Mockito.doReturn(fakeTitle1).when(fakeComic1).title();
        Mockito.doReturn(fakeTitle2).when(fakeComic2).title();
        Price fakePrice1 = Mockito.mock(Price.class);
        Price fakePrice2 = Mockito.mock(Price.class);
        Mockito.doReturn(fakePrice1).when(fakeComic1).price();
        Mockito.doReturn(fakePrice2).when(fakeComic2).price();
        BigDecimal fakeAmount1 = BigDecimal.valueOf(2);
        BigDecimal fakeAmount2 = BigDecimal.valueOf(5.8);
        Mockito.doReturn(fakeAmount1).when(fakePrice1).amount();
        Mockito.doReturn(fakeAmount2).when(fakePrice2).amount();

        List<Comic> list = new ArrayList<>();
        list.add(fakeComic1);
        list.add(fakeComic2);

        Assert.assertEquals("Error in setup of the list", fakeTitle1,list.get(0).title());
        Assert.assertEquals("Error in setup of the list", fakeTitle2,list.get(1).title());

        // when
        Collections.sort(list,comparator);

        // then
        Assert.assertEquals(fakeTitle1,list.get(0).title());
        Assert.assertEquals(fakeTitle2,list.get(1).title());

    }

    @Test
    public void test_WhenListHasItemsWithSameAmout_MustReturnListInAscendingOrderForOtherItems() throws Exception {

        // given
        Comic fakeComic1 = Mockito.mock(Comic.class);
        Comic fakeComic2 = Mockito.mock(Comic.class);
        Comic fakeComic3 = Mockito.mock(Comic.class);
        String fakeTitle1 = "wehfiuew";
        String fakeTitle2 = "feihfuweww";
        String fakeTitle3 = "wehwewgeiywfeuyegwuyfiuew";
        Mockito.doReturn(fakeTitle1).when(fakeComic1).title();
        Mockito.doReturn(fakeTitle2).when(fakeComic2).title();
        Mockito.doReturn(fakeTitle3).when(fakeComic3).title();
        Price fakePrice1 = Mockito.mock(Price.class);
        Price fakePrice2 = Mockito.mock(Price.class);
        Price fakePrice3 = Mockito.mock(Price.class);
        Mockito.doReturn(fakePrice1).when(fakeComic1).price();
        Mockito.doReturn(fakePrice2).when(fakeComic2).price();
        Mockito.doReturn(fakePrice3).when(fakeComic3).price();
        BigDecimal fakeAmount1 = BigDecimal.valueOf(2);
        BigDecimal fakeAmount2 = BigDecimal.valueOf(5.8);
        BigDecimal fakeAmount3 = BigDecimal.valueOf(2);
        Mockito.doReturn(fakeAmount1).when(fakePrice1).amount();
        Mockito.doReturn(fakeAmount2).when(fakePrice2).amount();
        Mockito.doReturn(fakeAmount3).when(fakePrice3).amount();

        List<Comic> list = new ArrayList<>();
        list.add(fakeComic1);
        list.add(fakeComic2);
        list.add(fakeComic3);

        Assert.assertEquals("Error in setup of the list", fakeTitle1,list.get(0).title());
        Assert.assertEquals("Error in setup of the list", fakeTitle2,list.get(1).title());
        Assert.assertEquals("Error in setup of the list", fakeTitle3,list.get(2).title());

        // when
        Collections.sort(list,comparator);

        // then
        Assert.assertEquals(fakeTitle1,list.get(0).title());
        Assert.assertEquals(fakeTitle3,list.get(1).title());
        Assert.assertEquals(fakeTitle2,list.get(2).title());

    }
}