package comics.com.app.presentation.list;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import comics.com.app.BaseActivity;
import comics.com.app.R;
import comics.com.app.di.component.ActivityComponent;
import comics.com.app.presentation.details.DetailActivity;

public class ListActivity extends BaseActivity implements ListView {

    private static final String BUNDLE_RECYCLER_LAYOUT = "ListActivity.layout_manager";

    @Inject
    ListPresenter presenter;

    @Inject
    ListAdapter adapter;

    @BindView(R.id.empty_list)
    View emptyView;

    @BindView(R.id.error)
    View errorView;

    @BindView(R.id.list)
    RecyclerView recyclerView;

    @BindView(R.id.number_of_comics)
    TextView numberOfComicsView;

    @BindView(R.id.insert_amount_error)
    View insertAmountErrorView;

    @BindView(R.id.total_amount)
    TextView totalAmountTextView;

    @BindView(R.id.total_pages)
    TextView totalPagesTextView;

    @BindView(R.id.insert_amount)
    EditText editText;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    private Unbinder unbinder;

    LinearLayoutManager layoutManager;

    boolean isFirstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_comics);
        if ( savedInstanceState != null ) {
            isFirstTime = false;
        }
        unbinder = ButterKnife.bind(this);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(new ListAdapter.ClickListener() {
            @Override
            public void onClick(@NonNull final ViewComic comic) {
                presenter.onComicClick(comic);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadComics();
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.refresh(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attach(this);
        if ( isFirstTime ) {
            presenter.loadComics();
        }
    }

    @Override
    protected void onStop() {
        presenter.detach();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if ( isFinishing()) {
            presenter.abort();
        }
        adapter.setClickListener(null);
        swipeRefreshLayout.setOnRefreshListener(null);
        if ( unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, recyclerView.getLayoutManager().onSaveInstanceState());

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Parcelable layoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
        layoutManager.onRestoreInstanceState(layoutState);
    }

    @Override
    protected void injectActivity(@NonNull ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showGenericError() {
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideGenericError() {
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showError(@NonNull String error) {
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showNoComics() {
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoComics() {
        emptyView.setVisibility(View.GONE);
    }

    @Override
    public void showComics(@NonNull List<ViewComic> comics) {
        recyclerView.setVisibility(View.VISIBLE);
        adapter.setComics(comics);
    }

    @Override
    public void hideComics() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showNumberOfComics(@NonNull String numberOfComics) {
        numberOfComicsView.setText(numberOfComics);
        numberOfComicsView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNumberOfComics() {
        numberOfComicsView.setVisibility(View.GONE);
    }

    @Override
    public void showTotalAmount(@NonNull String total) {
        totalAmountTextView.setText(total);
        totalAmountTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTotalAmount() {
        totalAmountTextView.setVisibility(View.GONE);
    }

    @Override
    public void showNumberOfPages(@NonNull String pages) {
        totalPagesTextView.setText(pages);
        totalPagesTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNumberOfPages() {
        totalPagesTextView.setVisibility(View.GONE);
    }

    @Override
    public void showTotalAmountError() {
        insertAmountErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTotalAmountError() {
        insertAmountErrorView.setVisibility(View.GONE);
    }

    @Override
    public void goToDetails(@NonNull ViewComic viewComic) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.BUNDLE_COMIC, Parcels.wrap(viewComic));
        startActivity(intent);
    }
}
