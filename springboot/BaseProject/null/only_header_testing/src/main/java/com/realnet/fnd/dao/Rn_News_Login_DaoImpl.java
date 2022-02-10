package com.realnet.fnd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.realnet.fnd.model.Rn_News_Login;

@Repository("Rn_News_Login_Dao")
public class Rn_News_Login_DaoImpl implements Rn_News_Login_Dao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	@Override
	public List<Rn_News_Login> DashbordApprover_List() {
		String sql = "SELECT news_title,news_url FROM rn_news_feeds_t";
		List<Rn_News_Login> rn_news_login = jdbcTemplate.query(sql, new RowMapper<Rn_News_Login>() {

			@Override
			public Rn_News_Login mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rn_News_Login rn_news_login_t = new Rn_News_Login();
	
				rn_news_login_t.setNews_title(rs.getString("NEWS_TITLE"));
				rn_news_login_t.setNews_url(rs.getString("NEWS_URL"));
				
				return rn_news_login_t;
			}
			
		});
		
		return rn_news_login;
	}
	
	
	
	

	}


