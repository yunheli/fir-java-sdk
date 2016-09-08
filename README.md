# uploadSdk

### `FIR的上传sdk`

- FIR的上传下载

- FIR的Swing组件

  ```java
  //引入对应的fir-upload-sdk.jar

  //设置可用
  FirDialog.getInstance().setVisible(true); 

  //设置IDE环境是Eclipse,主要支持IntelliJ IDEA、Eclipse
  //但是两个的方法使用不太兼容
  FirDialog.getInstance().setIdeEnvironmentEclipse(true);

  //设置平台是elipse 主要来统计
  Provider.getInstance().setProviderIde("eclipse");
  ```

  ​

### TODO:

```
代码有很多不足的地方，需要重构，欢迎批评和提issue
```

