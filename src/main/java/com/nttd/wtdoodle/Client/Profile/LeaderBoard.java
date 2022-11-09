package com.nttd.wtdoodle.Client.Profile;

import com.nttd.wtdoodle.Client.Game.Player.Player;
import com.nttd.wtdoodle.Client.Models.GameHistoryData;
import com.nttd.wtdoodle.Client.Models.LeaderBoardData;
import com.nttd.wtdoodle.Client.Models.LeaderBoardModel;
import com.nttd.wtdoodle.ResourceLocator;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LeaderBoard implements Initializable {

    public GridPane gridPane;
    LeaderBoardModel leaderBoardModel;

    public void goToDashboard(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(ResourceLocator.class.getResource("Dashboard.fxml"));
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 950, 570);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        leaderBoardModel = LeaderBoardModel.getInstance();
        /*
        Get leaderboard data here;
         */

        gridPane.addRow(0,createLabel("Rank"),createLabel("Username"),createLabel("Score"),createLabel("Date"));
      /*
      loop through the leaderboard and add rows
       */

        ArrayList<LeaderBoardData> lData = leaderBoardModel.getLeaderBoardData();
        int count = 1;
        for(LeaderBoardData l : lData) {
            gridPane.addRow(count,createLabel(count+""),createLabel(l.getUserName()+""),createLabel(l.getTotalScore() +""),createLabel(l.getDate().toString()));
            count++;
        }
    }
    private Label createLabel(String s){
        Label label=new Label(s);
        label.setFont(Font.font("Verdana",15));
        return label;
    }
}
