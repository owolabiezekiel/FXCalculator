/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxcalculator;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author TOBILOBA
 */
public class FXMLDocumentController implements Initializable {
    
    private BigDecimal left;
    private String operator;
    private boolean numberInput;
    
    @FXML
    private TextField viewArea;

    public FXMLDocumentController() {
        this.left = BigDecimal.ZERO;
        this.operator = "";
        this.numberInput = false;
    }
    
    @FXML
    public void buttonClicked(ActionEvent e) {
        Button button = (Button)e.getSource();
        String buttonValue = button.getText();
        
        if(buttonValue.equals("C")){
            if(buttonValue.equals("C")){
                left = BigDecimal.ZERO;
            }
            operator = "";
            numberInput = true;
            viewArea.setText("0");
            return;
        }
        
        if(buttonValue.matches("[0-9\\,]")) {
            if(!numberInput){
                numberInput = true;
                viewArea.clear();
            }
            viewArea.appendText(buttonValue);
            return;
        }
        
        if(buttonValue.equals("+") || buttonValue.equals("-") ||buttonValue.equals("/") || buttonValue.equals("*")) {  
            left = new BigDecimal(viewArea.getText());
            operator = buttonValue;
            numberInput = false;
            return;
        }
        
        if(buttonValue.equals("=")){
            final BigDecimal right = numberInput ? new BigDecimal(viewArea.getText()) : left;
            left = calculate(operator, left, right);
            viewArea.setText(left.toString());
            numberInput = false;
            return;
        }
    }
    
    public static BigDecimal calculate(String operator, BigDecimal left, BigDecimal right){
        switch(operator) {
            case "+" :
                return left.add(right);
            case"-":
                return left.subtract(right);
            case "*" :
                return left.multiply(right);
            case"/":
                return left.divide(right);
        }
        return right;
    }
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
