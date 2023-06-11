package ru.raiffeisen.pfrxml.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.raiffeisen.pfrxml.model.Pack;
import ru.raiffeisen.pfrxml.repository.PackRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcPackRepository //implements PackRepository
{
//    private static final RowMapper<Pack> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Pack.class);
//
//    private final JdbcTemplate jdbcTemplate;
//
//    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    private final SimpleJdbcInsert insertPack;
//
//    @Autowired
//    public JdbcPackRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
//        this.insertPack = new SimpleJdbcInsert(jdbcTemplate)
//                .withTableName("packs")
//                .usingGeneratedKeyColumns("id");
//
//        this.jdbcTemplate = jdbcTemplate;
//        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//    }
//
//    @Override
//    public Pack save(Pack pack) {
//        MapSqlParameterSource map = new MapSqlParameterSource()
//                .addValue("id", pack.getId())
//                .addValue("name", pack.getName())
//                .addValue("loaded", pack.getLoaded())
//                .addValue("processed", pack.isProcessed());
//
//        if (pack.isNew()) {
//            Number newId = insertPack.executeAndReturnKey(map);
//            pack.setId(newId.intValue());
//        } else {
//            if (namedParameterJdbcTemplate.update("" +
//                    "UPDATE packs " +
//                    "   SET name=:name, loaded=:loaded, processed=:processed" +
//                    " WHERE id=:id AND user_id=:user_id", map) == 0) {
//                return null;
//            }
//        }
//        return pack;
//    }
//
//    @Override
//    public boolean delete(int id) {
//        return jdbcTemplate.update("DELETE FROM packs WHERE id=?", id) != 0;
//    }
//
//    @Override
//    public Pack get(int id) {
//        List<Pack> packs = jdbcTemplate.query(
//                "SELECT * FROM packs WHERE id = ?", ROW_MAPPER, id);
//        return DataAccessUtils.singleResult(packs);
//    }
//
//    @Override
//    public List<Pack> getAll() {
//        return jdbcTemplate.query(
//                "SELECT * FROM packs WHERE user_id=? ORDER BY loaded DESC", ROW_MAPPER);
//    }
//
//    @Override
//    public List<Pack> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime) {
//        return jdbcTemplate.query(
//                "SELECT * FROM packs WHERE loaded >=  ? AND loaded < ? ORDER BY loaded DESC",
//                ROW_MAPPER, startDateTime, endDateTime);
//    }
}
