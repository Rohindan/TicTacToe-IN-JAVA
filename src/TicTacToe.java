import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe{

    int boardwidth = 600;
    int boardheight = 650;

    JFrame frame = new JFrame();
    JLabel textlabel = new JLabel();
    JPanel textpanel = new JPanel();
    JPanel boardpanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns =0;


    TicTacToe(){
        frame.setVisible(true);
        frame.setTitle("Tic-Tac-Toe");
        frame.setSize(boardwidth,boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textlabel.setBackground(Color.darkGray);
        textlabel.setForeground(Color.WHITE);
        textlabel.setFont(new Font("Arial",Font.BOLD,50));
        textlabel.setText("Tic-Tac-Toe");
        textlabel.setOpaque(true);
        textlabel.setHorizontalAlignment(JLabel.CENTER);

        textpanel.setLayout(new BorderLayout());
        textpanel.add(textlabel);
        frame.add(textpanel,BorderLayout.NORTH);

        boardpanel.setLayout(new GridLayout(3,3));
        boardpanel.setBackground(Color.darkGray);
        frame.add(boardpanel);

        for(int r=0 ; r<3 ; r++){
            for(int c = 0; c<3; c++){
                JButton tile = new JButton();
                board[r][c] = tile;
                boardpanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.WHITE);
                tile.setFont(new Font("Arial",Font.BOLD,120));
                tile.setFocusable(false);
//                tile.setText(playerX);

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if(gameOver) return;

                        JButton tile = (JButton) e.getSource();

                        if(tile.getText() == ""){
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();

                            if(!gameOver){
                                currentPlayer = (currentPlayer == playerX)?playerO : playerX;
                                textlabel.setText(currentPlayer + " s turn.");
                            }



                        }
                    }
                });
            }
        }


    }

    void checkWinner(){

        //Horizontal
        for(int r = 0;r<3;r++){
            if(board[r][0].getText() == "") continue;

            if(board[r][0].getText() == board[r][1].getText() &&
               board[r][1].getText() == board[r][2].getText()){
                for(int i = 0;i<3;i++){
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }

        //Vertical
        for(int c = 0;c<3;c++){
            if(board[0][c].getText() == "") continue;

            if (board[0][c].getText() == board[1][c].getText() &&
                    board[1][c].getText() == board[2][c].getText()){
                for(int i = 0;i<3;i++){
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }

        //Diagonal

        if(board[0][0].getText() == board[1][1].getText() &&
                board[1][1].getText() == board[2][2].getText() && board[0][0].getText() != ""){
            for(int i = 0;i<3;i++){
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
            }

        //Anti- Diagonal
        if(board[0][2].getText() == board[1][1].getText() &&
                board[1][1].getText() == board[2][0].getText() && board[0][2].getText() != ""){
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }

        if(turns == 9){
            for (int r = 0;r<3;r++){
                for(int c =0;c<3;c++){
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
        }



    }



    void setWinner(JButton tile){
        tile.setForeground(Color.green);
        tile.setBackground(Color.GRAY);
        textlabel.setText(currentPlayer + " is Winner! ");

    }

    void setTie(JButton tile){
        tile.setForeground(Color.ORANGE);
        tile.setBackground(Color.gray);
        textlabel.setText("Tie!");
    }
}