  package bookstore.goods.book.domain;

import bookstore.goods.category.domain.Category;

public class Book {
		private String bid;//����
		private String bname;//ͼ����
		private String author;//����
		private double price;//����
		private double  currPrice;//��ǰ�۸�
		private double discount;//�ۿ�
		private String press;//������
		private String publishtime;//����ʱ��
		private int edition;//�汾
		private int pageNum ;//ҳ��
		private int wordNum;//����
		private String printtime;//ˢ��ʱ��
		private int  booksize;//����
		private String paper;//ֽ��0
		private Category category;//cid�������������
		private String image_w;//��ͼ·��
		private String image_b;//Сͼ·��
		
		
		private String pid;
		private String cid;
		
		
	
		public String getPid() {
			return pid;
		}
		public void setPid(String pid) {
			this.pid = pid;
		}
		public String getCid() {
			return cid;
		}
		public void setCid(String cid) {
			this.cid = cid;
		}
		public int getPageNum() {
			return pageNum;
		}
		public void setPageNum(int pageNum) {
			this.pageNum = pageNum;
		}
		public String getBid() {
			return bid;
		}
		public void setBid(String bid) {
			this.bid = bid;
		}
		public String getBname() {
			return bname;
		}
		public void setBname(String bname) {
			this.bname = bname;
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public double getCurrPrice() {
			return currPrice;
		}
		public void setCurrPrice(double currPrice) {
			this.currPrice = currPrice;
		}
		public double getDiscount() {
			return discount;
		}
		public void setDiscount(double discount) {
			this.discount = discount;
		}
		public String getPress() {
			return press;
		}
		public void setPress(String press) {
			this.press = press;
		}
		public String getPublishtime() {
			return publishtime;
		}
		public void setPublishtime(String publishtime) {
			this.publishtime = publishtime;
		}
		public int getEdition() {
			return edition;
		}
		public void setEdition(int edition) {
			this.edition = edition;
		}
		
		public int getWordNum() {
			return wordNum;
		}
		public void setWordNum(int wordNum) {
			this.wordNum = wordNum;
		}
		public String getPrinttime() {
			return printtime;
		}
		public void setPrinttime(String printtime) {
			this.printtime = printtime;
		}
		public int getBooksize() {
			return booksize;
		}
		public void setBooksize(int booksize) {
			this.booksize = booksize;
		}
		public String getPaper() {
			return paper;
		}
		public void setPaper(String paper) {
			this.paper = paper;
		}
		
		public Category getCategory() {
			return category;
		}
		public String getImage_w() {
			return image_w;
		}
		public void setImage_w(String image_w) {
			this.image_w = image_w;
		}
		public String getImage_b() {
			return image_b;
		}
		public void setImage_b(String image_b) {
			this.image_b = image_b;
		}
		public void setCategory(Category category) {
			// TODO Auto-generated method stub
			
		}
		
		
		
}
