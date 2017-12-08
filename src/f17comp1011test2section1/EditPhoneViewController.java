/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package f17comp1011test2section1;

import java.io.File;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author jwright
 */
public class EditPhoneViewController implements Initializable {

    @FXML    private TextField manufacutureTextField;
    @FXML    private TextField modelTextField;
    @FXML    private TextField memoryTextField;
    @FXML    private TextField colourTextField;
    @FXML    private TextField screenSizeTextField;
    @FXML    private Spinner<Integer> phoneIDSpinner;


    public void loadPhoneInfo() throws SQLException
    {
         Connection conn = null;
        PreparedStatement preparedStatement = null;
        
        try{
            //1.  connect to the DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comp1011Test2?useSSL=false", "root", "ramanlove@1997");
            
            //2.  create a String that holds our SQL update command with ? for user inputs
            String sql = "UPDATE  SET manufacturer = ?, model = ?, "
                    + "memory=?, colour = ?, screenSize = ? "
                    + "WHERE phoneID = ?";
            
            //3. prepare the query against SQL injection
            preparedStatement = conn.prepareStatement(sql);
            
            
            //5. bind the parameters
            preparedStatement.setString(1,manufacutureTextField.getText() );
            preparedStatement.setString(2, modelTextField.getText());
            preparedStatement.setInt(3, Integer.parseInt(memoryTextField.getText()));
            preparedStatement.setString(4,  colourTextField.getText());
            preparedStatement.setFloat(5, Float.parseFloat(screenSizeTextField.getText()));
            preparedStatement.setInt(6,phoneIDSpinner.getValue() );
            
            //6. run the command on the SQL server
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        manufacutureTextField.setDisable(true);
        modelTextField.setDisable(true);
        memoryTextField.setDisable(true);
        colourTextField.setDisable(true);
        screenSizeTextField.setDisable(true);
               
        SpinnerValueFactory<Integer> phoneIDValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100);
        phoneIDSpinner.setValueFactory(phoneIDValueFactory);
        phoneIDSpinner.setEditable(true);
    }    
    
}
