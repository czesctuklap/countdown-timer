# Countdown Timer

A simple Android Timer app in Kotlin, which allows users to set a countdown timer, start/pause/stop it, and adjust the timer dynamically.

### Design Principles and Architecture

- **Fragments**: The app uses multiple fragments (`FragmentForm` for input and `FragmentMain` for displaying and controlling the countdown) to promote a modular design, which allows for better reuse and maintainability.
- **Object-Oriented Design**: The app follows object-oriented principles like encapsulation, where the `CountdownTimerManager` class encapsulates all the timer-related logic. It also employs separation of concerns by keeping the UI logic separate from the business logic, ensuring the app is modular and easier to extend or maintain.

### Components

- **`MainActivity`**: Initializes the app and displays the `FragmentForm`.
- **`FragmentForm`**: UI to set timer duration in minutes and seconds.
- **`FragmentMain`**: Displays the timer and allows controls for starting, pausing, stopping, and adjusting time.
- **`CountdownTimerManager`**: Handles all the timer logic - start, pause, stop, adjust time.

### Key Features

- **Set Timer**: Input minutes and seconds.
- **Start/Pause/Stop**: Control the countdown.
- **Dynamically Adjust Time**: Add or subtract time in predefined increments.
- **Notifications**: Alarm and toast when time is up.

### Preview
![image](https://github.com/user-attachments/assets/b9263ee3-d82a-4458-9346-a4f14ac9192a)

![image](https://github.com/user-attachments/assets/48486813-adbe-4ecd-842b-187c142e3fe4)

