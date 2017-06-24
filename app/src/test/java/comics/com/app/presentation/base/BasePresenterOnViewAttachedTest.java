package comics.com.app.presentation.base;

import android.support.annotation.NonNull;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * Created by alessandro.candolini on 22/06/2017.
 */
public class BasePresenterOnViewAttachedTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    interface FakeView extends View {
        void doSomething();
    }

    @InjectMocks
    BasePresenter<FakeView> presenter;

    @Test
    public void test_WhenExecutingViewMethodAndViewIsAttached_MustExecuteViewMethod() throws Exception {

        // given
        FakeView fakeView = Mockito.mock(FakeView.class);

        // when
        presenter.attach(fakeView);
        presenter.doOnViewAttached(new BasePresenter.OnViewAttachedAction<FakeView>() {
            @Override
            public void execute(@NonNull FakeView view) {
                view.doSomething();
            }
        });

        // then
        Mockito.verify(fakeView, Mockito.times(1)).doSomething();

    }

    @Test
    public void test_WhenExecutingViewMethodAndViewIsNotAttached_MustNotExecuteViewMethod() throws Exception {

        // given
        FakeView fakeView = Mockito.mock(FakeView.class);

        // when
        presenter.attach(fakeView);
        presenter.detach();
        presenter.doOnViewAttached(new BasePresenter.OnViewAttachedAction<FakeView>() {
            @Override
            public void execute(@NonNull FakeView view) {
                view.doSomething();
            }
        });

        // then
        Mockito.verify(fakeView, Mockito.times(0)).doSomething();

    }


}