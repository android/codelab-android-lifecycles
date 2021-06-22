# Android Lifecycle-aware Components Codelab

Please follow along the codelab steps [here](https://codelabs.developers.google.com/codelabs/android-lifecycles/).

# Filing issues

If you find errors in the codelab steps or the code, please file them [here](https://github.com/googlecodelabs/android-lifecycles/issues/new)

## Notes

1. An activity is a poor choice to manage app data.
   Activities and fragments are short-lived objects which are created and destroyed frequently as a user interacts with an app.

2. ViewModel can retain data across the entire lifecycle of an activity or a fragment.
   A ViewModel is also better suited to managing tasks related to network communication, as well as data manipulation and persistence.
   To avoid memory leaks, the ViewModel doesn't include references to the activity.

3. Instead of modifying views directly from the ViewModel, you configure an activity or fragment to observe a data source, receiving the data when it changes.
   This arrangement is called the observer pattern.

4. LiveData is a special observable class which is lifecycle-aware, and only notifies active observers.
   To make change value of MutableLiveData: setValue() method must be called from the main thread.
   To change from inside a background thread, you can use postValue().

5. LifecycleOwner is an interface that is used by any class that has an Android lifecycle. Practically, it means that the class has a Lifecycle object that represents its lifecycle.

6. ADB commands:

    ./adb shell ps -A |grep lifecycle
    ./adb shell am kill com.example.android.codelabs.lifecycle

7. Some UI elements, including EditText, save their state using their own onSaveInstanceState implementation.
   This state is restored after a process is killed the same way it's restored after a configuration change.

8. Use SavedStateHandle to save ViewModel properties during process kill.
