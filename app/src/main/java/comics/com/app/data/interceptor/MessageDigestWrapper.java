package comics.com.app.data.interceptor;

import android.support.annotation.Nullable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

/**
 * wrapper on top of java class to avoid use
 * Created by alessandro.candolini on 26/06/2017.
 */

public class MessageDigestWrapper {

    @Nullable
    private MessageDigest messageDigest;

    @Inject
    public MessageDigestWrapper() {
        messageDigest = null;
    }

    @Nullable
    public MessageDigest get() {

        if ( messageDigest != null ) {
            return messageDigest;
        }

        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            messageDigest = null;
        }
        return messageDigest;
    }

}
