<template>
	<view class="p-2">
		<!-- #ifndef MP-WEIXIN -->
		<uni-status-bar></uni-status-bar>
		<view class="banner p-2" :style="{ '--bgurl': 'url(' + user.bg + ')' }">
			<view class="avatar-con flex">
				<image :src="user.avatar" class="size-100 rounded-circle" mode=""></image>
				<view class="flex-1 flex justify-end">
					<text>{{user.nickname}}</text>
					<text>{{user.address}}</text>
				</view>
			</view>
			<!-- <view class="w-100 bg-pink mt-5" style="z-index: 50;height: 200rpx;"></view> -->
			
		</view>
		<!-- #endif -->
	</view>
</template>

<script>
import uniStatusBar from "@/components/uni/uni-status-bar/uni-status-bar.vue";
export default {
	components: {
		uniStatusBar
	},
	data() {
		return {
			user: {}
		};
	},
	onShow() {
		this.user = uni.getStorageSync("user");
	},
	methods: {}
};
</script>

<style scoped>
.banner {
	padding-top: 290rpx;
	box-sizing: border-box;
	position: relative;
	width: 100%;
	height: 500rpx;
}
.banner::before {
	content: "";
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 500rpx;
	/* background-image: linear-gradient(45deg,#1ec07d,#0ca7bf); */
	background-image: var(--bgurl);
	background-repeat: no-repeat;
	background-size: cover;
	border-radius: 0 0 50% 50%/0 0 100% 100%;
	transform: scaleX(1.5);
	z-index: 10;
}
.avatar-con {
	z-index: 20;
	position: absolute;
	left: 20rpx;
}
</style>
