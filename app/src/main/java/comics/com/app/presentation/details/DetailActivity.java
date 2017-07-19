package comics.com.app.presentation.details;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import comics.com.app.BaseActivity;
import comics.com.app.R;
import comics.com.app.di.component.ActivityComponent;
import comics.com.app.presentation.list.ViewComic;

public class DetailActivity extends BaseActivity implements DetailView {

    public static final String BUNDLE_COMIC = "DetailActivity.comic";

    @BindView(R.id.image)
    ImageView imageView;

    @BindView(R.id.title)
    TextView titleTextView;

    @BindView(R.id.price)
    TextView priceTextView;

    @BindView(R.id.pages)
    TextView pageTextView;

    @BindView(R.id.authors)
    TextView authorsTextView;

    @BindView(R.id.description)
    TextView descriptionTextView;

    @Inject
    DetailPresenter presenter;

    @Nullable
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        unbinder = ButterKnife.bind(this);

        if ( getIntent() != null && getIntent().getParcelableExtra(BUNDLE_COMIC) != null) {
            ViewComic comic = Parcels.unwrap(getIntent().getParcelableExtra(BUNDLE_COMIC));
            if ( comic != null ) {
                presenter.init(comic);
            }
        }

    }

    @Override
    protected void injectActivity(@NonNull ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attach(this);
    }

    @Override
    protected void onStop() {
        presenter.detach();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (isFinishing()) {
            presenter.abort();
        }
        if ( unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }


    @Override
    public void showImage(String imageUrl) {
        Glide.with(this)
                .load(imageUrl)
                .fitCenter()
                .crossFade()
                .into(imageView);
    }

    @Override
    public void showTitle(String title) {
        titleTextView.setText(title);
    }

    @Override
    public void showDescription(String description) {
        descriptionTextView.setText(description);
    }

    @Override
    public void showPageCount(String pageCount) {
        pageTextView.setText(pageCount);
    }

    @Override
    public void showPrice(String price) {
        priceTextView.setText(price);
    }

    @Override
    public void showAuthor(String authors) {
        authorsTextView.setText(authors);
    }
}
