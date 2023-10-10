import java.awt.*;
import javax.swing.*;

class BackgroundPanel extends JPanel {
        final ImageIcon icon = new ImageIcon("mistzombie.jpg");
        Image img = icon.getImage();
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 0, 0, null);
        }
}

public class Main {

    void testGame(){

        int rounds = 0;
        //TODO are we using infinite bets or a set one? we should change currentBets starting value accordingly
        int currentBet;
        int pool;
        int checkIndex;
        //TODO: have to Implement from infoscreen.
        int amountOfPlayers = 4; 
        int balance;
        //Array of players in order to call each one.
        Player[] Players = new Player[amountOfPlayers];
        
        for (int i = 0; i < amountOfPlayers; i++){
            //loop through each person and have them type in their name on the info screen
            Player[i] = new Player(scanner.nextString(), balance);
        }
       
        
        //TODO: at this point there should be checked if everyone still has money, then a new rounds starts:
        Cards deckOfCards = new Cards();
        deckOfCards.shuffle();

    //Deciding who is the dealer, who is the small/big blind
        //Ensuring the round isnt more than the players
        int index = rounds%amountOfPlayers;
        int b = 0;
        //Setting the players type until it reaches the last player in the array or the last type.
        while((index + b <= amountOfPlayers) && (b < 3)){
        P[index + i].setType(1 + b);
        b++;
        }
        //If the last player of the array was reached but not all the types were assigned it loops
        //back and assigns the remaining types from the front of the array.
        
        int j = 0;
        while(b < 3){
            P[j].setType(1 + i);
            j++;
        }
        
        int startingIndex = 0; //the index of the small blind so the game knows where to start
        while(startingIndex<amountOfPlayers){
            if(P[k].getType() == 2){
                startingIndex = k;
            }
        }

        //TODO implement this actually and the right way(not with scanner and changing somehting
        // to point to the button press)
        //TODO implement when it goes back to the beginning c = startingIndex - 1. and then after c = amountOfPlayers - 1, c = 0;
        
        Players[startingIndex].action(scanner.nextInt()); //small blind
        currentBet = scanner.nextInt();
        Players[startingIndex + 1].action(currentBet); //big blind
        for (int c = startingIndex + 2; c<amountOfPlayers; c++){
            if  (something.equals("call")){
            Players[c].action(currentBet);
            pool += currentBet;
            }   else if (something.equals("raise")){
                currentBet = scanner.nextInt();
                Players[c].action(currentBet);
                pool += currentBet;
            }   else if (something.equals("fold")){
                Players[c].folded = true;
            }
        }
        currentBet = 0; //in order to allow checks
        if (P.length == 1){
            P[0].balance += pool;
            pool = 0;
        }
        else{   //TODO: GUI show flop
            for (int c = startingIndex ; c<amountOfPlayers; c++){
                if (currentBet == 0 && something.equals("check")){//shouldnt have to have a && here and the button should dissapear if currentBet!=0
                    checkIndex = c;
                }   else if  (something.equals("call")){
                    Players[c].action(currentBet);
                    pool += currentBet;
                }   else if (something.equals("raise")){
                        currentBet = scanner.nextInt();
                        Players[c].action(currentBet);
                        pool += currentBet;
                }   else if (something.equals("fold")){
                        Players[c].folded = true;
                }
            
        }


        
    }
}


    void demo() {
        
        JFrame frame = new JFrame("Display an image in the background");
        BackgroundPanel panel = new BackgroundPanel();
        frame.add(panel);




        frame.setSize(800, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Main().demo();
    }
}