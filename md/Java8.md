# Android Studio对于Java8特性的支持

> GitHub地址：[Android Studio对于Java8特性的支持][1]

> 邮箱：fengqi.mao.march@gmail.com

> 博客：[http://blog.csdn.net/u011810138](http://blog.csdn.net/u011810138)

Android Studio从2.1开始官方通过Jack支持Java8，从而使用Lambda等特性。

## Android Studio 3.0版本之前配置

```
android {
    ...
    defaultConfig {
        ...
        jackOptions {
            enabled true
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    ...
}
```

如果没有打开jackOptions的配置，那么会提示如下错误：
```
Error:Jack is required to support java 8 language features. Either enable Jack or remove sourceCompatibility JavaVersion.VERSION_1_8.
```

但是如果打开jackOptions，意味着ButterKnife，Dagger等基于APT的注解框架将无法使用。会提示如下错误：
```
Error:Could not find property 'options' on task ':app:compileDebugJavaWithJack'
```

**解决方法**

使用第三方的Java8兼容插件，[retrolambda](https://github.com/evant/gradle-retrolambda)。

1. 在project的根build.gradle里添加：
    ```
    dependencies {
        classpath 'me.tatarka:gradle-retrolambda:3.2.5'
    }
    ```

2. 然后在module的build.gradle里添加：
    ```
    apply plugin: 'me.tatarka.retrolambda'
    ```

3. 当然下面compileOptions是少不了的：
    ```
    android {
        ...
        compileOptions {
            sourceCompatibility JavaVersion.VERSION_1_8
            targetCompatibility JavaVersion.VERSION_1_8
        }
        ...
    }
    ```

## Android Studio 3.0版本之后配置
```
android {
    ...
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    ...
}
```

[1]: https://github.com/maoqiqi/One/blob/master/md/Java8.md