package comics.com.app.domain.utilities;

import junit.framework.Assert;

import org.junit.Before;
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

/**
 * Created by alessandro.candolini on 22/06/2017.
 */
public class ExtractItemsByTotalAmountTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    ExtractItemsByTotalAmount extractItemsByTotalAmount;

    List<Comic> fakeList;

    @Before
    public void setUp() throws Exception {
        fakeList = new ArrayList<>();
        BigDecimal fakeAmount = BigDecimal.valueOf(5);
        Price fakePrice = Mockito.mock(Price.class);
        Mockito.doReturn(fakeAmount).when(fakePrice).amount();
        for ( int loop = 0; loop < 10; loop++) {
            Comic fakeComic = Mockito.mock(Comic.class);
            Mockito.doReturn(fakePrice).when(fakeComic).price();
            fakeList.add(fakeComic);
        }

    }

    @Test
    public void test_WhenListIsLessThanThreshold_MustReturnFullList() throws Exception {

        // given
        BigDecimal threshold = BigDecimal.valueOf(100);

        // when
        List<Comic> output = extractItemsByTotalAmount.filterListByTotalAmount(fakeList,threshold);

        // then
        Assert.assertEquals(fakeList.size(), output.size());

    }

    @Test
    public void test_WhenListIsHigherThanThreshold_MustReturnSubList() throws Exception {

        // given
        BigDecimal threshold = BigDecimal.valueOf(10);

        // when
        List<Comic> output = extractItemsByTotalAmount.filterListByTotalAmount(fakeList,threshold);

        // then
        Assert.assertEquals(2, output.size());

    }

    @Test
    public void test_WhenThresholdIsZero_MustReturnEmptyList() throws Exception {
        // given
        BigDecimal threshold = BigDecimal.valueOf(0);

        // when
        List<Comic> output = extractItemsByTotalAmount.filterListByTotalAmount(fakeList,threshold);

        // then
        Assert.assertEquals(0, output.size());

    }


}