package com.example.calendarapp.repository;

import com.example.calendarapp.dto.CalendarResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.example.calendarapp.entity.Calendar;
import org.springframework.web.server.ResponseStatusException;

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
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", calendar.getTitle());
        parameters.put("content", calendar.getContent());
        parameters.put("user", calendar.getUser());
        parameters.put("password", calendar.getPassword());
        parameters.put("createAt", calendar.getCreatedAt());
        parameters.put("updateAt", calendar.getUpdatedAt());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new CalendarResponseDto(key.longValue(), calendar.getTitle(), calendar.getContent(), calendar.getUser(), calendar.getUpdatedAt());

    }

    @Override
    public List<CalendarResponseDto> findAllCalendar() {
        return jdbcTemplate.query("select * from schedule", calendarRowMapperV2());
    }



    @Override
    public Calendar findCalendarByIdOrThrow(Long id) {
        List<Calendar> result = jdbcTemplate.query("select * from schedule where scheduledid = ?", calendarRowMapper(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found id = " + id));
    }

    @Override
    public int updateCalendar(Long id, String title, String content) {
        return jdbcTemplate.update("update schedule set title = ?, content = ? where scheduledid = ?", title, content, id);
    }

    @Override
    public int updateTitle(Long id, String title, String content) {
        return jdbcTemplate.update("update schedule set title = ? where scheduledid = ?", title, id);
    }

    @Override
    public int deleteCalendar(Long id) {
        return jdbcTemplate.update("delete from schedule where scheduledid = ?", id);
    }


    private RowMapper<Calendar> calendarRowMapper() {
        return new RowMapper<Calendar>() {
            @Override
            public Calendar mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Calendar(
                        rs.getLong("scheduledid"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("password"),
                        rs.getString("user"),
                        rs.getTimestamp("createAt").toLocalDateTime(),
                        rs.getTimestamp("updateAt").toLocalDateTime()
                );
            }
        };
    }

    private RowMapper<CalendarResponseDto> calendarRowMapperV2() {
        return new RowMapper<CalendarResponseDto>(){
            @Override
            public CalendarResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CalendarResponseDto(
                        rs.getLong("scheduledid"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("user"),
                        rs.getTimestamp("updateAt").toLocalDateTime()
                );
            }
        };
    }

}
