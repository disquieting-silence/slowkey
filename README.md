slowkey
=======

The `slowkey` application is a basic keyboard replacement for Android devices. It is not designed for rapid text input, but rather for accurate and deliberate text input. It clearly has no real use case, but that hasn't stopped any of the other projects available here. The images on the keys were reused from the `SoftKeyboard` project which comes as one of the samples provided with the Android SDK. 

The `master` branch is generally several months behind development. For the latest bugs (features), guess which one of the other multitude of branches is actually active.

Dependencies
------------

There are no dependencies for `slowkey`. All it requires is an extreme amount of patience, and misguided priorities.

Installation
------------

### Configuration
Installing `slowkey` requires the Android SDK with the `android` executable on the `path`. Inside your `slowkey` checkout directory, type: 

    android update project --path . 

This will generate the `local.properties` and `proguard` configuration required. The `android update project` command also provides you with other project configuration options, such as the target Android OS. For a full list of configuration options, consult the [Android Developer Documentation](http://developer.android.com/tools/projects/projects-cmdline.html#UpdatingAProject). 

### Target Android OS

`slowkey` has been written to operate on any 2.2 OS of Android (Froyo). However, it is possible to build `slowkey` for a different target OS. As mentioned above, using `android update project` is the approach to specify the target OS. In order to see a list of OS that your Android tools currently support, type: 

    android list targets

The output of this will provide each supported OS with an `id`. This `id` should be specified as the value for the `--target` parameter in the `android update project` command. E.g.

    android update project --path . --target android-8


### Installation

If you have configured your project correctly, then installation 'should' proceed. Installation and deployment to your system (connected device or running emulator) is achieved through the `ant` build tools. Specifically: 

    ant debug install

### Troubleshooting

Generating a build requires all involved projects to have a valid build script. If this is not the case, you will commonly see this error:

    sdk.dir is missing. Make sure to generate local.properties using 'android update project' or to inject it through an env var

As `slowkey` has no dependent projects, this error can only occur because `slowkey` itself has not been configured properly. The solution is to execute `android update project` with the relevant parameters for the `slowkey` project. A full guide to updating projects via the command line can be found [here.](http://developer.android.com/tools/projects/projects-cmdline.html#UpdatingAProject)
