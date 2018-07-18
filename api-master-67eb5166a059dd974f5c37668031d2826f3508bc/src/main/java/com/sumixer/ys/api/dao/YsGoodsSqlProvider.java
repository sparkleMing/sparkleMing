package com.sumixer.ys.api.dao;

import com.sumixer.ys.api.entity.YsGoods;
import com.sumixer.ys.api.entity.YsGoodsExample.Criteria;
import com.sumixer.ys.api.entity.YsGoodsExample.Criterion;
import com.sumixer.ys.api.entity.YsGoodsExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class YsGoodsSqlProvider {

    public String countByExample(YsGoodsExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("ys_goods");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(YsGoodsExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("ys_goods");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(YsGoods record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("ys_goods");
        
        if (record.getGoodsId() != null) {
            sql.VALUES("goods_id", "#{goodsId,jdbcType=VARCHAR}");
        }
        
        if (record.getCategoryId() != null) {
            sql.VALUES("category_id", "#{categoryId,jdbcType=VARCHAR}");
        }
        
        if (record.getGoodsName() != null) {
            sql.VALUES("goods_name", "#{goodsName,jdbcType=VARCHAR}");
        }
        
        if (record.getUnit() != null) {
            sql.VALUES("unit", "#{unit,jdbcType=VARCHAR}");
        }
        
        if (record.getKeyword() != null) {
            sql.VALUES("keyword", "#{keyword,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=VARCHAR}");
        }
        
        if (record.getCostPrice() != null) {
            sql.VALUES("cost_price", "#{costPrice,jdbcType=DOUBLE}");
        }
        
        if (record.getSalePrice() != null) {
            sql.VALUES("sale_price", "#{salePrice,jdbcType=DOUBLE}");
        }
        
        if (record.getMarketPrice() != null) {
            sql.VALUES("market_price", "#{marketPrice,jdbcType=DOUBLE}");
        }
        
        if (record.getStock() != null) {
            sql.VALUES("stock", "#{stock,jdbcType=DOUBLE}");
        }
        
        if (record.getLowStock() != null) {
            sql.VALUES("low_stock", "#{lowStock,jdbcType=DOUBLE}");
        }
        
        if (record.getPublish() != null) {
            sql.VALUES("publish", "#{publish,jdbcType=INTEGER}");
        }
        
        if (record.getPicture() != null) {
            sql.VALUES("picture", "#{picture,jdbcType=VARCHAR}");
        }
        
        if (record.getPutTime() != null) {
            sql.VALUES("put_time", "#{putTime,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(YsGoodsExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("goods_id");
        sql.SELECT("category_id");
        sql.SELECT("goods_name");
        sql.SELECT("unit");
        sql.SELECT("keyword");
        sql.SELECT("description");
        sql.SELECT("cost_price");
        sql.SELECT("sale_price");
        sql.SELECT("market_price");
        sql.SELECT("stock");
        sql.SELECT("low_stock");
        sql.SELECT("publish");
        sql.SELECT("picture");
        sql.SELECT("put_time");
        sql.FROM("ys_goods");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        YsGoods record = (YsGoods) parameter.get("record");
        YsGoodsExample example = (YsGoodsExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("ys_goods");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getGoodsId() != null) {
            sql.SET("goods_id = #{record.goodsId,jdbcType=VARCHAR}");
        }
        
        if (record.getCategoryId() != null) {
            sql.SET("category_id = #{record.categoryId,jdbcType=VARCHAR}");
        }
        
        if (record.getGoodsName() != null) {
            sql.SET("goods_name = #{record.goodsName,jdbcType=VARCHAR}");
        }
        
        if (record.getUnit() != null) {
            sql.SET("unit = #{record.unit,jdbcType=VARCHAR}");
        }
        
        if (record.getKeyword() != null) {
            sql.SET("keyword = #{record.keyword,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("description = #{record.description,jdbcType=VARCHAR}");
        }
        
        if (record.getCostPrice() != null) {
            sql.SET("cost_price = #{record.costPrice,jdbcType=DOUBLE}");
        }
        
        if (record.getSalePrice() != null) {
            sql.SET("sale_price = #{record.salePrice,jdbcType=DOUBLE}");
        }
        
        if (record.getMarketPrice() != null) {
            sql.SET("market_price = #{record.marketPrice,jdbcType=DOUBLE}");
        }
        
        if (record.getStock() != null) {
            sql.SET("stock = #{record.stock,jdbcType=DOUBLE}");
        }
        
        if (record.getLowStock() != null) {
            sql.SET("low_stock = #{record.lowStock,jdbcType=DOUBLE}");
        }
        
        if (record.getPublish() != null) {
            sql.SET("publish = #{record.publish,jdbcType=INTEGER}");
        }
        
        if (record.getPicture() != null) {
            sql.SET("picture = #{record.picture,jdbcType=VARCHAR}");
        }
        
        if (record.getPutTime() != null) {
            sql.SET("put_time = #{record.putTime,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("ys_goods");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("goods_id = #{record.goodsId,jdbcType=VARCHAR}");
        sql.SET("category_id = #{record.categoryId,jdbcType=VARCHAR}");
        sql.SET("goods_name = #{record.goodsName,jdbcType=VARCHAR}");
        sql.SET("unit = #{record.unit,jdbcType=VARCHAR}");
        sql.SET("keyword = #{record.keyword,jdbcType=VARCHAR}");
        sql.SET("description = #{record.description,jdbcType=VARCHAR}");
        sql.SET("cost_price = #{record.costPrice,jdbcType=DOUBLE}");
        sql.SET("sale_price = #{record.salePrice,jdbcType=DOUBLE}");
        sql.SET("market_price = #{record.marketPrice,jdbcType=DOUBLE}");
        sql.SET("stock = #{record.stock,jdbcType=DOUBLE}");
        sql.SET("low_stock = #{record.lowStock,jdbcType=DOUBLE}");
        sql.SET("publish = #{record.publish,jdbcType=INTEGER}");
        sql.SET("picture = #{record.picture,jdbcType=VARCHAR}");
        sql.SET("put_time = #{record.putTime,jdbcType=VARCHAR}");
        
        YsGoodsExample example = (YsGoodsExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(YsGoods record) {
        SQL sql = new SQL();
        sql.UPDATE("ys_goods");
        
        if (record.getGoodsId() != null) {
            sql.SET("goods_id = #{goodsId,jdbcType=VARCHAR}");
        }
        
        if (record.getCategoryId() != null) {
            sql.SET("category_id = #{categoryId,jdbcType=VARCHAR}");
        }
        
        if (record.getGoodsName() != null) {
            sql.SET("goods_name = #{goodsName,jdbcType=VARCHAR}");
        }
        
        if (record.getUnit() != null) {
            sql.SET("unit = #{unit,jdbcType=VARCHAR}");
        }
        
        if (record.getKeyword() != null) {
            sql.SET("keyword = #{keyword,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=VARCHAR}");
        }
        
        if (record.getCostPrice() != null) {
            sql.SET("cost_price = #{costPrice,jdbcType=DOUBLE}");
        }
        
        if (record.getSalePrice() != null) {
            sql.SET("sale_price = #{salePrice,jdbcType=DOUBLE}");
        }
        
        if (record.getMarketPrice() != null) {
            sql.SET("market_price = #{marketPrice,jdbcType=DOUBLE}");
        }
        
        if (record.getStock() != null) {
            sql.SET("stock = #{stock,jdbcType=DOUBLE}");
        }
        
        if (record.getLowStock() != null) {
            sql.SET("low_stock = #{lowStock,jdbcType=DOUBLE}");
        }
        
        if (record.getPublish() != null) {
            sql.SET("publish = #{publish,jdbcType=INTEGER}");
        }
        
        if (record.getPicture() != null) {
            sql.SET("picture = #{picture,jdbcType=VARCHAR}");
        }
        
        if (record.getPutTime() != null) {
            sql.SET("put_time = #{putTime,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, YsGoodsExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}