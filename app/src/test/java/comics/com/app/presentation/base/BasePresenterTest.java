package comics.com.app.presentation.base;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import comics.com.app.domain.usecases.list.CountPages;
import comics.com.app.domain.usecases.list.CountPagesDoNotThrowError;

import static org.junit.Assert.*;

/**
 * Created by alessandro.candolini on 22/06/2017.
 */
public class BasePresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    BasePresenter<View> presenter;

    @Test
    public void test_BeforeBinding_MustBeNotBound() throws Exception {

        // given
        View dummyView = Mockito.mock(View.class);

        // when

        // then
        Assert.assertFalse(presenter.isBound());

    }

    @Test
    public void test_AfterBinding_MustBeBound() throws Exception {

        // given
        View dummyView = Mockito.mock(View.class);

        // when
        presenter.bind(dummyView);

        // then
        Assert.assertTrue(presenter.isBound());

    }

    @Test
    public void test_AfterUnbinding_MustBeNotBound() throws Exception {

        // given
        View dummyView = Mockito.mock(View.class);

        // when
        presenter.bind(dummyView);
        presenter.unbind();

        // then
        Assert.assertFalse(presenter.isBound());

    }
}