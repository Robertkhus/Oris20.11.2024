

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRowMapper implements RowMapper<User>{

    @Override
    public User mapRow(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        return user;
    }


}
