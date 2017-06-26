package comics.com.app.presentation.list;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.util.List;

import comics.com.app.domain.entities.Comic;
import comics.com.app.domain.usecases.list.GetComics;
import comics.com.app.presentation.base.RxBasePresenter;
import comics.com.app.presentation.base.ScheduleOn;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by alessandro.candolini on 22/06/2017.
 */
public class ListPresenterImpl extends RxBasePresenter<ListView> implements ListPresenter {

    @NonNull
    private final GetComics getComics;

    /** Keep track of subscription */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    DisposableObserver<List<Comic>> disposable = null;

    public ListPresenterImpl(@NonNull ScheduleOn scheduleOn,
                         @NonNull GetComics getComics) {
        super(scheduleOn);
        this.getComics = getComics;
    }

    @Override
    public void loadComics() {
        refresh();
    }

    @Override
    public void refresh() {

        if ( disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

        disposable = getComics
                .execute()
                .subscribeOn(scheduleOn.io())
                .observeOn(scheduleOn.ui())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Disposable disposable) throws Exception {
                        doOnViewAttached(new OnViewAttachedAction<ListView>() {
                            @Override
                            public void execute(@NonNull ListView listView) {
                                listView.showLoading();
                                listView.hideError();
                                listView.hideGenericError();
                                listView.hideNoComics();
                                listView.hideComics();
                                listView.hideNumberOfComics();
                                listView.hideRetry();
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
                .subscribeWith(new DisposableObserver<List<Comic>>() {
                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull final List<Comic> comics) {
                        doOnViewAttached(new OnViewAttachedAction<ListView>() {
                            @Override
                            public void execute(@NonNull ListView listView) {
                                if ( comics.isEmpty() ) {
                                    listView.hideComics();
                                    listView.showNoComics();
                                    listView.hideNumberOfComics();
                                    listView.showRetry();
                                } else {
                                    listView.showComics(comics);
                                    listView.hideNoComics();
                                    listView.showNumberOfComics(Integer.toString(comics.size())); // this conversation in principle can be done earlier in background
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
                                listView.showRetry();
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
    public void onComicClick(@NonNull final Comic comic) {
        if ( comic.id() != null ) {
            doOnViewAttached(new OnViewAttachedAction<ListView>() {
                @Override
                public void execute(@NonNull ListView listView) {
                    listView.goToDetails(comic.id());
                }
            });
        }
    }
}
