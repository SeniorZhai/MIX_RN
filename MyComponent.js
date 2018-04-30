import React from 'react';

import {
  View,
  Text,
  StyleSheet
} from 'react-native';

class MyComponent extends  React.Component {

    render() {
      return(
        <View style={styles.container}>
          <Text>This is title</Text>
        </View>)
    }
}
var styles = StyleSheet.create({
  container: {
    flex: 0,
    backgroundColor:'red',
    justifyContent: 'center',
  }
})
export default MyComponent
