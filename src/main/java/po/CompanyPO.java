package po;


/**
 * 
 * @author hyf
 * companyName 单位名称
 * hangye 行业
 * SuoZaiDi 单位所在地
 * XinYongDaiMa 统一社会信用代码
 * DianHua 联系电话
 */
public class CompanyPO {
	private String companyName;
	private String hangye;
	private String SuoZaiDi;
	private String XinYongDaiMa;
	private String DianHua;

	public CompanyPO() {
	}

	public CompanyPO(String a, String b, String c, String d, String e){
		companyName=a;
		hangye=b;
		SuoZaiDi=c;
		XinYongDaiMa=d;
		DianHua=e;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getHangye() {
		return hangye;
	}

	public void setHangye(String hangye) {
		this.hangye = hangye;
	}

	public String getSuoZaiDi() {
		return SuoZaiDi;
	}

	public void setSuoZaiDi(String suoZaiDi) {
		SuoZaiDi = suoZaiDi;
	}

	public String getXinYongDaiMa() {
		return XinYongDaiMa;
	}

	public void setXinYongDaiMa(String xinYongDaiMa) {
		XinYongDaiMa = xinYongDaiMa;
	}

	public String getDianHua() {
		return DianHua;
	}

	public void setDianHua(String dianHua) {
		DianHua = dianHua;
	}
}
