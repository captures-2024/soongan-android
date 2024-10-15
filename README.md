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
- Kotlin Language : 1.9.20

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

- Timber

- coil(compose)

## Package Structure
```
├── app
│   └── Application
├── build-logic
├── core
│   ├── analytics
│   ├── auth
│   ├── common
│   ├── data
│   ├── datastore
│   ├── designsystem
│   ├── domain
│   ├── model
│   ├── navigator
│   └── network
├── feature
│   ├── awards
│   ├── feed
│   ├── intro
│   ├── main
│   ├── privacyPolicy 
│   ├── profile
│   ├── sign
│   ├── signIn
│   ├── signUp
│   ├── termsOfUse
│   └── welcome
└── gradle
    └── libs.versions.toml
```