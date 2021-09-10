package edu.vandy.mooc.aad3.framework.Interfaces;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import edu.vandy.mooc.aad3.framework.orm.Entry;

/**
 * Interface to allow communication between RecyclerView Fragment and Activity.
 */

public interface UpdateEntriesInterface {

    /**
     * Displays the specified list of entries in the recycler view.
     * @param entries List of entries to display.
     */
    void updateEntries(List<Entry> entries);

    /**
     * Returns the list of currently displayed entries.
     * @return An array list of entries (possibly empty).
     */
    @NonNull ArrayList<Entry> getEntries();
}
