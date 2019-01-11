
# react-native-toast-notifier

## Getting started

`$ npm install react-native-toast-notifier --save`

### Mostly automatic installation

`$ react-native link react-native-toast-notifier`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.cluster.mpos.bridge.RNToastNotifierPackage;` to the imports at the top of the file
  - Add `new RNToastNotifierPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-toast-notifier'
  	project(':react-native-toast-notifier').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-toast-notifier/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-toast-notifier')
  	```


## Usage
```javascript
import RNToastNotifier from 'react-native-toast-notifier';

// TODO: What to do with the module?
RNToastNotifier.show(YOUR_MESSAGE, RNToastNotifier.SHORT);
```
  
