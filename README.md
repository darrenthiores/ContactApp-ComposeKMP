# ContactApp-ComposeKMP

This app is a simple contact app, a CRUD app where user can create contact, read contact, update contact and delete contact. This app use Compose Multiplatform and KMM (Kotlin Multiplatform Mobile) to supports Android and IOS. And for the design, this app use material 3.

# Platform

There are still some limitation on Compose Multiplatform (since by the time this app created it is in alpha):
- For bitmap converter (byteArray to Bitmap) still using Compose Multiplatform but the implementation is different (use expect and actual)
- For theme since IOS don't support material 3, so using expect and actual to manage the theme)
- Image Picker, the implementation is different in each platform, this app use expect and actual to implement both natively in each platform, so even it is in shared module, still the Ios one use Swift
- Image Storage, the app need a storage to store image picked by user, and this have different implementation in each platform (use expect and actual)

# Demo

Android

![Portfolio - Darren (8)](https://github.com/darrenthiores/ContactApp-ComposeKMP/assets/69592810/5a6fd54f-b581-4702-a987-ad6fd83c9774)

Ios

![Portfolio - Darren (9)](https://github.com/darrenthiores/ContactApp-ComposeKMP/assets/69592810/253ce392-9309-4bdf-852b-ad4d1d6c487c)

# Technologies

- KMM (Kotlin Multiplatform Mobile)
- Kotlin
- Swift
- Compose Multiplatform (Alpha)
- SqlDelight (Local Database)
