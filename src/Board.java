import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class Board extends JFrame{
	public static final String FILE = "lightsout.in";
	public static int[][] gameBoardInt = new int[5][5];
	public static JButton[][] board = new JButton[5][5];
	public static JFrame win = new JFrame("Winner");
	public static int i;
	public static int j;
	Container container = getContentPane();
    
	public Board() {
		super("Lightsout");
		this.setPreferredSize(new Dimension(800, 600));
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(5,5));
		readFile();
		addButtons();
		this.pack();
		this.setVisible(true);
	}

	public void readFile() {
		//fileread for board
		try {
			FileReader fileReader = new FileReader(FILE); 
			BufferedReader in = new BufferedReader(fileReader);

			for (int i=0; i<5; i++) {
        		String row = in.readLine();
        		String[] perRow = row.split(" ");
        		
        		for (int t=0; t<5; t++) {
        			gameBoardInt[i][t] = Integer.parseInt(perRow[t]);
        			System.out.print(gameBoardInt[i][t] + " ");
        		}

        		System.out.print("\n");
    		}
    		in.close();
		} catch (Exception e) {
			System.out.println("An error occured in reading this file.");
		}
	}

	public void addButtons() {
		for (i=0; i<5; i++) {
			for (j=0; j<5; j++) {
				board[i][j] = new JButton("");

				if (gameBoardInt[i][j] == 0) {
					board[i][j].setBackground(Color.black);
				} else {
					board[i][j].setBackground(Color.white);
				}

    			final Integer x = i;
    			final Integer y = j;


				board[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Object obj = e.getSource();

						if (gameBoardInt[x][y] == 0) {
							gameBoardInt[x][y] = 1;
						} else {
							gameBoardInt[x][y] = 0;
						}

						int up 		= (x-1);   // -1, 0
						int down 	= (x+1);   //  1, 0
						int left 	= (y-1);   //  0,-1
						int right 	= (y+1);   //  0, 1

						if (up >= 0) {
							if (gameBoardInt[up][y] == 1) {
								gameBoardInt[up][y] = 0;
								board[up][y].setBackground(Color.black);
							} else {
								gameBoardInt[up][y] = 1;
								board[up][y].setBackground(Color.white);
							}
						}

						if (down < 5) {
							if (gameBoardInt[down][y] == 1) {
								gameBoardInt[down][y] = 0;
								board[down][y].setBackground(Color.black);
							} else {
								gameBoardInt[down][y] = 1;
								board[down][y].setBackground(Color.white);
							}
						}

						if (left >= 0) {
							if (gameBoardInt[x][left] == 1) {
								gameBoardInt[x][left] = 0;
								board[x][left].setBackground(Color.black);
							} else {
								gameBoardInt[x][left] = 1;
								board[x][left].setBackground(Color.white);
							}
						}

						if (right < 5) {
							if (gameBoardInt[x][right] == 1) {
								gameBoardInt[x][right] = 0;
								board[x][right].setBackground(Color.black);
							} else {
								gameBoardInt[x][right] = 1;
								board[x][right].setBackground(Color.white);
							}
						}

	        			if (obj instanceof Component) {
	           				if (((Component)obj).getBackground() == Color.black) {
	                			((Component)obj).setBackground(Color.white);
	           				} else {
	                			((Component)obj).setBackground(Color.black);
	                		}
	                	}
	                }
				});

				container.add(board[i][j]);
			}
		}
	}

	public static void checkWinner() {
		int checker = 0;

		for (int i=0; i<5; i++) {
			for (int j=0; j<5; j++) {
				if (gameBoardInt[i][j] == 1) {
					checker = 1;
				}
			}
		}

		if (checker == 0) {
			System.out.println("You Won!");
			JOptionPane.showMessageDialog(win, "Yay!");
		}
	}
}