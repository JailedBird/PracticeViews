# PracticeViews
Android自定义View工程



[玩Android ~~ Android自定义View - 学习路径](https://www.wanandroid.com/route/show/573)







## 参考文档：

### 文章1：

关于View绘制相关对象的流程：https://www.cnblogs.com/huansky/p/11911549.html ， 【**文章的流程相关问题很重要**】 

同系列文章：

- ### [Android View 绘制流程之 DecorView 与 ViewRootImpl](https://www.cnblogs.com/huansky/p/11911549.html)

- ### [Android View 的绘制流程之 Measure 过程详解 (一)](https://www.cnblogs.com/huansky/p/11920454.html)

- ### [Android View 的绘制流程之 Layout 和 Draw 过程详解 (二)](https://www.cnblogs.com/huansky/p/12000771.html)

- ### [Android View 的事件分发原理解析](https://www.cnblogs.com/huansky/p/9656394.html)

- ### [Android 自定义 View 详解](https://www.cnblogs.com/huansky/p/11808234.html)

Activity持有Window（PhoneWindow）对象，Windows获取WindowManager实现为WindowManagerImpl，添加View，最终通过WindowManagerGlobal（间接构建ViewRootImpl）添加DecorView，因此 PhoneWindow和DecorView之间是通过ViewRootImpl构建的控制桥梁；

![img](https://zhaojunchen-1259455842.cos.ap-nanjing.myqcloud.com//img916005-20191208154122390-1845090677.png)



> 文章的问题：
>
> ### **1. 首次 View 的绘制流程是在什么时候触发的？**
>
> 既然开始说到了 View 的绘制流程，那整个流程是什么时候触发的呢？
> 答案是在 ActivityThread.handleResumeActivity 里触发的。
>
> ActivityThread.handleResumeActivity 里会调用 wm.addView 来添加 DecorView，wm 是 WindowManagerImpl；
>
> 最终通过 WindowManagerImpl.addView -> WindowManagerGlobal.addView -> ViewRootImpl.setView -> ViewRootImpl.requestLayout 就触发了第一次 View 的绘制。
>
> ### **2. ViewRootImpl 创建的时机？** 
>
> 从上面流程里可以看到，ViewRootImpl 也是在 ActivityThread.handleResumeActivity 里创建的。具体是在 WindowManagerGlobal.addView 中创建的。这时候主要是为了把 DecorView 添加到页面中（ViewRootImpl.setView ）。具体可以看第一问
>
> ### **3. ViewRootImpl 和 DecorView 的关系是什么？** 
>
> 是在 PhoneWindow.installDecor -> generateLayout 中设置的。在 ViewRootImpl.setView 里，通过 DecorView.assignParent 把 ViewRootImpl 设置为 DecorView 的 parent。
> 所以 ViewRootImpl 和 DecorView 的关系就是 ViewRootImpl 是 DecorView 的 parent。因为 DecorView 是我们布局的顶层，现在我们就知道层层调用 requestLayout 等方法是怎么调用到 ViewRootImpl 里的了。 
>
> ### **4. DecorView 的布局是什么样的？**
>
> 当我们在调用 serContentView 的时候，这时候就需要保证 DecorView 已经初始化了。所以有个 ensureSubDecor() 方法，里面会有个 getDecorView，这时候会去初始化 decorView ，方面后面的使用。
>
> 对于 Activity 的层级，大家应该都看过一张图的描述，Activity -> PhoneWindow -> DecorView -> [title_bar, content]，其中 DecorView 里包括了 title_bar 和 content 两个 View，不过这个是默认的布局，实际上根据不同的主题样式，DecorView 对应有不同的布局。
> 图中所包含的 title_bar 和 content 对应的是 R.layout.screen_simple 布局。
> 那么这么多布局，是在什么时候设置的呢？
>
> ### **5. DecorView 的创建时机？**
>
> 上面说 DecorView 布局的时候，其实我们也看到了，在 PhoneWindow.installDecor -> generateDecor 其实就是创建 DecorView。
> 那 installDecor 是什么时候调用的呢？
> 调用链是 Activity.setContentView -> PhoneWindow.setContentView -> installDecor说到这里那就继续会想到，Activity.setContentView 的流程是什么呢？
>
> ### **6. setContentView 的流程**
>
> setContentView 流程比较简单，会调用 PhoneWindow.setContentView。
> 其中做的事情是两个：
>
> 1. 创建 DecorView
> 2. 根据 layoutResId 创建 View 并添加到 DecorView 中
>
> ### **7. Activity、PhoneWindow、DecorView、ViewRootImpl 的关系？**
>
> 其实上面的问题中，我们经常会说到 PhoneWindow 这个角色，PhoneWindow 其实是 Window 的唯一子类，是 Activity 和 View 交互系统的中间层，而 DecorView 是整个 View 层级的最顶层，ViewRootImpl 是 DecorView 的 parent，但是他并不是一个真正的 View，只是继承了 ViewParent 接口，用来掌管 View 的各种事件，包括 requestLayout、invalidate、dispatchInputEvent 等等。
>
> ### **8. PhoneWindow 的创建时机？**
>
> 既然上面又提到了 PhoneWindow，那么 PhoneWindow 是什么时候创建的呢？
> 是在 Activity.attach 里创建的，而 Activity.attach 又是在 ActivityThread.performLaunchActivity 里创建的。
> 这里就又能引申出 Activity 的启动流程，这里就先不讲了。
>
> ### **9. 如何触发重新绘制？**
>
> 既然上面说到 View 的绘制流程，那我们怎么触发 View 的重新绘制呢？
> 就是调用 requestLayout 和 invalidate。
