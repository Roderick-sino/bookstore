package bookstore.goods.category.domain;

import java.util.List;

/**
 * 分类模块的实体类
 * @author 23216
 *
 */
public class Category {
		private String cid;
		private String cname;
		/*
		 * 双向自生关联?--》子，子--》父
		 */
		private Category parent;//父分?
		private String desc;//分类描述
		private List<Category> children;//子分?
		public String getCid() {
			return cid;
		}
		public void setCid(String cid) {
			this.cid = cid;
		}
		public String getCname() {
			return cname;
		}
		public void setCname(String cname) {
			this.cname = cname;
		}
		public Category getParent() {
			return parent;
		}
		public void setParent(Category parent) {
			this.parent = parent;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public List<Category> getChildren() {
			return children;
		}
		public void setChildren(List<Category> children) {
			this.children = children;
		}
		
}
