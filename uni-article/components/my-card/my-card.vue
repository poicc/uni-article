<template>
	<view class="flex border-bottom border-danger" @click="open">
		<view class="col-3 p-2">
			<image :src="article.cover" mode="aspectFill" 
			style="width: 220rpx;height: 140rpx;" class="rounded" />
			<view class="text-body">{{ article.category }}</view>
			<view class="text-muted font-sm">{{ article.createTime }}</view>
		</view>

		<view class="ml-2 p-2">
			<view class="font-body font-weight-bold">{{ article.title }}</view>
			<view class="mt-1 font-sm ">
				{{ article.summary.length > 35 ? article.summary.substring(0, 35) : article.summary }}...
			</view>
			<view class="mt-1">
				<span class="mr-2 bg-color font-sm" v-for="(tag, index) in article.tagList" :key="index">
					#{{ tag.tagName }}
				</span>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name: 'MyCard',
		props: ['article'], //通过props传值，父组件中将article传过来
		computed: {
			//计算属性，文章摘要的长度超出，就用。。。代替
			// summary() {
			// 	let summary = this.article.summary;
			// 	if (summary && summary.length > 30) {
			// 		summary = summary.substring(0, 30) + '...';
			// 	}
			// 	return summary;
			// }
		},
		methods: {
			open() {
				//在子组件my-card.vue中将点击事件传回父组件index.vue
				this.$emit('open');
			}
		}
	};
</script>

<style scoped>
	.bg-color {
		color: #ffffff;
		background-image: linear-gradient(to right, #bf30ac 0%, #0f9d58 100%);
		opacity: 0.7;
		padding: 5rpx;
		border-radius: 20rpx;
	}
</style>
