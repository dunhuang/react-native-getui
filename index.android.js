/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  DeviceEventEmitter,
  Alert,
  NativeModules
} from 'react-native';

class getuiexample extends Component {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          Welcome to React Native!
        </Text>
        <Text style={styles.instructions}>
          To get started, edit index.android.js
        </Text>
        <Text style={styles.instructions}>
          Shake or press menu button for dev menu
        </Text>
      </View>
    );
  }
  componentDidMount() {
    let that = this;
    this.pushreceivedListener=DeviceEventEmitter.addListener('pushreceived', (e) => that.onPushReceived(e));
    this.getclientidListener=DeviceEventEmitter.addListener('getclientid', (e) => that.onGetClientId(e));
  }
  componentWillUnMount (){
    this.pushreceivedListener.remove();
    this.getclientidListener.remove();
  }
  onPushReceived(e){
    console.log(e);
    Alert.alert(
      '系统通知',
      e.payload,
      [
        {text: '知道了'},
      ]
    );
  }
  onGetClientId(e){
    console.log(e)
    Alert.alert(
      'cliendid',
      'clientid='+e.clientid,
      [
        {text: '知道了'},        
      ]
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});

AppRegistry.registerComponent('getuiexample', () => getuiexample);
