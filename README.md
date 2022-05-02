# AssignmentView

## Description

**An application that shows images of different types from an endpoint or mock urls, and prints the
time between the request and the execution.**

## Instructions

One can easily download the .zip file or import the project to get it. No further actions required
to run or work on the project.

Download the project, select from the spinner on the top left corner, or select the option on the
top right corner.

Then you will be able to the images of your selection.

**However**<br>
*To actually test the project, one needs Android Studio to check the logs!*

## To contribute

*libraries*<br>
hilt for injection, okhttp and retrofit for network requests, glide for showing images, coroutines

To inject other api services, please refer the following code in NetworkModule.kt 
*@Provides
@Singleton 
@Named(BuildConfig.BASE_URL)
fun providesImageService(
    retrofit: Retrofit
): ImageService = retrofit.create(ImageService::class.java)*

Coroutines is used to request service calls without any blockers or other issues.
In UseCase.kt the method to make the service request, named *run*, is requested in IO thread, which
is provided with the injected dispatcher. *check UseCase.kt for the previous information*


*architecture*<br>
**clean**<br>
there are data, domain(including use cases) and presentation(scene package) layers

*other used tools*<br>
view binding and data binding, assignment view is created as a component which can be found in
AssignmentView.kt, additionally its adapter can be found in the same package

To add other data binding methods please refer the code in ViewDataBinding.kt

The following code can be found in ByteExt.kt and is used to convert the network response to show the 
images successfully.

*fun decodeByteArray(byteArray: ByteArray): Bitmap? =
    BitmapFactory.decodeByteArray(byteArray, OFFSET, byteArray.size)*

**Creadits to Fernando Cejas for Either.kt**

## Possible further actions

*use fragments instead of activity*<br>
*implement a base adapter for future adapters*<br>
*implement and handle other network fails in Failure.kt*<br>