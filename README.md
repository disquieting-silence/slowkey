slowkey
=======

The `slowkey` application is a basic keyboard replacement for Android devices. It is not designed for rapid text input, but rather for accurate and deliberate text input. It clearly has no real use case, but that hasn't stopped any of the other projects available here. The images on the keys were reused from the `SoftKeyboard` project which comes as one of the samples provided with the Android SDK. 


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

Using `slowkey`
---------------

Using `slowkey` requires a lot of practice and a strong resistance (borderline immunity) to RSI. The basic rundown is as follows:

* The keyboard is sector-driven. A small number of keys is available in each sector.
* The `MOVE` key is used to navigate back to the sector selection menu. No other sector provides the ability to switch directly to another sector.
* Each alphabet sector contains six letters. These letters approximate a section of the `qwerty` keyboard.
* The `navigation` menu is used for moving the cursor through text. It is quite possibly the only improvement on the regular Android keyboard.
* Many, many common characters are just not available anywhere. The small number of buttons on each screen provides certain constraints for layout. At this stage, the sector breakdown is quite arbitrary. Continued use (hypothetically) would drive out better sector identification.

Known Issues
------------

* Most of it.
* The Shift key doesn't go off after pressing a letter. This grows beyond frustrating very quickly. Shift key behaviour is still uncertain.

Screenshots
-----------

![Sector Selection](https://github.com/disquieting-silence/slowkey/raw/master/screenshots/main_small.png) ![Q Sector](https://github.com/disquieting-silence/slowkey/raw/master/screenshots/qrow_small.png) ![J Sector](https://github.com/disquieting-silence/slowkey/raw/master/screenshots/jrow_small.png)

![Number Sector](https://github.com/disquieting-silence/slowkey/raw/master/screenshots/number_small.png) ![Symbol Sector](https://github.com/disquieting-silence/slowkey/raw/master/screenshots/symbol_small.png) ![Navigation Sector](https://github.com/disquieting-silence/slowkey/raw/master/screenshots/navigation_small.png)
