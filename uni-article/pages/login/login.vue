<template>
	<view>
		<!-- #ifndef MP -->
		<uni-status-bar></uni-status-bar>
		<view class="iconfont icon-guanbi flex  align-center justify-start font-lg size-100 p-2" @tap="back()"></view>

		<!-- #endif -->
		<template v-if="choice">
			<!-- 账号密码登录 -->
			<view>
				<view class="text-center" style="padding-top: 90rpx;padding-bottom: 90rpx;"><text class="h3 text-body">账号密码登录</text></view>
				<view class="flex p-2 mb-1"><input type="text" v-model="phone" placeholder="手机号" class="border-bottom p-2 flex-1" /></view>
				<view class="flex p-2 mb-1">
					<input type="password" v-model="password" placeholder="请输入密码" class="p-2 flex-1" />
					<view class="font-sm text-muted px-3 flex align-center">忘记密码?</view>
				</view>
				<view class="p-2"><button class="rounded-circle bg-pink text-white" @tap="login">登录</button></view>
			</view>

			<view class="text-center mt-3 text-blue font-sm">
				<text @tap="choice = !choice">手机验证码登录</text>
				<text>|</text>
				<text class="ml-1">登录遇到问题</text>
			</view>

			<view class="flex align-center justify-center text-muted mt-3">
				注册即代表同意
				<text class="text-blue">《xxx协议》</text>
				<!-- <input type="checkbox"/> -->
			</view>
		</template>
		<template v-else>
			<view>
				<view class="text-center" style="padding-top: 90rpx;padding-bottom: 90rpx;"><text class="h3 text-body">手机验证码登录</text></view>
				<view class="flex px-2 mb-3 ">
					<view class="font px-2 flex align-center ">+86</view>
					<input type="text" v-model="phone" placeholder="手机号" class="border-bottom p-2 flex-1" />
				</view>
				<view class="flex px-2 mb-3">
					<input type="number" v-model="verifyCode" placeholder="请输入验证码" class="p-2 flex-1" />
					<view @tap="getCode" :class="limitTime > 0 ? 'bg-ponk-disabled' : 'bg-pink'" class="font-sm text-white px-3 flex align-center bg-pink rounded">获取验证码</view>
				</view>
				<view class="p-2"><button class="rounded-circle bg-pink text-white" @tap="login">登录</button></view>
			</view>

			<view class="text-center mt-3 text-blue font-sm">
				<text @tap="choice = !choice">手机验证码登录</text>
				<text>|</text>
				<text class="ml-1">登录遇到问题</text>
			</view>

			<view class="flex align-center justify-center text-muted mt-3">
				注册即代表同意
				<text class="text-blue">《xxx协议》</text>
				<!-- <input type="checkbox"/> -->
			</view>
		</template>
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
			choice: true,
			phone: "",
			password: "",
			verifyCode: "",
			limitTime: 0,
			disabled: false
		};
	},
	methods: {
		back() {
			uni.showToast({
				title: "返回",
				duration: 2000
			});
		},
		validate() {
			//手机号正则
			var mPattern = /^1[34578]\d{9}$/;
			if (!mPattern.test(this.phone)) {
				uni.showToast({
					title: "手机号格式不正确",
					icon: "none"
				});
				return false;
			}
			// ...更多验证
			return true;
		},
		login() {
			//账号密码登录
			let data = {
				username: '陈蓉琪',
				password: '123'
			}
			const url = 'http://106.14.169.149:8071/login'
			uni.request({
				url:url,
				method:'post',
				data: data
			}).then((res) => {
				console.log(res[1].data.data)
				uni.showToast({
					title:res[1].data.data.nickname
				})
			})
		},
		getCode() {
			if (this.limitTime > 0) {
				return;
			}else if(!this.validate()) {
				return;
			} else {
				this.disabled = true
			}
			this.limitTime = 20;
			let timer = setInterval(() => {
				if(this.limitTime >= 1) {
					this.limitTime --
				} else {
					this.limitTime = 0
					clearInterval(timer)
				}
			},1000)
		}
	}
};
</script>

<style></style>
