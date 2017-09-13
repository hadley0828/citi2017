package po;

/**
 * 
 * @author hyf
 * 供应链融资
 * 
 * time                        时间
 * company     				     企业名称
 * Net          			     应收帐款／质押货物金额
 * FinacingType                融资方式  :  应收帐款融资/动产质押融资
 * ProposedFinancingScale      建议融资规模
 */
public class SupplyChainPO {
	private String time;
	private String company;
	private String FinacingType;
	private double Net;
	private double ProposedFinancingScale;
	
	public SupplyChainPO(String t,String c,String f,double n,double p){
		this.time=t;
		this.company=c;
		this.FinacingType=f;
		this.Net=n;
		this.ProposedFinancingScale=p;
	}

	public SupplyChainPO() {
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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
