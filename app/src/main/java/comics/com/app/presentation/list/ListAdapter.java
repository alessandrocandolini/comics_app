package comics.com.app.presentation.list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import comics.com.app.R;

/**
 * Created by alessandro.candolini on 25/06/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    @Nullable
    private List<ViewComic> comics;

    @Nullable
    private ClickListener clickListener;

    @Inject
    public ListAdapter() {
    }

    public void setClickListener(@Nullable ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setComics(@Nullable List<ViewComic> comics) {
        this.comics = comics;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_comic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ViewComic comic = comics.get(position);
        holder.titleView.setText(comic.getTitle());
//        final String priceString;
//        final Price price = comic.price();
//        if ( price != null) { // TODO move to presenter
//            priceString = price.printPrice();
//        } else {
//            priceString = "";
//        }
        holder.priceView.setText(comic.getPrice());
        Glide.with(holder.thumbNailView.getContext())
                .load(comic.getThumbnail())
                .fitCenter()
                .crossFade()
                .into(holder.thumbNailView);
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onClick(comic);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (comics != null) {
            return comics.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item)
        ViewGroup container;

        @BindView(R.id.thumbnail)
        ImageView thumbNailView;

        @BindView(R.id.title)
        TextView titleView;

        @BindView(R.id.price)
        TextView priceView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ClickListener {
        void onClick(@NonNull ViewComic comic);
    }

}
