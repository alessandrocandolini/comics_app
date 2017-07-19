package comics.com.app.presentation.details;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import comics.com.app.presentation.base.BasePresenter;
import comics.com.app.presentation.base.RxBasePresenter;
import comics.com.app.presentation.list.ViewComic;

/**
 * Created by alessandro.candolini on 27/06/2017.
 */

public class DetailPresenterImpl extends BasePresenter<DetailView>
        implements DetailPresenter {

    @Nullable
    private ViewComic comic;

    @Override
    public void init(@NonNull final ViewComic comic) {
        this.comic =comic;
    }

    @Override
    public void attach(@NonNull DetailView view) {
        super.attach(view);
        if ( comic != null) {
            populate(comic);
        }
    }

    private void populate(@NonNull final ViewComic comic) {
        doOnViewAttached(new OnViewAttachedAction<DetailView>() {
            @Override
            public void execute(@NonNull DetailView view) {
                view.showImage(comic.getThumbnail());
                view.showTitle(comic.getTitle());

                view.showPrice(comic.getPrice());
                view.showPageCount(Integer.toString(comic.getPageCount()));
                List<String> authors = comic.getAuthors();
                if (authors != null && authors.size() > 0) {
                    String allAuthors = "";
                    for (String author : authors) {
                        allAuthors += "\n";
                        allAuthors += author;

                    }
                    view.showAuthor(allAuthors);
                }
                view.showDescription(comic.getDescription());
            }
        });
    }
}
