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
 * Speed test, can be machine dependent but the set of data is such that it shoudl lead to errors if the
 * implementation keeps looping through all the list
 * Created by alessandro.candolini on 22/06/2017.
 */
public class ExtractItemsByTotalAmountSpeedTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    ExtractItemsByTotalAmount extractItemsByTotalAmount;

    List<Comic> veryLongList;


    @Before
    public void setUp() throws Exception {
        // prepare very long list outside the test to not impact the timeout
        veryLongList = new ArrayList<>();
        BigDecimal fakeAmount = BigDecimal.valueOf(5);
        Price fakePrice = Mockito.mock(Price.class);
        Mockito.doReturn(fakeAmount).when(fakePrice).amount();
        for ( int loop = 0; loop < 10000; loop++) {
            Comic fakeComic = Mockito.mock(Comic.class);
            Mockito.doReturn(fakePrice).when(fakeComic).price();
            veryLongList.add(fakeComic);
        }

    }

    @Test(timeout = 100)
    public void test_WhenListIsLongAndThresholdIsLow_MustBeEfficient() throws Exception {

        // given
        BigDecimal threshold = BigDecimal.valueOf(15);

        // when
        List<Comic> output = extractItemsByTotalAmount.filterListByTotalAmount(veryLongList,threshold);

        // then
        Assert.assertEquals(3, output.size());

    }
}