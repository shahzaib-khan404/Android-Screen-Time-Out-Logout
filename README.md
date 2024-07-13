**Overview**

The Android Screen Timeout Logout Utility (ScreenTimeOutUtil) is a Kotlin utility class designed to manage automatic logout functionality in Android applications based on user inactivity. This utility helps developers integrate a timeout-based logout mechanism effortlessly into their apps.

**Features**

Automatically logs out users after a specified period of inactivity.
Resets the logout timer on user interaction.
Provides a customizable timeout duration.
**Usage**
Starting the Logout Timer:

Call ScreenTimeOutUtil.startTimer(context, logoutCallback) in your activity's onStart() method to initiate the logout timer.

**Handling User Interaction:**

Call ScreenTimeOutUtil.startTimer(context, logoutCallback) in your activity's onUserInteraction() method to reset the logout timer whenever the user interacts with the screen.

**Logout Callback:**

Implement the LogoutCallback interface in your activity or fragment to handle logout actions in the makeLogoutCall() method.

**Customization**
Timeout Duration: Adjust the LOGOUT_TIME constant in ScreenTimeOutUtil.kt to set the desired timeout duration (default is 0.5 minutes).
