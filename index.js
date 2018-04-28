import React from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  NativeModules
} from 'react-native';

class HelloWorld extends React.Component {

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