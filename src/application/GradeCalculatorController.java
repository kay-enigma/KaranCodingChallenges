package application;



import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class GradeCalculatorController {
	Stage applicationStage;

    @FXML
    private ChoiceBox<Integer> ccChoiceBox;
    
    @FXML
    private ChoiceBox<Integer> occChoiceBox;	//Importing fx:id for optional CCs

    @FXML
    private ChoiceBox<Integer> quizzesCompletedChoiceBox;

	@FXML
    private ChoiceBox<Integer> optionalquizzesCompletedChoiceBox;

    @FXML
    private TextField projectGradeTextfield;
    
    @FXML
    private Label courseGradeLabel;

	@FXML
    private Label compulsoryAvg;
    
    @FXML
    private Label optionalAvg;
    
    @FXML
    private Label projectErrorLabel;


	@FXML
    /**
     * this method sets the window to accept the value of compulsory coding challenges from the user
     * @param compEnterQuizGradeEvent is the action of the button which brings us to another window to enter compulsory coding challenge values
     */
    void getCompQuizGrades(ActionEvent compEnterQuizGradeEvent)
    {
    	Scene mainScene=applicationStage.getScene();
    	
    	int compNum=quizzesCompletedChoiceBox.getValue();
    	int compRows=0;
    	VBox compQuizGradeContainer=new VBox();
    	
    	ArrayList<TextField> compQuizGradeTextfields=new ArrayList<TextField>();
    	while(compRows<compNum) {
    	
    	HBox compRowContainer=new HBox();
    	Label compQuizGradeLabel=new Label("Quiz Grade");
    	TextField compQuizGradeTextfield=new TextField();
    	compQuizGradeTextfields.add(compQuizGradeTextfield);
    	
    	compQuizGradeContainer.getChildren().addAll(compQuizGradeLabel,compQuizGradeTextfield);
    	compRows++;
    	
    	compQuizGradeContainer.getChildren().add(compRowContainer);
    	}
    	Button compDoneButton=new Button("Done");
    	compDoneButton.setOnAction(compDoneEvent -> calculateCompAverageQuizGrade(mainScene,compQuizGradeTextfields));
    	compQuizGradeContainer.getChildren().addAll(compDoneButton);

    	
    	Scene compQuizGradesScene=new Scene(compQuizGradeContainer);
    	applicationStage.setScene(compQuizGradesScene);
    }


	double CompQuizAvg=0.0;
    /**
     * This method is there to calculate the average value of compulsory coding challenges.
     * @param mainScene is the main window of the Grade Calculator
     * @param compQuizGradeTextfields is the field where the user will input the values of compulsory coding challenges 
     */
    void calculateCompAverageQuizGrade(Scene mainScene,ArrayList<TextField> compQuizGradeTextfields)
    {
    	applicationStage.setScene(mainScene);
    	CompQuizAvg=0.0;
    	for(TextField compQuizGradeTextfield: compQuizGradeTextfields) {
    			CompQuizAvg=Double.parseDouble(compQuizGradeTextfield.getText())+CompQuizAvg;
    	}
    
    	CompQuizAvg=CompQuizAvg/15;
    	compulsoryAvg.setText("Compulsory quiz grade is "+CompQuizAvg+"/10");
    	
    }
    /**
     * This method creates the window in which we will accept the optional quiz values.
     * @param compEnterQuizGradeEvent is the action of the button which brings us to another window to enter compulsory coding challenge values
     */

	@FXML
    void getOptQuizGrades(ActionEvent compEnterQuizGradeEvent)
	{
    	Scene mainScene=applicationStage.getScene();
    	
    	int optNum=optionalquizzesCompletedChoiceBox.getValue();
    	int optRows=0;
    	VBox optQuizGradeContainer=new VBox();
    	ArrayList<TextField> optQuizGradeTextfields=new ArrayList<TextField>();
    	int checking=0;//This is present to ensure that even if the user has grades of more than 5 optional, he will enter only the best 5
    	if(optNum>5)
    		checking=5;
    	else
    		checking=optNum;
    	while(optRows<checking) {
    
    	HBox optRowContainer=new HBox();
    	Label optQuizGradeLabel=new Label("Quiz Grade");
    	TextField optQuizGradeTextfield=new TextField();
    	optQuizGradeTextfields.add(optQuizGradeTextfield);

    	
    	
    	optQuizGradeContainer.getChildren().addAll(optQuizGradeLabel,optQuizGradeTextfield);
    	optRows++;
    	
    	optQuizGradeContainer.getChildren().add(optRowContainer);

    	}
    	
    	Button optDoneButton=new Button("Done");
    	optDoneButton.setOnAction(optDoneEvent -> calculateOptAverageQuizGrade(mainScene,optQuizGradeTextfields));
    	optQuizGradeContainer.getChildren().addAll(optDoneButton);
    	

    	Scene optQuizGradesScene=new Scene(optQuizGradeContainer);
    	applicationStage.setScene(optQuizGradesScene);
    }


	double OptQuizAvg=0.0;
    /**
     * This is a function which is present in a new window to input the optional quiz scores and find the avg 
     * @param mainScene This is the main window of the program
     * @param optQuizGradeTextfields this is another window where the user will enter the optional quiz grades to calculate the avg quiz score
     */
    void calculateOptAverageQuizGrade(Scene mainScene,ArrayList<TextField> optQuizGradeTextfields)
    {
    	applicationStage.setScene(mainScene);
    	OptQuizAvg=0.0;
    	for(TextField optQuizGradeTextfield: optQuizGradeTextfields) {
    	OptQuizAvg=Double.parseDouble(optQuizGradeTextfield.getText())+OptQuizAvg;
    	}
    	
    	OptQuizAvg=OptQuizAvg/5;
    	optionalAvg.setText("Optional Quiz Average is "+OptQuizAvg+"/10");
    	
    }

    /**
     * 		
     * Convert the value entered to a double value. This method will verify that the value
     * entered is indeed a number and is a valid percentage grade (between 6 and 100). 
     * If the value entered is not a valid percentage grade, this method will return 0.0 as the 
     * project grade instead.
     * 
     * This Will Also accept grade input values as decimals(not more than 1)
     * 
     * @param valueEntered a string that holds a value entered by the user intended to be a project grade
     * @return the project value entered by the user if it is valid % grade and 0 otherwise
     */
    double getProjectGrade(String valueEntered)
    {
    	boolean validProjectGrade=true;
    	int point=0;//used to count the number of decimal points
    	for(char c: valueEntered.toCharArray())
    		//This is for checking if the decimal entered is valid or not
    	{
    		if(!Character.isDigit(c)&& c!='.'&& c!='-')
    			//Check if character is a digit
    		{
    			validProjectGrade=false;
    			projectErrorLabel.setText("Do not use "+c+" in a project grade. Make sure it is a number");
    		}
    		
    		if(c=='.')
    		{
    			point=point+1;
    		}
    		if(point>1)
    			projectErrorLabel.setText("Do not use "+point+" decimal points in a project grade.");
    	}
    	
    	double projectGrade=0.0;
    	if(validProjectGrade) {
    		projectGrade=Double.parseDouble(valueEntered);
    		//Convert the string entered to double if it is a valid number.
    	}
    	if(projectGrade<0 ||projectGrade>100)
    		//Checks if the grade entered is within the boundary of 0 and 100
    	{
    		projectErrorLabel.setText("Project grade must be between 0 and 100. Invalid Input!!!");
    		projectGrade=0;
    	}
    	
    	return projectGrade;
    }

    double averageQuizGrade = 0.0;
    
    void calculateAverageQuizGrade(Scene mainScene, ArrayList<TextField> quizGradeTextfields) {
    	applicationStage.setScene(mainScene);
    	
    	averageQuizGrade = 0.0;
    	for (TextField quizGradeTextfield : quizGradeTextfields) {
        	averageQuizGrade += Double.parseDouble(quizGradeTextfield.getText());
    	}
    	averageQuizGrade = averageQuizGrade / quizGradeTextfields.size();
    }
    
    @FXML
    void getQuizGrades(ActionEvent enterQuizGradeEvent) {
    	Scene mainScene = applicationStage.getScene();
    	
    	int numberOfQuizzes = quizzesCompletedChoiceBox.getValue();
    	int rowsCreated = 0;
    	VBox quizGradeContainer = new VBox();
    	
    	ArrayList<TextField> quizGradeTextfields = new ArrayList<TextField>();
    	while (rowsCreated < numberOfQuizzes) {
    		
    		HBox rowContainer = new HBox();
        	Label quizGradeLabel = new Label("Quiz grade");
        	TextField quizGradeTextfield = new TextField();
        	
        	
        	
        	
        	quizGradeContainer.getChildren().addAll(quizGradeLabel, quizGradeTextfield);
        	rowsCreated+=1;
        	
        	quizGradeContainer.getChildren().add(rowContainer);
    		
    	}
    	
    	Button doneButton = new Button("Done");
    	doneButton.setOnAction(doneEvent -> calculateAverageQuizGrade(mainScene, quizGradeTextfields));
    	quizGradeContainer.getChildren().add(doneButton);
    	
    	Scene quizGradesScene = new Scene(quizGradeContainer);
    	applicationStage.setScene(quizGradesScene);
    	
    }
    
    
    @FXML
    /**
     * This will calculate the value of course grade after the button is pressed. It will pass the value
     * of the project grade to the function getProjectGrade for verification of it being a decimal and then 
     * on returning will add it to the course grade variable on the basis it is valid or not. The Coding Challenge
     * and quiz grades are calculated normally based on the scores in each of those components. 
     * @param event is the starter for running the code on pressing the button in the window of the GUI
     */
    void calculateGrade(ActionEvent event) {
   
    	projectErrorLabel.setText("");
    	double coursegrade=0.0;
    	String projectValueEntered=projectGradeTextfield.getText();
    	
    	double projectGrade=getProjectGrade(projectValueEntered);
    	coursegrade=coursegrade+projectGrade*0.5;
    
    	System.out.print("Project Grade "+projectGrade);
    	System.out.println(" Course Grade so far "+coursegrade);
    	
    	int compulsory= ccChoiceBox.getValue();
    	double compweight=compulsory*1.25;//There are a total of 20 coding challenges for a weight of 25%. so each will be worth 1.25%
    	coursegrade= compweight+coursegrade;
    	System.out.print("Compulsory Coding Challenges passed "+compulsory);
    	System.out.println(" Course Grade so far "+coursegrade);
    	
    	int notcomp=occChoiceBox.getValue();
    	double optionweight=notcomp*(1.25);
    	coursegrade=optionweight+coursegrade;
    	System.out.print("Optional Coding Challenges passed "+notcomp);
    	System.out.println(" Course Grade so far "+coursegrade);
    	
    			
    	//double comulsoryQuiz=compulsoryQuizzesCompletedChoicebox.getValue();
    	//double optionalQuiz=optionalQuizzesCompletedChoicebox.getValue();
    	double quiztotal= (CompQuizAvg*1.875)+(OptQuizAvg*0.625);
    	coursegrade=quiztotal+coursegrade;
    	System.out.println("Quiz Grade "+quiztotal+" Course Grade so far "+coursegrade);

    	courseGradeLabel.setText("Your course grade is "+coursegrade);

    	//System.out.println("Button was clicked");

    }

}
