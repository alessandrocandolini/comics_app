package comics.com.app.di.module;

import java.util.Comparator;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.usecases.list.CalculateTotal;
import comics.com.app.domain.usecases.list.CountPages;
import comics.com.app.domain.usecases.list.GetComicsFilteredByTotalAmount;
import comics.com.app.domain.usecases.list.NonRxGetComicsFilteredByTotalAmount;
import comics.com.app.domain.usecases.list.RxCalculateTotal;
import comics.com.app.domain.usecases.list.RxCountPages;
import comics.com.app.domain.utilities.ComicsByPriceComparator;
import comics.com.app.domain.utilities.ExtractItemsByTotalAmount;
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

    @Provides
    CalculateTotal providesCalculateTotal() {
        return new RxCalculateTotal();
    }

    @Provides
    GetComicsFilteredByTotalAmount providesGetComicsFilteredByTotalAmount(ExtractItemsByTotalAmount extractItemsByTotalAmount) {
            return new NonRxGetComicsFilteredByTotalAmount(extractItemsByTotalAmount);
    }
}
