package com.test.codingchallenge.contracts;

/**
 * Created for Coding Challenge Project of PF.
 */
public class SortOptionsContract {

    public interface View {
        void showSortedList(String sortOption);
    }

    public interface Presenter {
        void parseSortSelection(int viewId);
    }
}
