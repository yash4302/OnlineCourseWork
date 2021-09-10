package edu.vandy.mooc.aad3.framework.Interfaces;

import android.view.View;

/**
 *
 */
@SuppressWarnings("UnusedParameters")
public interface RecyclerViewClickListener {

    /**
     * Triggered when the RecyclerView list was clicked on
     *
     * @param v        view that was clicked
     * @param position the position the view was in when it was clicked
     */
    void recyclerViewListClicked(View v, int position);

}
