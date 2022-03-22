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
						<text class="text-warning">{{ data[0].posts }}</text>
						<text class="mx-2">今日帖子</text>
						<text class="text-warning">{{ data[1].dynamics }}</text>
					</view>
				</view>
				<text class="iconfont icon-jinru text-muted" @tap="open('settings')"></text>
			</view>

			<!-- 数据区域 -->
			<view class="flex align-center px-2 py-3">
				<view v-for="(item,index) in data" :key="index" class="flex flex-column flex-1 align-center justify-center">
					<text class="text-body font-weight-bold font-md mb-1">{{ item.num }}</text>
					<text class="font-sm text-muted">{{ item.title }}</text>
				</view>
			</view>

			<!-- banner区域 -->
			<view class="p-2">
				<image src="/static/img/banner.jpg" mode="widthFix" class="w-100 rounded"></image>
			</view>

			<!-- 列表区域 -->
			<view class="pt-2 bg-white mt-1">
				<view class="border-bottom">
				 <uni-list-item showExtraIcon title="浏览历史">
						<text slot="icon" class="iconfont icon-liulan text-body font-md"></text>
					</uni-list-item>
				</view>
				<view class="border-bottom">
					<uni-list-item showExtraIcon title="会员VIP">
						<text slot="icon" class="iconfont icon-huiyuanvip text-body font-md"></text>
					</uni-list-item>
				</view>
				<view class="border-bottom">
					<uni-list-item showExtraIcon title="帖子审核">
						<text slot="icon" class="iconfont icon-fatie_icon text-body font-md"></text>
					</uni-list-item>
				</view>
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
			loginStatus: true,
			nickname: "poicc",
			data: [{
				title: '帖子',
				num: 90
			},
			{
				title: '动态',
				num: 12
			},
			{
				title: '评论',
				num: 180
			},
			{
				title: '粉丝',
				num: 88
			},
			]
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
