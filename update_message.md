## 更新说明

#### v2.2.3

    * 修改adapter 数据相关操作方法
    * banner 去掉泛型返回
    * demo 添加更新数据注释
    * 更新viewpager2到beta2

#### v2.2.1-2.2.2

* 对kotlin使用的兼容支持
* 对kotlin使用demo进行了修改
* 新版本都将迁移至MavenCentral
* 更新viewpager2到1.1.0-alpha01
* 修改bug

#### v2.2.0

* 自定义属性中增加可选择设置banner圆角的具体方向
* 增加DrawableIndicator指示器,支持直接设置图片了，可以通过构造方法或者自定义属性设置
* 合并ZakAnun朋友提交的优化监听器的设置以及减少 onPageScrolled 回调
* 对demo进行了修改
* 新的版本迁移将至jitpack仓库

#### v2.1.0

* 修改banner圆角锯齿
* 修改画廊支持左右不同的大小
* 内置增加了默认的图片适配器，方便偷懒的人，但是其他类型的没有内置是因为可变性太高了，大家自定义就好
* 自定义参数全部修改成banner_开头了，避免和其他库冲突。麻烦使用的大家修改下！

#### v2.0.12

* 修改了new banner时无默认参数的问题
* 自带的指示器支持选中和默认的宽度
* setAdapter支持设置是否无限循环

#### v2.0.11

* 合并了lvshaojun朋友提交的代码

#### v2.0.9

* 增加了addBannerLifecycleObserver()方法，给banner添加生命周期观察者，将生命周期交给banner自己处理，
  你也可以不使用这种方式自己象以前的方式自己控制，具体可以参考文档和demo
* 修改了DepthPageTransformer效果，现在使用这种效果返回的位置应该没有问题了
* 其他优化

#### v2.0.8

* 修复bug
* 增加混淆配置

#### v2.0.7

* 修复画廊问题
* 增加了多种PageTransformer
* 增加滑动距离方法
* demo中提供了adapter数据操作示例
* 工具类中增加view圆角裁剪方法
* 增加魅族轮播效果

#### v2.0.6

* 修复指示器无法设置为白色问题
* 新增销毁方法
* 调小滑动距离

#### v2.0.5

* demo中增加tv遥控器控件切换，我没有真机测试，所以只放到了demo中，使用的朋友有TV真机的帮忙测试下反馈给我哈！
* 增加了view的添加和分离回调时，分别暂停和开始轮播，主要解决滑动嵌套时不可见轮播。这个也可以自己监听实现哈

#### v2.0.3-2.0.4

    banner 修复了几个紧急bug，圆角绘制引起的残影和焦点问题，替换layoutmanager为null的异常

#### v2.0.2

    banner bug修复

* 有朋友想要在引导页使用banner，所以增加了is_infinite_loop属性来控制是否能无限循环
* 增加了设置开始轮播位置的方法
* 修复圆角裁剪和首次设置开始位置不对的bug
* demo中增加了类似淘宝详情banner的案例，第一个放视频，不能无限循环
* 设置指示器方法调整，demo增加自定义数字指示器案例
* 对ProxyLayoutManger进行的优化

#### v2.0.1

    banner bug修改

* 解决一系列嵌套banner的事件冲突和解决方案
  （如果我没有考虑到的场景，大家再反馈或者提交方案）

#### v2.0.0

    banner 正式版发布

* 改变banner圆角实现方式，兼容低版本
* 新增两种指示器效果，基本能满足大部分需求了

#### v2.0.0-beta

    banner 测试版发布

* 修改banner内部轮播逻辑
* 增加设置画廊效果方法
* 内置有2种官方Transformer效果，更多效果你可以自定义
* 优化切换事件和点击事件,去掉onBannerChanged方法
* 新增轮播滑动时间控制，感谢zguop，这里的具体实现方法来自zguop的项目
* 支持多PageTransformer组合设置

#### v2.0.0-alpha03

    banner 预览版使用中的问题优化。
    这里要感谢BoldFruit朋友修复了一个bug；我爷爷得了癌症住院了需要照顾，公司事情也多，所以更新频率会慢很多。希望大家理解！
    不过我更希望大家要是发现bug，亦可以自己修复提交，有什么想法新增的功能也可以提交过来。可以提供一个良好的开源环境哈！
    还有不要一直夺命连环催，大家都有自己的事情，多多理解！

* 增加支持布局文件中使用自定义指示器了（自己发挥）
* 增加支持Android5.0以上的设置banner圆角方法
* 其他问题修复（有些实在是不好复现加上有点忙，只修改了部分bug）

#### v2.0.0-alpha02

    banner 预览版使用中的问题优化

* #666 修改orientation自定义属性与其他库冲突问题：改成banner_orientation
* 增加RecyclerView嵌套banner和ConstraintLayout中使用banner测试demo
* #669 RecyclerView嵌套banner 轮播优化代码建议，demo中可以查看
* 其他问题修复

#### v2.0.0-alpha

    banner 2.0全新基础升级，目前是预览版，使用中的问题慢慢迭代

* 使用了ViewPager2为基础控件
* 支持了androidx兼容包
* 方便了UI、Indicator自定义（现在还是基础版本，后面再提升）
* 依赖包目前只需要导入了ViewPager2
