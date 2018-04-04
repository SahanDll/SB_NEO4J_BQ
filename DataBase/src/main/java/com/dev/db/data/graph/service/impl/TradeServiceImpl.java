package com.dev.db.data.graph.service.impl;

import com.dev.db.data.graph.bean.edge.Trade;
import com.dev.db.data.graph.bean.edge.View;
import com.dev.db.data.graph.bean.node.ScreenTransition;
import com.dev.db.data.graph.bean.node.Stock;
import com.dev.db.data.graph.bean.node.User;
import com.dev.db.data.graph.repository.edge.TradeRepository;
import com.dev.db.data.graph.repository.edge.ViewRepository;
import com.dev.db.data.graph.repository.node.ScreenTransitionRepository;
import com.dev.db.data.graph.repository.node.StockRepository;
import com.dev.db.data.graph.repository.node.UserRepository;
import com.dev.db.data.graph.service.inte.TradeService;
import com.dev.db.data.graph.service.inte.ViewService;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by N5608296 on 14/09/17.
 */
@Service
public class TradeServiceImpl implements TradeService {
    private static final Logger logger = LoggerFactory.getLogger(TradeServiceImpl.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    StockRepository stockRepository;
    @Autowired
    TradeRepository tradeRepository;

    @Override
    public Trade create(Trade trade) {
        Trade trd = tradeRepository.findTop1ByUserIdAndTradeTime(trade.getUserId(), trade.getTradeTime());
        User us = userRepository.findTop1ByUserId(trade.getUserId());
        if(null == us){
            trade.getUser().setFirstLogin(new Date());
        }
        Stock stk = stockRepository.findTop1ByStkCode(trade.getStkCode());

        if(null == trd){
            if(null != us){
                trade.getUser().setId(us.getId());
            }
            if(null != stk){
                trade.getStock().setId(stk.getId());
            }
            trade.setUser(userRepository.save(trade.getUser()));
            trade.setStock(stockRepository.save(trade.getStock()));
            return tradeRepository.save(trade);
        }else{
            trade.getUser().setId(trd.getUser().getId());
            trd.setUser(trade.getUser());
            trade.getStock().setId(trd.getStock().getId());
            trd.setStock(trade.getStock());
            return tradeRepository.save(trd);
        }
    }

    @Override
    public Map<String, Object> read(String tradeId) {
        return null;
    }

    @Override
    public Map<String, Object> update(Trade trade) {
        return null;
    }

    @Override
    public Map<String, Object> delete(String tradeId) {
        return null;
    }

    @Override
    public JSONObject readAll() {
        JSONArray result = new JSONArray();
        JSONObject main = new JSONObject();
        try {
            Gson gson = new Gson();
            for (Trade trd : tradeRepository.findAll(new Sort(Sort.Direction.DESC, "TradeTime"))) {
                JSONObject json = new JSONObject();
                json.put("userId",trd.getUserId());
                json.put("stockCode",trd.getUserId());
                json.put("tradeTime",trd.getTradeTime());
                json.put("orderType",trd.getOrderType());
                json.put("quantity",trd.getQuantity());
                json.put("price",trd.getPrice());
                json.put("volume",trd.getVolume());
                trd.setUser(null);
                //result.add(json);
                result.add(gson.toJson(trd));
            }

            main.put("Result", result);
            main.put("RecordCount", result.size());
        } catch (Exception e) {
            logger.error("Error ", e);
        }
        return main;
    }
}
