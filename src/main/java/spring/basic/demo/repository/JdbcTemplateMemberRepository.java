package spring.basic.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import spring.basic.demo.domain.Member;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTemplateMemberRepository implements MemberRepositoryInterface{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateMemberRepository(DataSource dataSource){     // spring bean으로 가지고 있던 datasource를
        jdbcTemplate = new JdbcTemplate(dataSource);                //
    }

    @Override
    public void saveMember(Member m) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", m.getName());

        jdbcInsert.executeAndReturnKey(new MapSqlParameterSource((parameters)));
    }

    @Override
    public Member findById(int id) {
        return null;
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}
