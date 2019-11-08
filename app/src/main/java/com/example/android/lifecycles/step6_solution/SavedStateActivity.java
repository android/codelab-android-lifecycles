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

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.codelabs.lifecycle.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * Shows a simple form with a button and displays the value of a property in a ViewModel.
 */
public class SavedStateActivity extends AppCompatActivity {

    private SavedStateViewModel mSavedStateViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_state_activity);

        // Obtain the ViewModel
        mSavedStateViewModel = new ViewModelProvider(this).get(SavedStateViewModel.class);

        // Show the ViewModel property's value in a TextView
        mSavedStateViewModel.getName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String savedString) {
                ((TextView)findViewById(R.id.saved_vm_tv))
                        .setText(getString(R.string.saved_in_vm, savedString));

            }
        });

        // Save button
        findViewById(R.id.save_bt).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = ((EditText)findViewById(R.id.name_et)).getText().toString();
                mSavedStateViewModel.saveNewName(newName);
            }
        });
    }
}
