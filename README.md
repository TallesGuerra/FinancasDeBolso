# 💰 Pocket Finances

![Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Compose](https://img.shields.io/badge/Jetpack%20Compose-1.5+-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)
![MVVM](https://img.shields.io/badge/MVVM-FF6F00?style=for-the-badge&logoColor=white)
![Clean Architecture](https://img.shields.io/badge/Clean_Architecture-FF6F00?style=for-the-badge&logoColor=white)
![Material3](https://img.shields.io/badge/Material%203-Design-6200EE?style=for-the-badge)

**Personal finance Android app built with Kotlin and Jetpack Compose, following MVVM and Clean Architecture principles.**

---

## 📱 Features

- ✅ Register income and expense transactions
- ✅ Real-time financial balance overview
- ✅ Transaction history list
- ✅ Category-based organization
- ✅ Clean and intuitive UI with Material Design 3
- ✅ Light and dark theme support

---

## 🏗️ Architecture

This project follows **Clean Architecture** with **MVVM**, separating responsibilities into distinct layers:

```
com.example.fiancasdebolso/
│
├── 📱 ui/                              # Presentation layer
│   ├── MainActivity.kt                # Main entry point
│   ├── components/                    # Reusable Composables
│   └── utils/
│       └── Formatters.kt             # UI utility formatters
│
├── 🧠 viewmodel/                       # ViewModel layer
│   └── FinanceViewModel.kt            # State & operation management
│                                      # Uses StateFlow + Coroutines
│
├── 🎯 domain/                          # Business logic layer
│   ├── usecase/
│   │   ├── GetTotalIncomeUseCase.kt   # Calculates total income
│   │   └── GetTotalExpenseUseCase.kt  # Calculates total expenses
│   └── model/
│       └── Transaction.kt             # Domain data model
│
└── 🎨 ui/theme/                        # Material 3 theme
    ├── Color.kt
    ├── Theme.kt
    └── Type.kt
```

### Architecture Layers

- **UI Layer** — Composables observe state from ViewModel via `collectAsState()`
- **ViewModel** — Exposes state with `StateFlow`, uses `viewModelScope` for Coroutines
- **Use Cases** — Single-responsibility business logic (one action per class)
- **Domain Model** — Pure Kotlin data classes, independent of Android framework

### Design Principles Applied

- **MVVM** — clear separation between UI and business logic
- **Clean Architecture** — dependencies point inward, domain layer is framework-independent
- **Single Responsibility** — each Use Case handles one specific operation
- **Unidirectional Data Flow** — state flows down, events flow up
- **Clean Code** — readable and self-documented code

---

## 🔧 Technologies

| Technology          | Version | Description                              |
|---------------------|---------|------------------------------------------|
| **Kotlin**          | 1.9+    | Modern, concise Android language         |
| **Jetpack Compose** | 1.5+    | Declarative and reactive UI toolkit      |
| **Coroutines**      | 1.7+    | Asynchronous programming                 |
| **StateFlow**       | —       | Reactive state management                |
| **ViewModel**       | 2.7+    | Lifecycle-aware state holder             |
| **Material 3**      | Latest  | Google's design system                   |
| **Android SDK**     | 24+     | Compatible with 95%+ of devices          |

---

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or higher
- JDK 17+
- Android SDK 34
- Physical device or emulator with API 24+

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/TallesGuerra/FinancasDeBolso.git
   cd FinancasDeBolso
   ```

2. **Open in Android Studio**
   - File → Open → Select the project folder

3. **Sync dependencies**
   - Gradle will sync automatically

4. **Run the app**
   - Click **Run** ▶️ or press `Shift + F10`
   - Select a device or emulator

---

## 💡 Concepts Demonstrated

- **MVVM architecture** with ViewModel and StateFlow
- **Clean Architecture** with dedicated Use Case layer
- **Coroutines** with `viewModelScope.launch` for async operations
- **Reactive UI** with `collectAsState()` in Composables
- **Jetpack Compose** layouts, state management and LazyColumn
- **Material Design 3** components, colors and typography
- **Separation of concerns** across UI, ViewModel, and Domain layers

---

## 🔄 Roadmap

- [x] Income and expense transaction management
- [x] Real-time balance calculation
- [x] MVVM architecture with ViewModel + StateFlow
- [x] Clean Architecture with Use Cases
- [x] Coroutines for async operations
- [ ] Local persistence with Room Database
- [ ] Expense categories and filters
- [ ] Monthly spending charts

---

## 👨‍💻 Author

- 📧 [talles-guerra@hotmail.com](mailto:talles-guerra@hotmail.com)
- 💼 [LinkedIn](https://www.linkedin.com/in/talles-guerra/)
- 🐙 [GitHub](https://github.com/TallesGuerra)

---

**Made with ❤️ and Jetpack Compose**
