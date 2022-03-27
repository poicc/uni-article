<template>
	<view class="h-100 bg-white">
		<!-- #ifndef MP -->
		<uni-status-bar></uni-status-bar>
		<view class="iconfont icon-guanbi flex  align-center justify-start font-lg size-100 p-2" @tap="back()"></view>

		<!-- #endif -->
		<template v-if="choice">
			<!-- 账号密码登录 -->
			<view>
				<view class="text-center" style="padding-top: 130rpx;padding-bottom: 130rpx;"><text class="h3 text-body">账号密码登录</text></view>
				<view class="flex p-1 mb-1"><input type="text" v-model="phone" placeholder="手机号" class="border-bottom p-2 flex-1" /></view>
				<view class="flex p-1 mb-1">
					<input type="password" v-model="password" placeholder="请输入密码" class="p-2 flex-1" />
					<view class="font-sm text-muted px-3 flex align-center">忘记密码?</view>
				</view>
				<view class="p-2">
					<button class="rounded-circle bg-pink text-white" :class="disabled ? 'bg-ponk-disabled' : 'bg-pink'" :loading="loading" @tap="passwordLogin()">
						{{ loading ? "登录中..." : "登录" }}
					</button>
				</view>
			</view>

			<view class="text-center mt-3 text-blue font-sm">
				<text @tap="choice = !choice">验证码登录</text>
				<text class="mx-2">|</text>
				<text>登录遇到问题</text>
			</view>

			<view class="text-center text-muted mt-3">
				<checkbox-group @change="checkboxChange">
					<label class="flex justify-center ">
						<checkbox :value="checked" :checked="false" />
						<view class="">
							<text>登录即代表同意</text>
							<text class="text-blue">《xxx协议》</text>
						</view>
					</label>
				</checkbox-group>
			</view>
		</template>

		<template v-else>
			<view>
				<view class="text-center" style="padding-top: 130rpx;padding-bottom: 130rpx;"><text class="h3 text-body">手机验证码登录</text></view>
				<view class="flex p-1 mb-1 ">
					<view class="border-right font px-2 flex align-center">+86</view>
					<input type="text" v-model="phone" placeholder="手机号" class="border-bottom p-2 flex-1" />
				</view>
				<view class="flex p-2 mb-1">
					<input type="text" v-model="verifyCode" placeholder="请输入验证码" class="p-2 flex-1" />
					<view @tap="getCode" :class="limitTime > 0 ? 'bg-ponk-disabled' : 'bg-pink'" class="font-sm text-white px-3 flex align-center bg-pink rounded">
						{{ limitTime > 0 ? limitTime + "s" : "获取验证码" }}
					</view>
				</view>
				<view class="p-2"><button class="rounded-circle bg-pink text-white" @tap="smsLogin()" :class="disabled ? 'bg-ponk-disabled' : 'bg-pink'">登录</button></view>
			</view>

			<view class="text-center mt-3 text-blue font-sm">
				<text @tap="choice = !choice">账号密码登录</text>
				<text class="mx-2">|</text>
				<text>登录遇到问题</text>
			</view>

			<view class="text-center text-muted mt-3">
				<checkbox-group @change="checkboxChange">
					<label class="flex justify-center ">
						<checkbox :value="checked" :checked="false" />
						<view class="">
							<text>注册即代表同意</text>
							<text class="text-blue">《xxx协议》</text>
						</view>
					</label>
				</checkbox-group>
			</view>

			<!-- 第三方登录 -->
			<view class="flex align-center px-5 py-3 mt-3">
				<view class="flex-1 flex align-center justify-center">
					<view @tap="appLogin" class="iconfont icon-weixin bg-success font-lg text-white flex align-center justify-center rounded-circle size-100"></view>
				</view>
				<view class="flex-1 flex align-center justify-center">
					<view class="iconfont icon-QQ bg-primary font-lg text-white flex align-center justify-center rounded-circle size-100"></view>
				</view>
				<view class="flex-1 flex align-center justify-center">
					<view class="iconfont icon-xinlangweibo bg-warning font-lg text-white flex align-center justify-center rounded-circle size-100"></view>
				</view>
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
			phone: "18851699003",
			password: "123456",
			verifyCode: "",
			limitTime: 0,
			disabled: true,
			loading: false,
			checked: "选中"
		};
	},
	methods: {
		back() {
			uni.navigateBack({
				delta: 1
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
		passwordLogin() {
			const flag = this.validate();
			if (!flag) {
				return;
			}
			//账号密码登录
			let data = {
				phone: this.phone,
				password: this.password
			};
			this.$http.post("/users/login/pass", data, "json").then(res => {
				if (res.code === 1) {
					// this.message.toast('登录成功', type = 'suc');
					// this.$msg.toast('登录成功', type = 'suc');
					uni.setStorageSync("user", res.data);
					uni.switchTab({
						url: "../my/my"
					});
				} else {
					this.$msg.toast(res.msg);
					return false;
				}
			});
		},
		smsLogin() {
			let data = {
				phone: this.phone,
				code: this.verifyCode
			};
			this.$http.post("/users/login/sms", data, "json").then(res => {
				console.log(res);
				if (res.code === 1) {
					this.$msg.toast("登录成功");
					uni.setStorageSync("user", res.data);
					uni.switchTab({
						url: "../my/my"
					});
				} else {
					this.$msg.toast(res.msg);
					return false;
				}
			});
		},
		checkboxChange(e) {
			let values = e.detail.value;
			console.log(values);
			if (values.includes("选中")) {
				this.disabled = false;
			} else {
				this.disabled = true;
			}
		},
		appLogin() {
			let self = this;
			uni.login({
				provider: "weixin",
				success: function(loginRes) {
					console.log(loginRes);
					uni.getUserInfo({
						provider: "weixin",
						success: infoRes => {
							console.log(infoRes);
							let wxLoginDto = {
								wxOpenId: infoRes.userInfo.openId,
								nickname: infoRes.userInfo.nickName,
								avatar: infoRes.userInfo.avatarUrl,
								gender: infoRes.userInfo.gender
							};
							self.$http.post("/users/login/wx", wxLoginDto, "json").then(res => {
								console.log(res);
								if (res.code === 1) {
									uni.showModal({
										title: "登录成功",
										success() {
											uni.setStorageSync("user", res.data);
											// 已经绑定手机号,直接进主页
											if (res.data.phone.length > 0) {
												uni.switchTab({
													url: "../my/my"
												});
											} else {
												// 没有绑定，调到绑定手机页面
												uni.navigateTo({
													url: "../bind-phone/bind-phone"
												});
											}
										}
									});
								} else {
									uni.showModal({
										title: "登录失败"
									});
									return false;
								}
							});
						}
					});
				}
			});
		},
		getCode() {
			// 防止重复获取
			if (this.limitTime > 0) {
				return;
			}
			// 验证手机号没通过
			// if (!this.validate()) {
			// 	return;
			// }
			// 请求验证码接口
			this.$http.post("/users/sms?phone=" + this.phone).then(res => {
				if (res.code === 1) {
					this.limitTime = 60;
					let timer = setInterval(() => {
						if (this.limitTime >= 1) {
							this.limitTime--;
						} else {
							this.limitTime = 0;
							clearInterval(timer);
						}
					}, 1000);
				} else {
					this.$msg.toast(res.msg);
				}
			});
		}
	}
};
</script>

<style></style>
