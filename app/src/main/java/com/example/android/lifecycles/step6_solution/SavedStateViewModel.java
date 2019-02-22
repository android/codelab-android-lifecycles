package com.example.android.lifecycles.step6_solution;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class SavedStateViewModel extends ViewModel {

    private static final String NAME_KEY = "name";

    private SavedStateHandle mState;

    public SavedStateViewModel(SavedStateHandle savedStateHandle) {
        mState = savedStateHandle;
    }

    // Expose an immutable LiveData
    LiveData<String> getName() {
        mState.
        return mState.getLiveData(NAME_KEY);
    }

    void saveNewName(String newName) {
        mState.set(NAME_KEY, newName);
    }
}
