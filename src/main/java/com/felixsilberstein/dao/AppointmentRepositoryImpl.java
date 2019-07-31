package com.felixsilberstein.dao;

import com.felixsilberstein.model.Appointment;
import com.felixsilberstein.util.Format;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

@Repository
public class AppointmentRepositoryImpl implements AppointmentRepository {
    Logger logger = LoggerFactory.getLogger(AppointmentRepositoryImpl.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List findAll() {
        return jdbcTemplate.query("SELECT * from appointments where status<>0",
                (rs, rowNum) -> new Appointment(rs.getLong("id"), rs.getString("CustomerName"), rs.getString("CarId")));
    }

    @Override
    public Appointment findById(Long id) {
        List<Appointment> found = jdbcTemplate.query("SELECT * from appointments where status<>0 and id=?", new Object[]{id},
                new BeanPropertyRowMapper(Appointment.class));
        if (found.isEmpty()) {
            return null;
        } else if (found.size() == 1) {
            return found.get(0);
        } else {
            // list contains more than 1 elements, warn about it
            logger.error(String.format("Multiple appointments items found when expecting one for id=%d. Returning the first one", id));
            return found.get(0);
        }
        /*return (Appointment) jdbcTemplate.queryForObject("SELECT * from appointments where status<>0 and id=?", new Object[] { id },
                new RowMapper() {
                    @Override
                    public Object mapRow(ResultSet rs, int i) throws SQLException {
                        Appointment a = new Appointment();
                        a.setId(rs.getLong("id"));
                        a.setCustomerName(rs.getString("CustomerName"));
                        a.setCarId(rs.getString("CarId"));
                        return a;
                    }
                });
         */
    }

    @Override
    public Long create(Appointment appointment) {
        logger.info(appointment.toString());

        String INSERT_SQL = "INSERT INTO `carservices`.`appointments` ( `start`, `end`, `service_type_id`, `mechanic_id`, `CustomerName`, `CarId`, `status`) " +
                "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, Format.MySQLDate(appointment.getStartDateTime()));
            ps.setString(2, Format.MySQLDate(appointment.getEndDateTime()));
            ps.setInt(3, appointment.getServiceType());
            ps.setInt(4, appointment.getMechanic());
            ps.setString(5, appointment.getCustomerName());
            ps.setString(6, appointment.getCarId());
            ps.setInt(7, appointment.getStatus());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
}
