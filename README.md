# FreeCash Engine — Android source project

Version 0.2.0 · Android native Java · package `com.example.freecashengine`

## What this build contains
- Native Android earning-hub interface with Dashboard, Discover, and Withdraw screens.
- Broad earning categories: surveys/research, tasks/offers, cashback/promotions, referrals, authorized computing, and mining.
- Confirmed-payable balance starts at $0.00; no simulated or fabricated earnings.
- Lightning invoice entry and basic prefix sanity check. This is NOT a payment, invoice decoder, or wallet integration.
- No app-imposed withdrawal minimum is the product goal, subject to provider, Lightning invoice, routing, and fee constraints.
- No seed phrase/private-key collection, account scraping, fake engagement, or unauthorized use of third-party compute.

## What is not connected yet
This is an Android UI/source foundation, not a functioning income generator. No third-party earning provider is connected, no reward API credentials are configured, and no payout backend or Lightning wallet is integrated. The app therefore cannot currently earn or send money. It deliberately does not invent a balance.

A real integration requires, for each provider:
1. A legitimate partner/API agreement and user-authorized account connection.
2. Documented API access to eligible tasks, reward status, and account balances.
3. Server-side verification of completed actions and settled rewards.
4. A secure payout service with idempotency, fraud controls, audit logs, and fee disclosure.
5. A Lightning wallet/provider integration that can create and pay invoices from funded, confirmed balances.

Automatic discovery can find and evaluate publicly documented programs, but cannot silently enroll a user, complete paid tasks, bypass CAPTCHAs, create accounts, or use other people's computers without authorization. Many reward programs require user participation and have eligibility/withdrawal thresholds set by the provider. The app cannot override those terms.

## Build an APK
Open this folder in Android Studio, install Android SDK Platform 35, allow Gradle sync, then choose **Build > Build Bundle(s) / APK(s) > Build APK(s)**. The debug APK should be at `app/build/outputs/apk/debug/app-debug.apk`.

This environment does not have Android SDK/Gradle installed, so this source has not been compiled or device-tested here.
