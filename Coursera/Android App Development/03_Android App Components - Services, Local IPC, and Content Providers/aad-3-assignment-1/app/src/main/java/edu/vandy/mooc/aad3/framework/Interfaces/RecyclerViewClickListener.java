package edu.vandy.mooc.aad3.framework.Interfaces;

import android.view.View;

/**
 * Interface for notifying that the RecyclerView was clicked on.
 */
public interface RecyclerViewClickListener {

    /**
     * Triggered when the RecyclerView list was clicked on
     *
     * @param v        View being clicked
     * @param position position of view when clicked
     */
    @SuppressWarnings("UnusedParameters")
    void recyclerViewListClicked(View v, int position);

}
