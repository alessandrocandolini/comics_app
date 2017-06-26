package comics.com.app.data.interceptor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import javax.inject.Inject;

/**
 * Inspired from http://www.avajava.com/tutorials/lessons/how-do-i-generate-an-md5-digest-for-a-string.html
 * Created by alessandro.candolini on 26/06/2017.
 */

public class Md5HashGenerator {

    @NonNull
    private final MessageDigestWrapper messageDigestWrapper;

    @Inject
    Md5HashGenerator(@NonNull MessageDigestWrapper messageDigestWrapper) {
        this.messageDigestWrapper = messageDigestWrapper;
    }

    @NonNull
    String hash(@NonNull String message) {
        try {
            byte[] bytesOfMessage = message.getBytes("UTF-8");
            MessageDigest messageDigest = messageDigestWrapper.get();
            if (messageDigest != null) {
                messageDigest.update(bytesOfMessage);
                byte[] hash = messageDigest.digest();
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < hash.length; ++i) {
                    stringBuilder.append(Integer.toHexString((hash[i] & 0xFF) | 0x100).substring(1,3));
                }
                messageDigest.reset();
                return stringBuilder.toString();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return message;

    }
}
