<template>
	<view class="">
		<template v-if="!loginStatus">
			<view class="flex align-center p-2" @click="open('login')">
				<view class="flex-1">
					<image :src="avatar" class="size-100 rounded-circle"></image>
				</view>
				<view class="">去登录</view>
			</view>
			
		</template>

		<template v-else>
			<!-- 头像区域 -->
			<view class="flex align-center p-2">
				<image :src="avatar" class="size-100 rounded-circle"></image>
				<view class="flex flex-column flex-1 px-3">
					<text class="font-lg font-weight-bold text-muted">{{ nickname }}</text>
					<view class="">
						<text class="mr-2">总帖子</text>
						<text class="text-warning">{{ posts }}</text>
						<text class="mx-2">今日帖子</text>
						<text class="text-warning">{{ dynamics }}</text>
					</view>
				</view>
				<text class="iconfont icon-jinru"></text>
			</view>

			<!-- 数据区域 -->
			<view class="flex align-center px-2 py-3">
				<view class="flex flex-column flex-1 align-center justify-center">
					<text class="text-primary mb-1">{{ posts }}</text>
					<text class="font-sm text-muted">帖子</text>
				</view>
				<view class="flex flex-column flex-1 align-center justify-center">
					<text class="text-primary mb-1">{{ dynamics }}</text>
					<text class="font-sm text-muted">动态</text>
				</view>
				<view class="flex flex-column flex-1 align-center justify-center">
					<text class="text-primary mb-1">{{ comments }}</text>
					<text class="font-sm text-muted">评论</text>
				</view>
				<view class="flex flex-column flex-1 align-center justify-center">
					<text class="text-primary mb-1">{{ fans }}</text>
					<text class="font-sm text-muted">粉丝</text>
				</view>
			</view>

			<!-- banner区域 -->
			<view class="p-2"><image src="../../static/img/banner.jpg" mode="aspectFill" class="w-100 rounded" style="height: 160rpx;"></image></view>

			<!-- 列表区域 -->
			<view class="mb-1">
			 <uni-list-item showExtraIcon title="浏览历史">
					<text slot="icon" class="iconfont icon-liulan"></text>
				</uni-list-item>
			</view>
			<view class="mb-1">
				<uni-list-item showExtraIcon title="会员VIP">
					<text slot="icon" class="iconfont icon-huiyuanvip"></text>
				</uni-list-item>
			</view>
			<view class="mb-1">
				<uni-list-item showExtraIcon title="帖子审核">
					<text slot="icon" class="iconfont icon-fatie_icon"></text>
				</uni-list-item>
			</view>
		</template>
	</view>
</template>

<script>
import uniListItem from "@/components/uni/uni-list-item/uni-list-item.vue";
export default {
	components: {
		uniListItem
	},
	data() {
		return {
			loginStatus: false,
			nickname: "poicc",
			posts: 20,
			dynamics: 12,
			comments: 180,
			fans: 88
		};
	},
	computed: {
		avatar() {
			return this.loginStatus ? "https://cdn.jsdelivr.net/gh/1802343228/image@main/avatar.3sylmzwhoqi0.png" : "/static/img/nologin.jpeg";
		}
	},
	onNavigationBarButtonTap() {
		uni.showToast({
			title: "点击",
			duration: 2000
		});
	},
	methods: {
		open(path) {
			uni.navigateTo({
				url: `../${path}/${path}`
			});
		}
	}
};
</script>

<style></style>
