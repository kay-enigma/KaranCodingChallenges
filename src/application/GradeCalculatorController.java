package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class GradeCalculatorController {

    @FXML
    //private ChoiceBox<?> ccChoiceBox;
    private ChoiceBox<Integer> ccChoiceBox;
    
    @FXML
    private ChoiceBox<Integer> occChoiceBox;	//Importing fx:id for optional CCs

    //private ChoiceBox<Integer> occChoiceBox;
    
    @FXML
    private Slider quizSlider;

    @FXML
    private TextField projectGradeTextfield;
    
    @FXML
    private Label courseGradeLabel;
    
    @FXML
    private Label projectErrorLabel;
    /**
     * 		
     * Convert the value entered to a double value. This method will verify that the value
     * entered is indeed a number and is a valid percentage grade (between 6 and 100). 
     * If the value entered is not a valid percentage grade, this method will return 0.0 as the 
     * project grade instead.
     * 
     * @param valueEntered a string that holds a value entered by the user intended to be a project grade
     * @return the project value entered by the user if it is valid % grade and 0 otherwise
     */
    double getProjectGrade(String valueEntered) {
    	// Check that the entered value by the user is a valid decimal number
    	boolean validProjectGrade = true;
    	for (char c : valueEntered.toCharArray()) {
    		//Check if the character is a digit
    		if (!Character.isDigit(c) && c !='.') { // The && c !='.' is added to skip the decimal and not
    			validProjectGrade = false;			//show an error message.
    			projectErrorLabel.setText("Do no use "+ c +" in a project grade. Make sure to enter a number.");
    		}
    	}
    	// Convert the string entered by the user to double if the input is a valid number
    	// Otherwise the project grade will default to zero.
 
    	double projectGrade = 0.0;
    	if (validProjectGrade) {
    		projectGrade = Double.parseDouble(valueEntered);
    	}
    	
    	// check if the number entered by the user is a valid % grade
    	// IF valid, include it in the grade computation
    	projectGrade = Double.parseDouble(valueEntered); // This line here is entered to accept decimal as an input.
    	if (projectGrade < 0.0 || projectGrade > 100.0) {
    		projectErrorLabel.setText("Project grade should be between 0% and 100%. ");
    		projectGrade=0.0;
    	}
    	
    	return projectGrade;
    }

    @FXML
    void calculateGrade(ActionEvent event) {
    	projectErrorLabel.setText("");
    	double courseGrade = 0.0;
    	
    	String projectValueEntered = projectGradeTextfield.getText();
    	
    	double projectGrade= getProjectGrade(projectValueEntered);
    	
        courseGrade = courseGrade + projectGrade * 40 / 100;
    	
        System.out.println("Project grade " + projectGrade + " Course grade so far: " + courseGrade);
    	
    	double quizGrade = quizSlider.getValue();
    	courseGrade = courseGrade + (quizGrade * 3);
    	System.out.println("Quiz grade " + quizGrade + " Course grade so far: " + courseGrade);
    	
    	int codingChallengesPassed = ccChoiceBox.getValue();
    	courseGrade = courseGrade + (codingChallengesPassed * 1.5);
    	System.out.println("Coding Challanges passed:  " + codingChallengesPassed + " Course grade so far: " + courseGrade);
    	
    	int optionalcodingChallengesPassed = occChoiceBox.getValue();	//These 3 lines are exactly the same, but for Optional CCs
    	courseGrade = courseGrade + (optionalcodingChallengesPassed * 1.5);
    	System.out.println("Optional Coding Challanges passed:  " + optionalcodingChallengesPassed + " Course grade so far: " + courseGrade);

    	courseGradeLabel.setText(String.format("Your course grade is %.2f", courseGrade));
    	
    	//System.out.println("Button was clicked!");

    }

}
