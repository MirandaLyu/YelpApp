
package yelpBusiness.model;

import java.util.Date;

public class Tips {
	protected int tipsId;
	protected String content;
	protected Date tipDate;
	protected int complimentCount;
	protected Businesses business;
	protected Users user;

	public Tips(int tipsId, String content, Date tipDate, int complimentCount, Businesses business, Users user) {
		this.tipsId = tipsId;
		this.content = content;
		this.tipDate = tipDate;
		this.complimentCount = complimentCount;
		this.business = business;
		this.user = user;
	}

	public Tips(String content, Date tipDate, int complimentCount, Businesses business, Users user) {
		this.content = content;
		this.tipDate = tipDate;
		this.complimentCount = complimentCount;
		this.business = business;
		this.user = user;
	}

	public Tips(int tipsId) {
		this.tipsId = tipsId;
	}

	public int getTipsId() {
		return tipsId;
	}

	public void setTipsId(int tipsId) {
		this.tipsId = tipsId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTipDate() {
		return tipDate;
	}

	public void setTipDate(Date tipDate) {
		this.tipDate = tipDate;
	}

	public int getComplimentCount() {
		return complimentCount;
	}

	public void setComplimentCount(int complimentCount) {
		this.complimentCount = complimentCount;
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