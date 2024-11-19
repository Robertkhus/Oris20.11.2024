

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JdbcTemplate {

    private DataSource dataSource;



    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> List<T> execute(String sql, RowMapper<T> rowMapper, Object ... objects) {
        List<T> results = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            for(int i = 0; i < objects.length; i++) {
                preparedStatement.setObject(i + 1,objects[i]);
            }

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    results.add(rowMapper.mapRow(resultSet));
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;

    }

}
