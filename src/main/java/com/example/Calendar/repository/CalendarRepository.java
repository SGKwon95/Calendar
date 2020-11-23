package com.example.Calendar.repository;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import com.example.Calendar.entity.Calendar;

import java.util.Date;
import java.util.List;

@Log
@Repository
public class CalendarRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void makeTodo(Calendar calendar)throws Exception{
        String query = "insert into calendar(date, content, userNo) values(?, ?, ?)";
        log.info(calendar.getDateString());
        jdbcTemplate.update(query,calendar.getDateString(),calendar.getContent(),calendar.getUserNo());
    }

    public void removeTodo(Long todoNo, Long userNo) throws Exception {
        log.info("todoNo="+todoNo);
        log.info("userNo="+userNo);
        jdbcTemplate.update("delete from calendar where id = ? and userNo = ?", todoNo, userNo);
    }

    public void modifyTodo(Calendar calendar) throws Exception {
        Date _date = calendar.getDate();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(_date).toString();

        String query = "update calendar set content = ? where date = ?";
        jdbcTemplate.update(
                query, calendar.getContent(), date
        );
    }

    public List<Calendar> list(String df, Long userNo) throws Exception {
        String query = "select content, date, id from calendar " +
                "where date like ? and userNo=? order by id";
        List<Calendar> results = jdbcTemplate.query(
                query, new RowMapper<Calendar>() {
                    @Override
                    public Calendar mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Calendar cal = new Calendar();
                        cal.setContent(rs.getString("content"));
                        cal.setDate(rs.getDate("date"));
                        cal.setContentId(rs.getInt("id"));
                        cal.setDateString(df);
                        return cal;
                    }
                }, "%"+df+"%",userNo
        );
        
        results.add(0, new Calendar(df));

        return results;
    }
}
