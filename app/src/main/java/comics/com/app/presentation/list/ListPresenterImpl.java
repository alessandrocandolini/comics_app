package comics.com.app.presentation.list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import java.math.BigDecimal;
import java.util.List;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.usecases.list.CalculateTotal;
import comics.com.app.domain.usecases.list.CountPages;
import comics.com.app.domain.usecases.list.GetComics;
import comics.com.app.domain.usecases.list.MaxListOfComincsWithGivenAmount;
import comics.com.app.presentation.base.RxBasePresenter;
import comics.com.app.presentation.base.ScheduleOn;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by alessandro.candolini on 22/06/2017.
 */
public class ListPresenterImpl extends RxBasePresenter<ListView> implements ListPresenter {

    @NonNull
    private final GetComics getComics;

    @NonNull
    private final MaxListOfComincsWithGivenAmount maxListOfComincsWithGivenAmount;

    @NonNull
    private final CalculateTotal calculateTotal;

    @NonNull
    private final CountPages countPages;

    @Nullable
    private List<Comic> cacheInMemory = null;

    /** Keep track of subscription */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    DisposableObserver<ComicInfoDisplay> disposable = null;

    DisposableObserver<BigDecimal> amountDisposable = null;

    @NonNull
    private final ListComicViewMapper listComicViewMapper;

    public ListPresenterImpl(@NonNull ScheduleOn scheduleOn,
                             @NonNull GetComics getComics,
                             @NonNull ListComicViewMapper listComicViewMapper,
                             @NonNull CalculateTotal calculateTotal,
                             @NonNull MaxListOfComincsWithGivenAmount maxListOfComincsWithGivenAmount,
                             @NonNull CountPages countPages
    ) {
        super(scheduleOn);
        this.getComics = getComics;
        this.listComicViewMapper = listComicViewMapper;
        this.maxListOfComincsWithGivenAmount = maxListOfComincsWithGivenAmount;
        this.calculateTotal = calculateTotal;
        this.countPages = countPages;
    }

    @Override
    public void loadComics() {
        refresh(BigDecimal.ZERO);
    }


    Observable<List<Comic>> fromUseCase() {
        return getComics
                .execute().doOnNext(new Consumer<List<Comic>>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull List<Comic> comics) throws Exception {
                        cacheInMemory = comics;
                    }
                });
    }

    Observable<List<Comic>> fromPresenterCache() {
        if (cacheInMemory != null) {
            return Observable.just(cacheInMemory);
        } else {
            return Observable.empty();
        }
    }

    Observable<List<Comic>> getListComics() {
        return Observable.concat(fromPresenterCache(), fromUseCase())
                .firstElement()
                .toObservable();
    }

    @Override
    public void refresh(@NonNull final CharSequence text) {
        doOnViewAttached(new OnViewAttachedAction<ListView>() {
            @Override
            public void execute(@NonNull ListView listView) {
                listView.hideTotalAmountError();
            }
        });
        String value = text.toString();
        if (value.isEmpty()) {
            refresh(BigDecimal.ZERO);
        } else {
            try {
                Double amountDouble = Double.parseDouble(value);
                BigDecimal maximumAmount = BigDecimal.valueOf(amountDouble);
                refresh(maximumAmount);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                doOnViewAttached(new OnViewAttachedAction<ListView>() {
                    @Override
                    public void execute(@NonNull ListView listView) {
                        listView.showTotalAmountError();
                    }
                });
            }
        }
    }

    private void refresh(@NonNull final BigDecimal maximumAmount) {

        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

        disposable = getListComics()
                // TODO move this chain into a usecase
                // filter by total
                // calculate total and pages
                // format for the screen
                .flatMap(new Function<List<Comic>, ObservableSource<List<Comic>>>() {
                    @Override
                    public ObservableSource<List<Comic>> apply(@io.reactivex.annotations.NonNull final List<Comic> comics) throws Exception {
                        return maxListOfComincsWithGivenAmount.execute(comics, maximumAmount);
                    }
                })
                .flatMap(new Function<List<Comic>, ObservableSource<ComicInfo>>() {
                    @Override
                    public ObservableSource<ComicInfo> apply(@io.reactivex.annotations.NonNull final List<Comic> comics) throws Exception {
                        return calculateTotal.execute(comics)
                                .map(new Function<BigDecimal, ComicInfo>() {
                                    @Override
                                    public ComicInfo apply(@io.reactivex.annotations.NonNull final BigDecimal total) throws Exception {
                                        ComicInfo comicInfo = new ComicInfo();
                                        comicInfo.setComics(comics);
                                        comicInfo.setTotalAmount(total);
                                        return comicInfo;
                                    }
                                }).filter(new Predicate<ComicInfo>() {
                                    @Override
                                    public boolean test(@io.reactivex.annotations.NonNull ComicInfo comicInfo) throws Exception {
                                        return comicInfo.getComics() != null;
                                    }
                                }).flatMap(new Function<ComicInfo, ObservableSource<ComicInfo>>() {
                                    @Override
                                    public ObservableSource<ComicInfo> apply(@io.reactivex.annotations.NonNull final ComicInfo comicInfo) throws Exception {
                                        return countPages.execute(comicInfo.getComics()).map(new Function<Integer, ComicInfo>() {
                                            @Override
                                            public ComicInfo apply(@io.reactivex.annotations.NonNull Integer totalPages) throws Exception {
                                                comicInfo.setTotalPages(totalPages);
                                                return comicInfo;
                                            }
                                        });
                                    }
                                });
                    }
                })
                .map(listComicViewMapper)
                .subscribeOn(scheduleOn.io())
                .observeOn(scheduleOn.ui())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Disposable disposable) throws
                            Exception {
                        doOnViewAttached(new OnViewAttachedAction<ListView>() {
                            @Override
                            public void execute(@NonNull ListView listView) {
                                listView.showLoading();
                                listView.hideError();
                                listView.hideGenericError();
                                listView.hideNoComics();
                                listView.hideComics();
                                listView.hideNumberOfComics();
                                listView.hideTotalAmount();
                                listView.hideNumberOfPages();
                                listView.hideTotalAmountError();
                            }
                        });
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        doOnViewAttached(new OnViewAttachedAction<ListView>() {
                            @Override
                            public void execute(@NonNull ListView listView) {
                                listView.hideLoading();
                            }
                        });
                    }
                })
                .subscribeWith(new DisposableObserver<ComicInfoDisplay>() {
                    @Override
                    public void onNext(
                            @io.reactivex.annotations.NonNull final ComicInfoDisplay comicInfoDisplay) {
                        doOnViewAttached(new OnViewAttachedAction<ListView>() {
                            @Override
                            public void execute(@NonNull ListView listView) {
                                List<ViewComic> comics = comicInfoDisplay.getComics();
                                if (comics != null && !comics.isEmpty()) {
                                    listView.showComics(comics);
                                    listView.hideNoComics();
                                    listView.showNumberOfComics(Integer.toString(comics.size())); // this conversation in principle can be done earlier in background
                                    listView.showTotalAmount(comicInfoDisplay.getTotalAmount());
                                    listView.showNumberOfPages(comicInfoDisplay.getTotalPages());
                                } else {
                                    listView.showNoComics();
                                    listView.hideComics();
                                    listView.hideNumberOfComics();
                                    listView.hideNumberOfPages();
                                    listView.hideTotalAmount();
                                }
                            }
                        });
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        e.printStackTrace();
                        doOnViewAttached(new OnViewAttachedAction<ListView>() {
                            @Override
                            public void execute(@NonNull ListView listView) {
                                listView.showGenericError();
                            }
                        });
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        addToAutoUnsubscribe(disposable);
    }

    @Override
    public void onComicClick(@NonNull final ViewComic comic) {
        doOnViewAttached(new OnViewAttachedAction<ListView>() {
            @Override
            public void execute(@NonNull ListView listView) {
                listView.goToDetails(comic);
            }
        });
    }
}
