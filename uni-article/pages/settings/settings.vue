<template>
	<view><uni-list-item title="账号与安全"></uni-list-item>
	<uni-list-item title="资料编辑"></uni-list-item>
	<uni-list-item title="清除缓存"></uni-list-item>
	<uni-list-item title="意见反馈"></uni-list-item>
	<uni-list-item title="关于社区"></uni-list-item>
	<view class="p-2">
		<button class="rounded-circle bg-pink text-white shadow" @tap="open('login')">退出登录</button>
	</view>
	</view>
</template>

<script>
import uniListItem from '@/components/uni/uni-list-item/uni-list-item.vue';
export default {
	components:{
		uniListItem
	},
	data() {
		return {
			// 缓存大小
			currentSize: 10010,
			
		};
	},
	methods: {
		open(path){
			uni.navigateTo({
				url:`../${path}/${path}`
			})
		}
	}
};
</script>

<style></style>
