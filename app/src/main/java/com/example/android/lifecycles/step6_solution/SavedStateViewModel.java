/*
 * Copyright 2019, The Android Open Source Project
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
        // getLiveData obtains an object that is associated with the key wrapped in a LiveData
        // so it can be observed for changes.
        return mState.getLiveData(NAME_KEY);
    }

    void saveNewName(String newName) {
        // Sets a new value for the object associated to the key. There's no need to set it
        // as a LiveData.
        mState.set(NAME_KEY, newName);
    }
}
