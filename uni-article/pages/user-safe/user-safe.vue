<template>
	<view class="px-2">
		<view class="bg-white border-bottom mt-2">
			<uni-list-item title="修改密码" @click="open('edit-password')"></uni-list-item>
		</view>
		<view class="bg-white border-bottom">
			<uni-list-item title="绑定手机" @click="open('bind-phone')"></uni-list-item>
		</view>
		<view class="bg-white border-bottom">
			<uni-list-item title="绑定微信"></uni-list-item>
		</view>
		<view class="bg-white border-bottom">
			<uni-list-item title="绑定QQ"></uni-list-item>
		</view>
	</view>
</template>

<script>
	import uniListItem from '@/components/uni/uni-list-item/uni-list-item.vue';

	export default {
		components: {
			uniListItem
		},
		data() {
			return {};
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
