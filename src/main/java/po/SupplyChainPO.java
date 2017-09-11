package po;

/**
 * 
 * @author hyf
 * 供应链融资
 * 
 * company     				     企业名称
 * Net          			     应收帐款／质押货物金额
 * FinacingType                融资方式
 * ProposedFinancingScale      建议融资规模
 */
public class SupplyChainPO {
	private String company;
	private String FinacingType;
	private double Net;
	private double ProposedFinancingScale;
	
	public SupplyChainPO(String c,String f,double n,double p){
		this.company=c;
		this.FinacingType=f;
		this.Net=n;
		this.ProposedFinancingScale=p;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getFinacingType() {
		return FinacingType;
	}

	public void setFinacingType(String finacingType) {
		FinacingType = finacingType;
	}

	public double getNet() {
		return Net;
	}

	public void setNet(double net) {
		Net = net;
	}

	public double getProposedFinancingScale() {
		return ProposedFinancingScale;
	}

	public void setProposedFinancingScale(double proposedFinancingScale) {
		ProposedFinancingScale = proposedFinancingScale;
	}
	
	
}
