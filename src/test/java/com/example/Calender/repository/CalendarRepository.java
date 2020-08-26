package com.example.Calender.repository;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import com.example.Calender.entity.Calendar;
import java.util.Date;
import java.util.List;

@Log
@Repository
public class CalendarRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void makeTodo(Calendar calendar) throws Exception{
        log.info("makeTodo()");
        String query = "insert into calendar(contentId, content) values(?, ?)";

        jdbcTemplate.update(query,calendar.getContentId(),calendar.getContent());
    }

    public void removeTodo(Calendar calendar) throws Exception {
        log.info("removeTodo()");
        Date _date = calendar.getDate();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(_date).toString();

        String query = "delete from calendar where date = ?";
        jdbcTemplate.update(query,date);
    }

    public Calendar readTodo(Calendar calendar) throws Exception {
        log.info("readTodo()");
        Date _date = calendar.getDate();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(_date).toString();

        List<Calendar> results = jdbcTemplate.query(
                "select contentId, content, date from calendar where date = ?",
                new RowMapper<Calendar>() {
                    @Override
                    public Calendar mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Calendar cal = new Calendar();

                        cal.setContent(rs.getString("content"));
                        cal.setContentId(rs.getInt("contentId"));
                        cal.setDate(rs.getDate("date"));

                        return cal;
                    }
                }, date
        );

        return results.isEmpty() ? null : results.get(0);
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

    public List<Calendar> list() throws Exception {
        List<Calendar> results = jdbcTemplate.query(
                "select * from calendar", new RowMapper<Calendar>() {
                    @Override
                    public Calendar mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Calendar cal = new Calendar();
                        cal.setContent(rs.getString("content"));
                        cal.setDate(rs.getDate("date"));
                        cal.setContentId(rs.getInt("id"));
                        return cal;
                    }
                }
        );
        return results;
    }
}
