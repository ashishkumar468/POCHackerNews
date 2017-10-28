package tricahshasps.com.pochackernews.application;

/**
 * Created by Ashish on 28/10/17.
 */

public interface BasePresenter<V> {

    void attachView(V view);

    void detachView();


}
