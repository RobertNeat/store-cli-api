package pl.britenet.store.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseService {
    private HikariDataSource dataSource;

    public DatabaseService() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/store");
        config.setUsername("root");
        config.setPassword("");
        this.dataSource= new HikariDataSource(config);
    }


    public void performDML(String dml) {
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(dml)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public<T> T performSQL(String sql, ResultsParser<T> resultsParser) {
        try (Connection connection = this.dataSource.getConnection() ;
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            return resultsParser.parse(resultSet);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
