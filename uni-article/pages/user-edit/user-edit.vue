<template>
	<view class="px-2">
		<view class="bg-white border-bottom my-1 rounded">
			<uni-list-item title="头像" @click="changeAvatar">
				<view class="flex align-center" slot="right">
					<image :src="avatar" class="rounded-circle size-100">
					</image>
					<text class="iconfont icon-bianji1 ml-2"></text>
				</view>
			</uni-list-item>
		</view>

		<view class="bg-white border-bottom my-1 rounded">
			<uni-list-item title="昵称">
				<view class="flex align-center" slot="right">
					<input class="uni-input text-right" v-model="nickname" />
					<text class="iconfont icon-bianji1 ml-2"></text>
				</view>
			</uni-list-item>
		</view>

		<view class="bg-white border-bottom my-1 rounded">
			<uni-list-item title="性别" @click="changeGender">
				<view class="flex align-center" slot="right">
					<text>{{ genderArray[gender] }}</text>
					<text class="iconfont icon-bianji1 ml-2"></text>
				</view>
			</uni-list-item>
		</view>

		<picker mode="date" :value="birthday" @change="onDateChange">
			<view class="bg-white border-bottom my-1">
				<uni-list-item title="生日">
					<view class="flex align-center" slot="right">
						<text>{{ birthday }}</text>
						<text class="iconfont icon-bianji1 ml-2"></text>
					</view>
				</uni-list-item>
			</view>
		</picker>

		<view class="bg-white border-bottom my-1 rounded">
			<uni-list-item title="地址" @click="showCityPicker">
				<view class="flex align-center" slot="right">
					<text>{{ pickerText }}</text>
					<text class="iconfont icon-bianji1 ml-2"></text>
				</view>
			</uni-list-item>
		</view>

		<view class="py-2 px-3">
			<button class="text-white rounded-circle bg-pink">
				完成
			</button>
		</view>

		<mpvue-city-picker :themeColor="themeColor" :pickerValueDefault="cityPickerValueDefault" ref="mpvueCityPicker"
			@onConfirm="onConfirm"></mpvue-city-picker>
	</view>
</template>

<script>
	const genderArray = ['男', '女', '保密'];
	import uniListItem from '@/components/uni/uni-list-item/uni-list-item.vue';
	import mpvueCityPicker from '@/components/uni/mpvue-citypicker/mpvueCityPicker.vue';

	export default {
		components: {
			uniListItem,
			mpvueCityPicker
		},
		data() {
			return {
				genderArray: ['男', '女', '保密'],
				themeColor: '#fb6084',
				cityPickerValueDefault: [0, 0, 1],
				pickerText: '',
				nickname: '',
				gender: 0,
				avatar: '',
				birthday: '',
				user: {}
			};
		},
		onShow() {
			this.user = uni.getStorageSync('user')
			this.nickname = this.user.nickname
			this.avatar = this.user.avatar
			this.gender = this.user.gender
		},
		computed: {
			genderText() {
				return genderArray[this.gender];
			}
		},
		methods: {
			//显示三级联动城市选择组件
			showCityPicker() {
				this.$refs.mpvueCityPicker.show();
			},
			// 三级联动选择组件提交事件，监听相应的值并显示
			onConfirm(e) {
				this.pickerText = e.label;
			},
			// 监听日期选取，改变生日显示的值
			onDateChange(e) {
				this.birthday = e.detail.value;
			},
			// 修改头像
			changeAvatar() {
				uni.chooseImage({
					count: 1,
					sizeType: ['compressed'],
					sourceType: ['album', 'camera'],
					success: (chooseImageRes) => {
						const tempFilePaths = chooseImageRes.tempFilePaths;
						// console.log(tempFilePaths[0]);
						this.avatar = tempFilePaths[0]
						uni.uploadFile({
								url: 'http://9c6g5xk0.dnat.tech/api/v1/users/upload',
								filePath: tempFilePaths[0],
								name: 'file' //一定要和后端接口的入参名字一样
							})
							.then(result => {
								const res = JSON.parse(result[1].data)
								console.log(res.data)
								this.avatar = res.data
							})
							.catch(err => {
								console.log(err);
							});
					}
				});
			},
			// 修改性别
			changeGender() {
				uni.showActionSheet({
					itemList: genderArray,
					success: res => {
						this.gender = res.tapIndex;
						if (this.gender == 0) {
							this.genderText = '女';
						} else if (this.gender == 1) {
							this.genderText = '男';
						} else if (this.gender == 2) {
							this.genderText = '保密';
						}
					}
				});
			},
		}
	};
</script>

<style></style>
