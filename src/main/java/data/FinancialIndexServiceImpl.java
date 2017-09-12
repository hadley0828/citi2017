package data;

import dataservice.FinancialIndexService;
import po.IndexPo;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by loohaze on 2017/9/12 上午1:20
 */
public class FinancialIndexServiceImpl implements FinancialIndexService{

    SqlManager sqlManager = SqlManager.getSqlManager();


    @Override
    public ArrayList<IndexPo> getFinancialIndex(String industry) {
        sqlManager.getConnection();

        ArrayList<IndexPo> list = new ArrayList<>();
        String sql = "select * from financial_index where industry=?";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{industry});

        for (Map<String,Object> map : maps){
            list.add(getIndexPOByMap(map));
        }

        return list;
    }

    @Override
    public String getIndustry(String company_id) {
        sqlManager.getConnection();

        String sql = "select industry from account_set where company_id=?";
        Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{company_id});

        String industry = map.get("industry").toString();
        sqlManager.releaseAll();
        return industry;
    }

    private IndexPo getIndexPOByMap(Map<String,Object> map){
        IndexPo po = new IndexPo();

        po.setIndustry(map.get("industry").toString());
        po.setIndex_name(map.get("index_name").toString());
        po.setExcellent_value(Double.parseDouble(map.get("excellent_value").toString()));
        po.setFine_value(Double.parseDouble(map.get("fine_value").toString()));
        po.setAverage_value(Double.parseDouble(map.get("average_value").toString()));
        po.setLow_value(Double.parseDouble(map.get("low_value").toString()));
        po.setBad_value(Double.parseDouble(map.get("bad_value").toString()));

        return po;
    }


}
