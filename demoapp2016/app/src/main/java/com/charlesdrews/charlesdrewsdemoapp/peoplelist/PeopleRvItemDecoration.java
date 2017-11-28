package com.charlesdrews.charlesdrewsdemoapp.peoplelist;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Add spacing and dividers to the People recycler vew
 *
 * Created by charlie on 8/16/16.
 */
public class PeopleRvItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private float mMargin;

    public PeopleRvItemDecoration(Drawable divider, float margin) {
        mDivider = divider;
        mMargin = margin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.top = (int) mMargin;
        outRect.bottom = (int) mMargin;

        if (parent.getChildAdapterPosition(view) > 0) {
            // If not first item, also add space above for divider line
            outRect.top += mDivider.getIntrinsicHeight();
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int dividerLeft = parent.getPaddingLeft();
        int dividerRight = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();

        // Start with first item and draw divider below each item; stop before last item
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int dividerTop = child.getBottom() + params.bottomMargin + (int) mMargin;
            int dividerBottom = dividerTop + mDivider.getIntrinsicHeight();

            mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
            mDivider.draw(c);
        }
    }
}
