import React from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  ToastAndroid,
  NativeModules,
  DeviceEventEmitter
} from 'react-native';

class HelloWorld extends React.Component {

  componentWillMount(){
    console.log(NativeModules.RN_Module.Constant);
  }

  // 接受原生调用
  componentDidMount() {
      DeviceEventEmitter.addListener('EVENT',(msg)=>{
           var title = "收到数据：" + msg;
           ToastAndroid.show(title, ToastAndroid.SHORT);
      })
  }

  rnCallNative(){
    NativeModules.RN_Module.rnCallNative("message");
  }

  // 调用原生代码 并callback
  callbackComm(msg) {
       NativeModules.RN_Module.rnCallNativeFromCallback(msg,(result) => {
            console.log("回调:" + result)
       })
   }

   // 只能调用一次
   promiseComm(msg) {
       NativeModules.RN_Module.rnCallNativeFromPromise(msg).then(
           (result) =>{
               console.log("Promise收到消息:" + result, ToastAndroid.SHORT)
           }
       ).catch((error) =>{console.log(error)});
   }

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.hello}

        onPress={this.rnCallNative}>
        Click me
        </Text>

        <Text style={styles.hello}
        onPress={this.callbackComm("调用原生callback")}>
        Callback
        </Text>

         <Text style={styles.hello}
        onPress={this.promiseComm("调用原生promise")}>
        Promise
        </Text>

      </View>
    )
  }
}
var styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
  },
  hello: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
});

AppRegistry.registerComponent('APP', () => HelloWorld);
