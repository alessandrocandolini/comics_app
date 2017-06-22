package comics.com.app.domain.utilities;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.entities.DetailedComic;

import static org.junit.Assert.*;

/**
 * Created by alessandro.candolini on 22/06/2017.
 */
public class PageCounterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    PageCounter pageCounter;

    @Test
    public void test_WhenListIsEmpty_MustReturnZero() throws Exception {

        // given
        List<Comic> emptyList = Collections.emptyList();

        // when
        int pages = pageCounter.countPages(emptyList);

        // then
        Assert.assertEquals(0,pages);

    }

    @Test
    public void test_WhenListContainsOneElement_MustReturnNumberOfPagesOfThatElement() throws Exception {

        // given
        int dummyNumberOfPages = 74;
        Comic fakeComic = Mockito.mock(Comic.class);
        Mockito.doReturn(dummyNumberOfPages).when(fakeComic).pageCount();
        List<Comic> fakeList = Collections.singletonList(fakeComic);

        // when
        int pages = pageCounter.countPages(fakeList);

        // then
        Assert.assertEquals(dummyNumberOfPages,pages);

    }

    @Test
    public void test_WhenListContainsMoreThanOneElement_MustReturnTotalNumberOfPages() throws Exception {

        // given
        int dummyNumberOfPages1 = 74;
        Comic fakeComic1 = Mockito.mock(Comic.class);
        Mockito.doReturn(dummyNumberOfPages1).when(fakeComic1).pageCount();

        int dummyNumberOfPages2 = 1724;
        Comic fakeComic2 = Mockito.mock(Comic.class);
        Mockito.doReturn(dummyNumberOfPages2).when(fakeComic2).pageCount();

        List<Comic> fakeList = new ArrayList<>();
        fakeList.add(fakeComic1);
        fakeList.add(fakeComic2);
        int expectedTotalNumberOfPages = dummyNumberOfPages1 + dummyNumberOfPages2;

        // when
        int pages = pageCounter.countPages(fakeList);

        // then
        Assert.assertEquals(expectedTotalNumberOfPages,pages);

    }

    @Test
    public void test_WhenListContainsOneDetailComic_MustReturnNumberOfPagesOfThatElement() throws Exception {

        // given
        int dummyNumberOfPages = 74;
        DetailedComic fakeComic = Mockito.mock(DetailedComic.class);
        Mockito.doReturn(dummyNumberOfPages).when(fakeComic).pageCount();
        List<DetailedComic> fakeList = Collections.singletonList(fakeComic);

        // when
        int pages = pageCounter.countPages(fakeList);

        // then
        Assert.assertEquals(dummyNumberOfPages,pages);

    }
}