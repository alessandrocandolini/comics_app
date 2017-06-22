package comics.com.app.di.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by alessandro.candolini on 22/06/2017.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ComputationScheduler {
}
