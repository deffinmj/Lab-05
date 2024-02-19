import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    JPanel mainPnl;
    JPanel titlePnl;  // Top
    JPanel statsPnl;
    JPanel resultsPnl; // Center
    JPanel controlPnl; // Bottom

    JTextField playerWinsTF;
    JTextField computerWinsTF;
    JTextField tiesTF;

    JTextArea resultsTA;
    JScrollPane scroller;

    JLabel titleLbl;
    ImageIcon icon;

    JButton rockBtn;
    JButton paperBtn;
    JButton scissorsBtn;
    JButton quitBtn;

    int playerWins = 0;
    int computerWins = 0;
    int ties = 0;

    public enum Selection {
        Rock,
        Paper,
        Scissors
    }

    public RockPaperScissorsFrame()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createTitlePanel();
        createStatsPanel();
        createResultsPanel();
        JPanel upperPnl = new JPanel();
        upperPnl.add(statsPnl, BorderLayout.CENTER);
        upperPnl.add(resultsPnl, BorderLayout.SOUTH);

        mainPnl.add(titlePnl,BorderLayout.NORTH);
        mainPnl.add(upperPnl, BorderLayout.CENTER);

        createControlPanel();
        mainPnl.add(controlPnl, BorderLayout.SOUTH);

        add(mainPnl);
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createTitlePanel()
    {
        titlePnl = new JPanel();;
        titleLbl = new JLabel("Rock Paper Scissors Game", icon, JLabel.CENTER);
        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);
        titlePnl.add(titleLbl);
    }

    private void createResultsPanel()
    {
        resultsPnl = new JPanel();
        resultsTA = new JTextArea(10, 25);
        resultsTA.setEditable(false);
        scroller = new JScrollPane(resultsTA);
        resultsPnl.add(scroller);
    }

    private void createStatsPanel()
    {
        statsPnl = new JPanel();
        statsPnl.setLayout(new GridLayout(3, 2));
        playerWinsTF = new JTextField();
        playerWinsTF.setEditable(false);
        computerWinsTF = new JTextField();
        computerWinsTF.setEditable(false);
        tiesTF = new JTextField();
        tiesTF.setEditable(false);
        statsPnl.add(new JLabel("Player Wins: "));
        statsPnl.add(playerWinsTF);
        statsPnl.add(new JLabel("Computer Wins: "));
        statsPnl.add(computerWinsTF);
        statsPnl.add(new JLabel("Ties: "));
        statsPnl.add(tiesTF);
    }


    private void createControlPanel()
    {
        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(1, 4));

        rockBtn = new JButton("Rock", new ImageIcon(new ImageIcon("rock.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        rockBtn.addActionListener((ActionEvent ae) ->
        {
            CompleteGame(Selection.Rock);
        });

        paperBtn = new JButton("Paper", new ImageIcon(new ImageIcon("paper.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        paperBtn.addActionListener((ActionEvent ae) ->
        {
            CompleteGame(Selection.Paper);
        });

        scissorsBtn = new JButton("Scissors", new ImageIcon(new ImageIcon("scissors.jpg").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        scissorsBtn.addActionListener((ActionEvent ae) ->
        {
            CompleteGame(Selection.Scissors);
        });

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        controlPnl.add(rockBtn);
        controlPnl.add(paperBtn);
        controlPnl.add(scissorsBtn);
        controlPnl.add(quitBtn);

    }

    void DisplayWinMessage(Selection winningSelection, Boolean humanWins) {
        String winString = humanWins ? "Human Wins: " : "Computer Wins: ";
        if(winningSelection == Selection.Rock) {
            winString += "Rock breaks scissors!\n";
        } else if(winningSelection == Selection.Paper) {
            winString += "Paper covers rock!\n";
        } else {
            winString += "Scissors cuts paper!\n";
        }
        resultsTA.append(winString);
    }

    void CompleteGame(Selection selection) {
        Selection computerSelection = Selection.values()[new Random().nextInt(3)];
        if (selection == computerSelection) {
            resultsTA.append("Result is a tie!\n");
            ++ties;
            tiesTF.setText(Integer.toString(ties));
        } else if ((selection == Selection.Rock && computerSelection == Selection.Scissors) ||
                (selection == Selection.Paper && computerSelection == Selection.Rock) ||
                (selection == Selection.Scissors && computerSelection == Selection.Paper)) {
            DisplayWinMessage(selection, true);
            ++playerWins;
            playerWinsTF.setText(Integer.toString(playerWins));
        } else {
            DisplayWinMessage(computerSelection, false);
            ++computerWins;
            computerWinsTF.setText(Integer.toString(computerWins));
        }
    }

}