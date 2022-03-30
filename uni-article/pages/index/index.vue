<template>
	<view class="content">
		<image class="logo" src="/static/logo.png"></image>
		<view class="text-area">
			<text class="title">{{title}}</text>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello'
			}
		},
		onLoad() {
			this.$http.get("/article/id?id="+37).then(res => {
				console.log(res)
				// if (res.code === 1) {
				// 	// this.message.toast('登录成功', type = 'suc');
				// 	// this.$msg.toast('登录成功', type = 'suc');
				// 	uni.setStorageSync("user", res.data);
				// 	uni.switchTab({
				// 		url: "../my/my"
				// 	});
				// } else {
				// 	this.$msg.toast(res.msg);
				// 	return false;
				// }
			});
		},
		methods: {

		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}
</style>
