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


    @FXML
    void calculateGrade(ActionEvent event) {
    	double courseGrade = 0.0;
    	String projectGrade = projectGradeTextfield.getText();
    	courseGrade = Double.parseDouble(projectGrade) * 50 / 100;
    	System.out.println("Project grade " + projectGrade + " Course grade so far: " + courseGrade);
    	
    	double quizGrade = quizSlider.getValue();
    	courseGrade = courseGrade + (quizGrade * 2.5);
    	System.out.println("Quiz grade " + quizGrade + " Course grade so far: " + courseGrade);
    	
    	int codingChallengesPassed = ccChoiceBox.getValue();
    	courseGrade = courseGrade + (codingChallengesPassed * 1.25);
    	System.out.println("Coding Challanges passed:  " + codingChallengesPassed + " Course grade so far: " + courseGrade);
    	
    	int optionalcodingChallengesPassed = occChoiceBox.getValue();	//These 3 lines are exactly the same, but for Optional CCs
    	courseGrade = courseGrade + (optionalcodingChallengesPassed * 1.25);
    	System.out.println("Optional Coding Challanges passed:  " + optionalcodingChallengesPassed + " Course grade so far: " + courseGrade);

    	courseGradeLabel.setText(String.format("Your course grade is %.2f", courseGrade));
    	
    	//System.out.println("Button was clicked!");

    }

}
