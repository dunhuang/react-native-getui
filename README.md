# react-native-getui
This is a react native demo for getui android

[个推的android实现]

第一次写java，写的太简单，等我有空学深入了再改成插件~~

### Steps  步骤
- 在build.gradle(project:…)里增加getui的Maven库地址

```
//个推地址 maven {     url "http://mvn.gt.igexin.com/nexus/content/repositories/releases/" }
```

- 在build.gradle(Module:app)里增加

```
dependences:{
…,
compile 'com.getui:sdk:2.8.1.0' compile 'com.getui:ext:2.0.3'
}
```

在defaultConfig里增加参数，注意填上你自己的应用配置信息

```
manifestPlaceholders = [
    GETUI_APP_ID : "APP_ID",
    GETUI_APP_KEY : "APP_KEY",
    GETUI_APP_SECRET : "APPSECRET",
    PACKAGE_NAME : applicationId
]
```

- js的实现

```    
//透传消息监听
    this.pushreceivedListener=DeviceEventEmitter.addListener('pushreceived', ...);

//cid注册监听
    this.getclientidListener=DeviceEventEmitter.addListener('getclientid', ...);
   ```
  
## Contact 联系
shiyh2012@foxmail.com

qq: 573329