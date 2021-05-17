# UniversityApp

During my last in year in the college, I had android development classes. The first exam was build an app very similar to this. It had 2 screens, one for searching and the second for displaying the list, it was written in Java and the items was displayed in a List View. After the exam, I had a good app template to try out newer things. I refactored to Kotlin, removed 1 screen and used Recycler View instead of List View.

The main functionality is: you can search for education institutions. It searchs for all colleges around the world. Type any character you desire 
(or nothing, if you want to bring the whole list!) and it will search in the API for the characters that you have typed in.
The second functionality is: when you click on any university displayed on the list, you will be redirected to their respective website!

* API that I used: http://universities.hipolabs.com
* Example on how to search for name: http://universities.hipolabs.com/search?name=mit

| http://universities.hipolabs.com/search?name=santa      | Accessing respective website      |
|------------|-------------|
|   <img src="https://user-images.githubusercontent.com/66192808/117543055-96634780-aff1-11eb-8705-1502d6fbe7c5.gif" alt="drawing" width="350"/>|<img src="https://user-images.githubusercontent.com/66192808/117543107-cc083080-aff1-11eb-8f76-8a8b798517f1.gif" alt="drawing" width="350">|

This app covers MVP architecture and presenter tests and here are a few topics that can be useful:

* [Very helpful (totally) youtube playlist](https://www.youtube.com/playlist?list=PLTihuzC3BWUrMwZoFj7pFu4-OCWkSAEq0)
* Inside my presenter layer, I can change the views inside the coroutine thread (not UI thread) thankfully to the LifecycleScope class (base package) wich sets dispatchers on MAIN (non-default behavior)
* Also, on the service layer, I don't return a http response object to make tests easier to stub. If I had to return a retrofit response, it would be much more difficult (a retrofit2 response object takes another okhttp3 response object) and that wouldn't make any difference on the presenter behavior
* On the service layer, I don't return an usual collection because the response class inherits ArrayList
* [MVP guides collection](https://www.youtube.com/playlist?list=PL4Shz0ergABUSI0Jtdgw9jCBpmNo1NGLt)

I hope you enjoy it, have a nice day!
