package com.es_demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.es_demo.dao.MessageDao;
import com.es_demo.model.Message;

@Repository
public class MessageDaoImpl implements MessageDao {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public Message insert(Message message) {
		try{
            NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(message);
            String sql = "insert into message(id, title, content, add_time) " +
            		"values (:id ,:title ,:content ,:addTime)";

            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(sql, paramSource, keyHolder);
            message.setId(keyHolder.getKey().longValue());
            return message;
        }catch (Exception e) {
        	e.printStackTrace();
        	return null;
		}
	}

	@Override
	public List<Message> findMessages() {
		String sql = "select * from message where 1=1";
		sql += " order by add_time desc";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Message.class));
	}

}
