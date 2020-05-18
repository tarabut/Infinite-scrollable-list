# Infinite Scrollable List

Infinite scrollable list of items fetched from Restful web service

# Design Pattern: 
Model View Presenter

# Application Flow:
  Splash Screen: Just for a starting state
  Home Screen: Two screens added as Fragments 
  
    1.	Property Fragment
      a)	Contract: PropertyContract.java 
      b)	View: PropertyFragment.java
      c)	Presenter: PropertyPresenter.java
      d)	Model: PropertyListResponse.java, PropertyResource.java, Broker.java
      e)	Interactor: PropertyInteractor.java
     
    2.	Sort Options Fragment 
      a)	Contract: SortOptionsContract.java 
      b)	View: SortOptionsFragment.java
      c)	Presenter: SortOptionsPresenter.java
      
  Following DRY Property fragment is designed in a way to fetch data list with respect to sorting order.
  Interfaces and Bundles are used for communication between fragments and activities 
  API calls are separated from base URL and Smart URL pick added for Release project

# Libraries:
    Retrofit with Gson for Web services
    ButterKnife for View injeciton
    Picasso for Image loading
    Crashlytics for crash reporting

