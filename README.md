# BaseDialogBuilder 技术文档

## 概述
`BaseDialogBuilder` 是一个用于构建和管理自定义对话框的基类。它提供了一系列的配置方法，允许开发者自定义对话框的外观和行为。

## 类功能
- 创建对话框实例
- 设置对话框的布局、位置、动画等属性
- 提供接口以供子类实现特定的布局初始化逻辑

## 成员变量
- `context`: 上下文，用于获取系统服务和资源。
- `dialog`: 对话框实例。
- `resource`: 对话框布局资源ID。
- `curGravity`: 对话框显示位置，默认为屏幕底部。
- `curCancel`: 设置点击对话框外部是否关闭对话框，默认为true。
- `curWidth`: 对话框宽度，默认为屏幕宽度。
- `curHeight`: 对话框高度，默认为包裹内容。
- `curLayout`: 当前对话框的布局视图。
- `curTheme`: 对话框的主题样式。
- `animationStyle`: 对话框显示和消失的动画样式。
- `onKeyListener`: 点击返回键的回调。

## 核心方法
### 创建对话框
```kotlin
open fun createDialog(): BaseDialogBuilder
```
创建并配置对话框实例。此方法必须首先被调用以初始化对话框。

### 初始化布局视图
```kotlin
open fun initView(layout: View)
```
由子类实现，用于初始化对话框的布局视图。

### 设置对话框主题
```kotlin
open fun setDialogTheme(theme: Int): BaseDialogBuilder
```
设置对话框的主题样式。

### 设置对话框动画
```kotlin
open fun setDialogAnimations(resId: Int): BaseDialogBuilder
```
设置对话框的显示和消失动画。

### 添加对话框关闭监听器
```kotlin
open fun addDismissListener(listener: DialogInterface.OnKeyListener): BaseDialogBuilder
```
添加对话框关闭事件的监听器。

### 设置布局参数
```kotlin
open fun setLayoutParams(width: Int, height: Int): BaseDialogBuilder
```
设置对话框的布局参数，包括宽度和高度。

### 设置对话框位置
```kotlin
open fun setGravity(gravity: Int): BaseDialogBuilder
```
设置对话框的显示位置。

### 显示对话框
```kotlin
open fun show(): Dialog
```
显示配置好的对话框。

## 使用说明
1. 继承 `BaseDialogBuilder` 类并实现 `initView` 方法以自定义对话框布局。
2. 使用 `createDialog` 方法初始化对话框及其属性。
3. 通过链式调用其他配置方法来设置对话框的各种属性。
4. 最后，调用 `show` 方法来显示对话框。

## 示例
```kotlin
class MyDialogBuilder : BaseDialogBuilder() {
    override fun initView(layout: View) {
        // 自定义布局初始化逻辑
    }
}

// 使用示例
val dialogBuilder = MyDialogBuilder()
    .createDialog()
    .setDialogTheme(R.style.MyDialogTheme)
    .setLayoutWidth(ViewGroup.LayoutParams.MATCH_PARENT)
    .show()
```

## 注意事项
- 必须先调用 `createDialog` 方法来初始化对话框。
- 对话框的所有配置应在 `createDialog` 方法调用之后完成。
- 确保在对话框显示前设置了正确的布局资源ID。