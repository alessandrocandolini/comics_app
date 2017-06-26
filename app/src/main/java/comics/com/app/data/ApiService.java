package comics.com.app.data;

import comics.com.app.data.pojo.ComicsResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alessandro.candolini on 26/06/2017.
 */

public interface ApiService {

    @GET("/v1/public/comics")
    Observable<ComicsResponse> comics(@Query("limit") int numberOfComics);

}
