package com.donhat.pagingmovieapp.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridSpace extends RecyclerView.ItemDecoration {
    private int _spanCount;
    private int _spacing;
    private boolean _includeEdge;

    public GridSpace(int spanCount, int spacing, boolean includeEdge) {
        _spanCount = spanCount;
        _spacing = spacing;
        _includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % _spanCount; // item column

        if (_includeEdge) {
            outRect.left = _spacing - column * _spacing / _spanCount;
            outRect.right = (column + 1) * _spacing / _spanCount;

            if (position < _spanCount) {
                outRect.top = _spacing;
            }
            outRect.bottom = _spacing;
        } else {
            outRect.left = column * _spacing / _spanCount;
            outRect.right = _spacing - (column + 1) * _spacing / _spanCount;
            if (position >= _spanCount) {
                outRect.top = _spacing;
            }
        }
    }
}
