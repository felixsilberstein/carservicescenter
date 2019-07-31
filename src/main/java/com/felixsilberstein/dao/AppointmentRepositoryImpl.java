package com.felixsilberstein.dao;

import com.felixsilberstein.model.Appointment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static com.felixsilberstein.util.Format.Date2MySQLTimestamp;

@Repository
public class AppointmentRepositoryImpl implements AppointmentRepository {
    Logger logger = LoggerFactory.getLogger(AppointmentRepositoryImpl.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List findAll() {
        return jdbcTemplate.query("SELECT * from appointments",
                (rs, rowNum) -> new Appointment(rs.getInt("id"), rs.getString("CustomerName"), rs.getString("CarId")));
    }

    @Override
    public Appointment findById(Integer id) {
        List<Appointment> found = jdbcTemplate.query("SELECT * from appointments where id=?", new Object[] { id },
                new RowMapper() {
            /*
                    @Override
                    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                        return null;
                    }*/

                    @Override
                    public Object mapRow(ResultSet rs, int i) throws SQLException {
                        Appointment a = new Appointment();
                        a.setId(rs.getInt("id"));
                        a.setStartDateTime(rs.getTimestamp("start"));
                        a.setEndDateTime(rs.getTimestamp("end"));
                        a.setServiceType(rs.getInt("service_type_id"));
                        a.setMechanic(rs.getInt("mechanic_id"));
                        a.setCustomerName(rs.getString("CustomerName"));
                        a.setCarId(rs.getString("CarId"));
                        return a;
                    }
                });

        if (found.isEmpty()) {
            return null;
        } else if (found.size() == 1) {
            return found.get(0);
        } else {
            // list contains more than 1 elements, warn about it
            logger.error(String.format("Multiple appointments items found when expecting one for id=%d. Returning the first one", id));
            return found.get(0);
        }
    }

    @Override
    public Integer create(Appointment appointment) {
//        logger.info(appointment.toString());

        String INSERT_SQL = "INSERT INTO `carservices`.`appointments` ( `start`, `end`, `service_type_id`, `mechanic_id`, `CustomerName`, `CarId`) " +
                "VALUES ( ?, ?, ?, ?, ?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, Date2MySQLTimestamp(appointment.getStartDateTime()));
            ps.setTimestamp(2, Date2MySQLTimestamp(appointment.getEndDateTime()));
            ps.setInt(3, appointment.getServiceType());
            ps.setInt(4, appointment.getMechanic());
            ps.setString(5, appointment.getCustomerName());
            ps.setString(6, appointment.getCarId());
            return ps;
        }, keyHolder);
        Integer newId = keyHolder.getKey().intValue();
        logger.info(String.format("Appointment %d CREATED", newId));
        return newId;
    }

    @Override
    public void update(Appointment appointment) {
        logger.info(appointment.toString());
        String UPDATE_SQL = "UPDATE `carservices`.`appointments` " +
                "SET " +
                "`start` = ?, " +
                "`end` = ?, " +
                "`service_type_id` = ?, " +
                "`mechanic_id` = ?, " +
                "`CustomerName` = ?, " +
                "`CarId` = ? " +
                "WHERE `id` = ?";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);
            ps.setTimestamp(1, Date2MySQLTimestamp(appointment.getStartDateTime()));
            ps.setTimestamp(2, Date2MySQLTimestamp(appointment.getEndDateTime()));
            ps.setInt(3, appointment.getServiceType());
            ps.setInt(4, appointment.getMechanic());
            ps.setString(5, appointment.getCustomerName());
            ps.setString(6, appointment.getCarId());
            ps.setInt(7, appointment.getId());
            return ps;
        });
        logger.info(String.format("Appointment %s UPDATED", appointment.toString()));

    }

    @Override
    public void deleteById(Integer id) {
        String DELETE_SQL = "DELETE FROM `carservices`.`appointments` WHERE `id` = ?";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(DELETE_SQL);
            ps.setInt(1, id);
            return ps;
        });
        logger.info(String.format("Appointment %d deleted", id));

    }
}
