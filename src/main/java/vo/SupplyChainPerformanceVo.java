package vo;

import java.util.Arrays;

/**
 * 供应链绩效评价
 * 
 * @author hyf
 * 
 * Supplier     供应商
 * Manufacturer 生产商
 * Distributor  分销商
 * Supply_chain 供应链
 * OnTimeDeliveryRateBySupplier 供应商 原材料名称+准时交货率
 * OnTimeDeliveryRateByManufacturer 生产商 库存商品名称+准时交货率
 * ReturnRateByManufacturer 生产商 原材料名称+退货率
 * ReturnRateByDistributor 分销商 库存商品名称+退货率
 */
public class SupplyChainPerformanceVo {

	private double[][]Supplier;
	private double[][]Manufacturer;
	private double[][]Distributor;
	private double[]Supply_chain;
	private String[][] OnTimeDeliveryRateBySupplier;
	private String[][] OnTimeDeliveryRateByManufacturer;
	private String[][] ReturnRateByManufacturer;
	private String[][] ReturnRateByDistributor;
	
	public SupplyChainPerformanceVo(double[][]s,double[][] m,double[][] d,double []dp,String[][]o1,String[][]o2,String[][]o3,String[][]o4){
		Supplier=s;
		Manufacturer=m;
		Distributor=d;
		Supply_chain=dp;
		OnTimeDeliveryRateBySupplier=o1;
		OnTimeDeliveryRateByManufacturer=o2;
		ReturnRateByManufacturer=o3;
		ReturnRateByDistributor=o4;
	}

	public String[][] getOnTimeDeliveryRateBySupplier() {
		return OnTimeDeliveryRateBySupplier;
	}

	public void setOnTimeDeliveryRateBySupplier(String[][] onTimeDeliveryRateBySupplier) {
		OnTimeDeliveryRateBySupplier = onTimeDeliveryRateBySupplier;
	}

	public String[][] getOnTimeDeliveryRateByManufacturer() {
		return OnTimeDeliveryRateByManufacturer;
	}

	public void setOnTimeDeliveryRateByManufacturer(String[][] onTimeDeliveryRateByManufacturer) {
		OnTimeDeliveryRateByManufacturer = onTimeDeliveryRateByManufacturer;
	}

	public String[][] getReturnRateByManufacturer() {
		return ReturnRateByManufacturer;
	}

	public void setReturnRateByManufacturer(String[][] returnRateByManufacturer) {
		ReturnRateByManufacturer = returnRateByManufacturer;
	}

	public String[][] getReturnRateByDistributor() {
		return ReturnRateByDistributor;
	}

	public void setReturnRateByDistributor(String[][] returnRateByDistributor) {
		ReturnRateByDistributor = returnRateByDistributor;
	}

	public double[][] getSupplier() {
		return Supplier;
	}

	public void setSupplier(double[][] supplier) {
		Supplier = supplier;
	}

	public double[][] getManufacturer() {
		return Manufacturer;
	}

	public void setManufacturer(double[][] manufacturer) {
		Manufacturer = manufacturer;
	}

	public double[][] getDistributor() {
		return Distributor;
	}

	public void setDistributor(double[][] distributor) {
		Distributor = distributor;
	}

	public double[] getSupply_chain() {
		return Supply_chain;
	}

	public void setSupply_chain(double[] supply_chain) {
		Supply_chain = supply_chain;
	}

}
