[README.md](https://github.com/user-attachments/files/31880604/README.md)
# Streakk

A minimal, privacy-first habit and task tracker for Android — built entirely with Kotlin and Jetpack Compose.

Streakk helps you build consistency around daily habits and to-dos without ever touching the internet, showing an ad, or asking for an account.

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Privacy](#privacy)
- [Roadmap](#roadmap)
- [Contributing](#contributing)
- [License](#license)
- [Author](#author)

---

## Features

### Habits
- Create habits with a custom name, icon, and color
- Choose which days of the week a habit repeats on
- Set an optional start and end time for time-blocked habits
- Daily goal tracking with a configurable duration
- Three end-condition modes:
  - **Off** — active by default for 45 days, then automatically retires
  - **On a date** — active until a specific calendar date
  - **After X days** — active for a fixed number of days from creation
- Mark a habit **Done**, **Skipped**, or **Undo** it back to active for any given day
- Streak counter that reflects real completion history

### To-Dos
- Quick-add to-dos with subtasks
- Optional reminders with an editable time (tap to change, tap the cross to clear)
- Reminders automatically cancel once a task is marked complete

### Calendar & Navigation
- Horizontal week-strip calendar (swipe between weeks, jump back to Today)
- Today / Tomorrow / Yesterday get contextual labels; every other day shows its date and weekday
- Filter habits by **All / Morning / Afternoon**

### Reminders
- Local notifications scheduled with `AlarmManager` — no server, no push service
- Habit reminders repeat only on the days you've chosen, and automatically stop once a habit's end condition is reached
- To-do reminders fire once and clean up after themselves

### PDFs
- Built-in PDF viewer tab with folders and search
- Sort by newest or oldest first

### Settings
- General app preferences, all stored locally
- A dedicated Privacy card that's true by design, not just by promise:
  - No internet access requested
  - No account or sign-in required
  - No ads or third-party tracking
  - All data is stored locally on this device

---

## Tech Stack

| Layer | Choice |
|---|---|
| Language | Kotlin |
| UI | Jetpack Compose, Material 3 |
| Local storage | SharedPreferences (JSON-serialized) |
| Scheduling | `AlarmManager` (exact, wake-from-idle) |
| Splash screen | AndroidX `core-splashscreen` |
| Min SDK | 31 |
| Target SDK | 37 |

No networking libraries, no analytics SDKs, no ad libraries — by design.

---

## Project Structure

```
app/
 └─ src/main/
     ├─ java/com/streakk/app/
     │   └─ MainActivity.kt        # All screens, state, and scheduling logic
     ├─ res/
     │   ├─ drawable/              # Adaptive icon layers (foreground/background)
     │   ├─ mipmap-anydpi-v26/     # Adaptive icon definitions
     │   └─ values/                # Strings, colors, themes
     └─ AndroidManifest.xml
```

---

## Getting Started

### Prerequisites
- Android Studio (latest stable)
- JDK 11+
- An Android device or emulator running API 31+

### Clone

```bash
git clone https://github.com/AryanV4R/STREAKK.git
cd STREAKK
```

### Build & Run
1. Open the project in Android Studio
2. Let Gradle sync finish
3. Run on a device or emulator, or build from the command line:

```bash
./gradlew assembleDebug
```

The debug APK will be generated under `app/build/outputs/apk/debug/`.

---

## Privacy

Streakk requests **zero runtime permissions related to networking** and has no internet permission declared in its manifest. Every habit, to-do, and setting lives only in local `SharedPreferences` on your device. There is nothing to sync, nothing to leak, and nothing to sell.

---

## Roadmap

- [ ] Theming (light mode / custom accent colors)
- [ ] Data export/import (local backup file)
- [ ] Widget support
- [ ] Weekly/monthly progress view

---

## Contributing

Contributions, bug reports, and feature suggestions are welcome.

1. Fork the repo
2. Create a branch (`git checkout -b feature/your-feature`)
3. Commit your changes
4. Open a pull request

For bugs or ideas, open an issue at `https://github.com/AryanV4R/STREAKK/issues`.

---

## Author

([@AryanV4R](https://github.com/AryanV4R))
