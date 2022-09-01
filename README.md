# Forbes Coding Assignment

*Project is located on master branch*

**Thoughts about Splash Screens:** 

I created the Splash Screen using a traditional Launch Screen storyboard, connected to the target app in the App Icons and Launch Images section. 
There is also a way to create the launch screen in the Info.plist Launch Screen dictionary, but I found it difficult to control the logo's sizing. 

Apple's guidelines on Splash Screen/Launch Screen are confusing... They provide LaunchScreen.storyboard for creating iOS apps, but you'll notice they don't seem to use one in their own apps - at least not in the traditional way of logo and solid color background that many other apps use.

![1_XJ5EQXXPGS07Bb325OgeYQ](https://user-images.githubusercontent.com/102271892/187965360-e7a7bc9a-426c-4f27-9b45-e8307233ab79.png)


Per Apple's guidelines:

A launch screen appears instantly when your app starts up and is quickly replaced with the app’s first screen, giving the impression that your app is fast and responsive. **The launch screen isn’t an opportunity for artistic expression. It’s solely intended to enhance the perception of your app as quick to launch and immediately ready for use.**

- Design a launch screen that’s nearly identical to the first screen of your app. People shouldn’t experience an unpleasant flash between the launch screen and the first screen of the app.
- Avoid including text on your launch screen.
- Downplay launch. People value apps that let them quickly access content and perform tasks. Designing a launch screen that resembles an app’s interface creates the illusion that the app starts instantly.
- Don’t advertise. The launch screen isn’t a branding opportunity.

So... for future efforts, instead of displaying a logo for splash screen, provide a bare-bones representation of your app's initial UI to give the impression of faster launching. 

**Other Notes:**
I used a package called [SDWebImage](https://github.com/SDWebImage) to manage the asynchronous download and caching of images on the ContentView.
