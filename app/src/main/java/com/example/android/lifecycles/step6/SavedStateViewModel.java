package com.example.android.lifecycles.step6;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SavedStateViewModel extends ViewModel {

    private MutableLiveData<String> name = new MutableLiveData<>();

    // Expose an immutable LiveData
    LiveData<String> getName() {
        return name;
    }

    void saveNewName(String newName) {
        name.setValue(newName);
    }
}
