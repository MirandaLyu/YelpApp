package yelpBusiness.model;

//ReviewId VARCHAR(255),
//UserId VARCHAR(255),
//BusinessId VARCHAR(255),
//Stars DECIMAL(2,1),
//UserfulCount INT,
//FunnyCount INT,
//CoolCount INT,
//ReviewContent TEXT,
//ReviewDate TIMESTAMP NOT NULL,
import java.util.Date;

public class Reviews {

	protected String reviewId;
	protected double stars;
	protected int usefulCount;
	protected int funnyCount;
	protected int coolCount;
	protected String reviewContent;
	protected Date reviewDate;
	protected Businesses business;
	protected Users user;

	public Reviews(String reviewId, double stars, int usefulCount, int funnyCount, int coolCount, String reviewContent,
			Date reviewDate, Businesses business, Users user) {
		this.reviewId = reviewId;
		this.stars = stars;
		this.usefulCount = usefulCount;
		this.funnyCount = funnyCount;
		this.coolCount = coolCount;
		this.reviewContent = reviewContent;
		this.reviewDate = reviewDate;
		this.business = business;
		this.user = user;
	}

	public Reviews(String reviewId) {
		this.reviewId = reviewId;
	}

	public String getReviewId() {
		return reviewId;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}

	public double getStars() {
		return stars;
	}

	public void setStars(double stars) {
		this.stars = stars;
	}

	public int getUsefulCount() {
		return usefulCount;
	}

	public void setUsefulCount(int usefulCount) {
		this.usefulCount = usefulCount;
	}

	public int getFunnyCount() {
		return funnyCount;
	}

	public void setFunnyCount(int funnyCount) {
		this.funnyCount = funnyCount;
	}

	public int getCoolCount() {
		return coolCount;
	}

	public void setCoolCount(int coolCount) {
		this.coolCount = coolCount;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public Businesses getBusiness() {
		return business;
	}

	public void setBusiness(Businesses business) {
		this.business = business;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}