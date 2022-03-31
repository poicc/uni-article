<template>
	<view>
		<view v-for="(article, index) in articles" :key="index">
			<my-card :article="article" @open="gotoDetail(article.id)"></my-card>
		</view>
	</view>
</template>

<script>
import myCard from '@/components/my-card/my-card.vue';
import config from '@/lib/config.js';
export default {
	components: {
		myCard
	},
	data() {
		return {
			articles: [],
			pageNum: 1,
			pageSize: 6,
			more: true
		};
	},
	onNavigationBarButtonTap() {
		uni.navigateTo({
			url: '../write/write'
		});
	},
	onLoad() {
		uni.getSystemInfo({
			success: res => {
				this.scrollH = res.windowHeight - uni.upx2px(101);
			}
		});
		uni.showLoading({
			title: '加载中'
		});
		this.getData();
		setTimeout(function() {
			console.log('已加载下拉刷新');
		}, 1000);
		uni.startPullDownRefresh();
	},
	onReachBottom() {
		if (!this.more) {
			uni.showToast({
				title: '已加载完毕',
				duration: 1000
			});
			return false;
		}
		this.pageNum = this.pageNum + 1;
		uni.showLoading({
			title: '加载中'
		});
		this.$http.post('/articles/page?pageNum=' + this.pageNum + '&pageSize=' + this.pageSize).then(res => {
			setTimeout(() => {
				uni.hideLoading();
			}, 100);
			this.articles = this.articles.concat(res.data.list);
			if (res.data.list.length < this.pageSize && this.pageNum > 0) {
				this.more = false;
			}
		});
	},
	onPullDownRefresh() {
		this.pageNum = 1;
		this.more = true;
		this.getData();
		setTimeout(function() {
			uni.stopPullDownRefresh();
		}, 1000);
	},
	methods: {
		getData() {
			this.$http.post('/articles/page?pageNum=' + this.pageNum + '&pageSize=' + this.pageSize).then(res => {
				console.log(res.data);
				setTimeout(() => {
					uni.hideLoading();
				}, 100);
				this.articles = res.data.list;
			});
		},
		gotoDetail(id) {
			console.log(id);
			uni.navigateTo({
				url: '../article/article?id=' + id
			});
		}
	}
};
</script>

<style></style>
