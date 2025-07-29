import com.example.OracleDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {H2DBConfig.class})
class DaoTest {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private OracleDao dao;

    @BeforeEach
    void setUp() {
        dao = new OracleDao(template);
    }

    @Test
    void testInsert() {
        int result = dao.insertData("06", "FUBON");
        assertEquals(1, result);
    }

    @Test
    void testUpdate() {
        Map<String, Object> queryResult = dao.selectData("99");
        String oriVanName = (String) queryResult.get("van_name");
        dao.updateData("99", "DEFAULT");
        Map<String, Object> queryAgainResult = dao.selectData("99");
        String updateVanName = (String) queryAgainResult.get("van_name");
        assertNotEquals(updateVanName, oriVanName);
    }

    @Test
    void testSelect() {
        Map<String, Object> queryResult = dao.selectData("03");
        String vanName = (String) queryResult.get("van_name");
        assertEquals("CTBC", vanName);
    }

    @Test
    void testDelete() {
        dao.deleteData("04");
        assertThrows(EmptyResultDataAccessException.class, () -> dao.selectData("04"));
    }

}
