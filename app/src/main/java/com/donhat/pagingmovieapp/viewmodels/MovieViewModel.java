package com.donhat.pagingmovieapp.viewmodels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.donhat.pagingmovieapp.models.Movie;
import com.donhat.pagingmovieapp.paging.MoviePagingSource;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

public class MovieViewModel extends ViewModel {
    private Flowable<PagingData<Movie>> _moviePagingDataFLowable;

    public MovieViewModel() {
        init();
    }

    private void init() {
        MoviePagingSource moviePagingSource = new MoviePagingSource();

        Pager<Integer, Movie> pager = new Pager(
                new PagingConfig(
                        20,
                        20,
                        false,
                        20,
                        20 * 499
                ),
                () -> moviePagingSource
        );

        // Flowable
        _moviePagingDataFLowable = PagingRx.getFlowable(pager);
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        PagingRx.cachedIn(_moviePagingDataFLowable, coroutineScope);
    }
}
