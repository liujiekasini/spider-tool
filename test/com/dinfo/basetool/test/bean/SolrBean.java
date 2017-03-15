package com.dinfo.basetool.test.bean;


/**
 * @ClassName: SolrBean
 * @Description: TODO
 * @author xulonglong
 * @date 2016-4-8 下午3:06:28
 */
public class SolrBean {
	/**
	 * 法律文书网
	 */
	private String plaintiffName;//原告名称
	private String defendantName;//被告名称
	private String cause;//<!--案由-->
	private String judgementTitle;//<!--裁判标题-->
	private String judgementNumber;//<!--案号-->
	private String amountInvolved;//<!--涉案金额(判决中标的金额)-->
	private String judgementType;//<!--案件类型-->
	private String wenshuContent;//文书内容
	private String judgementContent;//<!--裁判内容-->
	private String judgementProcedure;//<!--审判程序-->
	private String courtArea;//<!--法院地区-->
	private String courtName;//<!--法院名称-->
	private String caseFacts;//<!--案件事实-->
	private String caseGrounds;//<!--案件理由-->
	private String resultCase;//<!--案件判决结果-->
	private String relatedCase;//<!--关联案件-->
	private String caseStatus;//<!--案件状态-->
	private String judgementDate;//<!--裁判日期-->
	private String releaseTime;//<!--发布时间-->
	private String collectionTime;//<!--采集时间-->
	private String url;//<!--原始链接-->
	private String judgementId;//<!--裁判Id-->
	private String enterpriseName;//公司名称
	
	private String createDate;//分析时间
	private String infoType;//<!--信息类型(公告、判决、失信执行、欠税、中标、处罚)-->
	private String infoSource ;//<!--数据来源(eg.广东地税、国税、中国政府采购网...)-->
	private String rowKey ;//<!--hbase 表rowkey--> 
	
	private String maxAmountInvolved;//最大金额

	public String getMaxAmountInvolved() {
		return maxAmountInvolved;
	}
	public void setMaxAmountInvolved(String maxAmountInvolved) {
		this.maxAmountInvolved = maxAmountInvolved;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getJudgementId() {
		return judgementId;
	}
	public void setJudgementId(String judgementId) {
		this.judgementId = judgementId;
	}
	public String getWenshuContent() {
		return wenshuContent;
	}
	public void setWenshuContent(String wenshuContent) {
		this.wenshuContent = wenshuContent;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getRowKey() {
		return rowKey;
	}
	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}
	public String getInfoType() {
		return infoType;
	}
	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}
	public String getInfoSource() {
		return infoSource;
	}
	public void setInfoSource(String infoSource) {
		this.infoSource = infoSource;
	}
	public String getPlaintiffName() {
		return plaintiffName;
	}
	public void setPlaintiffName(String plaintiffName) {
		this.plaintiffName = plaintiffName;
	}
	public String getDefendantName() {
		return defendantName;
	}
	public void setDefendantName(String defendantName) {
		this.defendantName = defendantName;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getJudgementTitle() {
		return judgementTitle;
	}
	public void setJudgementTitle(String judgementTitle) {
		this.judgementTitle = judgementTitle;
	}
	public String getJudgementNumber() {
		return judgementNumber;
	}
	public void setJudgementNumber(String judgementNumber) {
		this.judgementNumber = judgementNumber;
	}
	public String getAmountInvolved() {
		return amountInvolved;
	}
	public void setAmountInvolved(String amountInvolved) {
		this.amountInvolved = amountInvolved;
	}
	public String getJudgementType() {
		return judgementType;
	}
	public void setJudgementType(String judgementType) {
		this.judgementType = judgementType;
	}
	public String getJudgementContent() {
		return judgementContent;
	}
	public void setJudgementContent(String judgementContent) {
		this.judgementContent = judgementContent;
	}
	public String getJudgementProcedure() {
		return judgementProcedure;
	}
	public void setJudgementProcedure(String judgementProcedure) {
		this.judgementProcedure = judgementProcedure;
	}
	public String getCourtArea() {
		return courtArea;
	}
	public void setCourtArea(String courtArea) {
		this.courtArea = courtArea;
	}
	public String getCourtName() {
		return courtName;
	}
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	public String getCaseFacts() {
		return caseFacts;
	}
	public void setCaseFacts(String caseFacts) {
		this.caseFacts = caseFacts;
	}
	public String getCaseGrounds() {
		return caseGrounds;
	}
	public void setCaseGrounds(String caseGrounds) {
		this.caseGrounds = caseGrounds;
	}
	public String getResultCase() {
		return resultCase;
	}
	public void setResultCase(String resultCase) {
		this.resultCase = resultCase;
	}
	public String getRelatedCase() {
		return relatedCase;
	}
	public void setRelatedCase(String relatedCase) {
		this.relatedCase = relatedCase;
	}
	public String getCaseStatus() {
		return caseStatus;
	}
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
	public String getJudgementDate() {
		return judgementDate;
	}
	public void setJudgementDate(String judgementDate) {
		this.judgementDate = judgementDate;
	}
	public String getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	public String getCollectionTime() {
		return collectionTime;
	}
	public void setCollectionTime(String collectionTime) {
		this.collectionTime = collectionTime;
	}
	
}
