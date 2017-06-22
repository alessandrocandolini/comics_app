package comics.com.app.domain.utilities;

import android.support.annotation.NonNull;

import java.util.List;

import comics.com.app.domain.entities.Comic;

/**
 * Created by alessandro.candolini on 22/06/2017.
 */

public class PageCounter {

    /**
     * @param comics List of comics
     *
     * @return Total number of pages of the comics in the list
     */
    public int countPages(@NonNull List<? extends Comic> comics) {
        int accumulator = 0;
        for (Comic comic : comics) {
            accumulator += comic.pageCount();
        }
        return accumulator;
    }

}
