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
 */
public class SupplyChainPerformanceVo {

	private double[][]Supplier;
	private double[][]Manufacturer;
	private double[][]Distributor;
	private double[]Supply_chain;
	
	public SupplyChainPerformanceVo(double[][]s,double[][] m,double[][] d,double []dp){
		Supplier=s;
		Manufacturer=m;
		Distributor=d;
		Supply_chain=dp;
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
