<template>
	<view class="px-2">
		<input class="uni-input p-2 bg-white border-bottom my-2" type="password" placeholder="输入新密码" v-model="newpassword" />
		<input class="uni-input p-2 bg-white border-bottom" type="password" placeholder="输入确认密码" v-model="renewpassword" />

		<view class="py-2"><button class="bg-pink text-white rounded-circle" :disabled="disabled" :class="disabled ? 'bg-pink-disabled' : ''" @click="submit">设置</button></view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			newpassword: "",
			renewpassword: "",
			user: {}
		};
	},
	computed: {
		disabled() {
			return this.newpassword == "" || this.renewpassword == "";
		}
	},
	onLoad() {
		this.user = uni.getStorageSync("user");
	},
	methods: {
		// 验证密码
		check() {
			if (this.newpassword !== this.renewpassword) {
				this.$msg.toast("两次密码不一致", "err");
				return false;
			}
			return true;
		},
		submit() {
			if (!this.check()) {
				return;
			}
			let data = {
				phone: this.user.phone,
				password: this.newpassword,
				nickname: this.user.nickname,
				avatar: this.user.avatar,
				gender: this.user.gender,
				birthday: this.user.birthday,
				address: this.user.address,
				bg: this.user.bg
			};
			this.$http.post("/users/update", data, "json").then(res => {
				uni.showModal({
					title: "修改成功,请重新登录",
					success() {
						//修改成功 重新登录
						uni.navigateTo({
							url: "../login/login"
						});
					}
				});
			});
		}
	}
};
</script>

<style></style>
