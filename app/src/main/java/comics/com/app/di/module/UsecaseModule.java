package comics.com.app.di.module;

import java.util.Comparator;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.usecases.list.CountPages;
import comics.com.app.domain.usecases.list.RxCountPages;
import comics.com.app.domain.utilities.ComicsByPriceComparator;
import dagger.Module;
import dagger.Provides;

/**
 * Provides actual implemnetation for utilities that have an interface.
 * For utilities that have just the concrete implementation class and no inyerface associated with
 * their behavior, eg, {@link comics.com.app.domain.utilities.PageCounter}, we rely on inject
 * constructor.
 * Created by alessandro.candolini on 22/06/2017.
 */
@Module
public class UsecaseModule {

    @Provides
    CountPages providesCountPages() {
        return new RxCountPages();
    }
}
