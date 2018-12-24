# Tools2018
Android tools 包含的类库：
#### utilbanner:banner轮播图
	来源 com.bigkoo:convenientbanner:2.0.5
#### utilindicator:横向指示器，可以作为头部Toolbar/底部按钮/页面切换等
	来源 com.github.hackware1993:MagicIndicator:1.5.0
#### utilnavigation:底部导航
	来源 com.github.ittianyu:BottomNavigationViewEx:1.2.4
#### 添加主题样式(四种方式)
	两种方式：举例修改首页背景色
	1.从xml配置：setTheme(SkinManager.get().getThemeId(ThemeStyles.MAIN_ACTIVITY_THEME));
	2.从code配置：SkinManager.getSkinStrategy().setMainActivityBgColor(flowLayout);
	3.通过SkinImageView SkinLinearLayout SkinTextView change skin.
	4.add utilskin library to change skin.
