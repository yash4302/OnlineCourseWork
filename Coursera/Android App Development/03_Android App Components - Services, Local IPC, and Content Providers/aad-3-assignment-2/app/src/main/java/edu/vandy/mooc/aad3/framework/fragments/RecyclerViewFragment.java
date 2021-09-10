/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package edu.vandy.mooc.aad3.framework.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.vandy.mooc.aad3.R;
import edu.vandy.mooc.aad3.framework.Interfaces
        .RecyclerViewClickListener;
import edu.vandy.mooc.aad3.framework.Interfaces.UpdateEntriesInterface;
import edu.vandy.mooc.aad3.framework.adapters.CustomAdapter;
import edu.vandy.mooc.aad3.framework.common.logger.Log;
import edu.vandy.mooc.aad3.framework.orm.Entry;

/**
 * Demonstrates the use of {@link RecyclerView} with a {@link
 * LinearLayoutManager} and a {@link GridLayoutManager}.
 */
public class RecyclerViewFragment extends Fragment
        implements UpdateEntriesInterface,
                   RecyclerViewClickListener {

    private static final String TAG =
            RecyclerViewFragment.class.getCanonicalName();
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private LayoutManagerType mCurrentLayoutManagerType;
    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    /**
     * Displays the specified list of entries in the recycler view.
     * @param entries List of entries to display.
     */
    @Override
    public void updateEntries(List<Entry> entries) {
        mAdapter.setData(entries);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * Returns the list of currently displayed entries.
     * @return An array list of entries (possibly empty).
     */
    @Override
    @NonNull
    public ArrayList<Entry> getEntries() {
        return mAdapter.getItemCount() > 0
               ? new ArrayList<>(Arrays.asList(mAdapter.getData()))
               : new ArrayList<Entry>();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.recycler_view_frag, container, false);
        rootView.setTag(TAG);

        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        // LinearLayoutManager is used here, this will layout the elements in
        // a similar fashion to the way ListView would layout elements.
        // The RecyclerView.LayoutManager defines how elements are laid out.
        mLayoutManager = new LinearLayoutManager(getActivity());

        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        mAdapter = new CustomAdapter(this);

        mAdapter.notifyDataSetChanged();
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    /**
     * Set RecyclerView's LayoutManager to the one given.
     *
     * @param layoutManagerType Type of layout manager to switch to.
     */
    private void setRecyclerViewLayoutManager(
            LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll
        // position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition =
                    ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                            .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager =
                        new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType =
                        LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType =
                        LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType =
                        LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER,
                                           mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Triggered when the RecyclerView list was clicked on
     *
     * @param v        view that was clicked
     * @param position the position the view was in when it was clicked
     */
    @Override
    public void recyclerViewListClicked(View v, int position) {
        Log.d(TAG, "link for # : "
                + position
                + " "

        );
    }

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

}
