package com.sumixer.ys.api.dao;

import com.sumixer.ys.api.entity.YsOrderItem;
import com.sumixer.ys.api.entity.YsOrderItemExample.Criteria;
import com.sumixer.ys.api.entity.YsOrderItemExample.Criterion;
import com.sumixer.ys.api.entity.YsOrderItemExample;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class YsOrderItemSqlProvider {

    public String countByExample(YsOrderItemExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("ys_order_item");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(YsOrderItemExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("ys_order_item");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(YsOrderItem record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("ys_order_item");
        
        if (record.getOrderItemId() != null) {
            sql.VALUES("order_item_id", "#{orderItemId,jdbcType=VARCHAR}");
        }
        
        if (record.getPrice() != null) {
            sql.VALUES("price", "#{price,jdbcType=DOUBLE}");
        }
        
        if (record.getDiscount() != null) {
            sql.VALUES("discount", "#{discount,jdbcType=DOUBLE}");
        }
        
        if (record.getPayPrice() != null) {
            sql.VALUES("pay_price", "#{payPrice,jdbcType=DOUBLE}");
        }
        
        if (record.getCount() != null) {
            sql.VALUES("`count`", "#{count,jdbcType=DOUBLE}");
        }
        
        if (record.getOrderId() != null) {
            sql.VALUES("order_id", "#{orderId,jdbcType=VARCHAR}");
        }
        
        if (record.getGoodsId() != null) {
            sql.VALUES("goods_id", "#{goodsId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(YsOrderItemExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("ys_order_item.id");
        } else {
            sql.SELECT("ys_order_item.id");
        }
        sql.SELECT("ys_order_item.order_item_id");
        sql.SELECT("ys_order_item.price");
        sql.SELECT("ys_order_item.discount");
        sql.SELECT("ys_order_item.pay_price");
        sql.SELECT("ys_order_item.`count`");
        sql.SELECT("ys_order_item.order_id");
        sql.SELECT("ys_order_item.goods_id");
        sql.SELECT("ys_goods.goods_name");
        sql.FROM("ys_order_item, ys_goods");
        applyWhere(sql, example, false);
        sql.WHERE("ys_goods.goods_id = ys_order_item.goods_id");
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        YsOrderItem record = (YsOrderItem) parameter.get("record");
        YsOrderItemExample example = (YsOrderItemExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("ys_order_item");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getOrderItemId() != null) {
            sql.SET("order_item_id = #{record.orderItemId,jdbcType=VARCHAR}");
        }
        
        if (record.getPrice() != null) {
            sql.SET("price = #{record.price,jdbcType=DOUBLE}");
        }
        
        if (record.getDiscount() != null) {
            sql.SET("discount = #{record.discount,jdbcType=DOUBLE}");
        }
        
        if (record.getPayPrice() != null) {
            sql.SET("pay_price = #{record.payPrice,jdbcType=DOUBLE}");
        }
        
        if (record.getCount() != null) {
            sql.SET("`count` = #{record.count,jdbcType=DOUBLE}");
        }
        
        if (record.getOrderId() != null) {
            sql.SET("order_id = #{record.orderId,jdbcType=VARCHAR}");
        }
        
        if (record.getGoodsId() != null) {
            sql.SET("goods_id = #{record.goodsId,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("ys_order_item");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("order_item_id = #{record.orderItemId,jdbcType=VARCHAR}");
        sql.SET("price = #{record.price,jdbcType=DOUBLE}");
        sql.SET("discount = #{record.discount,jdbcType=DOUBLE}");
        sql.SET("pay_price = #{record.payPrice,jdbcType=DOUBLE}");
        sql.SET("`count` = #{record.count,jdbcType=DOUBLE}");
        sql.SET("order_id = #{record.orderId,jdbcType=VARCHAR}");
        sql.SET("goods_id = #{record.goodsId,jdbcType=VARCHAR}");
        
        YsOrderItemExample example = (YsOrderItemExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(YsOrderItem record) {
        SQL sql = new SQL();
        sql.UPDATE("ys_order_item");
        
        if (record.getOrderItemId() != null) {
            sql.SET("order_item_id = #{orderItemId,jdbcType=VARCHAR}");
        }
        
        if (record.getPrice() != null) {
            sql.SET("price = #{price,jdbcType=DOUBLE}");
        }
        
        if (record.getDiscount() != null) {
            sql.SET("discount = #{discount,jdbcType=DOUBLE}");
        }
        
        if (record.getPayPrice() != null) {
            sql.SET("pay_price = #{payPrice,jdbcType=DOUBLE}");
        }
        
        if (record.getCount() != null) {
            sql.SET("`count` = #{count,jdbcType=DOUBLE}");
        }
        
        if (record.getOrderId() != null) {
            sql.SET("order_id = #{orderId,jdbcType=VARCHAR}");
        }
        
        if (record.getGoodsId() != null) {
            sql.SET("goods_id = #{goodsId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    public String insertList(Map<String, Object> parameter) {
        List<YsOrderItem> items = (List<YsOrderItem>) parameter.get("list");
        StringBuilder sb = new StringBuilder("insert into ys_order_item (" +
                "order_item_id, price, discount, pay_price, count, order_id, goods_id) values ");
        YsOrderItem item;
        for (int i = 0, l = items.size(); i < l; i++) {
            item = items.get(i);
            sb.append("(")
            .append("'").append(item.getOrderItemId().replace("'", "")).append("'").append(",")
            .append(item.getPrice()).append(",")
            .append(item.getDiscount()).append(",")
            .append(item.getPayPrice()).append(",")
            .append(item.getCount()).append(",")
            .append("'").append(item.getOrderId().replace("'", "")).append("'").append(",")
            .append("'").append(item.getGoodsId().replace("'", "")).append("'")
            .append(")");
            if (i < l - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    protected void applyWhere(SQL sql, YsOrderItemExample example, boolean includeExamplePhrase) {
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