# 🩸 LifeConnect – Smart Blood Search App

LifeConnect is a **cross-platform mobile application** built with **Kotlin Multiplatform (KMP)** that helps patients instantly connect with nearby blood donors and blood banks during emergencies.  
Designed to make blood donation and search **faster, reliable, and accessible**, LifeConnect bridges the gap between donors, recipients, and healthcare facilities.

---

## 🚨 Problem

In medical emergencies, finding a compatible blood donor quickly is often difficult.  
Current methods depend on manual calls and social media posts, costing precious time and lives.

---

## 💡 Our Solution

LifeConnect offers **real-time blood donor search** using GPS and a verified database of donors and blood banks.  
If no donor is available nearby, the app intelligently shows **nearby hospitals and blood banks** to ensure help is always within reach.

---

## 🌟 Key Features

- 📍 **Real-Time Donor Search** via GPS  
- 🚨 **Emergency SOS Broadcasts** to nearby donors  
- 🏥 **Blood Bank & Hospital Directory**  
- 🔐 **Verified Donor Profiles**  
- 💬 **In-App Communication (upcoming)**  
- 📊 **Donation History & Tracking (upcoming)**  
- 🤖 **AI-Powered Predictions (future phase)**  

---

## 🛠️ Tech Stack

### 📱 Mobile
- **Kotlin Multiplatform (KMP)** – Shared business logic across Android and iOS  
- **Jetpack Compose** – Modern declarative UI for Android  
- **SwiftUI** – Native declarative UI for iOS  
- **Room Database** – Local caching and offline data access for Android  
- **DataStore (Proto/Preferences)** – Persistent key-value data storage for user preferences  
- **Hilt (DI)** – Dependency Injection for modular, testable, and maintainable codebase  

### 🌐 Networking
- **Ktor Client** – Asynchronous networking across KMP shared module  
  - Used for RESTful API calls to backend services  
  - Handles secure requests with authentication and data serialization  

### ☁️ Backend
- **Node.js** – API and server-side logic  
- **Firebase Functions** – Event-driven cloud logic & push notifications  

### 🗄️ Database & Services
- **Firestore** – Real-time NoSQL database for live donor data  
- **PostgreSQL** – Relational database for structured medical records  
- **Firebase Auth** – Secure user authentication and session management  
- **Google Maps API** – Location tracking, routing, and nearby search services  

---
