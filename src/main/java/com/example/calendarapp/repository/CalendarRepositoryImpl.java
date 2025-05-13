package com.example.calendarapp.repository;

import com.example.calendarapp.dto.CalendarResponseDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.example.calendarapp.entity.Calendar;

import javax.sql.DataSource;

@Repository
public class CalendarRepositoryImpl implements CalendarRepository {

    private final JdbcTemplate jdbcTemplate;

    public CalendarRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public CalendarResponseDto saveCalendar(Calendar calendar) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("calendar").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters =new HashMap<>();
        parameters.put("title", calendar.getTitle());
        parameters.put("content", calendar.getContent());
        parameters.put("user", calendar.getUser());
        parameters.put("password", calendar.getPassword());
        parameters.put("created_at", calendar.getCreatedAt());
        parameters.put("updated_at", calendar.getUpdatedAt());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new CalendarResponseDto(key.longValue(), calendar.getTitle(), calendar.getContent(), calendar.getUser(), calendar.getUpdatedAt());

    }

    @Override
    public List<CalendarResponseDto> findAllCalendar() {
        return jdbcTemplate.query("select * from calendar", calendarRowMapper());
    }

    @Override
    public Calendar findCalendarById(Long id) {
        return calendarList.get(id);
    }

    @Override
    public void deletCalendar(Long id) {
        calendarList.remove(id);
    }

    private RowMapper<Calendar> calendarRowMapper() {
        return new RowMapper<CalendarResponseDto>() {
            @Override
            public CalendarResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CalendarResponseDto(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("user"),
                        rs.getTimestamp("updated_at").toLocalDateTime())
            }
        };
    }
}
