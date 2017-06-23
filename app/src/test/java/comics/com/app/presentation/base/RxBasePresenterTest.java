package comics.com.app.presentation.base;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;


/**
 * Created by alessandro.candolini on 22/06/2017.
 */
public class RxBasePresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    ScheduleOn ScheduleOn;

    @InjectMocks
    RxBasePresenter presenter;

    @Test
    public void test_WhenAbort_MustUnsubscribeAllRegisteredSubscriptions() throws Exception {

        // given
        Observable<String> fakeObservable = Observable.just("dwjkwui");
        TestObserver observer = fakeObservable.test();
        presenter.addToAutoUnsubscribe(observer);

        // when
        presenter.abort();

        // then
        Assert.assertTrue(observer.isDisposed());


    }

    @Test
    public void test_WhenNotAbort_MustIgnoreUNRegisteredSubscriptions() throws Exception {

        // given
        Observable<String> fakeObservable = Observable.just("dwjkwui");
        TestObserver observer = fakeObservable.test();
        presenter.addToAutoUnsubscribe(observer);

        // when
        presenter.detach();

        // then
        Assert.assertFalse(observer.isDisposed());


    }

    @Test
    public void test_WhenNotAddedToAutoUnsubscribe_MustIgnoreUNRegisteredSubscriptions() throws Exception {

        // given
        Observable<String> fakeObservable = Observable.just("dwjkwui");
        TestObserver observer = fakeObservable.test();

        // when
        presenter.abort();

        // then
        Assert.assertFalse(observer.isDisposed());


    }

}