package comics.com.app.domain.usecases.details;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.entities.DetailedComic;
import comics.com.app.domain.repositories.DetailedComicRepository;
import io.reactivex.Observable;

/**
 * Usecase to get the full details {@link DetailedComic}, given the basic information in {@link Comic}
 * Created by alessandro.candolini on 22/06/2017.
 */

public class GetComicDetails {

    @NonNull
    private final DetailedComicRepository detailedComicRepository;

    @Inject
    public GetComicDetails(@NonNull DetailedComicRepository detailedComicRepository) {
        this.detailedComicRepository = detailedComicRepository;
    }

    public Observable<DetailedComic> execute(@NonNull Comic comic) {
        return detailedComicRepository.comic(comic);
    }

}
