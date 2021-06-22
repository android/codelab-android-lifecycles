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

5. Lifecycle-aware components perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.
   The androidx.lifecycle package provides classes and interfaces that let you build lifecycle-aware componentsLifecycleOwner is an interface that is used by any class that has an Android lifecycle.

   ***Lifecycle*** is a class that holds the information about the lifecycle state of a component (like an activity or a fragment) and allows other objects to observe this state.
   Lifecycle uses two main enumerations to track the lifecycle status for its associated component:

   ***Event***
   The lifecycle events that are dispatched from the framework and the Lifecycle class. These events map to the callback events in activities and fragments.
   ***State***
   The current state of the component tracked by the Lifecycle object.

   **Think of the states as nodes of a graph and events as the edges between these nodes**

    [![LifeCycle State  up and down events ](https://developer.android.com/images/topic/libraries/architecture/lifecycle-states.svg)]

    ```
     static State getStateAfter(Event event) {
            switch (event) {
                case ON_CREATE:
                case ON_STOP:
                    return CREATED;
                case ON_START:
                case ON_PAUSE:
                    return STARTED;
                case ON_RESUME:
                    return RESUMED;
                case ON_DESTROY:
                    return DESTROYED;
                case ON_ANY:
                    break;
            }
            throw new IllegalArgumentException("Unexpected event value " + event);
        }

        private static Event downEvent(State state) {
            switch (state) {
                case INITIALIZED:
                    throw new IllegalArgumentException();
                case CREATED:
                    return ON_DESTROY;
                case STARTED:
                    return ON_STOP;
                case RESUMED:
                    return ON_PAUSE;
                case DESTROYED:
                    throw new IllegalArgumentException();
            }
            throw new IllegalArgumentException("Unexpected state value " + state);
        }

        private static Event upEvent(State state) {
            switch (state) {
                case INITIALIZED:
                case DESTROYED:
                    return ON_CREATE;
                case CREATED:
                    return ON_START;
                case STARTED:
                    return ON_RESUME;
                case RESUMED:
                    throw new IllegalArgumentException();
            }
            throw new IllegalArgumentException("Unexpected state value " + state);
        }
        ```

    A class can monitor the component's lifecycle status by adding annotations to its methods. Then you can add an observer by calling the addObserver() method of the Lifecycle class and passing an instance of your observer, as shown in the following example:


    ```
    public class MyObserver implements LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public void connectListener() {
            ...
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        public void disconnectListener() {
            ...
        }
    }

    myLifecycleOwner.getLifecycle().addObserver(new MyObserver());
    ```

   ***LifecycleOwner*** is a single method interface that denotes that the class has a Lifecycle. It has one method, getLifecycle(), which must be implemented by the class

   Components that implement LifecycleObserver work seamlessly with components that implement LifecycleOwner because an owner can provide a lifecycle, which an observer can register to watch.

6. ADB commands:

    ./adb shell ps -A |grep lifecycle
    ./adb shell am kill com.example.android.codelabs.lifecycle

7. Some UI elements, including EditText, save their state using their own onSaveInstanceState implementation.
   This state is restored after a process is killed the same way it's restored after a configuration change.

8. Use SavedStateHandle to save ViewModel properties during process kill.
