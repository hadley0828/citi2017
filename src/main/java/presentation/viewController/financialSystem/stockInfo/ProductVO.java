package presentation.viewController.financialSystem.stockInfo;

import java.sql.Timestamp;

/**
 * Created by YZ on 2017/9/13.
 */
public class ProductVO {
        private String product_variety;    //原材料种类
        private String voucher_id;  //凭证编号
        private String datetime;    //时间
        private boolean is_delivery_ontime;  //是否准时交货
        private boolean is_return;  //是否属于退货
        private String input_num;  //收⼊数量
        private String input_price; //收入单价
        private String input_account;  //收入金额
        private String out_num;    //发出数量
        private String out_price;   //发出单价
        private String out_account; //发出金额
        private String balance_num;    //结存数量

    public ProductVO(){

    }
        public ProductVO(String product_variety, String voucher_id, String datetime, boolean is_delivery_ontime, boolean is_return, String input_num,String input_price, String input_account,
                         String out_num, String out_price, String out_account, String balance_num) {
            this.product_variety = product_variety;
            this.voucher_id = voucher_id;
            this.datetime = datetime;
            this.is_delivery_ontime = is_delivery_ontime;
            this.is_return = is_return;
            this.input_num = input_num;
            this.input_price = input_price;
            this.input_account = input_account;
            this.out_num = out_num;
            this.out_price = out_price;
            this.out_account = out_account;
            this.balance_num = balance_num;
        }

        public String getProduct_variety() {
            return product_variety;
        }

        public boolean isIs_delivery_ontime() {
            return is_delivery_ontime;
        }

        public boolean isIs_return() {
            return is_return;
        }

        public String getInput_account() {
            return input_account;
        }

        public String getOut_account() {
            return out_account;
        }

        public String getBalance_num() {
            return balance_num;
        }

        public String getInput_num() {
            return input_num;
        }

        public String getOut_num() {
            return out_num;
        }

        public String getDatetime() {
            return datetime;
        }

        public String getVoucher_id() {
            return voucher_id;
        }

        public String getInput_price() {
            return input_price;
        }

        public String getOut_price() {
            return out_price;
        }

        public void setBalance_num(String balance_num) {
            this.balance_num = balance_num;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public void setInput_account(String input_account) {
            this.input_account = input_account;
        }

        public void setInput_num(String input_num) {
            this.input_num = input_num;
        }

        public void setIs_delivery_ontime(boolean is_delivery_ontime) {
            this.is_delivery_ontime = is_delivery_ontime;
        }

        public void setIs_return(boolean is_return) {
            this.is_return = is_return;
        }

        public void setOut_account(String out_account) {
            this.out_account = out_account;
        }

        public void setOut_num(String out_num) {
            this.out_num = out_num;
        }

        public void setProduct_variety(String product_variety) {
            this.product_variety = product_variety;
        }

        public void setVoucher_id(String voucher_id) {
            this.voucher_id = voucher_id;
        }

        public void setInput_price(String input_price) {
            this.input_price = input_price;
        }

        public void setOut_price(String out_price) {
            this.out_price = out_price;
        }
}
