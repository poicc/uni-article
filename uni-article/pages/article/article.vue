<template>
	<view class="px-2">
		<view class="flex align-center p-2">
			<image :src="article.avatar" class="rounded-circle size-100"></image>
			<view class="flex flex-column flex-1 px-2">
				<text class="font-md"> {{article.nickname}}</text>
				<text class="font-sm  font-weight-light">{{ article.creaTime }}</text>
			</view>
		</view>

		<!-- 文章封面图、摘要、标签等信息 -->
		<view class="flex flex-column justify-center align-start p-2">
			<view class="h3">{{ article.title }}</view>
			<image :src="article.cover" class="rounded" mode="widthFix"></image>
			<view class="mt-2">
				来自
				<span class="font-body mr-3">{{ article.category }}:</span>
				<span class="mr-3 rounded" v-for="(tag, index) in article.tagList" :key="index" style="color:#cbe9bf">
					#{{ tag.tagName }}
				</span>
			</view>
		</view>

		<!-- 正文 -->
		<view class="bg-white p-3 mt-3"><rich-text :nodes="article.content"></rich-text></view>

		<!-- 评论数据 -->
		<template v-if="commentList.length != 0">
			<view class="bg-white mt-2 rounded p-2" v-for="(comment, index) in commentList" :key="index">
				<view class="flex align-center">
					<image :src="comment.avatar" class="rounded-circle size-100 m-1"></image>
					<text class="text-body">{{ comment.nickname }}</text>
				</view>

				<view class="flex flex-column flex-1 px-2">
					<text class="font-weight-light">{{ comment.content }}</text>
				</view>

				<view class="font-sm text-muted px-2">{{ comment.createTime }}</view>
			</view>
		</template>

		<!-- 发布评论 -->
		<view class="pt-2">
			<textarea v-model="content" placeholder="说点什么……" class="bg-hover-light rounded w-100"></textarea>
			<button class="bg-pink text-white rounded-circle mt-2" @tap="submit()">发布</button>
		</view>
	</view>
</template>

<script>

export default {
	data() {
		return {
			id: '',
			article: {},
			isVisible: false,
			content: '',
			user: {},
			commentList: {}
		};
	},
	onShow() {
		this.user = uni.getStorageSync('user');
	},
	onLoad(option) {
		this.id = option.id;
		console.log('文章id' + this.id);
		this.getData();
	},
	methods: {
		getData() {
			this.$http.get('/articles/' + this.id).then(res => {
				console.log(res.data);
				this.article = res.data;
				this.commentList = res.data.commentList;
			});
		},
		submit() {
			let data ={
				articleId:this.id,
				userId:this.user.id,
				content:this.content
			}
			this.$http.post('/comments/add', data, 'json').then(res => {
				console.log(res);
				this.$http.get('/comments/' + this.id).then(res => {
					console.log(res.data);
					this.commentList = res.data;
				});
				// 清空文本区域
				this.content = '';
			});
		}
	}
};
</script>

<style>
</style>
