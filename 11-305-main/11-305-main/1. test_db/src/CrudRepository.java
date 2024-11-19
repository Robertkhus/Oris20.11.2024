import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    List<T> findAll() throws SQLException;

    List<T> findById(Long id);

    void save(T entity);
    boolean update(T entity);
    void remove(T entity);
    void removeById(Long id);
    void saveSeveral(int quantity);
    List<T> findByEmail(String email);
    List<T> findByNumberPhone(String number);










}
