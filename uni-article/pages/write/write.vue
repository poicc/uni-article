<template>
	<view>
		<uni-list-item title="标题">
			<view class="flex align-center" slot="right">
				<input class="uni-input text-right" v-model="title" />
				<text class="iconfont icon-bianji1 ml-2"></text>
			</view>
		</uni-list-item>
		<uni-list-item title="类型" @click="selectCategory()">
			<view class="flex align-center" slot="right">
				<text>{{ category }}</text>
				<text class="iconfont icon-bianji1 ml-2"></text>
			</view>
		</uni-list-item>

		<view class="flex justify-start align-center px-3 flex-wrap">
			<span style="font-size: 32upx;" class="my-3 mr-2">标签</span>
			<uni-tag
				v-for="(tag, index) in tagList"
				:key="index"
				:text="tag.tagName"
				:type="types[index % 4]"
				:circle="true"
				@click="removeTag(tag, index)"
				style="display: inline-block;"
				class="mr-1 mb-1"
			></uni-tag>
		</view>

		<uni-collapse accordion="true">
			<uni-collapse-item title="请选择" class="ml-1 font-lg">
				<view class="flex justify-start align-center px-3 flex-wrap">
					<uni-tag
						v-for="(tag, index) in tags"
						:key="index"
						:text="tag.tagName"
						:type="types[index % 4]"
						:circle="true"
						@click="addTag(tag, index)"
						style="display: inline-block;"
						class="mr-1 mb-1"
					></uni-tag>
				</view>
			</uni-collapse-item>
		</uni-collapse>
		
		<jinEdit placeholder="请输入内容" @editOk="submit" fileKeyName="file" uploadFileUrl="http://ufys6qe0.dnat.tech:39647/api/v1/users/upload"></jinEdit>
	</view>
</template>

<script>
import uniListItem from '@/components/uni/uni-list-item/uni-list-item.vue';
import uniCollapse from '@/components/uni/uni-collapse/uni-collapse.vue';
import uniCollapseItme from '@/components/uni/uni-collapse-item/uni-collapse-item.vue';
import uniTag from '@/components/uni/uni-tag/uni-tag.vue';
import jinEdit from '@/components/jin-edit/jin-edit.vue';
const categoryArray = ['计算机知识', '资讯', '开源软件', '博客', '码云', '众包'];
export default {
	data() {
		return {
			title: '',
			categoryIndex: 0,
			tags: [],
			tagList:[],
			types: ['primary', 'success', 'warning', 'error'],
			user: {},
			
		};
	},
	computed: {
		category() {
			return categoryArray[this.categoryIndex];
		}
	},
	onShow() {
		this.user = uni.getStorageSync('user');
	},
	onLoad() {
		this.$http.get('/tags/all').then(res => {
			this.tags = res;
			this.tags = this.tags.slice(0, 10);
		});
	},
	components: {
		jinEdit,
		uniListItem,
		uniCollapse,
		uniCollapseItme,
		uniTag
	},
	methods: {
		selectCategory() {
			uni.showActionSheet({
				itemList: categoryArray,
				success: res => {
					(this.categoryIndex = res.tapIndex), (this.category = categoryArray[this.categoryIndex]);
				}
			});
		},

		addTag(tag, index) {
			this.tags.splice(index, 1);
			this.tagList.push(tag);
		},
		removeTag(tag, index) {
			this.tagList.splice(index, 1);
			this.tags.push(tag);
		},
		submit(res) {
			console.log(res.html)
			let list = [];
			this.tagList.forEach(tag => {
				let articleTag = {
					tagId: tag.id,
					tagName: tag.tagName
				};
				list.push(articleTag);
			});
			let article = {
				userId: this.user.id,
				title: this.title,
				category: this.category,
				summary: '摘要',
				content: res.html,
				tagList: list
			};
			this.$http.post('/articles/post', article, 'json').then(res => {
				uni.switchTab({
					url: '../index/index'
				});
			});
		}
	}
};
</script>

<style></style>
