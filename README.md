# HOW TO RUN
```
npm install
npm start
android studio run android
```
## open dev menu
```
adb reverse tcp:8081 tcp:8081
adb shell input keyevent 82
```

## release build 
```
# 将js文件打包 
react-native bundle --entry-file index.js --bundle-output ./app/src/main/assets/index.bundle --platform android  --assets-dest ./app/src/main/res/ --dev false
```