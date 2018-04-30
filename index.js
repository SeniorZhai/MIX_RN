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

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.hello}

        onPress={this.rnCallNative.bind(this)}>
        Click me
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
