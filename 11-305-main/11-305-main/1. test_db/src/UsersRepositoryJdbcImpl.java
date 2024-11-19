import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UserRepository {

    private  DataSource dataSource;
    private  RowMapper<User> rowMapper;
    private  final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    private static final String SQL_SELECT_FROM_USERS = "select * from users";
    private static final String SQL_SELECT_FROM_USERS_BY_ID = "select * from users where id = ?";


    @Override
    public List<User> findAll() {
        return(jdbcTemplate.execute(SQL_SELECT_FROM_USERS, rowMapper));

    }

    @Override
    public List<User> findById(Long id) {
        return(jdbcTemplate.execute(SQL_SELECT_FROM_USERS_BY_ID,rowMapper,id));

    }

    @Override
    public void save(User entity) {
        String sql = "INSERT INTO users (id, first_name, last_name) VALUES (?, ?, ?)";
        jdbcTemplate.execute(sql,rowMapper,entity);
    }


    public boolean update(User type) {
        String sql = "UPDATE users SET  first_name = ?, last_name = ? WHERE id = ?";
        int rowsAffected = (jdbcTemplate.execute(sql,rowMapper,type )).size();
        return rowsAffected > 0;
    }

    @Override
    public void remove(User type) {
        String sql = "DELETE FROM users WHERE first_name = ? AND last_name = ?";
        jdbcTemplate.execute(sql,rowMapper,type);
    }

    @Override
    public void removeById(Long id) {
        String sql = "DELETE FROM users WHERE id = ? ";
        jdbcTemplate.execute(sql,rowMapper,id);

    }

    @Override
    public List<User> findAllByAge(Integer age) {
        String sql = "select * from users where age = ?";
        return(jdbcTemplate.execute(sql,rowMapper,age));
    }
    @Override
    public void saveSeveral(int quantity) {
        String sql = "insert into users (first_name, last_name, age) values (?, ?, ?)";
        for (int i=0;i<quantity;i++) {
            jdbcTemplate.execute(sql,rowMapper,i,quantity);
        }
    }
    @Override
    public List<User> findByEmail(String email) {
        String sql = "select * from users where email = ?";
        return(jdbcTemplate.execute(sql,rowMapper,email));
    }
    @Override
    public List<User> findByNumberPhone(String numberPhone) {
        String sql = "select * from users where numberPhone = ?";
        return(jdbcTemplate.execute(sql,rowMapper,numberPhone));

    }
}
