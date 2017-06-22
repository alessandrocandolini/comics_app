package comics.com.app.domain.usecases.list;

import android.support.annotation.NonNull;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.utilities.ExtractItemsByTotalAmount;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;

/**
 * Created by alessandro.candolini on 22/06/2017.
 */
public class NonRxGetComicsFilteredByTotalAmountTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    ExtractItemsByTotalAmount extractItemsByTotalAmount;

    @InjectMocks
    NonRxGetComicsFilteredByTotalAmount usecase;

    @Test
    public void test_WhenExtractItemsByTotalAmountReturnsList_MustReturnSameList() throws Exception {

        // given
        BigDecimal dummyThreshold = Mockito.mock(BigDecimal.class);
        List<Comic> dummyList = new ArrayList<>();
        List<Comic> fakeOutput = new ArrayList<>();
        Mockito.doReturn(fakeOutput).when(extractItemsByTotalAmount).filterListByTotalAmount(dummyList,dummyThreshold);

        // when
        TestObserver<List<Comic>> testObserver = usecase.execute(dummyList,dummyThreshold).test();

        // then
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(fakeOutput);
        testObserver.assertComplete();

    }

}