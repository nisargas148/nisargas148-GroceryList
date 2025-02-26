import Util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroceryDAO {

    /**
     * Select all of the rows from the Grocery table.
     * @return a List of all the groceries contained within the database.
     */
    public List<String> getAllGroceries() {
        Connection connection = ConnectionUtil.getConnection();
        List<String> groceries = new ArrayList<>();
        try {
            // SQL query to select all rows from the Grocery table
            String sql = "SELECT grocery_name FROM Grocery";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            // Iterate through the result set and add grocery names to the list
            while (rs.next()) {
                groceries.add(rs.getString("grocery_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groceries;
    }

    /**
     * Insert a new row into the Grocery table, which contains a column named grocery_name.
     * @param groceryName the name of the grocery passed in from the GroceryService.
     */
    public void addGrocery(String groceryName) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            // SQL query to insert a new grocery into the Grocery table
            String sql = "INSERT INTO Grocery (grocery_name) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            // Set the groceryName parameter to the prepared statement
            ps.setString(1, groceryName);

            // Execute the update to insert the new grocery
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
