<template>
	<view class="">
		<view class="img-view">
			<image :src="imgUrl" mode=""></image>
		</view>
		<view class="p-2 border-danger border-bottom m-2" v-for="count in 10">
			<view class="my-1 font-md">在App端默认为标题栏透明，当用户向下滚动时，标题栏逐渐由透明转变为不透明</view>
			<view class="my-1 font-md">当用户再次向上滚动时，标题栏又从不透明变为透明状态</view>
			<view class="my-1 font-md">在微信小程序端 导航栏始终为不透明样式</view>
		</view>
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
