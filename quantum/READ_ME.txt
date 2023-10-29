In order to play the game(s) you must open the Poker2.0.jar file. When opened this will present you
with a window showing 3-5 buttons which will be explained below. In order to run the maven project
through the terminal make sure you have maven and javafx navigate to folder quantum(where pom.xml is) and use the command:
mvn -q clean javafx:run

1. Standard Poker
    This button will let you set up an play a digital game of Texas Hold'em Unlimited poker. It
    takes you to a screen to pick the amount of players and their starting balance. After picking
    these you are able to give the players names and start playing. At the end of a full round,
    for example when everyone's bets are equal after the river is shown, the game automatically 
    calculates who has the best combination and therefore wins.
2. Rules
    These are the rules explaining how to play standard poker. This includes good 
    card combinations, betting actions, etc.
3. Quantum Poker
    This button lets you set up and play quantum poker. Much like normal poker it is played with 
    2-6 people, the balance and the names are chosen and the computer calculates who won. The 
    calculating of the score is done by a simulation through the Strange API, the exact details
    explained when clicked on the next button;
4. Rules
    This rules button takes you to the rules for quantum poker. In here the important knowledge
    of gates and qubits is explained to make the game playable to most people.
5. Quit 
    ...quits the program. 

Why quantum poker?
    While initially intrested in quantum computing for its true randomness and therefore choosing
    poker, we decided simply using its randomness would be too simple. Instead we decided to 
    study the ways of quantum computing to still create quantum computing as we had hoped. While
    indulging in the laws of quantum computing we created a standard game of poker, to which we 
    later applied the laws to created a new version of poker.

Quantum poker is able to be played on Windows because Strange is only supported on windows.
The makers of the project strongly discourage gambling. The game is purely for educational purposes.