package application;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    private Label projectGradeErrorLabel;
    
    @FXML
    private Label projectErrorLabel;
    
    Label quizErrorLabel = new Label();


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

    	compQuizGradeContainer.getChildren().add(quizErrorLabel);
    	
    	ArrayList<TextField> compQuizGradeTextfields=new ArrayList<TextField>();
    	while(compRows<compNum) {
    	
	    	HBox compRowContainer=new HBox();
	    	Label compQuizGradeLabel=new Label("Quiz Grade");
	    	TextField compQuizGradeTextfield=new TextField();

	    	Label quizGraderangeLabel = new Label("Enter Grade from 0-10");
	    	compQuizGradeTextfields.add(compQuizGradeTextfield);
	    	
	    	compRowContainer.getChildren().addAll(compQuizGradeLabel,compQuizGradeTextfield, quizGraderangeLabel);
	    	compRows++;
	    	
	    	compQuizGradeContainer.getChildren().add(compRowContainer);
    	}
    	Button compDoneButton=new Button("Done");
    	compDoneButton.setOnAction(compDoneEvent -> calculateCompAverageQuizGrade(mainScene,compQuizGradeTextfields));
    	compQuizGradeContainer.getChildren().addAll(compDoneButton);

    	
    	Scene compQuizGradesScene=new Scene(compQuizGradeContainer);
    	applicationStage.setScene(compQuizGradesScene);
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
    	optQuizGradeContainer.getChildren().add(quizErrorLabel);
    	ArrayList<TextField> optQuizGradeTextfields=new ArrayList<TextField>();
    	int checking=0;//This is present to ensure that even if the user has grades of more than 5 optional, he will enter only the best 5
    	if(optNum>5)
    		checking=5;
    	else
    		checking=optNum;
    	while(optRows<checking) {
    
	    	HBox optRowContainer=new HBox();
	    	Label optQuizGradeLabel=new Label("Quiz Grade");
	    	TextField optQuizGradeTextfield=new TextField("0.0");
	    	Label quizGraderangeLabel = new Label("Enter Grade from 0-10");
	    	optQuizGradeTextfields.add(optQuizGradeTextfield);
	
	    	
	    	
	    	optRowContainer.getChildren().addAll(optQuizGradeLabel,optQuizGradeTextfield, quizGraderangeLabel);
	    	optRows++;
	    	
	    	optQuizGradeContainer.getChildren().add(optRowContainer);

    	}
    	
    	Button optDoneButton=new Button("Done");
    	optDoneButton.setOnAction(optDoneEvent -> calculateOptAverageQuizGrade(mainScene,optQuizGradeTextfields));
    	optQuizGradeContainer.getChildren().addAll(optDoneButton);
    	

    	Scene optQuizGradesScene=new Scene(optQuizGradeContainer);
    	applicationStage.setScene(optQuizGradesScene);
    }
	
	
	double CompQuizAvg=0.0;
    /**
     * This method is there to calculate the average value of compulsory coding challenges.
     * @param mainScene is the main window of the Grade Calculator
     * @param compQuizGradeTextfields is the field where the user will input the values of compulsory coding challenges 
     */
    void calculateCompAverageQuizGrade(Scene mainScene,ArrayList<TextField> compQuizGradeTextfields)
    {
    	quizErrorLabel.setText("");
    	
    	double weightPerQuiz = .1/15.0;
    	
    	CompQuizAvg = 0.0;
    	boolean validquizGrade = false;
    	    	   	
    	for (TextField quizGradeTextfield : compQuizGradeTextfields) {
    		Grade quizGrade = new Grade(0,10,weightPerQuiz);
    		String errorMessage=quizGrade.setValue(quizGradeTextfield.getText());
    		if (!errorMessage.equals("")) {
    			validquizGrade=true;
    			quizErrorLabel.setText(errorMessage);
    		}	
    		CompQuizAvg+= quizGrade.getWeightedPercentageGrade();
    		compulsoryAvg.setText(String.format("Quiz Average: %.2f", CompQuizAvg));
    	}
    	if(!validquizGrade) {
    		applicationStage.setScene(mainScene);
    	}
    	
    }
    


	double OptQuizAvg=0.0;
    /**
     * This is a function which is present in a new window to input the optional quiz scores and find the avg 
     * @param mainScene - This is the main window of the program
     * @param optQuizGradeTextfields - this is another window where the user will enter the optional quiz grades to calculate the avg quiz score
     */
    void calculateOptAverageQuizGrade(Scene mainScene,ArrayList<TextField> optQuizGradeTextfields)
    {
    	quizErrorLabel.setText("");
    	double weightPerQuiz = .1/5.0;
    	OptQuizAvg = 0.0;
    	boolean validQuizGrade=false;
    	
    	int size=optQuizGradeTextfields.size();
    	
    	for (TextField optQuizGradeTextfield : optQuizGradeTextfields) {
    		Grade optionalQuizGrade = new Grade(0,10,weightPerQuiz);
    		String errorMessage=optionalQuizGrade.setValue(optQuizGradeTextfield.getText());
    		if(!errorMessage.equals("")) {
    			validQuizGrade=true;
    			quizErrorLabel.setText(errorMessage);
    		}
	    	if(size<=5) {
	    		OptQuizAvg=OptQuizAvg+optionalQuizGrade.getWeightedPercentageGrade();
	    		optionalAvg.setText(String.format("Quiz Average: %.2f", OptQuizAvg));
	    	}
	    	if(size>5) {
	    		double sum = gradeQuizSum(optQuizGradeTextfields);
	    		OptQuizAvg=sum; 
	    	}
    	}
    	if(!validQuizGrade) {
    		applicationStage.setScene(mainScene);
    	}
    	
    }
    /**
     * This function creates a double array used to remove the lowest grade when there is more than 5 optional quiz grades.
     * @param optQuizGradeTextfields: Using the ArrayList of the optional grade text fields to generate a double array but parsing the array list and getting the values  
     * @return array returned is then used in the gradeQuizSum() to remove then add up the remaining quiz grades that are then used for calculating the average quiz grade and weighted grade
     */
    Double[] createGradesArray(ArrayList<TextField> optQuizGradeTextfields) {		
 		int i=0;	
 		Double[] temp = new Double[optQuizGradeTextfields.size()];
     	if (optQuizGradeTextfields.size()>5) {   	
 	    	for(i=0;i<optQuizGradeTextfields.size();i++) {
 	    		temp[i] = Double.parseDouble(optQuizGradeTextfields.get(i).getText());
 	    	}
     	}
     	return temp;
 	}
    /**
     * This function removes the the lowest grade when there is more than 5 optional quiz grades then returns that value to calculateAverageOptionalQuizGrade() 
     * @param optQuizGradeTextfields used as a parameter in the createGradesArray to create the array used for the calculation
     * @return returns the sum of all the remaining quiz grades added together and divided by 5.
     */
	private double gradeQuizSum(ArrayList<TextField> optQuizGradeTextfields) {
		
		double sum =0.0;    	
		int i=0;
		int size = 0;
    	Double[] gradesArray = createGradesArray(optQuizGradeTextfields);   	    	
	 	
	    		
    	Arrays.sort(gradesArray, Collections.reverseOrder());
    	System.out.println("the length is: "+gradesArray.length);
    	if(gradesArray.length==6) {
    		size=gradesArray.length-1;
    	}
    	if(gradesArray.length==7) {
    		size=(gradesArray.length)-2;
    	}
    	System.out.println("the size is: "+size);
    	for (i=0;i<size;i++) {
    		sum = sum + gradesArray[i]; 
		
			}
    	optionalAvg.setText(String.format("Quiz Average: %.2f", sum/5.0));
    	sum = (sum/5.0);
    	System.out.println("the sum is:"+sum);
		return sum;
	}
    
    
    double averageQuizGrade = 0.0;
    
    /**
     * This fuction calculates the average of all the entered Quiz Grades
     * @param mainScene - This is the main window of the program
     * @param quizGradeTextfields - This is another window where the user will enter the Required quiz grades to calculate the quiz score
     */
    void calculateAverageQuizGrade(Scene mainScene, ArrayList<TextField> quizGradeTextfields) {
    	applicationStage.setScene(mainScene);
    	
    	averageQuizGrade = 0.0;
    	for (TextField quizGradeTextfield : quizGradeTextfields) {
        	averageQuizGrade += Double.parseDouble(quizGradeTextfield.getText());
    	}
    	averageQuizGrade = averageQuizGrade / quizGradeTextfields.size();
    }
    
    /**
     * This function takes the user to the scene where the grades are input
     * @param enterQuizGradeEvent - Is the action of the button for entering the grades is clicked; it takes the 
     * user to the page with the input text fields, which is equal to the entered no. in the choicebox
     */
    @FXML
    void getQuizGrades(ActionEvent enterQuizGradeEvent) {
    	Scene mainScene = applicationStage.getScene();
    	
    	int numberOfQuizzes = quizzesCompletedChoiceBox.getValue();
    	int rowsCreated = 0;
    	VBox quizGradeContainer = new VBox();
    	quizGradeContainer.getChildren().add(quizErrorLabel);
    	
    	ArrayList<TextField> quizGradeTextfields = new ArrayList<TextField>();
    	while (rowsCreated < numberOfQuizzes) {
    		
    		HBox rowContainer = new HBox();
        	Label quizGradeLabel = new Label("Quiz grade");
        	TextField quizGradeTextfield = new TextField("0.0");
        	Label quizGraderangeLabel = new Label("Enter Grade from 0-10");
	    	quizGradeTextfields.add(quizGradeTextfield);
        	
        	
        	rowContainer.getChildren().addAll(quizGradeLabel, quizGradeTextfield, quizGraderangeLabel);
        	rowsCreated+=1;
        	
        	quizGradeContainer.getChildren().add(rowContainer);
    		
    	}
    	
    	Button doneButton = new Button("Done");
    	doneButton.setOnAction(doneEvent -> calculateAverageQuizGrade(mainScene, quizGradeTextfields));
    	quizGradeContainer.getChildren().add(doneButton);
    	
    	Scene quizGradesScene = new Scene(quizGradeContainer);
    	applicationStage.setScene(quizGradesScene);
    	
    }
    
    /**
     * This is the main calculating functions which calculates all the different values and equates them together
     * @param event - This event is called when the calculate button is hit; it assigns all the different distribution to 
     * all the grade values and calculates the final grade.
     */
    @FXML
    void calculateGrade(ActionEvent event) {
   
    	projectErrorLabel.setText("");
    	
    	Grade projectGrade = new Grade(0.0,100,.5);
    	projectErrorLabel.setText(projectGrade.setValue(projectGradeTextfield.getText()));
   	
    	Grade requiredQuizGrade = new Grade(CompQuizAvg,10,.1875);
    	
    	Grade optionalQuizGrade = new Grade(OptQuizAvg,10,.0625);
    	
    	Grade requiredCCGrade = new Grade(ccChoiceBox.getValue(),15,.1875);
    	
    	Grade optionalCCGrade = new Grade(occChoiceBox.getValue(),5,.0625);
   	
    	double courseGrade= projectGrade.getWeightedPercentageGrade()+
    			requiredQuizGrade.getWeightedPercentageGrade()+
    			optionalQuizGrade.getWeightedPercentageGrade()+
    			requiredCCGrade.getWeightedPercentageGrade()+
    			optionalCCGrade.getWeightedPercentageGrade();

    	courseGradeLabel.setText(String.format("Your course grade is: %.2f", courseGrade));
    	
    	//System.out.println("Button was clicked");

    }

}
