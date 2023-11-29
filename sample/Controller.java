package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    @FXML
    private Button Exit1;

    @FXML
    private Button Refresh;

    @FXML
    private ImageView Game_Over;

    @FXML
    private Button Lose_new_game;


    @FXML
    private Button Win_new_game;

    @FXML
    private ImageView Winner;

    @FXML
    private ImageView head;

    @FXML
    private ImageView left_hand;

    @FXML
    private ImageView left_leg;

    @FXML
    private ImageView pants;

    @FXML
    private ImageView right_hand;

    @FXML
    private ImageView right_leg;

    @FXML
    private ImageView rope;

    @FXML
    private ImageView shirt;

    @FXML
    private ImageView pillar;

    @FXML
    private Text text;
    @FXML
    private Pane buttons;

    @FXML
    private Text realWord;

    private int mistakes;
    private int correct;
    private Words word = new Words();
    private String myWord;
    private List<String> myLetters;
    private List<String> answer;

    public Controller() throws FileNotFoundException {
    }

    public void initialize() {
        Exit1.setVisible(false);
        rope.setVisible(false);
        head.setVisible(false);
        shirt.setVisible(false);
        pants.setVisible(false);
        left_hand.setVisible(false);
        right_hand.setVisible(false);
        left_leg.setVisible(false);
        right_leg.setVisible(false);
        Winner.setVisible(false);
        Win_new_game.setVisible(false);
        Game_Over.setVisible(false);
        Lose_new_game.setVisible(false);
        pillar.setVisible(true);
        Refresh.setVisible(true);
        mistakes = 0;
        correct = 0;
        myWord = word.getRandomWord();
        myLetters = Arrays.asList(myWord.split(""));
        answer = new ArrayList<>(myLetters.size() * 2);

        for (int i = 0; i < myLetters.size() * 2; i++) {
            if (i % 2 == 0) {
                answer.add("_");
            } else {
                answer.add(" ");
            }
        }

        String res = String.join("", answer);
        text.setText(res);
        //winStatus.setText("");
        realWord.setText("");
        buttons.setDisable(false);
    }


    public void onClick(ActionEvent event) {
        String letter = ((Button) event.getSource()).getText();
        ((Button) event.getSource()).setDisable(true);

        if (myLetters.contains(letter)) {
            correct++;

            for (int i = 0; i < myLetters.size(); i++) {
                if (myLetters.get(i).equals(letter)) {
                    answer.set(i * 2, letter);
                }
            }

            String res = String.join("", answer);
            text.setText(res);

            if (answer.stream().noneMatch(s -> s.equals("_"))) {
                Winner.setVisible(true);
                Win_new_game.setVisible(true);
                buttons.setDisable(true);
                rope.setVisible(false);
                head.setVisible(false);
                shirt.setVisible(false);
                pants.setVisible(false);
                left_hand.setVisible(false);
                right_hand.setVisible(false);
                left_leg.setVisible(false);
                right_leg.setVisible(false);
                pillar.setVisible(false);
                Exit1.setVisible(true);
            }
        }else{
            mistakes++;
            if(mistakes == 1) {
                rope.setVisible(true);
            }
            else if (mistakes == 2) head.setVisible(true);
            else if (mistakes == 3) shirt.setVisible(true);
            else if (mistakes == 4) pants.setVisible(true);
            else if (mistakes == 5) left_hand.setVisible(true);
            else if (mistakes == 6) right_hand.setVisible(true);
            else if (mistakes == 7) left_leg.setVisible(true);
            else if (mistakes == 8)
            {
                right_leg.setVisible(true);
                rope.setVisible(false);
                head.setVisible(false);
                shirt.setVisible(false);
                pants.setVisible(false);
                left_hand.setVisible(false);
                right_hand.setVisible(false);
                left_leg.setVisible(false);
                right_leg.setVisible(false);
                pillar.setVisible(false);
                Game_Over.setVisible(true);
                Lose_new_game.setVisible(true);
                Exit1.setVisible(true);
                realWord.setText("The actual word was " + myWord);
                buttons.setDisable(true);
            }
        }
    }

    public void newGame(){
        for(int i=0; i<26; i++){
            buttons.getChildren().get(i).setDisable(false);
        }
        initialize();
    }

    public void Exit() {
        System.exit(0);
    }
}