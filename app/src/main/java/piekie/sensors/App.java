package piekie.sensors;

import android.app.Application;
import android.content.Context;

/**
 * Created by piekie (Artem Vasylenko)
 * on 10/4/16
 */

public class App extends Application {

    static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
    }
}
