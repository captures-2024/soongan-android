# soongan-android
soongan(순간) - 당신의 눈으로 만든 우리의 순간

<p align="center">
    <img src="https://github.com/user-attachments/assets/8743f5f3-6597-49cc-a931-574fdf9a6549" width="30%"/>
    <img src="https://github.com/user-attachments/assets/ae2368b4-a2dd-4784-9d13-9bef0662690a" width="30%"/>
    <img src="https://github.com/user-attachments/assets/795721fd-cd05-41b7-9530-e9cae65c5e95" width="30%"/>
</p>

## Development

### Required
- IDE : Android Studio Koala
- JDK : Java 17을 실행할 수 있는 JDK
- Kotlin Language : 2.1.21
- version: gradle/libs.versions.toml

### Language
- Kotlin

### Libraries
- AndroidX
    - Activity Compose 
    - Core 
    - Lifecycle & ViewModel Compose 
    - Navigation 
    - DataStore 
    - StartUp 
    - Splash
- Kotlin Libraries (Coroutine, Serialization, DateTime)
- Compose 
    - Material3 
    - Navigation
- Dagger Hilt
- Retrofit
- napier
- coil(compose)

## Package Structure
```
├── app
│   └── Application
├── build-logic
├── core
│   ├── analytics
│   ├── analytics-android
│   ├── auth
│   ├── common
│   ├── model
│   └── navigator
├── data
│   ├── dataSource
│   ├── network
│   ├── repository
│   │    ├── auth
│   │    ├── auth
│   │    ├── auth
│   │    ├── auth-impl
│   │    ├── contest
│   │    ├── contest-impl
│   │    ├── fcm
│   │    ├── fcm-impl
│   │    ├── home
│   │    ├── home-impl
│   │    ├── member
│   │    ├── member-impl
│   │    ├── notification
│   │    ├── notification-impl
│   │    ├── report
│   │    ├── report-impl
│   │    ├── system
│   │    ├── system-impl
│   │    ├── token
│   │    └── token-impl
│   └── source
│        ├── auth
│        ├── auth-impl
│        ├── contest
│        ├── contest-impl
│        ├── fcm
│        ├── fcm-impl
│        ├── home
│        ├── home-impl
│        ├── member
│        ├── member-impl
│        ├── notification
│        ├── notification-i
│        ├── report
│        ├── report-impl
│        ├── system
│        ├── system-impl
│        ├── token
│        ├── token-impl
│        └── utils
├── domain
│   └── usecase
│        ├── auth
│        ├── auth-impl
│        ├── contest
│        ├── contest-impl
│        ├── fcm
│        ├── fcm-impl
│        ├── home
│        ├── home-impl
│        ├── member
│        ├── member-impl
│        ├── notification
│        ├── notification-impl
│        ├── report
│        ├── report-impl
│        ├── system
│        ├── system-impl
│        ├── token
│        ├── token-impl
│        └── utils
├── presentation
│   ├── designSystem
│   │    ├── icon
│   │    └── ui
│   ├── feature
│   │    ├── main
│   │    ├── main-awards
│   │    ├── main-feed
│   │    ├── main-home
│   │    ├── main-post
│   │    ├── main-profile
│   │    ├── sign
│   │    ├── sign-in
│   │    └── sign-up
│   └── viewmodel
└── gradle
    └── libs.versions.toml
```

<p align="center">
    <img src="https://raw.githubusercontent.com/captures-2024/soongan-android/refs/heads/develop/project.dot.png"/>
</p>