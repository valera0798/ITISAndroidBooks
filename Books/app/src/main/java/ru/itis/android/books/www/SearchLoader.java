package ru.itis.android.books.www;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import retrofit2.Call;
import ru.itis.android.books.presenter.Presenter;
import ru.itis.android.books.www.api.ArticleSearchApiInterface;
import ru.itis.android.books.model.bean.SearchResult;

/**
 * Created by Users on 21.12.2017.
 */

public class SearchLoader extends AsyncTaskLoader {
    private ArticleSearchApiInterface searchApiInterface;
    private Presenter presenter;

    private String keyWord;

    public SearchLoader(Context context,
                        ArticleSearchApiInterface searchApiInterface,
                        Presenter presenter,
                        String keyWord) {
        super(context);
        this.searchApiInterface = searchApiInterface;
        this.presenter = presenter;
        this.keyWord = keyWord;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public Object loadInBackground() {
        Call<SearchResult> c = searchApiInterface.getArticlesByKeyWord(keyWord, ArticleSearchApiInterface.API_KEY);
        c.enqueue(presenter);

        return null;
    }
}
